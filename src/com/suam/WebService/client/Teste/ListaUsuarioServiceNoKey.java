package com.suam.WebService.client.Teste;

import org.apache.http.client.fluent.Request;

//Classe que envia uma requisição HTTP e consome WEB-SERVICE
public class ListaUsuarioServiceNoKey {

	public static void main(String[] args) throws Exception {
		
		String conteudo = Request
			.Post("http://localhost:8080/Web2Sistema/ListaUsuario")
			.addHeader("Accept", "application/json")
			.execute()
			.returnContent()
			.asString();
		
		//Será devolvido um json ou xml e impresso no console
		System.out.println(conteudo);
		
	}

}
