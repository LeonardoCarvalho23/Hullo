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
			dateFormat : 'dd/mm/yy',
			maxDate: new Date //funcao que impede datas futuras
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
	
	function checkFuture(data){
		
		var date = new Date(data.split('/').reverse().join('/'));
		var novaData = new Date();
		
		if(date > novaData){
	    	document.getElementById("datepicker").setCustomValidity('Data Inválida');
			document.getElementById("datepicker").style.color = "red";
			return false;
	    }
		document.getElementById("datepicker").setCustomValidity('');
		document.getElementById("datepicker").style.color = "black";
		return true;
		
	}
	
	//verifica se senhas digitadas sao iguais
	function checkPasswordMatch() {
	    var password = $("#txtNewPassword").val();
	    var confirmPassword = $("#txtConfirmPassword").val();

	    if (password != confirmPassword){
	    	alert ("Senhas não são iguais");
	    	return false;
	    }
	    return true
	}
	
	function validaCnpj(cnpj){
		cnpj = cnpj.replace(/[^\d]+/g,'');
		 
	    if(cnpj == '') return true;
	     
	    if (cnpj.length != 14){
	    	document.getElementById("cnpj").setCustomValidity('CNPJ Inválido');
			document.getElementById("cnpj").style.color = "red";
			return false;
	    }
	        
	 
	    // Elimina CNPJs invalidos conhecidos
	    if (cnpj == "00000000000000" || 
	        cnpj == "11111111111111" || 
	        cnpj == "22222222222222" || 
	        cnpj == "33333333333333" || 
	        cnpj == "44444444444444" || 
	        cnpj == "55555555555555" || 
	        cnpj == "66666666666666" || 
	        cnpj == "77777777777777" || 
	        cnpj == "88888888888888" || 
	        cnpj == "99999999999999"){
	    	document.getElementById("cnpj").setCustomValidity('CNPJ Inválido');
			document.getElementById("cnpj").style.color = "red";
	    	return false;
	    }
	        
	         
	    // Valida DVs
	    tamanho = cnpj.length - 2
	    numeros = cnpj.substring(0,tamanho);
	    digitos = cnpj.substring(tamanho);
	    soma = 0;
	    pos = tamanho - 7;
	    for (i = tamanho; i >= 1; i--) {
	      soma += numeros.charAt(tamanho - i) * pos--;
	      if (pos < 2)
	            pos = 9;
	    }
	    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	    if (resultado != digitos.charAt(0)){
	    	document.getElementById("cnpj").setCustomValidity('CNPJ Inválido');
			document.getElementById("cnpj").style.color = "red";
	    	return false;
	    }
	        
	         
	    tamanho = tamanho + 1;
	    numeros = cnpj.substring(0,tamanho);
	    soma = 0;
	    pos = tamanho - 7;
	    for (i = tamanho; i >= 1; i--) {
	      soma += numeros.charAt(tamanho - i) * pos--;
	      if (pos < 2)
	            pos = 9;
	    }
	    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	    if (resultado != digitos.charAt(1)){
	    	document.getElementById("cnpj").setCustomValidity('CNPJ Inválido');
			document.getElementById("cnpj").style.color = "red";
	    	return false;
	    }
	    else{
	    	document.getElementById("cnpj").setCustomValidity('');
	    	document.getElementById("cnpj").style.color = "black";
	    	return true;
	    }
	           
	    
	}
	
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
				action="updateProfessor" onsubmit="return checkPasswordMatch();">
				<fieldset>
					<!-- Nome do formulário -->
					<legend>Editar perfil de professor</legend>
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
					
					<!-- Campo CNPJ -->
					<div class="form-group">
				  		<label class="col-md-4 control-label">CNPJ</label>  
				  			<div class="col-md-4 inputGroupContainer">
				  				<div class="input-group">
				  				<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
				  				<form:input value="${usuario.cnpj_usuario}" class="form-control" path="cnpj_usuario" id = "cnpj" onblur="validaCnpj(this.value)" pattern="\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2}"
							 placeholder="xx.xxx.xxx/xxxx-xx" maxlength="18" size="18"
							  title="Digite um CNPJ válido no formato: xx.xxx.xxx/xxxx-xx" />
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
									placeholder="dd/mm/aaaa" value="${dateString}" onblur="checkFuture(this.value)" /> <input
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
									tittle="Número incluindo DDD, sem traços" pattern="[0-9]+$"
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
								onclick="window.location.href='../professor/showPerfilProfessor'; return false;" />
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