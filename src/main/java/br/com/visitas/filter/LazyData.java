package br.com.visitas.filter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

import br.com.visitas.DAO.DAO;

public class LazyData<T> extends LazyDataModel<T> implements SelectableDataModel<T>, Serializable{
	private static final long serialVersionUID = 1L;

	private List<Object> exampleFilter;

	private LazyList<T> lista;

	private FilterTable filtro;

	private DAO<T> dao;

	public LazyData(DAO<T> dao, List<Object> exampleFilter, LazyList<T> lista, FilterTable filtro) {
		System.out.println("Construtor do LazyData");
		
		System.out.println(exampleFilter.size());
		
		this.dao = dao;
		this.exampleFilter = exampleFilter;
		this.lista = lista;
		this.filtro = filtro;
	}
	
	@Override
	public T getRowData(String rowKey) {
		return dao.buscaPorId(Long.decode(rowKey));
	}
	
	@Override
	public Object getRowKey(T object) {
		return object;
	}
	
	@Override
	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		System.out.println("Entrou no Load");
		filtro.setPrimeiroRegistro(first);
		filtro.setQuantidadeRegistros(pageSize);
		filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
		filtro.setPropriedadeOrdenacao(sortField);
		
		Long id = 0l;
		try {
			if(filters.get("id") != null) id = Long.valueOf((String)filters.get("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setRowCount(lista.quantidadeFiltrados(id, filtro, exampleFilter));
		
		return lista.filtrados(id, filtro, exampleFilter);
	}
	
}
