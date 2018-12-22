<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:url value="/formEntradaCadastro" var="linkEntradaServlet" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="script_estilos.jsp" />
<link rel="stylesheet" type="text/css" href="css/cadastro.css">
<title>Cadastrar Novo Usuário</title>
</head>
<body>
	<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
		<c:import url="navBar.jsp" />
	</c:if>
	<c:if test="${usuarioLogado.isAdm ==  'FALSE'}">
		<c:import url="navBarCli.jsp" />
	</c:if>

	<a class="voltar" href="formLogin.jsp">Tela de Login</a>
	<br>
	<p class="intro">Seja bem vindo, insira seus dados!</p>


	<form class="card-form" action="${linkEntradaServlet}" method="post">
		Nome: <input type="text" name="nome" value="" required="required" /> <br>
		Sobrenome:<input type="text" name="sobrenome" value=""
			required="required"> <br> Endereço:<input type="text"
			name="endereco" value="" required="required"> <br>
		Login:<input type="text" name="login" value="" required="required">
		<br> Senha:<input type="text" name="senha" value=""
			required="required"> <br> Confirma Senha:<input
			type="text" name="confirmaSenha" value="" required="required">
		<br> <br> Data Nascimento: <input type="text" name="data"
			value="" required="required" /> <br> <br> <input
			class="botao-cadastro" type="submit" value="Cadastrar" />
	</form>


	<br>
	<c:import url="footerBar.jsp" />

</body>
</html>