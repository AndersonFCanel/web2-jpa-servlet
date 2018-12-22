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
		 * Executando-se a classe, al�m de buscar e imprimir, o Hibernate realizou um
		 * update, que verificamos se est� correto no terminal, digitando select * from
		 * Conta;. Como ser� que isto ocorre? A JPA conseguiu sincronizar os dados da
		 * Conta com os do registro do banco de dados.
		 * 
		 * Isto acontece porque o m�todo find() nos devolve uma inst�ncia de Conta
		 * considerado como estado Managed (gerenciado), estado da entidade da JPA cujos
		 * dados s�o automaticamente sincronizados com o banco de dados.
		 */

		us1.setSobrenome("TESTE UP");
		
		em.getTransaction().commit();
		em.close();
		// emf.close();
	}

}
