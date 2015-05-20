package br.com.visitas.filter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

public class LazyData<T> extends LazyDataModel<T> implements SelectableDataModel<T>, Serializable{
	private static final long serialVersionUID = 1L;

	private Object exampleFilter;

	private LazyList<T> lista;

	private FilterTable filtro;

	public LazyData(Object exampleFilter, LazyList<T> lista, FilterTable filtro) {
		
		this.exampleFilter = exampleFilter;
		this.lista = lista;
		this.filtro = filtro;
	}

	@Override
	public T getRowData() {
		return super.getRowData();
	}
	
	@Override
	public Object getRowKey(T object) {
		return super.getRowKey(object);
	}
	
	@Override
	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		
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
