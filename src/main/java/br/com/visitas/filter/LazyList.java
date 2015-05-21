package br.com.visitas.filter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class LazyList<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Class<T> classe;

	private EntityManager em;
	
	public LazyList(Class<T> classe, EntityManager em){
		this.classe = classe;
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> filtrados(Long codigo, FilterTable filtro, List<Object> o) {
		Criteria criteria = criarCriteriaParaFiltro(codigo, filtro, o);
		
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());
		
		if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}
		
		return criteria.list();
	}
	
	public int quantidadeFiltrados(Long codigo, FilterTable filtro, List<Object> o) {
		Criteria criteria = criarCriteriaParaFiltro(codigo, filtro, o);
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	private Criteria criarCriteriaParaFiltro(Long codigo, FilterTable filtro, List<Object> o) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classe);
		
		if(codigo != 0) criteria.add(Restrictions.idEq(codigo));
		
		for (Object object : o) {
			Example example = Example.create(object)
					.enableLike(MatchMode.ANYWHERE)
					.ignoreCase()
					.excludeZeroes();
			
			criteria.add(example);
		}
		
		
		return criteria;
	}
}
