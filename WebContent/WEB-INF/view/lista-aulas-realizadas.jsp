<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lista de Aulas Realizadas</title>
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
			style="position: relative;">


			<h3>Lista de Aulas Realizadas</h3>

			<br> <br>

			

			<!--  busca por nome -->


			<form:form action="search" method="POST">
				<input type="text" name="nomeBusca" maxlength="45" />
				<input type="submit" value="Buscar" class="btn btn-primary" />
			</form:form>

			<br>

			<!-- pagina em construção -->
			<table>
				<tr>
					<th>Id da Aula</th>
					<th>Data da realização da aula</th>
					<th> </th>
				</tr>

				<!-- loop over and print usuarios -->
				<c:forEach var="tempModulo" items="${aulas}">

					<tr>
						<td>${tempModulo.id_aula_realizada}</td>
						<td>${tempModulo.dt_inicio_chamada_aula_realizada}</td>
						
					</tr>

				</c:forEach>

			</table>
			<br>

			<!-- Botao Voltar -->
			<input class="btn btn-primary" type="button" value="Voltar"
				onclick="window.location.href='../aluno/home-aluno'; return false;"
				class="add-button" />

		</div>
		<div id="footer">
			<p style="display: block; margin: auto; padding: 10px;"
				align="center">Copyright © 2017 Hullo. Todos os direitos
				reservados.</p>
		</div>
	</div>


</body>

</html>