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
import br.com.visitas.filter.Questoes;
import br.com.visitas.filter.QuestoesFilterTable;
import br.com.visitas.modelo.questionario.Questao;
import br.com.visitas.modelo.questionario.TipoQuestao;

@Named
@RequestScoped
public class QuestaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject private DAO<Questao> dao;
	
	@Inject private Questoes questoes;
	@Inject private QuestoesFilterTable filtro;
	
	@Inject DAO<TipoQuestao> daoTipo;
	
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
				
				setRowCount(questoes.quantidadeFiltrados(filtro));
				
				return questoes.filtrados(filtro);
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
	
	public QuestoesFilterTable getFiltro() {
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

}
