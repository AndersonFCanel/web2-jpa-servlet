<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/entrada" var="linkEntradaServlet" />

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<c:import url="script_estilos.jsp" />
<title>Cadastrar Cartao de Credito para ${usuarioLogado.login}</title>
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
				<h1>Tela de Cadastro de Novo Cartão de Crédito</h1>
				<p>Olá ${usuarioLogado.login}, insira os dados de seu cartão
					aqui:</p>
				<br> ${erro}
				<form action="${linkEntradaServlet}" method="post">
					<label class="descricao" for="nome">Nome </label> <input
						type="text" class="dados-cadastro" name="nome"
						value="${usuario.nome}- " readonly="true" /> <br> <label
						class="descricao" for="numero">Numero </label> <input type="text"
						class="dados-cadastro" name="numero" value="" required="required" />
					<br> <label class="descricao" for="dataVencimento">Data
						Vencimento </label> <input class="dados-cadastro" type="text"
						name="dataVencimento" value="" required="required"
						placeholder="DD/MM/YYYY" /> <br> <input
						class="dados-cadastro" type="hidden" name="id"
						value="${usuario.id}" /> <input type="hidden" name="acao"
						value="NovoCartao"><br> <input type="submit"
						class="botao-novo" value="Cadastrar Novo Cartão" />
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

	<c:import url="footerBar.jsp" />
</body>

</html>