package br.com.visitas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.visitas.DAO.DAO;
import br.com.visitas.filter.FilterTable;
import br.com.visitas.filter.LazyData;
import br.com.visitas.filter.LazyList;
import br.com.visitas.modelo.questionario.Questao;
import br.com.visitas.modelo.questionario.TipoQuestao;

@Named
@RequestScoped
public class QuestaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Questao> dao;
	@Inject
	DAO<TipoQuestao> daoTipo;

	private Questao filtroQuestao = new Questao();
	private TipoQuestao filtroTipoQuestao = new TipoQuestao();

	private LazyData<Questao> model;

	@Inject
	private Questao questao;

	public QuestaoBean() {
		this(null, null, null);
	}

	@Inject
	public QuestaoBean(DAO<Questao> dao, LazyList<Questao> imoveis, FilterTable filtro) {
		this.dao = dao;
		List<Object> listaFiltro = new ArrayList<Object>();
		
		listaFiltro.add(filtroQuestao);
		if(filtroTipoQuestao.getId() != null) listaFiltro.add(filtroTipoQuestao);
		
		model = new LazyData<Questao>(dao, listaFiltro, imoveis, filtro);
	}

	public List<TipoQuestao> getTiposQuestao() {
		return daoTipo.listaTodos();
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public LazyData<Questao> getModel() {
		return model;
	}

	public void salvar() {
		try {
			if(questao.getId() == null){
				dao.adiciona(questao);
			}else{
				dao.atualiza(questao);
			}
			questao = new Questao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {
		this.questao = (Questao) event.getObject();
	}
	
	public Questao getFiltroQuestao() {
		return filtroQuestao;
	}
	
	public TipoQuestao getFiltroTipoQuestao() {
		return filtroTipoQuestao;
	}
	
	public void setFiltroQuestao(Questao filtroQuestao) {
		System.out.println("Entrou no setFiltroQuestao");
		this.filtroQuestao = filtroQuestao;
	}
}
