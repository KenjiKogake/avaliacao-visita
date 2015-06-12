package br.com.visitas.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.visitas.ENUM.Status;

public class DAO<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private final Class<T> classe;
	private EntityManager em;
	
	public DAO(Class<T> classe, EntityManager em) {
		this.classe = classe;
		this.em = em;
	}
	
	public void adiciona(T t) throws Exception{
		try {
			// abre transacao
			em.getTransaction().begin();
			
			// persiste o objeto
			em.persist(t);
			
			// commita a transacao
			em.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void remove(T t) throws Exception{
		try{
			em.getTransaction().begin();
			
			em.remove(em.merge(t));
			
			em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualiza(T t) throws Exception{
		try{
			em.getTransaction().begin();
			
			em.merge(t);
	
			em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<T> listaTodos() {
		List<T> lista = new ArrayList<T>();
		try {
			CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
			
			query.select(query.from(classe));

			lista = em.createQuery(query).getResultList();
			
			return lista;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public T buscaPorId(Long id) {
		try{
			T instancia = em.find(classe, id);
			return instancia;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void queryById(String namedQuery, long id, Status status){
		try {
			em.getTransaction().begin();
			
			Query query = em.createNamedQuery(namedQuery)
					.setParameter("pId", id);

			if(status != null) query.setParameter("pStatus", status);
			
			query.executeUpdate();
			
			em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<T> listaNamedQuery(String namedQuery){
		List<T> lista = new ArrayList<T>();
		try {
			lista = em.createNamedQuery(namedQuery, classe)
				.getResultList();
			
			return lista;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
