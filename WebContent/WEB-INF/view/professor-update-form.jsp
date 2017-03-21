<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
	<title>Editar professor</title>
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


<script type="text/javascript">

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
</script>

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Editar profesor</h2>
		</div>
	</div>
	
	<div id="container">
	${errorMessage}
		
		<form:form action="updateProfessor" modelAttribute="usuario" method="POST" onsubmit="return checkPasswordMatch();">
			<!-- campo oculto com o id para ele não perder o contexto/id -->
			<form:hidden path="id_usuario" />
					
						<p><label>Nome:</label>
						<form:input path="nome_usuario" value="${usuario.nome_usuario}"/></p>
						
						<p><label>Sobrenome:</label>
						<form:input path="sobrenome_usuario" value="${usuario.sobrenome_usuario}"/></p>
						
						<p><label>CPF:</label>
						<form:input path="cpf_usuario" value="${usuario.cpf_usuario}" disabled="true"/></p>

						<p><label>CNPJ:</label>
						<form:input path="cnpj_usuario" value="${usuario.cnpj_usuario}"/></p>
										
						<p><label>*Senha:</label>
						<form:input type="password" path="senha_usuario" value="${usuario.senha_usuario}" id = "txtNewPassword" required="true" maxlength="40"/></p>
					
						<p><label>*Confirme a Senha:</label>
						<input type="password" id = "txtConfirmPassword" value="${usuario.senha_usuario}" required/></p>
						
						<p><label>Email:</label>
						<form:input path="email_usuario" value="${usuario.email_usuario}"/></p>
				
						<p><label>Sexo:</label>
						Feminino <form:radiobutton path="sexo_usuario" value="F" required="true"/>
						Masculino <form:radiobutton path="sexo_usuario" value="M" /></p>
						
						<p><label>Data de Nascimento:</label>
						<fmt:formatDate value="${usuario.data_nascimento_usuario}" var="dateString" pattern="dd/MM/yyyy" />
						<fmt:formatDate value="${usuario.data_nascimento_usuario}" var="altDateString" pattern="MM/dd/yyyy" /></p>
						
						<p><input type="text" id="datepicker" name="datepicker" value="${dateString}" />
						 <input type="hidden" id="data_nascimento_usuario" name="data_nascimento_usuario" value="${altDateString}" /></p>
						
						<p><label>Telefone:</label>
						<form:input path="telefone_usuario" value="${usuario.telefone_usuario}"/></p>
						
						<p><label>Profissao:</label>
						<form:input path="profissao_usuario" value="${usuario.profissao_usuario}"/></p>
						
						<br>
						<label></label>
						<input type="submit" value="Atualizar" class="add-button" />
						
						<input type="button" value="Voltar"
							onclick = "window.history.go(-1); return false;"
							class="add-button"
							/>		
						<br>
						*Campos obrigatórios
			
		</form:form>
		<br>
	</div>
</body>

</html>






