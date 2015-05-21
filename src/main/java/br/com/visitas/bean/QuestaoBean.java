package br.com.visitas.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

	private LazyData<Questao> model;

	@Inject
	private Questao questao;

	public QuestaoBean() {
		this(null, null, null);
	}

	@Inject
	public QuestaoBean(DAO<Questao> dao, LazyList<Questao> imoveis, FilterTable filtro) {
		this.dao = dao;
		
		model = new LazyData<Questao>(dao, filtroQuestao, imoveis, filtro);
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
			dao.adiciona(questao);
			questao = new Questao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Questao getFiltroQuestao() {
		return filtroQuestao;
	}
}
