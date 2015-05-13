package br.com.visitas.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.visitas.DAO.DAO;
import br.com.visitas.DAO.EntityManagerProducer;
import br.com.visitas.modelo.imovel.Imovel;

@Named
@ViewScoped
public class ImovelBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Imovel> imoveis;
//	@Inject private DAO<Imovel> dao;
	private DAO<Imovel> dao = new DAO<Imovel>(Imovel.class, new EntityManagerProducer().createEntityManager());
	
//	@Inject private EntityManager em;
	private Imovel imovel;

	public void buscaTodos() {
		
//		try {
//			
//			CriteriaQuery<Imovel> query = em.getCriteriaBuilder().createQuery(Imovel.class);
//			query.select(query.from(Imovel.class));
//
//			this.imoveis = em.createQuery(query).getResultList();
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
					this.imoveis = dao.listaTodos();
	}
	
	public void filtrar(){
		buscaTodos();
//		this.imoveis = dao.listaFiltrada("teste");
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
