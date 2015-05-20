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
import br.com.visitas.modelo.imovel.Imovel;

@Named
@RequestScoped
public class ImovelBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject private DAO<Imovel> dao;
	
	@Inject private Imovel filtroImovel;
	
	@Inject private LazyList<Imovel> imoveis;
	
	@Inject private FilterTable filtro;
	private LazyDataModel<Imovel> model;
	
	@Inject private Imovel imovel;
	
	public ImovelBean(){
		model = new LazyDataModel<Imovel>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Imovel> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);
				
				setRowCount(imoveis.quantidadeFiltrados(filtro, filtroImovel));
				
				return imoveis.filtrados(filtro, filtroImovel);
			}
			
		};
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
	public LazyDataModel<Imovel> getModel() {
		return model;
	}
	
	public FilterTable getFiltro() {
		return filtro;
	}

	public void salvar() {
		try {
			dao.adiciona(imovel);
			imovel = new Imovel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Imovel getFiltroImovel() {
		return filtroImovel;
	}

	public void setFiltroImovel(Imovel filtroImovel) {
		this.filtroImovel = filtroImovel;
	}
	
	

}
