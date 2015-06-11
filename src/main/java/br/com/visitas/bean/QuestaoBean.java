package br.com.visitas.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.visitas.DAO.DAO;
import br.com.visitas.filter.LazyData;
import br.com.visitas.filter.LazyList;
import br.com.visitas.modelo.questionario.Questao;
import br.com.visitas.modelo.questionario.TipoQuestao;

@Named 
@ManagedBean @ViewScoped
public class QuestaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Questao> dao;
	@Inject DAO<TipoQuestao> daoTipo;

	@Inject
	private Questao questao;

	private Questao filtroQuestao = new Questao();
	private LazyData<Questao> model;
	private LazyList<Questao> imoveis;
	private Map<String, Object> filtrosAdicionais = new HashMap<String, Object>();
	
	public QuestaoBean() {
		this(null, null);
	}

	@Inject
	public QuestaoBean(DAO<Questao> dao, LazyList<Questao> imoveis) {
		this.dao = dao;
		this.imoveis = imoveis;
		
		atualizaTabela();
	}
	
	public void atualizaTabela(){
		//Defino o status do tipo como nulo para as questoes ñ serem filtradas por tipos de questao ativos
		filtroQuestao.getTipo().setStatus(null);
		filtrosAdicionais.put("tipo", filtroQuestao.getTipo());
		
		model = new LazyData<Questao>(dao, imoveis, filtroQuestao, filtrosAdicionais);
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

	public void onRowSelect(SelectEvent event) {
		this.questao = (Questao) event.getObject();
	}
	
	public Questao getFiltroQuestao() {
		return filtroQuestao;
	}
	
	public void setFiltroQuestao(Questao filtroQuestao) {
		this.filtroQuestao = filtroQuestao;
	}
	
	public void aplicaFiltroOneMenu(AjaxBehaviorEvent event){
		atualizaTabela();
	}
}
