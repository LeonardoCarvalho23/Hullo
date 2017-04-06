<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<!-- CSS Custom -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css?v=29" />
<title>Detalhes da Aula</title>
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

			<legend>Detalhes da Aula</legend>

			<div class="form-group">
				<label class="col-md-4 control-label">Nome: </label>
				${aula.nm_aula}
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label">Número:</label>
				${aula.numero_aula}
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Índice:</label>
				${aula.indice_aula}
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label">Revisão:</label>
				${aula.revisao_aula}
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label">Conteúdo:</label>
				${aula.conteudo_aula}
			</div>
			

			<div class="form-group">
					<label class="col-md-4 control-label"></label> 
					
					<form:form action="../modulos/showFormUpdateAula" method="POST">
					<input type="submit" name="Editar" value="Editar">
					</form:form>
			</div>


	</div>

	<div id="footer">
		<p style="display: block; margin: auto; padding: 10px;" align="center">Copyright
			© 2017 Hullo. Todos os direitos reservados.</p>
	</div>

</body>
</html>