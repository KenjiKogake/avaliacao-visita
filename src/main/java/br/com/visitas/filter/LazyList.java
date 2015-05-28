package br.com.visitas.filter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.visitas.modelo.questionario.TipoQuestao;

public class LazyList<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Class<T> classe;

	private EntityManager em;
	
	public LazyList(Class<T> classe, EntityManager em){
		this.classe = classe;
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> filtrados(Long codigo, FilterTable filtro, Object o, Map<String, Object> filtrosAdicionais) {
		Criteria criteria = criarCriteriaParaFiltro(codigo, filtro, o, filtrosAdicionais);
		
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());
		
		if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}
		
		return criteria.list();
	}
	
	public int quantidadeFiltrados(Long codigo, FilterTable filtro, Object o, Map<String, Object> filtrosAdicionais) {
		Criteria criteria = criarCriteriaParaFiltro(codigo, filtro, o, filtrosAdicionais);
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	private Criteria criarCriteriaParaFiltro(Long codigo, FilterTable filtro, Object o, Map<String, Object> filtrosAdicionais) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classe);
		
		if(codigo != 0) criteria.add(Restrictions.idEq(codigo));
		
		Example example = Example.create(o)
				.enableLike(MatchMode.ANYWHERE)
				.ignoreCase()
				.excludeZeroes();
		
		
		
		if(filtrosAdicionais.isEmpty()) criteria.add(example);
		else{
			for (Entry<String, Object> pair : filtrosAdicionais.entrySet()) {
				System.out.println("Entrou no foreach");
				System.out.println(pair.getKey());
				TipoQuestao value = (TipoQuestao) pair.getValue();
				
				System.out.println(value.getTipo());
				
				System.out.println(pair.getValue());
				
				criteria.add(example)
					.createCriteria(pair.getKey())
					.add(Example.create(pair.getValue()));
			}
		}
		
		
		
		return criteria;
	}
}
