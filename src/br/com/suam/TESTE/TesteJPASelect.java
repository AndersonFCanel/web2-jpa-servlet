package br.com.suam.TESTE;

import java.text.ParseException;

import javax.persistence.EntityManager;

import com.suam.factory.JPAUtil;
import com.suam.model.Usuario;
import com.suam.util.DataUtils;

public class TesteJPASelect {

	public static void main(String[] args) {
		// EntityManagerFactory emf =
		// Persistence.createEntityManagerFactory("Web2Sistema");
		// EntityManager em = emf.createEntityManager();
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Usuario us1 = em.find(Usuario.class, 2);
		System.out.println("Nome: " + us1.getNome());

		/*
		 * Executando-se a classe, além de buscar e imprimir, o Hibernate realizou um
		 * update, que verificamos se está correto no terminal, digitando select * from
		 * Conta;. Como será que isto ocorre? A JPA conseguiu sincronizar os dados da
		 * Conta com os do registro do banco de dados.
		 * 
		 * Isto acontece porque o método find() nos devolve uma instância de Conta
		 * considerado como estado Managed (gerenciado), estado da entidade da JPA cujos
		 * dados são automaticamente sincronizados com o banco de dados.
		 */

		us1.setSobrenome("TESTE UP");
		
		em.getTransaction().commit();
		em.close();
		// emf.close();
	}

}
