<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Nova Aula</title>
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

<!-- CK Editor -->
<script src="https://cdn.ckeditor.com/4.6.2/standard/ckeditor.js"></script>

</head>

<body>
	<div id="wrapper">
		<div id="header">
			<img class="logo"
				src="${pageContext.request.contextPath}/resources/images/logo200v2.png"
				width="100px">
		</div>
		<div id="container">

			<form:form class="well form-horizontal" action="newAula"
				modelAttribute="aula" method="post">
				<form:hidden path="id_modulo_aula" />

				<fieldset>
					${errorMessage}
					<!-- Nome do formulário -->
					<legend>Nova Aula</legend>

					<!-- Campo nome -->
					<div class="form-group">
						<label class="col-md-4 control-label">Nome</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<form:input placeholder="Nome" class="form-control"
									path="nm_aula" required="true" maxlength="40" />
							</div>
						</div>
					</div>

					<!-- Campo numero -->
					<div class="form-group">
						<label class="col-md-4 control-label">Número</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-th"></i></span>
								<form:input pattern="[1-5]{1}" placeholder="1"
									class="form-control" path="numero_aula" required="true"
									maxlength="1" title="Número de 1 a 5" />
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
								<form:input  placeholder="A" pattern="[A-Za-z]{1}" maxlength="1" title="Letra de A a Z"
									class="form-control" path="indice_aula" required="true"
									/>

							</div>
						</div>
					</div>
					
					<!-- Campo teaser -->
					<div class="form-group">
						<label class="col-md-4 control-label">Teaser</label>
						<div style="padding: 40px;">
							<form:textarea path="teaser_aula" required="true" />
							<!-- Script para carregar o editor -->
							<script>
								CKEDITOR.replace('teaser_aula');
							</script>
						</div>
					</div>

					<!-- Campo conteudo -->
					<div class="form-group">
						<label class="col-md-4 control-label">Conteúdo</label>
						<div style="padding: 40px;">
							<form:textarea path="conteudo_aula" required="true" />
							<!-- Script para carregar o editor -->
							<script>
								CKEDITOR.replace('conteudo_aula');
							</script>
						</div>
					</div>

					<!-- Campo atividade -->
					<div class="form-group">
						<label class="col-md-4 control-label">Atividade</label>
						<div style="padding: 40px;">
							<form:textarea path="atividade_aula" required="true" />
							<!-- Script para carregar o editor -->
							<script>
								CKEDITOR.replace('atividade_aula');
							</script>
						</div>
					</div>

					<!-- Campo revisao -->
					<div class="form-group">
						<label class="col-md-4 control-label">Revisão</label>
						<div style="padding: 40px;">
							<form:textarea path="revisao_aula" required="true" />
							<!-- Script para carregar o editor -->
							<script>
								CKEDITOR.replace('revisao_aula');
							</script>
						</div>
					</div>

					<!-- Botões -->
					<div class="form-group">
						<label class="col-md-4 control-label"></label>
						<div class="col-md-4">
							<button type="submit" class="btn btn-primary">Salvar</button>


							<input type="button" value="Voltar"
								onclick="window.history.go(-1); return false;"
								class="btn btn-primary" />

						</div>
					</div>



				</fieldset>
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
