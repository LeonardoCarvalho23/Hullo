<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Novo Professor</title>

	<link type="text/css" 
		  rel="stylesheet" 
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css" 
		  rel="stylesheet" 
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Novo Profesor</h2>
		</div>
	</div>
	
	<div id="container">
		
		<form:form action="newProfessor" modelAttribute="usuario" method="POST">
					
					<label>*nome:</label><form:input path="nome_usuario" required="true" maxlength="45"/>
					
					<label>*Sobrenome:</label>
					<form:input path="sobrenome_usuario" required="true" maxlength="45"/>
					
					<label>*CPF:</label>
					<form:input path="cpf_usuario" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" 
						title="Digite um CPF no formato: xxx.xxx.xxx-xx" value="xxx.xxx.xxx-xx" required="true"/>
						
					<label>CNPJ:</label>
					<form:input path="cnpj_usuario" pattern="\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2}" 
						title="Digite um CNPJ no formato: xx.xxx.xxx/xxxx-xx" value="xx.xxx.xxx/xxxx-xx" required="false"/>
					
					<label>*Email:</label>
					<form:input type="email" path="email_usuario" required="true" maxlength="45"/>
					<div style = widht:40>
					<label>*Senha:</label>
					<form:input type="password" path="senha_usuario" required="true" maxlength="40"/>
					</div>
					<label>*Sexo:</label>
					Feminino <form:radiobutton path="sexo_usuario" value="F" required="true"/>
					Masculino <form:radiobutton path="sexo_usuario" value="M" />
					
					<label>*Data de Nascimento:</label>
					<form:input path="data_nascimento_usuario" pattern="\d{1,2}/\d{1,2}/\d{4}" 
						title="Digite uma data no formato: dd/mm/aaaa" value="dd/mm/aaaa" required="true"/>
					
					<label>*Telefone:</label>
					<form:input path="telefone_usuario" required="true" maxlength="20"/>
					
					<label>Profissao:</label>
					<form:input path="profissao_usuario" size="40"/>
					
					<label>CV:</label>			
					<textarea rows="2" cols="100">Anexe seu CV aqui</textarea>
					
					<input type="submit" value="Salvar" class="add-button" />
						
					<input type="button" value="Voltar"
							onclick = "window.location.href='../'; return false;"
							class="add-button"
							/>		
						
						
		</form:form>
		<br>
	</div>
</body>

</html>






