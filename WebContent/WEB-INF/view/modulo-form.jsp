<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Novo Modulo</title>

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
		<div id="container">

			<form:form class="well form-horizontal" action="newModulo"
				modelAttribute="modulo">
				<fieldset>
					${errorMessage}
					<!-- Nome do formulário -->
					<legend>Novo Módulo</legend>

					<!-- Campo nome -->
					<div class="form-group">
						<label class="col-md-4 control-label">Nome</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<form:input path="nm_modulo" placeholder="Nome"
									class="form-control" />
								<form:errors path="nm_modulo"/>
							</div>
						</div>
					</div>

					<!-- Campo indice -->
					<div class="form-group">
						  <label class="col-md-4 control-label">Índice</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-th"></i></span>
								<form:input placeholder="0.0" class="form-control"
									path="indice_modulo" required="true" maxlength="5" />
							</div>
						</div>
					</div>

					<!-- Botões -->
					<div class="form-group">
						<label class="col-md-4 control-label"></label>
						<div class="col-md-4">
							<button type="submit" class="btn btn-primary">Salvar</button>
							<input type="button" class="btn btn-primary" value="Voltar"
								onclick="window.location.href='../modulos/lista'; return false;" />
						</div>
					</div>



				</fieldset>
			</form:form>
			<%-- <form:form class="well form-horizontal" action="../modulos/formAula"
				modelAttribute="modulo" method="GET">
				<button type="submit" class="btn btn-primary">Nova Aula</button>
			</form:form> --%>

		</div>
		<div id="footer">
			<p style="display: block; margin: auto; padding: 10px;"
				align="center">Copyright © 2017 Hullo. Todos os direitos
				reservados.</p>
		</div>
	</div>
</body>

</html>
