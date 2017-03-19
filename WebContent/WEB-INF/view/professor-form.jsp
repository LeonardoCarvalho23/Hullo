<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Novo Professor</title>

	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
	<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
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
			<h2>Novo Professor</h2>
		</div>
	</div>
	
	<div id="container">
	
		${errorMessage}
		
		<form:form action="newProfessor" modelAttribute="usuario" method="POST" onsubmit="return checkPasswordMatch();">
					
					<label>*Nome:</label><form:input path="nome_usuario" required="true" maxlength="45"/>
					
					<label>*Sobrenome:</label>
					<form:input path="sobrenome_usuario" required="true" maxlength="45"/>
					
					<label>*CPF:</label>
					<form:input path="cpf_usuario" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" 
						title="Digite um CPF no formato: xxx.xxx.xxx-xx" value="xxx.xxx.xxx-xx" required="true"/>
						
					<label>CNPJ:</label>
					<form:input path="cnpj_usuario" pattern="\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2}" 
						title="Digite um CNPJ no formato: xx.xxx.xxx/xxxx-xx" value="xx.xxx.xxx/xxxx-xx" />
					
					<label>*Email:</label>
					<form:input type="email" path="email_usuario" required="true" maxlength="45"/>
					<div style = widht:40>
					
					<label>*Senha:</label>
					<form:input type="password" path="senha_usuario" id = "txtNewPassword" required="true" maxlength="40"/>
					</div>
					<label>*Confirme a Senha:</label>
					<input type="password" id = "txtConfirmPassword" required/>
					
					<label>*Sexo:</label>
					Feminino <form:radiobutton path="sexo_usuario" value="F" required="true"/>
					Masculino <form:radiobutton path="sexo_usuario" value="M" />
					
					<label>*Data de Nascimento:</label>
					<form:input path="data_nascimento_usuario" pattern="\d{1,2}/\d{1,2}/\d{4}" 
						title="Digite uma data no formato: dd/mm/aaaa" value="dd/mm/aaaa" required="true"/>
					
					<label>*Telefone:</label>
					<form:input path="telefone_usuario" required="true" maxlength="20"/>
					
					<label>Profissão:</label>
					<form:input path="profissao_usuario" />
														
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






