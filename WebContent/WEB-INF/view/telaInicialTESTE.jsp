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
<link rel="stylesheet" type="text/css" href="css/inicial.css">
<script src="javascript/inicial.js" type="text/javascript"></script>
<title>Inicio</title>
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
				<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
					<h1 class="menu-cliente--intro">Menu do Administrador</h1>

					<!-- <a href="entrada?acao=FormNovoUsuario"><button type="button">Criar
				Usuário</button></a>
					<br><br> -->
					<a href="entrada?acao=ListaUsuario"><button type="button"
							class="botao-menu">Tela de administração de usuários</button></a>
					<br>
					<br>
					<a href="entrada?acao=ListaVoo"><button type="button"
							class="botao-menu">Tela administração de Voos</button></a>
					<br>
					<br>
					<a href="entrada?acao=ListaCompras"><button type="button"
							class="botao-menu">Tela de acompanhamento dos pedidos de
							voos</button></a>
					<br>
					<br>
				</c:if>

				<c:if test="${usuarioLogado.isAdm ==  'FALSE'}">
					<h3 class="menu-cliente">Menu do Cliente</h3>
					<br>
					<h3class"menu-cliente--intro">É um prazer ter você de volta, ${usuarioLogado.nome}!</h3>
					<a href="entrada?acao=MostraUsuario&id=${usuarioLogado.id}"><button type="button" class="botao-menu">Alterar Meus Dados</button></a>
					<br>
					<a href="entrada?acao=ListaVoo"><button type="button" class="botao-menu">Tela
							de Compra de passagem Aérea</button></a>
					<br>
					<a href="entrada?acao=ListaCompras"><button type="button" class="botao-menu">Tela
							de acompanhamento dos pedidos de voos</button></a>
					<br>
				</c:if>
			</div>
			<div class="col-sm-2 sidenav">
				<!-- <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div> -->
			</div>
		</div>
		<div class="row banner-convite">
			<div class="text flick">
				<p class="flick">
					<span class="word wisteria">#AirFly</span> <span
						class="word belize">#SejaBemVindo${usuarioLogado.nome}!</span> <span
						class="word green">#SeuDestino</span> <span class="word midnight">#NossasAsas</span>
					<span class="word pomegranate">#VoeConosco</span>
				</p>
			</div>


		</div>
	</div>
	<div class="filler-inicial"></div>

	<c:import url="footerBar.jsp" />

	<script src="js/inicial.js"></script>
</body>

</html>