package br.com.visitas.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import br.com.visitas.DAO.DAO;
import br.com.visitas.filter.LazyData;
import br.com.visitas.filter.LazyList;
import br.com.visitas.modelo.questionario.Questao;
import br.com.visitas.modelo.questionario.TipoQuestao;

@Named
@ViewScoped
public class TipoQuestaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<TipoQuestao> dao;
	
	private LazyData<TipoQuestao> model;
	
	private LazyData<Questao> modelQuestao;
	
	@Inject private TipoQuestao tipoQuestao;
	
	private TipoQuestao filtroTipoQuestao = new TipoQuestao();

	public TipoQuestaoBean() {
		this(null, null);
	}

	@Inject
	public TipoQuestaoBean(DAO<TipoQuestao> dao, LazyList<TipoQuestao> tiposQuestao) {
		this.dao = dao;
		
		model = new LazyData<TipoQuestao>(dao, tiposQuestao, filtroTipoQuestao, null);
	}
	
	@Inject
	public void buscaListaQuestoes(DAO<Questao> dao, LazyList<Questao> questoes){
		Map<String, Object> filtrosAdicionais = new HashMap<String, Object>();
		
		System.out.println(this.getTipoQuestao());
		System.out.println(this.getTipoQuestao().getTipo());
		filtrosAdicionais.put("tipo", this.getTipoQuestao());
		
		modelQuestao = new LazyData<Questao>(dao, questoes, new Questao(), filtrosAdicionais);
	}

	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}
	
	public LazyData<TipoQuestao> getModel() {
		return model;
	}
	
	public LazyData<Questao> getModelQuestao() {
		return modelQuestao;
	}
	
	public TipoQuestao getFiltroTipoQuestao() {
		return filtroTipoQuestao;
	}
	
	public void setFiltroTipoQuestao(TipoQuestao filtroTipoQuestao) {
		this.filtroTipoQuestao = filtroTipoQuestao;
	}

	public void salvar() {
		try {
			if(tipoQuestao.getId() == null)
				dao.adiciona(tipoQuestao);
			else 
				dao.atualiza(tipoQuestao);
			
			tipoQuestao = new TipoQuestao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event){
		tipoQuestao = (TipoQuestao) event.getObject();
	}
	
	public boolean isNovo(){
		return this.tipoQuestao.getId() == null;
	}
	
	public void onTabChange(TabChangeEvent event) {
		if(event.getTab().getId() == "tabLista")
			buscaListaQuestoes(null, null);
	}
}
