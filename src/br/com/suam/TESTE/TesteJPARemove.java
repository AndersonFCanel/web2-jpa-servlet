package br.com.suam.TESTE;

import java.text.ParseException;

import javax.persistence.EntityManager;

import com.suam.factory.JPAUtil;
import com.suam.model.Usuario;
import com.suam.util.DataUtils;

public class TesteJPARemove {

	public static void main(String[] args) {
		// EntityManagerFactory emf =
		// Persistence.createEntityManagerFactory("Web2Sistema");
		// EntityManager em = emf.createEntityManager();
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Usuario us1 = em.find(Usuario.class, 15);
		System.out.println("Nome: " + us1.getNome());

		
		em.remove(us1);
		
		em.getTransaction().commit();
		em.close();
		// emf.close();
	}

}
