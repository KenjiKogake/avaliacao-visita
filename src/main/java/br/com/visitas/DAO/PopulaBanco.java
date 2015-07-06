package br.com.visitas.DAO;
import javax.persistence.EntityManager;

import br.com.visitas.modelo.pessoa.Usuario;

public class PopulaBanco {
	public static void main(String[] args) {
		EntityManager em = new EntityManagerProducer().createEntityManager();
		
		Usuario u = new Usuario();
		
		u.setNome("Kenji");
		u.setCpf("123123123");

		em.getTransaction().begin();
		
		em.persist(u);
		
		em.getTransaction().commit();
		
		em.close();
		
	}
	
}
