<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
	href="${pageContext.request.contextPath}/resources/css/style.css?v=30" />



</head>

<body>
	<div id="wrapper">
		<div id="header">
			<img class="logo"
				src="${pageContext.request.contextPath}/resources/images/logo200v2.png"
				width="100px">
		</div>
		<div id="container" class="well form-horizontal"
			style="height: 60vh; position: relative; display: table; width: 100%;">


			<div style="width: 100%;">
				<!-- 2nd Container -->
				<!-- Bootstrap tabs -->
				<div style="float: left; width: 600px; margin: 10px;">
					<ul class="nav nav-tabs">
						<li><a data-toggle="tab" href="#lastclass">Last class</a></li>
						<li class="active"><a data-toggle="tab" href="#classscript">Class
								script</a></li>
					</ul>

					<div class="tab-content">
						<!-- First tab -->
						<div id="lastclass" class="tab-pane fade">

							<!-- Resultados da aula anterior do aluno -->
							<h3>Last Class results</h3>
							
							

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

						</div>

						<!-- Second tab -->
						<div id="classcript" class="tab-pane fade in active">
							<!-- Script Aula atual -->
							<h3>Class Script</h3>
							<p><font color="#1588c9">Last Class Review:</font>
								${aulaRealizadaModel.aulaAnterior.revisao_aula}</p>
							<p><font color="#1588c9"><b>Class Name: ${aulaRealizadaModel.aulaAtual.nm_aula}</b></font></p>
							<p><font color="#1588c9">Class Content:</font>
								${aulaRealizadaModel.aulaAtual.conteudo_aula}</p>

						</div>

					</div>
				</div>

				<!-- Twilio controls -->
				<div style="float: left; width: 200px; margin: 10px;">
					<div id="controls">
						<div id="info">
							<div id="client-name" style="display: none;"></div>
							<b>Student:</b> <br>
							<h2>${aulaRealizadaModel.aluno.nome_usuario}</h2>
						</div>
						<div id="call-controls">
							<!-- <p class="instructions">Make a Call:</p> -->
							<input id="phone-number" type="hidden"
								value="${aulaRealizadaModel.aluno.telefone_usuario}" />
							<button id="button-call" class="btn btn-primary"
								onclick="toggle_visibility('grade')">Call Student</button>
							<button id="button-hangup" class="btn btn-danger"
								style="display: none;">Hangup</button>
							<button id="button-no-answer"
								onclick="window.location.href='../professor/updateAulaNaoAtendida'; return false;"
								class="btn btn-danger" style="display: none;">No Answer</button>

							<p id="timer"
								style="display: none; background: red; border-radius: 2px; color: white; margin-left: 10px; padding: 10px; font-weight: bold;"></p>
						</div>
						<div id="log"
							style="height: 100px; overflow: auto; padding: 10px; margin-top: 20px; border-radius: 5px; border: 1px solid #808080; font-size: 10px;"></div>
					</div>
				</div>
				<!-- End twilio controls -->
			</div>
			<!-- End container -->


			<!-- Objetivos da Aula Atual -->
			<div id="grade" style="clear: both; display: none;">
				<form:form action="encerrarAula"  modelAttribute="aulaRealizadaModel"
					method="POST">
					<!-- id do professor que esta ministrando a aula, para guardar na finalizacao da aula -->
					<form:hidden path="aulaRealizadaAtual.id_professor_aula_realizada" />
					<!-- id da aula_realizada_atual, para guardar na finalizacao da aula -->
					<form:hidden path="aulaRealizadaAtual.id_aula_realizada" />
					<!-- id do aluno, para criar a proxima aula -->
					<form:hidden path="aulaRealizadaAtual.id_aluno_aula_realizada" />
					<!-- id do aula atual, para criar a proxima aula -->
					<form:hidden path="aulaRealizadaAtual.id_aula_aula_realizada" />
					<h3>Grade your student!</h3>

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
						<label class="col-md-4 control-label">Comentario Aula
							Realizada</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<form:textarea rows="4" cols="50" placeholder="General comments"
									type="textarea"
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

		</div>
		<div id="footer">
			<p style="display: block; margin: auto; padding: 10px;"
				align="center">Copyright © 2017 Hullo. Todos os direitos
				reservados.</p>
		</div>
	</div>
	<script type="text/javascript"
		src="https://media.twiliocdn.com/sdk/js/client/v1.3/twilio.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/quickstart.js?v=32"></script>

</body>

</html>