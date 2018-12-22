package br.com.suam.TESTE;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.EntityManager;

import org.apache.http.client.utils.DateUtils;

import com.suam.model.Usuario;
import com.suam.factory.JPAUtil;
import com.suam.util.DataUtils;

public class TesteJPAINsert {

	public static void main(String[] args) {
		Usuario usuario = new Usuario();

		usuario.setNome("nome");
		usuario.setSobrenome("sobrenome");
		usuario.setEndereco("endereco");
		usuario.setSenha("senha");
		usuario.setLogin("login");
		usuario.setConfirmaSenha("senha");
		try {
			usuario.setDataNascimento(DataUtils.formatarDataBarra_dd_mm_yyyy().parse("15/04/1988"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		usuario.setIsAdm(true);

		// EntityManagerFactory emf =
		// Persistence.createEntityManagerFactory("Web2Sistema");
		// EntityManager em = emf.createEntityManager();
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		em.persist(usuario);
		// em.merge(usuario);

		em.getTransaction().commit();
		em.close();
		// emf.close();
	}

}
