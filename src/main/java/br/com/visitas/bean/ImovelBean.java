package br.com.visitas.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.visitas.DAO.DAO;
import br.com.visitas.filter.FilterTable;
import br.com.visitas.filter.LazyData;
import br.com.visitas.filter.LazyList;
import br.com.visitas.modelo.imovel.Imovel;

@Named
@RequestScoped
public class ImovelBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Imovel> dao;
	
	private Imovel filtroImovel = new Imovel();
	
	private LazyData<Imovel> model;
	
	@Inject private Imovel imovel;

	public ImovelBean() {
		this(null, null, null);
	}
	
	@Inject
	public ImovelBean(DAO<Imovel> dao, LazyList<Imovel> imoveis, FilterTable filtro) {
		this.dao = dao;
		
		model = new LazyData<Imovel>(dao, filtroImovel, imoveis, filtro);
	}
	
	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
	public LazyData<Imovel> getModel() {
		return model;
	}
	
	public void setModel(LazyData<Imovel> model) {
		this.model = model;
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
	
	public void onRowSelect(SelectEvent event){
		imovel = (Imovel) event.getObject();
	}
	
}
