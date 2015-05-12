package br.com.visitas.DAO;
import javax.persistence.EntityManager;

import br.com.visitas.modelo.JPAUtil;
import br.com.visitas.modelo.Usuario;

public class PopulaBanco {
	public static void main(String[] args) {
		EntityManager em = JPAUtil.criaEntityManager();
		
		Usuario u = new Usuario();
		
		u.setNome("Kenji");
		u.setCpf("123123123");

		em.getTransaction().begin();
		
		em.persist(u);
		
		em.getTransaction().commit();
		
		em.close();
		
	}
	
}
