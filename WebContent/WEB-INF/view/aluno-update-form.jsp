<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Editar perfil</title>
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
<script>
	$(function() {
		$('#datepicker').datepicker({
			altField : '#data_nascimento_usuario',
			altFormat : 'mm/dd/yy',
			dateFormat : 'dd/mm/yy'
		});
	});
	/* Portuguese initialisation for the jQuery UI date picker plugin. */
	jQuery(function($) {
		$.datepicker.regional['pt'] = {
			closeText : 'Fechar',
			prevText : '<Anterior',
			nextText : 'Seguinte',
			currentText : 'Hoje',
			monthNames : [ 'Janeiro', 'Fevereiro', 'Mar&ccedil;o', 'Abril',
					'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro',
					'Novembro', 'Dezembro' ],
			monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun',
					'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
			dayNames : [ 'Domingo', 'Segunda-feira', 'Ter&ccedil;a-feira',
					'Quarta-feira', 'Quinta-feira', 'Sexta-feira',
					'S&aacute;bado' ],
			dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex',
					'S&aacute;b' ],
			dayNamesMin : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex',
					'S&aacute;b' ],
			weekHeader : 'Sem',
			dateFormat : 'dd/mm/yy',
			firstDay : 0,
			isRTL : false,
			showMonthAfterYear : false,
			yearSuffix : ''
		};
		$.datepicker.setDefaults($.datepicker.regional['pt']);
	});
</script>

</head>

<body>
	<div id="wrapper">
		<div id="header">
			<img class="logo"
				src="${pageContext.request.contextPath}/resources/images/logo200v2.png"
				width="100px">
		</div>
		<div id="container">


			<form:form class="well form-horizontal" modelAttribute="usuario"
				action="updateAluno" onsubmit="return checkPasswordMatch();">
				<fieldset>
					<!-- Nome do formulário -->
					<legend>Editar perfil de aluno</legend>
					${errorMessage}
					<!-- Campo nome -->
					<div class="form-group">
						<label class="col-md-4 control-label">Nome</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<form:input placeholder="Nome" class="form-control"
									path="nome_usuario" maxlength="45"
									value="${usuario.nome_usuario}" />
							</div>
						</div>
					</div>

					<!-- Campo Sobrenome -->
					<div class="form-group">
						<label class="col-md-4 control-label">Sobrenome</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<form:input placeholder="Sobrenome" class="form-control"
									path="sobrenome_usuario" required="true" maxlength="45"
									value="${usuario.sobrenome_usuario}" />
							</div>
						</div>
					</div>

					<!-- Campo email -->
					<div class="form-group">
						<label class="col-md-4 control-label">E-mail</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span>
								<form:input placeholder="E-mail" class="form-control"
									type="email" path="email_usuario" required="true"
									maxlength="45" value="${usuario.email_usuario}" />
							</div>
						</div>
					</div>

					<!-- Campo senha -->
					<div class="form-group">
						<label class="col-md-4 control-label">Senha</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-asterisk"></i></span>
								<form:input placeholder="Senha" class="form-control"
									type="password" path="senha_usuario" id="txtNewPassword"
									required="true" maxlength="40" value="${usuario.senha_usuario}" />
							</div>
						</div>
					</div>

					<!-- Campo confirmação senha -->
					<div class="form-group">
						<label class="col-md-4 control-label">Confirme sua senha</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-asterisk"></i></span> <input
									placeholder="Confirme sua senha" class="form-control"
									type="password" id="txtConfirmPassword" required
									value="${usuario.senha_usuario}" />
							</div>
						</div>
					</div>

					<!-- Campo sexo -->
					<div class="form-group">
						<label class="col-md-4 control-label">Sexo</label>
						<div class="col-md-4">
							<div class="radio">
								<label> <form:radiobutton path="sexo_usuario" value="F" />
									Feminino
								</label>
							</div>
							<div class="radio">
								<label> <form:radiobutton path="sexo_usuario" value="M" />
									Masculino
								</label>
							</div>
						</div>
					</div>

					<!-- Campo Data nascimento -->
					<div class="form-group">
						<label class="col-md-4 control-label">Data de nascimento</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-calendar"></i></span>
								<fmt:formatDate value="${usuario.data_nascimento_usuario}"
									var="dateString" pattern="dd/MM/yyyy" />
								<fmt:formatDate value="${usuario.data_nascimento_usuario}"
									var="altDateString" pattern="MM/dd/yyyy" />
								<input class="form-control" id="datepicker" name="datepicker"
									placeholder="dd/mm/aaaa" value="${dateString}" /> <input
									type="hidden" id="data_nascimento_usuario"
									name="data_nascimento_usuario" value="${altDateString}" />

							</div>
						</div>
					</div>


					<!-- Campo Telefone -->
					<div class="form-group">
						<label class="col-md-4 control-label">Telefone</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-earphone"></i></span>
								<form:input class="form-control" path="telefone_usuario"
									required="true" placeholder="DDDXXXXXXXX" maxlength="11"
									tittle="Número incluindo DDD, sem traços"
									value="${usuario.telefone_usuario}" />
							</div>
						</div>
					</div>

					<!-- Campo Profissão -->
					<div class="form-group">
						<label class="col-md-4 control-label">Profissão</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-briefcase"></i></span>
								<form:input class="form-control" placeholder="Profissão"
									path="profissao_usuario" maxlength="40"
									value="${usuario.profissao_usuario}" />
							</div>
						</div>
					</div>



					<!-- Botões -->
					
					<div class="form-group">
						<label class="col-md-4 control-label"></label>
						<div class="col-md-4">
							<button type="submit" class="btn btn-primary">Salvar</button>
							<input type="button" class="btn btn-primary" value="Voltar"
								onclick="window.location.href='../aluno/showPerfilAluno'; return false;" />
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