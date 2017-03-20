
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Novo Aluno</title>

	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
	<!--  Basic jquery and Bootstrap -->
		<script src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css" />
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
   		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script>		
$(function(){
    $('#datepicker').datepicker({ 
    	altField: '#data_nascimento_usuario', 
    	altFormat: 'mm/dd/yy',
    	dateFormat: 'dd/mm/yy'
    });
});
/* Portuguese initialisation for the jQuery UI date picker plugin. */
jQuery(function ($) {
    $.datepicker.regional['pt'] = {
        closeText: 'Fechar',
        prevText: '<Anterior',
        nextText: 'Seguinte',
        currentText: 'Hoje',
        monthNames: ['Janeiro', 'Fevereiro', 'Mar&ccedil;o', 'Abril', 'Maio', 'Junho',
        'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
        monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun',
        'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
        dayNames: ['Domingo', 'Segunda-feira', 'Ter&ccedil;a-feira', 'Quarta-feira', 'Quinta-feira', 'Sexta-feira', 'S&aacute;bado'],
        dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'S&aacute;b'],
        dayNamesMin: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'S&aacute;b'],
        weekHeader: 'Sem',
        dateFormat: 'dd/mm/yy',
        firstDay: 0,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ''
    };
    $.datepicker.setDefaults($.datepicker.regional['pt']);
});
</script> 
	
		  
		  <script src="${pageContext.request.contextPath}/resources/javascript/jquery-3.1.1.js"></script>
		  <script src="${pageContext.request.contextPath}/resources/javascript/cadastro.js"></script>
</head>

<body>

<script type="text/javascript">
function checkPasswordMatch() {
    var password = $("#txtNewPassword").val();
    var confirmPassword = $("#txtConfirmPassword").val();

    if (password != confirmPassword){
    	alert ("Senhas não são iguais");
    	return false;
    }
    return true
    
}

//Verifica se CPF é válido
function TestaCPF(strCPF) {
	strCPF = strCPF.replace(/[^\d]+/g,'');
	var Soma, Resto, borda_original;
	Soma = 0;
	
	
	if (strCPF == "00000000000"){
		document.getElementById("cpf").setCustomValidity('Invalid');
		alert("CPF INVALIDO");
		return false;
	}
	
	for (i=1; i<=9; i++){
		Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
	}
	
	Resto = (Soma * 10) % 11;
	if ((Resto == 10) || (Resto == 11)){
		Resto = 0;
	}
	
	if (Resto != parseInt(strCPF.substring(9, 10))){
		document.getElementById("cpf").setCustomValidity('Invalid');
		alert("CPF INVALIDO");
		return false;
	}
	
	Soma = 0;
	for (i = 1; i <= 10; i++){
		Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
	}
	
	Resto = (Soma * 10) % 11;
	if ((Resto == 10) || (Resto == 11)){
		Resto = 0;
	}
	
	if (Resto != parseInt(strCPF.substring(10, 11))){
		document.getElementById("cpf").setCustomValidity('Invalid');
		alert("CPF INVALIDO");
		return false;
	}
	
	document.getElementById("cpf").setCustomValidity('');
	alert("CPF VALIDO!");
	return true;
}
       
</script>

	<div id="wrapper">
		<div id="header">
			<h2>Novo Aluno</h2>
		</div>
	</div>
	
	<div id="container">

					${errorMessage}

		<form:form action="newAluno" modelAttribute="usuarioModel" method="POST">

					
					<label>*Nome:</label>
					<form:input path="usuario.nome_usuario" required="true" maxlength="45"/>
					
					
					<label>*Sobrenome:</label>
					<form:input path="usuario.sobrenome_usuario" required="true" maxlength="45"/>
					
					<label>*CPF:</label>
					<form:input path="usuario.cpf_usuario" id = "cpf" onblur="TestaCPF(this.value)" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"
					 placeholder="xxx.xxx.xxx-xx" maxlength="14" size="14" required="true"
					  title="Digite um CPF válido no formato: xxx.xxx.xxx-xx" />
					
					<!-- <label>*CPF:</label>
					<form:input path="cpf_usuario" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" 
						title="Digite um CPF no formato: xxx.xxx.xxx-xx" placeholder="xxx.xxx.xxx-xx" required="true"/> -->
					
					<label>*Email:</label>
					<form:input type="email" path="usuario.email_usuario" required="true" maxlength="45"/>
					
					<label>*Senha:</label>
					<form:input type="password" path="usuario.senha_usuario" required="true" maxlength="40"/>
					
					<label>*Confirme a Senha:</label>
					<input type="password" id = "txtConfirmPassword" required/>
					
					<label>*Sexo:</label>
					Feminino <form:radiobutton path="usuario.sexo_usuario" value="F" required="true"/>
					Masculino <form:radiobutton path="usuario.sexo_usuario" value="M" />
					
					
					<label>*Data de Nascimento:</label>
					<form:input path="usuario.data_nascimento_usuario" pattern="\d{1,2}/\d{1,2}/\d{4}" title="Digite uma data no formato: dd/mm/aaaa" value="dd/mm/aaaa" required="true"/>
						<input type="hidden" id="data_nascimento_usuario" name="data_nascimento_usuario" />
					
					<label>*Telefone:</label>
					<form:input path="usuario.telefone_usuario" required="true"/>
					
					<label>Profissao:</label>
					<form:input path="usuario.profissao_usuario" />
					
					<label>*Estado:</label>
					<form:select path="" id="estado" multiple="false">
					<td><form:options items="${usuarioModel.estado}" itemLabel="nm_estado"/><td>
					</form:select>
						
					<label>*Cidade:</label>
					<form:select path="cidade" id="cidade" multiple="false">
					</form:select>
						
						
					<br><br>
						<input type="submit" value="Salvar" class="add-button" />
					
						<input type="button" value="Voltar"
							onclick = "window.location.href='../'; return false;"
							class="add-button"
							/>		
						
					<br><br>
					*Campos de preenchimento obrigatório
		</form:form>
		<br>
	</div>
</body>

</html>






