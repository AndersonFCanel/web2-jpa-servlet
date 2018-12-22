
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Usuario Logado:
				${usuarioLogado.login}</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="entrada?acao=TelaInicial">Tela
						Inicial</a></li>


				<li><a href="entrada?acao=MostraUsuario&id=${usuarioLogado.id}">Alterar
						Meus Dados</a></li>
				<li><a href="entrada?acao=ListaVoo">Comprar Passagem</a>
				<li><a href="entrada?acao=ListaCompras">Acompanhar Pedidos</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="entrada?acao=Logout&idUsuario=${usuarioLogado.id}"><span
						class="glyphicon glyphicon-log-in"></span>Sair</a></li>
			</ul>
		</div>
	</div>
</nav>



