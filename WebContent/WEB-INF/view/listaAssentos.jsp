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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<c:import url="script_estilos.jsp" />

<title>Assentos</title>
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
				<h3>Tela de escolha de assentos</h3>
				<br> Listagem dos assentos do Voo Indentificador: ${vooId}.
				<form action="${linkEntradaServlet }" method="post">
					<br> Assentos já ocupados: Clique para desocupar: <br>
					<c:forEach items="${assentos}" var="assento">
						<c:if test="${assento.ocupante == usuarioLogado.id}">
							<c:if test="${assento.comfirmaPagamento != true}">
								<c:out value="${assento.numeroAssento}" />
								<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
									- OCUPADO POR ADMINISTRADOR				
								</c:if>
								<c:if test="${usuarioLogado.isAdm ==  'FALSE'}">
									- PRÉ COMPRA
								</c:if>
								<input type="checkbox" name="numeroAssentoOcupado"
									id="numeroAssentoOcupado" value="${assento.numeroAssento}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 </c:if>
						</c:if>

						<c:if test="${assento.ocupante == usuarioLogado.id}">
							<c:if test="${assento.comfirmaPagamento == true}">
								<c:out value="${assento.numeroAssento}" /> - Pagamento Confirmado
					<input type="checkbox" name="numeroAssentoOcupado"
									id="numeroAssentoOcupado" value="${assento.numeroAssento}"
									disabled="disabled">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 </c:if>
						</c:if>


						<c:if test="${assento.ocupante != usuarioLogado.id}">
							<c:out value="${assento.numeroAssento}" />
							<input type="checkbox" name="numeroAssentoOcupado"
								id="numeroAssentoOcupado" value="${assento.numeroAssento}"
								disabled="disabled">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 </c:if>
					</c:forEach>
					<br> <br> Assentos Livres: Clique para ocupar: <br>
					<c:forEach items="${assentosDesocupados}" var="assento">
						<%--  <c:if test="${assento.ocupado == 'false'}">  --%>
						<c:out value="${assento.numeroAssento}" />
						<input type="checkbox" name="numeroAssento" id="numeroAssento"
							value="${assento.numeroAssento}">
		    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <%-- </c:if> --%>
					</c:forEach>

					<!-- parametro adicionado via jQuery -->
					<div id="ocupa" style="display: none">
						<input type="hidden" name="ocupa" value="true">
					</div>

					<div id="desocupa" style="display: none">
						<input type="hidden" name="desocupa" value="true">
					</div>
					<input type="hidden" name="ocupante" value="${usuarioLogado.id}" />
					<input type="hidden" name="idvoo" value="${vooId}"> <input
						type="hidden" name="idVooVolta" value="${vooIdVolta}"> <input
						type="hidden" name="acao" value="AssentoOcupa"> <br>
					<input type="submit" value="Ocupar/Desocupar Assento" />
				</form>
				<br> <br> <br>
				<c:if test="${volta == true }">
					<br> Listagem dos assentos do Voo de VOLTA - Indentificador: ${vooIdVolta}.
					<br>
					<!-- SOMENTE O CLIENTE QUE OCUPOU O ASSENTO PODERÁ DESOCUPAR O MESMO -->
					<form action="${linkEntradaServlet }" method="post">
						<br> Assentos do voo de Volta já ocupados: Clique para
						desocupar: <br>
						<c:forEach items="${assentosVolta}" var="assento">
							<c:if test="${assento.ocupante == usuarioLogado.id}">
								<c:if test="${assento.comfirmaPagamento != true}">
									<c:out value="${assento.numeroAssento}" />
									<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
										- OCUPADO POR ADMINISTRADOR				
									</c:if>
									<c:if test="${usuarioLogado.isAdm ==  'FALSE'}">
										- PRÉ COMPRA
									</c:if>
									<input type="checkbox" name="numeroAssentoOcupadoVolta"
										id="numeroAssentoOcupadoVolta"
										value="${assento.numeroAssento}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 </c:if>
							</c:if>

							<c:if test="${assento.ocupante == usuarioLogado.id}">
								<c:if test="${assento.comfirmaPagamento == true}">
									<c:out value="${assento.numeroAssento}" /> - Pagamento Confirmado
					<input type="checkbox" name="numeroAssentoOcupadoVolta"
										id="numeroAssentoOcupadoVolta"
										value="${assento.numeroAssento}" disabled="disabled">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 </c:if>
							</c:if>


							<c:if test="${assento.ocupante != usuarioLogado.id}">
								<c:out value="${assento.numeroAssento}" />
								<input type="checkbox" name="numeroAssentoOcupadoVolta"
									id="numeroAssentoOcupadoVolta" value="${assento.numeroAssento}"
									disabled="disabled">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 </c:if>
						</c:forEach>
						<br> <br> Assentos do voo de volta livres: Clique para
						ocupar: <br>
						<c:forEach items="${assentosDesocupadosVolta}" var="assento">
							<%--  <c:if test="${assento.ocupado == 'false'}">  --%>
							<c:out value="${assento.numeroAssento}" />
							<input type="checkbox" name="numeroAssentoVolta"
								id="numeroAssentoVolta" value="${assento.numeroAssento}">
		    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <%-- </c:if> --%>
						</c:forEach>
						<!-- parametro adicionado via jQuery -->
						<div id="ocupa" style="display: none">
							<input type="hidden" name="ocupa" value="true">
						</div>
						<div id="desocupa" style="display: none">
							<input type="hidden" name="desocupa" value="true">
						</div>
						<input type="hidden" name="ocupante" value="${usuarioLogado.id}" />
						<input type="hidden" name="idvoo" value="${vooId}"> <input
							type="hidden" name="idVooVolta" value="${vooIdVolta}"> <input
							type="hidden" name="acao" value="AssentoOcupa"> <br>
						<input type="submit" value="Ocupar/Desocupar Assento" />
					</form>
					<br>
				</c:if>

				<c:if test="${usuarioLogado.isAdm == 'FALSE'}">
					<form action="${linkEntradaServlet }" method="post">
						<input type="hidden" name="compradorId"
							value="${usuarioLogado.id}" /> <input type="hidden" name="idvoo"
							value="${vooId}"> <input type="hidden" name="idvooVolta"
							value="${vooIdVolta}"> <input type="hidden" name="acao"
							value="FormNovoCompraVoo"> <input type="submit"
							value="Comprar Assentos(s) selecionados (PRÉ COMPRA)" />
					</form>
				</c:if>

				<br>
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