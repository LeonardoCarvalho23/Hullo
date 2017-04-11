<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
<title>Editar Módulo</title>
<title>Editar Modulo</title>
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
		<div id="container">

			<form:form class="well form-horizontal" action="updateModulo"
				modelAttribute="moduloModel" method="POST">
				<form:hidden path="modulo.id_modulo" />
				<fieldset>${errorMessage}
					<!-- Nome do formulário -->
					<legend>Editar Módulo</legend>

					<!-- Campo nome -->
					<div class="form-group">
						<label class="col-md-4 control-label">Nome</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<form:input class="form-control" path="modulo.nm_modulo"
									required="true" maxlength="45" />
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
									path="modulo.indice_modulo" required="true" maxlength="5" />
							</div>
						</div>
					</div>

					<!-- para mostrar lista de aulas desse modulo -->
					<div class="form-group">
						<label class="col-md-4 control-label">Aulas do modulo</label><br>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<c:forEach var="tempAula" items="${moduloModel.listaAulas}">
									<p>
										Aula: ${tempAula.numero_aula} ${tempAula.indice_aula} -

										<!-- botao de update da aula-->
										<c:url var="viewLink" value="/modulos/showAula">
											<c:param name="id_aula" value="${tempAula.id_aula}" />
										</c:url>
										<!-- mostra o link -->
										<a href="${viewLink}">${tempAula.nm_aula}</a>
									</p>
								</c:forEach>
							</div>
						</div>
					</div>

					<!-- Campo ativo -->
					<div class="form-group">
						<label class="col-md-4 control-label">Módulo Ativo</label>
						<div class="col-md-4">
							<div class="radio">
								<label> <form:radiobutton path="modulo.ativo_modulo"
										value="1" /> Sim
								</label>
							</div>
							<div class="radio">
								<label> <form:radiobutton path="modulo.ativo_modulo"
										value="0" /> Nâo
								</label><br>
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

			<form:form class="well form-horizontal" action="../modulos/formAula"
				modelAttribute="moduloModel" method="POST">
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<form:hidden path="modulo.id_modulo"
							value="${moduloModel.modulo.id_modulo}" />
						<button type="submit" class="btn btn-primary">Nova Aula</button>
					</div>
				</div>
			</form:form>

<%-- 			<form:form class="well form-horizontal"
				action="../modulos/deleteModulo" modelAttribute="aula" method="POST">
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<form:hidden path="id_aula" value="${aula.id_aula}" />
						<form:hidden path="id_modulo_aula" value="${aula.id_modulo_aula}" />
						<input type="submit" value="Excluir" class="btn btn-primary"
							onclick="if(!(confirm('Tem certeza que deseja excluir essa aula?')))return false">
					</div>
				</div>
			</form:form> --%>

		</div>
	</div>
	<div id="footer">
		<p style="display: block; margin: auto; padding: 10px;" align="center">Copyright
			© 2017 Hullo. Todos os direitos reservados.</p>
	</div>
</body>

</html>
