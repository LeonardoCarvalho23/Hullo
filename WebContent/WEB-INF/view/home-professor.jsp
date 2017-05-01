<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Teacher's Lounge</title>
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
<script
	src="${pageContext.request.contextPath}/resources/javascript/cadastro.js"></script>
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
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
			style="height: 600px; position: relative;">

			<h3>Welcome, ${usuario.nome_usuario}.</h3>
			<div style="position: absolute; bottom: 88%; right: 100px;">
				<input type="checkbox" id="status" checked data-toggle="toggle"
					data-on="Online" data-off="Offline"> <input type='hidden'
					name='usuario' value='${online}' id='sessionVar' />
			</div>


			<form:form action="online" modelAttribute="usuario" method="POST">
				<br>
				<br>
				<input class="btn btn-primary" style="display: none" type="submit"
					name="Perfil" id="o">
			</form:form>

			<form:form action="../professor/ministrarAula"
				modelAttribute="usuario" method="POST">
				<br>
				<br>
				<input class="btn btn-primary"
					type="submit" name="Perfil" id="teste" value="Teach a Class">
			</form:form>


			<form:form action="../professor/showPerfilProfessor"
				modelAttribute="usuario" method="POST">
				<br>
				<br>
				<input class="btn btn-primary"
					type="submit" name="Perfil" id="teste" value="My Profile">
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

</script>