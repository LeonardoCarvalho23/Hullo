<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Teach a class</title>
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



</head>

<body>
	<div id="wrapper">
		<div id="header">
			<img class="logo"
				src="${pageContext.request.contextPath}/resources/images/logo200v2.png"
				width="100px">
		</div>
		<div id="container" class="well form-horizontal"
			style="height: 600px; position: relative; display: table; width: 100%;">

			<div id="controls">
				 <div id="info">
					<div id="client-name" style="display: none;"></div>
					<p><b>Your student's name is: ${aulaRealizadaModel.aluno.nome_usuario}</b></p>
				</div> 
				<div id="call-controls">
					<!-- <p class="instructions">Make a Call:</p> -->
					<input id="phone-number" type="hidden" value="${aulaRealizadaModel.aluno.telefone_usuario}" />
					<button id="button-call">Call Student</button>
					<button id="button-hangup">Hangup</button>
				</div>
				<div id="log"></div>
			</div>

			<form:form class="well form-horizontal" action="encerrarAula"
				modelAttribute="aulaRealizadaModel" method="POST">
				<!-- id do professor que esta ministrando a aula, para guardar na finalizacao da aula -->
				<form:hidden path="aulaRealizadaAtual.id_professor_aula_realizada" />
				<!-- id da aula_realizada_atual, para guardar na finalizacao da aula -->
				<form:hidden path="aulaRealizadaAtual.id_aula_realizada" />
				<!-- id do aluno, para criar a proxima aula -->
				<form:hidden path="aulaRealizadaAtual.id_aluno_aula_realizada" />
				<!-- id do aula atual, para criar a proxima aula -->
				<form:hidden path="aulaRealizadaAtual.id_aula_aula_realizada" />

				<!-- Resultados da aula anterior do aluno -->
				<legend>Last Class results</legend>

				<div class="form-group">
					<label class="col-md-4 control-label">Nome da Aula:</label>
					${aulaRealizadaModel.aulaAnterior.nm_aula}
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label">Model</label>
					${aulaRealizadaModel.aulaRealizadaAnterior.nota_model_aula_realizada}
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label">Practice</label>
					${aulaRealizadaModel.aulaRealizadaAnterior.nota_practice_aula_realizada}
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label">Production</label>
					${aulaRealizadaModel.aulaRealizadaAnterior.nota_production_aula_realizada}
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label">Overall Comments</label>
					${aulaRealizadaModel.aulaRealizadaAnterior.comentario_aula_realizada}
				</div>

				<!-- Script Aula atual -->
				<legend>Class Script</legend>
				<p>Last Class Review:
					${aulaRealizadaModel.aulaAnterior.revisao_aula}</p>
				<p>Class Name: ${aulaRealizadaModel.aulaAtual.nm_aula}</p>
				<p>Class Content: ${aulaRealizadaModel.aulaAtual.conteudo_aula}</p>

				<!-- Objetivos da Aula Atual -->
				<legend>Grade your student!</legend>

				<div class="form-group">
					<label class="col-md-4 control-label">Model</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-stats"></i></span>
							<form:input placeholder="Nota" class="form-control"
								path="aulaRealizadaAtual.nota_model_aula_realizada"
								required="true" maxlength="1"
								value="${aulaRealizadaModel.aulaRealizadaAtual.nota_model_aula_realizada}" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label">Practice</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-stats"></i></span>
							<form:input placeholder="Nota" class="form-control"
								path="aulaRealizadaAtual.nota_practice_aula_realizada"
								required="true" maxlength="1"
								value="${aulaRealizadaModel.aulaRealizadaAtual.nota_practice_aula_realizada}" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label">Production</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-stats"></i></span>
							<form:input placeholder="Nota" class="form-control"
								path="aulaRealizadaAtual.nota_production_aula_realizada"
								required="true" maxlength="1"
								value="${aulaRealizadaModel.aulaRealizadaAtual.nota_production_aula_realizada}" />
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-4 control-label">Comentario Aula Realizada</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<form:textarea rows="4" cols="50" placeholder="General comments" type="textarea"
								path="aulaRealizadaAtual.comentario_aula_realizada"
								required="true" maxlength="200" 
								value="${aulaRealizadaModel.aulaRealizadaAtual.comentario_aula_realizada}" />
						</div>
					</div>
				</div>

				<!-- Botao para encerrar e fazer update na aula_realizada -->
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<button type="submit" class="btn btn-primary">Encerrar
							Aula</button>
					</div>
				</div>
			</form:form>
		</div>
		<div id="footer">
			<p style="display: block; margin: auto; padding: 10px;"
				align="center">Copyright © 2017 Hullo. Todos os direitos
				reservados.</p>

		</div>
	</div>
	<script type="text/javascript"
		src="//media.twiliocdn.com/sdk/js/client/v1.3/twilio.min.js"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/quickstart.js"></script>

</body>

</html>