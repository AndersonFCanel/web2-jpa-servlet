<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:url value="/entrada" var="linkEntradaServlet" />

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<c:import url="script_estilos.jsp" />
<title>Alterar dados do usuário</title>
<link rel="stylesheet" type="text/css" href="css/alteraUsuario.css">


<script language="JavaScript">
		function enviarDadosAlteraUsuario() {

		/* if (document.dados.nome.value == ""
				|| document.dados.nome.value.length < 8) {
			alert("Preencha campo NOME corretamente!");
			document.dados.tx_nome.focus();
			return false;
		}
		return true; */
	}
</script>

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
				<h1 class="menu-cliente--intro">Altere seu cadastro</h1>
				<br> <span class="erro"> ${erro}</span>
				<form action="${linkEntradaServlet }" method="post" name="dados"
					onSubmit="return enviarDadosAlteraUsuario();">

					<label class="descricao" for="nome">Nome </label> <input
						type="text" name="nome" value="${usuario.nome}" name=nome
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
						class="dados-cadastro"> <br> <label class="descricao"
						for="data">Data Nascimento</label>
					<%-- <input type="date" name="data" class="dados-cadastro" pattern="\d{2}/\d{2}/\d{4}" value="<fmt:formatDate value="${usuario.dataNascimento}" pattern="dd/mm/yyyy" />"
					required="required" /> <br> --%>
					<input type="text" name="data" class="dados-cadastro"
						placeholder="DD/MM/YYYY" pattern="\d{2}/\d{2}/\d{4}"
						value="<fmt:formatDate value="${usuario.dataNascimento}" pattern="dd/MM/yyyy" />"
						required="required"></input>

					<c:if test="${usuarioLogado.isAdm=='TRUE'}">
						<label for="ehAdm">Usuario possui perfil administrador:</label>
						<br>
						<c:if test="${usuario.isAdm=='TRUE'}">
							<input type="radio" name="ehAdm" value="1" checked="checked"> Administrador<br>
							<input type="radio" name="ehAdm" value="0"> Cliente
							<br>
						</c:if>
						<c:if test="${usuario.isAdm=='FALSE'}">
							<input type="radio" name="ehAdm" value="1"> Administrador<br>
							<input type="radio" name="ehAdm" value="0" checked="checked"> Cliente
						</c:if>
					</c:if>
					<br> <input type="hidden" name="id" value="${usuario.id}">
					<input type="hidden" name="acao" value="AlteraUsuario"> <input
						type="submit" value="Alterar Dados" class="botao-confirmar" />
				</form>
				<ul>
					<c:if test="${usuario.isAdm ==  'FALSE'}">
					${erro}
						
						<c:if test="${cartoes!=null}">
							<h3>Lista de cartões de ${usuario.nome}</h3>
							<c:forEach items="${cartoes}" var="cartao">
								<c:if test="${usuario.id ==  cartao.idUser}">
									<li class="card-slot">Nome do titular: ${cartao.titular} <br>Numero
										do cartao: ${cartao.numeroCartao}<br> Data de Vencimento:
										<fmt:formatDate value="${cartao.dataVencimento}"
											pattern="dd/MM/yyyy" /><br> <a
										href="entrada?acao=MostraCartao&id=${cartao.idUser}&numeroCartao=${cartao.numeroCartao}"><button
												type="button">Editar Cartão</button></a> <a
										href="entrada?acao=RemoveCartao&numero=${cartao.numeroCartao}"><button
												type="button">Remover Cartão</button></a>
									</li>
									<br>
								</c:if>
							</c:forEach>
						</c:if>
					</c:if>
					<c:if test="${usuarioLogado.isAdm =='FALSE'}">
						<li class="lista-cartao"><a
							href="entrada?acao=FormNovoCartao&id=${usuario.id}"><button
									type="button" class="botao-novo">Cadastrar Novo Cartão</button></a></li>
					</c:if>
				</ul>
				<br> <br> <br> <br>

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