package br.com.visitas.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.visitas.DAO.DAO;
import br.com.visitas.modelo.imovel.Imovel;

@ManagedBean
public class ImovelBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Imovel> imoveis;
	private final DAO<Imovel> dao = new DAO<Imovel>(Imovel.class);
	private Imovel imovel;

	public void buscaTodos() {
		this.imoveis = dao.listaTodos();
	}
	
	public void filtrar(){
		this.imoveis = dao.listaFiltrada("teste");
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public void salvar() {
		try {
			dao.adiciona(imovel);
			buscaTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
