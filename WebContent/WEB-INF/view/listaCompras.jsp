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
<title>Listar Compras Realizadas</title>
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
				<h1 class="menu-cliente--intro">Lista de Compras Confirmadas:</h1>
				<br> <label class="descricao">Pesquisar</label> <br> <input
					type="text" id="txtBusca" class="dados-cadastro"
					placeholder="Digite aqui um valor para filtrar..." /> <br> <br>
				<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
					<ul id="ulItens">
						<c:forEach items="${compras}" var="compra">
							<li>Número da Compra: ${compra.idCompra} <br> Nome do
								Comprador: ${compra.nomeUsuario} <br> Hora da compra:
								${compra.horaCompra} <br> <a
								href="entrada?acao=MostraCompra&idCompra=${compra.idCompra}">
									<button type="button">Mostrar Detalhes</button>
							</a> <br>
							</li>
							<br>
						</c:forEach>
					</ul>
				</c:if>

				<c:if test="${usuarioLogado.isAdm ==  'FALSE'}">

					<ul id="ulItens">
						<c:forEach items="${compras}" var="compra">
							<c:if test="${usuarioLogado.id ==  compra.idUser}">
								<li><br> Número da Compra: ${compra.idCompra} <br>
									Nome do Comprador: ${compra.nomeUsuario} <br> Hora da
									compra: ${compra.horaCompra} <br> <a
									href="entrada?acao=MostraCompra&idCompra=${compra.idCompra}">
										<button type="button">Mostrar Detalhes</button>
								</a> <br></li>
							</c:if>
						</c:forEach>
					</ul>
				</c:if>
				<br> <br>


			</div>
			<div class="col-sm-2 sidenav">
				<!--  <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div> -->
			</div>
		</div>
	</div>

	<br>
	<br>
	<br>
	<br>
	<c:import url="footerBar.jsp" />




</body>
</html>



