<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/entrada" var="linkEntradaServlet" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<c:import url="WEB-INF/view/script_estilos.jsp" />
<!-- Inclusão da folha de estilo-->
<link rel="stylesheet" type="text/css" href="css/estiloLogin.css">
<meta charset="UTF-8">
<title>Tela de Login</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Trabalho Web2</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<!-- <ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
			</ul> -->
				<ul class="nav navbar-nav navbar-right">
					<li><a href="formEntradaCadastro?cadastrarNovo=true"><span
							class="glyphicon glyphicon-log-in"></span>Registrar-se</a></li>
				</ul>
				<!-- <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul> -->
			</div>
		</div>
	</nav>



	<div class="container-fluid text-center login--fundo">
		<div class="row login--card">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12">
								<h2 class="active nome-empresa">Login</h2>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="${linkEntradaServlet}"
									method="post" role="form" style="display: block;">
									<div class="form-group">
										<input type="text" name="login" id="username" tabindex="1"
											class="form-control" placeholder="Username" value="">
									</div>
									<div class="form-group">
										<input type="password" name="senha" id="password" tabindex="2"
											class="form-control" placeholder="Password">
									</div>
									<div>
										<input type="hidden" name="acao" value="Login">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit"
													tabindex="4" class="form-control btn btn-login"
													value="Log In">
											</div>
										</div>
									</div>
									<div class="form-group">
										<h2 class="active"></h2>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<footer class="footer navbar-fixed-bottom">
		<p>Sistema de gestão\compra\venda de Passagens Aéreas</p>
	</footer>
</body>
</html>

