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
import br.com.visitas.modelo.questionario.TipoQuestao;

@Named
@RequestScoped
public class TipoQuestaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject private DAO<TipoQuestao> dao;
	
	@Inject private LazyList<TipoQuestao> tipos;
	@Inject private FilterTable filtro;
	private LazyDataModel<TipoQuestao> model;
	
	@Inject private TipoQuestao tipoQuestao;
	
	@Inject private TipoQuestao filtroTipoQuestao;
	
	public TipoQuestaoBean(){
		model = new LazyDataModel<TipoQuestao>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<TipoQuestao> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);
				
				setRowCount(tipos.quantidadeFiltrados(filtro, filtroTipoQuestao));
				
				return tipos.filtrados(filtro, filtroTipoQuestao);
			}
		};
	}

	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}
	
	public LazyDataModel<TipoQuestao> getModel() {
		return model;
	}
	
	public FilterTable getFiltro() {
		return filtro;
	}
	
	public TipoQuestao getFiltroTipoQuestao() {
		return filtroTipoQuestao;
	}
	
	public void setFiltroTipoQuestao(TipoQuestao filtroTipoQuestao) {
		this.filtroTipoQuestao = filtroTipoQuestao;
	}

	public void salvar() {
		try {
			dao.adiciona(tipoQuestao);
			tipoQuestao = new TipoQuestao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
