
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
       
</script>

	<div id="wrapper">
		<div id="header">
			<h2>Novo Aluno</h2>
		</div>
	</div>
	
	<div id="container">

					${errorMessage}

		<form:form action="newAluno" modelAttribute="usuario" method="POST" onsubmit="return checkPasswordMatch();">

					
					<label>*Nome:</label><form:input path="nome_usuario" required="true" maxlength="45"/>
					
					<label>*Sobrenome:</label>
					<form:input path="sobrenome_usuario" required="true" maxlength="45"/>
					
					<label>*CPF:</label>
					<form:input path="cpf_usuario" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" 
						title="Digite um CPF no formato: xxx.xxx.xxx-xx" placeholder="xxx.xxx.xxx-xx" required="true"/>
					
					<label>*Email:</label>
					<form:input type="email" path="email_usuario" required="true" maxlength="45"/>
					
					<label>*Senha:</label>
					<form:input type="password" path="senha_usuario" id = "txtNewPassword" required="true" maxlength="40"/>
					
					<label>*Confirme a Senha:</label>
					<input type="password" id = "txtConfirmPassword" required/>
					
					<label>*Sexo:</label>
					Feminino <form:radiobutton path="sexo_usuario" value="F" required="true"/>
					Masculino <form:radiobutton path="sexo_usuario" value="M" />
					
					<label>*Data de Nascimento:</label>
					<input type="text" id="datepicker" name="datepicker" placeholder="dd/mm/aaaa" required />
						<input type="hidden" id="data_nascimento_usuario" name="data_nascimento_usuario" />
					
					<label>*Telefone:</label>
					<form:input path="telefone_usuario" required="true" maxlength="20"/>
					
					<label>Profissão:</label>
					<form:input path="profissao_usuario" size="40"/>
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






