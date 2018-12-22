<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url value="/entrada" var="linkEntradaServlet" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="script_estilos.jsp" />
<title>Cadastrar Voo</title>
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
				<h1>Tela de Cadastro de novo Voo</h1>
				<form action="${linkEntradaServlet }" method="post">
					<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
			Origem:<input type="text" name="origem" value="" required="required" />
						<br>	
			Destino:<input type="text" name="destino" value=""
							required="required" />
						<br>
			Ida: <input type="date" name="ida" class="dados-cadastro"
							required="required" />

						<!-- <input type="text" pattern="\d{1,2}-\d{1,2}-\d{4}" required="required"
							class="datepicker" name="ida" value="" placeholder="DD-MM-YYYY" /> -->
						<!-- <input class="datepicker" type="text" name="ida"
							value="XX-XX-XXXX" readonly /> -->
						<br>
			Confirmação:<!-- <input type="checkbox" name="confirmacao" value="true" /> -->
						<br>
						<input type="radio" name="confirmacao" value="1"> Confirmado<br>
						<input type="radio" name="confirmacao" value="0">Não Confirmado<br>
			Valor:<input type="text" name="valorVoo" value="" required="required" />
					</c:if>
					<br> <br> <input type="hidden" name="acao"
						value="NovoVoo"> <input type="submit"
						value="Cadastrar Novo Voo" />
				</form>
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

	<c:import url="footerBar.jsp" />
</body>
</html>