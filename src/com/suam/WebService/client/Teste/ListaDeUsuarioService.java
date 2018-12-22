package com.suam.WebService.client.Teste;

import java.io.IOException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

//Classe que envia uma requisição HTTP e consome WEB-SERVICE
public class ListaDeUsuarioService {

    public static void main(String[] args) throws Exception, IOException {

        String retorno = Request
        		.Post("http://localhost:8080/Web2Sistema/ListaUsuario") //fazendo a requisição
                .addHeader("accept", "application/json") //adicionando um cabeçalho, definindo o que é aceito
                .bodyString("{'api-key':'a12bc8e423'}", ContentType.APPLICATION_JSON) //
                .execute()
                .returnContent()
                .asString();

        //Será devolvido um json ou xml e impresso no console
        System.out.println(retorno);       
        
    }    
}