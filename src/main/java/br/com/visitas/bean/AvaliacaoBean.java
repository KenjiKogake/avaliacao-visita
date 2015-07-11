package br.com.visitas.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.visitas.DAO.DAO;
import br.com.visitas.filter.LazyData;
import br.com.visitas.filter.LazyList;
import br.com.visitas.modelo.questionario.Avaliacao;
import br.com.visitas.modelo.questionario.Nota;
import br.com.visitas.modelo.questionario.Questao;
import br.com.visitas.modelo.questionario.QuestoesDaAvaliacao;

@Named 
@ViewScoped
public class AvaliacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Avaliacao> dao;
	
	@Inject
	private DAO<Nota> daoNota;
	@Inject
	private DAO<Questao> daoQuestao;
	@Inject
	private DAO<QuestoesDaAvaliacao> daoQuestoesDaAvaliacao;

	private List<Nota> notas;

	@Inject
	private Avaliacao avaliacao;

	private Avaliacao filtroAvaliacao = new Avaliacao();
	private LazyData<Avaliacao> model;
	private LazyList<Avaliacao> avaliacoes;
	private Map<String, Object> filtrosAdicionais = new HashMap<String, Object>();
	
	public AvaliacaoBean() {
		this(null, null);
	}

	@Inject
	public AvaliacaoBean(DAO<Avaliacao> dao, LazyList<Avaliacao> avaliacoes) {
		this.dao = dao;
		this.avaliacoes = avaliacoes;
		
		atualizaTabela();
	}
	
	public void atualizaTabela(){
		
		model = new LazyData<Avaliacao>(dao, avaliacoes, filtroAvaliacao, filtrosAdicionais);
	}
	
	public void salvar() {
		try {
			dao.atualiza(avaliacao);
			daoQuestoesDaAvaliacao.atualizaListaDeObjetos(avaliacao.getQuestoes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Questao> getQuestoesAtivas(){
		return daoQuestao.listaNamedQuery("questoesAtivas");
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public LazyData<Avaliacao> getModel() {
		return model;
	}

	public void onRowSelect(SelectEvent event) {
		this.avaliacao = (Avaliacao) event.getObject();
	}
	
	public Avaliacao getFiltroAvaliacao() {
		return filtroAvaliacao;
	}
	
	public void setFiltroAvaliacao(Avaliacao filtroAvaliacao) {
		this.filtroAvaliacao = filtroAvaliacao;
	}
	
	public void criaNovaAvaliacao(){
		this.avaliacao = new Avaliacao();
	}
	
	public boolean isNovo(){
		return this.avaliacao.getId() != null;
	}
	
	public void iniciarQuestionario(){
		try {
		
			List<Questao> questoes = getQuestoesAtivas();
			for (Questao questao : questoes) {
				avaliacao.adicionaQuestao(questao);
			}
			
			dao.adiciona(avaliacao);
			daoQuestoesDaAvaliacao.adicionaListaDeObjetos(avaliacao.getQuestoes());
			
			notas = daoNota.listaTodos();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Nota> getNotas() {
		return notas;
	}
}
