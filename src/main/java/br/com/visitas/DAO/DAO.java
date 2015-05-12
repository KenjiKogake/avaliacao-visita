package br.com.visitas.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.visitas.modelo.JPAUtil;
import br.com.visitas.modelo.imovel.Imovel;

public class DAO<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private EntityManager em;
	private final Class<T> classe;
	
	public DAO(Class<T> classe) {
		this.classe = classe;
		em = JPAUtil.criaEntityManager();
	}
	
	public void adiciona(T t) throws Exception{
		try {
			// abre transacao
			em.getTransaction().begin();
			
			// persiste o objeto
			em.persist(t);
			
			// commita a transacao
			em.getTransaction().commit();
			
			// fecha a entity manager
			
		}finally{
			em.close();
		}			
	}

	public void remove(T t) throws Exception{
		try {
			
			em.getTransaction().begin();
			
			em.remove(em.merge(t));
			
			em.getTransaction().commit();
		} finally{
			em.close();
		}
	}

	public void atualiza(T t) throws Exception{
		try {
			
			em.getTransaction().begin();
			
			em.merge(t);
	
			em.getTransaction().commit();
			
		}finally{
			em.close();
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
		}finally{
			em.close();
		}
		return lista;
	}
	
	public T buscaPorId(Long id) {
		try{
			T instancia = em.find(classe, id);
			return instancia;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			em.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Imovel> listaFiltrada(String filtro){
		Session session = em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(classe);
		
		if(!filtro.isEmpty()){
			criteria.add(Restrictions.ilike("descricao", filtro, MatchMode.ANYWHERE));
		}
		return criteria.list();
	}
	
}
