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
import br.com.visitas.modelo.questionario.GrupoQuestao;
import br.com.visitas.modelo.questionario.Questao;

@Named 
@ManagedBean @ViewScoped
public class QuestaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Questao> dao;
	@Inject DAO<GrupoQuestao> daoGrupo;

	@Inject
	private Questao questao;

	private Questao filtroQuestao = new Questao();
	private LazyData<Questao> model;
	private LazyList<Questao> questoes;
	private Map<String, Object> filtrosAdicionais = new HashMap<String, Object>();
	
	public QuestaoBean() {
		this(null, null);
	}

	@Inject
	public QuestaoBean(DAO<Questao> dao, LazyList<Questao> questoes) {
		this.dao = dao;
		this.questoes = questoes;
		
		atualizaTabela();
	}
	
	public void atualizaTabela(){
		//Defino o status do tipo como nulo para as questoes ñ serem filtradas por tipos de questao ativos
		filtroQuestao.getGrupo().setStatus(null);
		
		filtrosAdicionais.put("grupo", filtroQuestao.getGrupo());
		
		model = new LazyData<Questao>(dao, questoes, filtroQuestao, filtrosAdicionais);
	}
	
	public void salvar() {
		try {
			if(questao.getId() == null){
				dao.adiciona(questao);
			}else{
				dao.atualiza(questao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<GrupoQuestao> getGruposQuestao() {
		return daoGrupo.listaTodos();
	}
	
	public List<GrupoQuestao> getGruposQuestaoAtivos(){
		return daoGrupo.listaNamedQuery("gruposQuestaoAtivos");
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
	
	public void aplicaFiltro(AjaxBehaviorEvent event){
		if(filtroQuestao.getGrupo() == null) filtroQuestao.setGrupo(new GrupoQuestao());  
		atualizaTabela();
	}
	
	public void criaNovaQuestao(){
		this.questao = new Questao();
	}
}
