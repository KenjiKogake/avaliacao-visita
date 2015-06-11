package br.com.visitas.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import br.com.visitas.DAO.DAO;
import br.com.visitas.ENUM.Status;
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
	private LazyList<TipoQuestao> tiposQuestao;

	private TipoQuestao filtroTipoQuestao = new TipoQuestao();
	
	@Inject private TipoQuestao tipoQuestao;
	
	@Inject private LazyList<Questao> questoes;
	private LazyData<Questao> modelQuestao;
	@Inject private Questao filtroQuestao;

	public TipoQuestaoBean() {
		this(null, null);
	}

	@Inject
	public TipoQuestaoBean(DAO<TipoQuestao> dao, LazyList<TipoQuestao> tiposQuestao) {
		this.dao = dao;
		this.tiposQuestao = tiposQuestao;
		
		atualizaTabela();
	}

	public void atualizaTabela(){
		model = new LazyData<TipoQuestao>(dao, tiposQuestao, filtroTipoQuestao, null);
	}
	
	@Inject
	public void buscaListaQuestoes(DAO<Questao> dao){
		Map<String, Object> filtrosAdicionais = new HashMap<String, Object>();
		
		filtrosAdicionais.put("tipo", this.getTipoQuestao());
		
		modelQuestao = new LazyData<Questao>(dao, questoes, filtroQuestao, filtrosAdicionais);
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
	
	public Questao getFiltroQuestao() {
		return filtroQuestao;
	}

	public void setFiltroQuestao(Questao filtroQuestao) {
		this.filtroQuestao = filtroQuestao;
	}
	
	public void salvar() {
		try {
			if(tipoQuestao.getId() == null)
				dao.adiciona(tipoQuestao);
			else 
				dao.atualiza(tipoQuestao);
			
			novoTipoQuestao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alterarStatus(){
		try {
			if(tipoQuestao.getStatus() == Status.Ativo){
				dao.queryById("alteraStatusTipoQuestao", tipoQuestao.getId(), Status.Inativo);
				dao.queryById("desativaPorTipoQuestao", tipoQuestao.getId(), Status.Inativo);
			}else{
				dao.queryById("alteraStatusTipoQuestao", tipoQuestao.getId(), Status.Ativo);
			}
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
	
	public boolean isAtivo(){
		if(!isNovo()) return this.tipoQuestao.getStatus() == Status.Ativo;
		return false;
	}
	
	public void onTabChange(TabChangeEvent event) {
		if(event.getTab().getId().toString().equals("tabLista")){
			buscaListaQuestoes(null);
		}
	}
	
	public void novoTipoQuestao(){
		System.out.println("Novo Tipo Questao");
		
		this.tipoQuestao = new TipoQuestao();
	}
	
	public void aplicaFiltroOneMenu(AjaxBehaviorEvent event){
		atualizaTabela();
	}
	
	public void aplicaFiltroOneButton(AjaxBehaviorEvent event){
		buscaListaQuestoes(null);
	}
}
