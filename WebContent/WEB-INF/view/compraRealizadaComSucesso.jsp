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
<title>Compra Realizada Com Sucesso</title>
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
				<h1>Compra Realizada com Sucesso!</h1>
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
	<c:import url="footerBar.jsp" />
</body>
</html>



