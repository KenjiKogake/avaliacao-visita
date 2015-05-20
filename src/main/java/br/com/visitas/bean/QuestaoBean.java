package br.com.visitas.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.visitas.DAO.DAO;
import br.com.visitas.filter.FilterTable;
import br.com.visitas.filter.LazyList;
import br.com.visitas.modelo.questionario.Questao;
import br.com.visitas.modelo.questionario.TipoQuestao;

@Named
@RequestScoped
public class QuestaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject private DAO<Questao> dao;
	@Inject DAO<TipoQuestao> daoTipo;
	
	@Inject private FilterTable filtro;
	@Inject private LazyList<Questao> questoes;
	@Inject private Questao filtroQuestao;
	
	private LazyDataModel<Questao> model;
	
	@Inject private Questao questao;
	
	public QuestaoBean(){
		model = new LazyDataModel<Questao>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<Questao> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);
				
				setRowCount(questoes.quantidadeFiltrados(filtro, filtroQuestao));
				
				return questoes.filtrados(filtro, filtroQuestao);
			}
			
		};
	}
	
	public List<TipoQuestao> getTiposQuestao(){
		return daoTipo.listaTodos();
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	
	public LazyDataModel<Questao> getModel() {
		return model;
	}
	
	public FilterTable getFiltro() {
		return filtro;
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
	
	public void setFiltro(FilterTable filtro) {
		this.filtro = filtro;
	}
}
