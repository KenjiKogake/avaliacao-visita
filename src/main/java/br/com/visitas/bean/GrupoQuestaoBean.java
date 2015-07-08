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
import br.com.visitas.modelo.questionario.GrupoQuestao;
import br.com.visitas.modelo.questionario.Questao;

@Named
@ViewScoped
public class GrupoQuestaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<GrupoQuestao> dao;
	private LazyData<GrupoQuestao> model;
	private LazyList<GrupoQuestao> gruposQuestao;

	private GrupoQuestao filtroGrupoQuestao = new GrupoQuestao();
	
	@Inject private GrupoQuestao grupoQuestao;
	
	@Inject private LazyList<Questao> questoes;
	private LazyData<Questao> modelQuestao;
	@Inject private Questao filtroQuestao;

	public GrupoQuestaoBean() {
		this(null, null);
	}

	@Inject
	public GrupoQuestaoBean(DAO<GrupoQuestao> dao, LazyList<GrupoQuestao> gruposQuestao) {
		this.dao = dao;
		this.gruposQuestao = gruposQuestao;
		
		atualizaTabela();
	}

	public void atualizaTabela(){
		model = new LazyData<GrupoQuestao>(dao, gruposQuestao, filtroGrupoQuestao, null);
	}
	
	@Inject
	public void buscaListaQuestoes(DAO<Questao> dao){
		Map<String, Object> filtrosAdicionais = new HashMap<String, Object>();
		
		filtrosAdicionais.put("tipo", this.getGrupoQuestao());
		
		modelQuestao = new LazyData<Questao>(dao, questoes, filtroQuestao, filtrosAdicionais);
	}

	public GrupoQuestao getGrupoQuestao() {
		return grupoQuestao;
	}

	public void setGrupoQuestao(GrupoQuestao tipoQuestao) {
		this.grupoQuestao = tipoQuestao;
	}
	
	public LazyData<GrupoQuestao> getModel() {
		return model;
	}
	
	public LazyData<Questao> getModelQuestao() {
		return modelQuestao;
	}
	
	public GrupoQuestao getFiltroGrupoQuestao() {
		return filtroGrupoQuestao;
	}
	
	public void setFiltroGrupoQuestao(GrupoQuestao filtroGrupoQuestao) {
		this.filtroGrupoQuestao = filtroGrupoQuestao;
	}
	
	public Questao getFiltroQuestao() {
		return filtroQuestao;
	}

	public void setFiltroQuestao(Questao filtroQuestao) {
		this.filtroQuestao = filtroQuestao;
	}
	
	public void salvar() {
		try {
			if(grupoQuestao.getId() == null)
				dao.adiciona(grupoQuestao);
			else 
				dao.atualiza(grupoQuestao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alterarStatus(){
		try {
			if(grupoQuestao.getStatus() == Status.Ativo){
				dao.queryById("alteraStatusGrupoQuestao", grupoQuestao.getId(), Status.Inativo);
				dao.queryById("desativaPorGrupoQuestao", grupoQuestao.getId(), Status.Inativo);
			}else{
				dao.queryById("alteraStatusGrupoQuestao", grupoQuestao.getId(), Status.Ativo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event){
		grupoQuestao = (GrupoQuestao) event.getObject();
	}
	
	public boolean isNovo(){
		return this.grupoQuestao.getId() == null;
	}
	
	public boolean isAtivo(){
		if(!isNovo()) return this.grupoQuestao.getStatus() == Status.Ativo;
		return false;
	}
	
	public void onTabChange(TabChangeEvent event) {
		if(event.getTab().getId().toString().equals("tabLista")){
			buscaListaQuestoes(null);
		}
	}
	
	public void criaNovoGrupoQuestao(){
		this.grupoQuestao = new GrupoQuestao();
	}
	
	public void aplicaFiltroOneMenu(AjaxBehaviorEvent event){
		atualizaTabela();
	}
	
	public void aplicaFiltroOneButton(AjaxBehaviorEvent event){
		buscaListaQuestoes(null);
	}
}
