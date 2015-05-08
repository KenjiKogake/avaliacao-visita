package br.com.visitas.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("visitas-pu");
	
	public static EntityManager criaEntityManager(){
		return factory.createEntityManager();
	}
	
	public void close(EntityManager em){
		em.close();
	}
}
