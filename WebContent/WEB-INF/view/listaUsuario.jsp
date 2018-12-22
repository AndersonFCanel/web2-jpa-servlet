<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.suam.bean.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<c:import url="script_estilos.jsp" />
<title>Lista de Usuários</title>
</head>

<body>
	<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
		<c:import url="navBar.jsp" />
	</c:if>
	<c:if test="${usuarioLogado.isAdm ==  'FALSE'}">
		<c:import url="navBarCli.jsp" />
	</c:if>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<!--  <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p> -->
			</div>
			<div class="col-sm-8 text-left">
				<h1 classe="menu-cliente--intro">Lista de usuários cadastrados
					no sistema</h1>

				Lista de usuarios: <br> <label class="descricao">Pesquisar</label>
				<br> <input type="text" id="txtBusca" class="dados-cadastro"
					placeholder="Digite aqui um valor para filtrar..." /> <br> <br>
				<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
					<a href="entrada?acao=FormNovoUsuario"><button
							class="botao-novo">Cadastrar novo usuário</button></a>
				</c:if>
				<br>
				<ul id="ulItens">
					<c:if test="${usuarioLogado.isAdm == 'TRUE'}">
						<h3>Usuários cadastrados com perfil administrador do sistema:</h3>
						<c:forEach items="${usuarios}" var="usuario">
							<c:if test="${usuario.isAdm ==  'TRUE'}">
								<div class="row espacador">
									<li>
										<div class="col-sm-6 text-left">NOME: ${usuario.nome}
											${usuario.sobrenome}</div>
										<div class="col-sm-6 text-right">
											<a href="entrada?acao=MostraUsuario&id=${usuario.id}"><button
													type="button" class="botao-novo">Editar\Mostrar
													Usuário</button></a> <a
												href="entrada?acao=RemoveUsuario&id=${usuario.id}"><button
													type="button" class="botao-novo">Remover Usuário</button></a> <br>
										</div>
									</li>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
					<br>
					<h3>Usuários cadastrados com perfil Cliente</h3>
					<c:forEach items="${usuarios}" var="usuario">
						<c:if test="${usuario.isAdm ==  'FALSE'}">

							<div class="row espacador">
								<li>
									<div class="col-sm-6 text-left">NOME: ${usuario.nome}
										${usuario.sobrenome}</div>
									<div class="col-sm-6 text-right">
										<a href="entrada?acao=MostraUsuario&id=${usuario.id}"><button
												type="button" class="botao-novo">Editar\Mostrar
												Usuário</button></a> <a
											href="entrada?acao=RemoveUsuario&id=${usuario.id}"><button
												type="button" class="botao-novo">Remover Usuário</button></a> <br>
									</div>
								</li>
							</div>
						</c:if>
					</c:forEach>
				</ul>


				<br> <br> <br> <br> <br>
			</div>
			<div class="col-sm-2 sidenav">
				<!--  <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div> -->
				<!-- Deveria ser dinamico -->
				<a href="entrada?acao=ListaUsuario&pagina_inicio=0&pagina_fim=10">0..10</a>
				<a href="entrada?acao=ListaUsuario&pagina_inicio=11&pagina_fim=20">11..20</a>
				<a href="entrada?acao=ListaUsuario&pagina_inicio=21&pagina_fim=30">21..30</a>
			</div>
		</div>
	</div>

	<c:import url="footerBar.jsp" />
</body>

</html>