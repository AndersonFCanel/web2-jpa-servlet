<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url value="/entrada" var="linkEntradaServlet" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="script_estilos.jsp" />
<title>Comprar passagem</title>
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
				<h1>Dados da compra</h1>
				<br> Nome: ${usuario.nome} <br> Identificador do voo:
				${idvoo.idVoo} - Origem: ${idvoo.origem} - Destino: ${idvoo.destino}
				- Ida:
				<fmt:formatDate value="${idvoo.dataPartida}" pattern="dd/MM/yyyy" />
				- Confirmação:
				<c:if test="${idvoo.confirmacao == 'TRUE'}">
	Confirmado
	</c:if>
				<c:if test="${idvoo.confirmacao == 'FALSE'}">
	Não confirmado
	</c:if>
				<br> <br> Valor por assento: ${idvoo.valorVoo} <br>

				<c:if test="${idaVolta == true }">
					<br> Nome: ${usuario.nome}
		<br> Identificador do voo: ${idvooVolta.idVoo} - Origem:
			${idvooVolta.origem} - Destino: ${idvooVolta.destino} - Ida:
			<fmt:formatDate value="${idvooVoltavoo.dataPartida}"
						pattern="dd/MM/yyyy" />
			- Confirmação:
		<c:if test="${idvooVolta.confirmacao == 'TRUE'}">
			Confirmado
		</c:if>
					<c:if test="${idvooVolta.confirmacao == 'FALSE'}">
			Não confirmado
		</c:if>
					<br>
					<br> Valor por assento: ${idvooVolta.valorVoo}
			<br>
				</c:if>

				<form action="${linkEntradaServlet }" method="post">
					<c:if test="${cartoes!=null}">
						<h3>Selecione o cartão que deseja usar nesta compra
							${usuario.nome}</h3>
			Numero do cartao:<select name="numerocartao">
							<c:forEach items="${cartoes}" var="cartao">
								<c:if test="${usuario.id ==  cartao.idUser}">
									<option value="${cartao.numeroCartao}">${cartao.numeroCartao}</option>
									<br>
								</c:if>
							</c:forEach>
						</select>
					</c:if>
					<br> <br> Assentos selecionados no voo identificador
					${idvoo.idVoo}: <br>
					<c:forEach items="${assentos}" var="assento">
						<%-- <c:out value="${assento.numeroAssento}" /> --%>
			${assento.numeroAssento}<input type="hidden" name="assento"
							id="numeroAssentoOcupado" value="${assento.numeroAssento}"
							checked="checked">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<c:set var="precoTotal" value="${precoTotal + idvoo.valorVoo}"
							scope="page" />
					</c:forEach>


					<c:if test="${idaVolta == true }">
						<br>
						<br> Assentos selecionados no voo de VOLTA, identificador ${idvooVolta.idVoo}:
<br>
						<c:forEach items="${assentosVolta}" var="assento">
				${assento.numeroAssento} <input type="hidden" name="assentoVolta"
								id="numeroAssentoOcupado" value="${assento.numeroAssento}"
								checked="checked">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<c:set var="precoTotal" value="${precoTotal + idvooVolta.valorVoo}"
								scope="page" />
						</c:forEach>
					</c:if>

					<br> <br> Preço total:<input type="text"
						name="valorTotalCompra" value="${precoTotal}" readonly="readonly">


					<input type="hidden" name="idUser" value="${usuarioLogado.id}">
					<input type="hidden" name="idVoo" value="${idvoo.idVoo}"> <input
						type="hidden" name="idVooVolta" value="${idvooVolta.idVoo}">
					<input type="hidden" name="acao" value="NovoCompraVoo"> <br>
					<br> <input type="submit" value="Confirmar Compra" />
				</form>
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