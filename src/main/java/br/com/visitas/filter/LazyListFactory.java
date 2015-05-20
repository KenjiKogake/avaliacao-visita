package br.com.visitas.filter;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public class LazyListFactory<T> {
	
	@Inject
	private EntityManager em;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Dependent
	@Produces
	public LazyList<T> create(InjectionPoint injectionPoint){
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
	    Class classe = (Class) type.getActualTypeArguments()[0];
	    return new LazyList(classe, em);
	}
}
