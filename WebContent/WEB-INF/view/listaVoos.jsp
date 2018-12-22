<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.suam.bean.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:url value="/entrada" var="linkEntradaServlet" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="script_estilos.jsp" />
<link rel="stylesheet" type="text/css" href="css/alteraUsuario.css">
<title>Lista de Voos</title>
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
				<h1 class="menu-cliente--intro">Lista de voos disponiveis</h1>

				<br> <label class="descricao" for="sobrenome">Pesquisar</label>

				<input type="text" id="txtBusca" class="dados-cadastro"
					placeholder="Digite aqui um valor para filtrar..." /> <br>
				<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
					<br>
					<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
						<a href="entrada?acao=FormNovoVoo"><button type="button"
								class="botao-confirmar-small">Cadastrar Novo Voo</button></a>
					</c:if>
					<br>
					<form name="formulario" action="${linkEntradaServlet }"
						method="post">
						<ul id="ulItens">
							<c:forEach items="${voos}" var="voo">
								<div class="row espacador">
									<li class="liIda" id="liSelect${voo.idVoo}"><input
										class="liIdaImput${voo.idVoo}" type="radio" name="vooId"
										value="${voo.idVoo}"> Origem: ${voo.origem} - Destino:
										${voo.destino} - Ida: <fmt:formatDate
											value="${voo.dataPartida}" pattern="dd/MM/yyyy" /><br>
										- Confirmação: <c:if test="${voo.confirmacao == true}">
								Confirmado
							</c:if> <c:if test="${voo.confirmacao == false}">
								Não confirmado
							</c:if> - Valor por assento: ${voo.valorVoo} <br> <c:if
											test="${usuarioLogado.isAdm ==  'TRUE'}">
											<a href="entrada?acao=MostraVoo&idvoo=${voo.idVoo}"><button
													type="button" class="botao-novo">Editar\Mostrar
													este Voo</button></a>
											<a href="entrada?acao=RemoveVoo&idvoo=${voo.idVoo}"><button
													type="button" class="botao-novo">Remover este Voo</button></a>
										</c:if></li>
								</div>
								<!-- <li class="separador"></li> -->
								<br>
								<br>
							</c:forEach>
						</ul>
						<br> <input type="hidden" name="acao" value="ListaAssento">
						<input type="submit" class="botao-confirmar-small"
							value="Selecionar Voo" /> <br>
					</form>
				</c:if>

				<c:if test="${usuarioLogado.isAdm ==  'FALSE'}">
					<form action="${linkEntradaServlet }" method="post">
						<ul id="ulItens">
							<c:forEach items="${voos}" var="voo">
								<div class="row espacador">
									<li class="liIda" id="liSelect${voo.idVoo}"><input
										class="liIdaImput${voo.idVoo}" type="radio" name="vooId"
										value="${voo.idVoo}"> Origem: ${voo.origem} - Destino:
										${voo.destino} - Ida: <fmt:formatDate
											value="${voo.dataPartida}" pattern="dd/MM/yyyy" /> -
										Confirmação: ${voo.confirmacao} - Valor por assento:
										${voo.valorVoo} <br> <c:if
											test="${usuarioLogado.isAdm ==  'TRUE'}">
											<a href="entrada?acao=MostraVoo&idvoo=${voo.idVoo}"><button
													type="button" class="botao-novo">Editar\Mostrar
													este Voo</button></a>
											<a href="entrada?acao=RemoveVoo&idvoo=${voo.idVoo}"><button
													type="button" class="botao-novo">Remover este Voo</button></a>
										</c:if></li>
									<!-- <li class="separador"></li>
								<br> -->
								</div>
							</c:forEach>
						</ul>
						<br> Deseja comprar passagem de volta:<br> <input
							type="radio" name="volta" id="soIda" checked="checked">
						Somente Ida <br> <input type="radio" name="volta" id="volta"
							value="1">Volta <br>
						<div id="ulVolta">
							<ul id="ulItens">
								<c:forEach items="${voos}" var="voo">
									<div class="row espacador" id="liLinha${voo.idVoo}Volta">
										<li class="liVolta" id="liSelect${voo.idVoo}Volta"><input
											class="liVoltaImput${voo.idVoo}" type="radio" name="voltaId"
											id="vooDeVolta" value="${voo.idVoo}"> Origem:
											${voo.origem} - Destino: ${voo.destino} - Ida: <fmt:formatDate
												value="${voo.dataPartida}" pattern="dd/MM/yyyy" /> -
											Confirmação: ${voo.confirmacao} - Valor por assento:
											${voo.valorVoo}</li>
										<%-- 	<li class="separador" id="liLinha${voo.idVoo}Volta"></li>
									<br> --%>
									</div>
								</c:forEach>
							</ul>
						</div>
						<br> <input type="hidden" name="acao" value="ListaAssento">
						<input type="submit" class="botao-confirmar-small"
							value="Confirmar escolhas" /> <br>
					</form>
				</c:if>
			</div>
			<div class="col-sm-2 sidenav">
				<!--  <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div> -->
				<!-- Deveria ser dinamico -->
				<a href="entrada?acao=ListaVoo&pagina_inicio=0&pagina_fim=10">0..10</a>
				<a href="entrada?acao=ListaVoo&pagina_inicio=11&pagina_fim=20">11..20</a>
				<a href="entrada?acao=ListaVoo&pagina_inicio=21&pagina_fim=30">21..30</a>
			</div>
		</div>
	</div>







	<c:import url="footerBar.jsp" />
</body>
</html>
