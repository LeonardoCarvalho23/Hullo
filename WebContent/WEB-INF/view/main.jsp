<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Novo Aluno</title>
<!--  Basic jquery and Bootstrap -->
<script src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

<!-- CSS Custom -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css?v=29" />


</head>

<body>
	<div id="wrapper">
		<div id="header">
			<img class="logo"
				src="${pageContext.request.contextPath}/resources/images/logo200v2.png"
				width="100px">
		</div>
		<div id="container" class="well form-horizontal"
			style="height: 400px; position: relative;">

			<h3>Bem-vindo, Administrador</h3>

			<br> <br>

			<!-- ver lista de alunos -->
			<form:form action="../adm/listaAlunos" method="GET">
				<input class="btn btn-primary" type="submit" name="Perfil"
					value="Listar Alunos">
			</form:form>
			<br> <br>

			<!-- ver lista de professores -->
			<form:form action="../adm/listaProfessores" method="POST">
				<input class="btn btn-primary" type="submit" name="Perfil"
					value="Listar Professores">
			</form:form>
			<br> <br>

			<!-- <!-- acesso ao CRUD de material pedagogico -->
			<form:form action="../adm/modulos/lista" method="POST">
				<input class="btn btn-primary" type="submit" name="Perfil"
					value="Material Pedagógico">
			</form:form>


		</div>
		<div id="footer">
			<p style="display: block; margin: auto; padding: 10px;"
				align="center">Copyright © 2017 Hullo. Todos os direitos
				reservados.</p>
		</div>
	</div>
</body>

</html>