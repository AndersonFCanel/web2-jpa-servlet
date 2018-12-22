<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:url value="/entrada" var="linkEntradaServlet" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="script_estilos.jsp" />
<link rel="stylesheet" type="text/css" href="css/alteraUsuario.css">
<title>Cadastrar Novo Usuário</title>
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
				<!-- <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p> -->
			</div>
			<div class="col-sm-8 text-left">
				<h1>Cadastrar novo usuário</h1>
				<span class="erro"> ${erro}</span>
				<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
					<form action="${linkEntradaServlet}" method="post">

						<label class="descricao" for="nome">Nome </label> <input
							type="text" name="nome" value="${usuario.nome}"
							required="required" class="dados-cadastro" /> <label
							class="descricao" for="sobrenome">Sobrenome</label> <input
							type="text" name="sobrenome" value="${usuario.sobrenome}"
							required="required" class="dados-cadastro"> <label
							class="descricao" for="endereco"> Endereço</label> <input
							type="text" name="endereco" value="${usuario.endereco}"
							required="required" class="dados-cadastro"> <label
							class="descricao" for="login">Login</label> <input type="text"
							name="login" value="${usuario.login}" required="required"
							class="dados-cadastro"> <label class="descricao"
							for="senha">Senha</label> <input type="password" name="senha"
							value="${usuario.senha}" required="required"
							class="dados-cadastro"> <label class="descricao"
							for="senha">Confirma Senha</label> <input type="password"
							name="confirmaSenha" value="" required="required"
							class="dados-cadastro"> <br> <label for="ehAdm">Usuario
							possui perfil administrador:</label><br> <input type="radio"
							name="ehAdm" value="1" required="required"> Administrador<br>
						<input type="radio" name="ehAdm" value="0"> Cliente<br>
						<label class="descricao" for="data">Data Nascimento</label> <input
							type="date" name="data" class="dados-cadastro"
							required="required" />
						<%-- <input type="date" name="data"
							placeholder="DD/MM/YYYY" required="required"
							value="<fmt:formatDate value="${usuario.dataNascimento}" pattern="dd/MM/yyyy"/>" 
							class="dados-cadastro" /> --%>
						<br> <br> <input type="hidden" name="acao"
							value="NovoUsuario"> <input type="submit"
							value="Cadastrar novo Usuário" /> <input name="Reset"
							type="reset" class="formobjects" value="Redefinir">
					</form>
					<c:import url="footerBar.jsp" />
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
	</div>


</body>
</html>