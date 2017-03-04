<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Novo Aluno</title>

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
			<h2>Novo Aluno</h2>
		</div>
	</div>
	
	<div id="container">
		
		<form:form action="newUsuario" modelAttribute="usuario" method="POST">
					
			<table>
				<tbody>
					<tr>
						<td><label>Nome:</label></td>
						<td><form:input path="nome_usuario" /></td>
					</tr>
					
					<tr>
						<td><label>Sobrenome:</label></td>
						<td><form:input path="sobrenome_usuario" /></td>
					</tr>
					
					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email_usuario" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Salvar" class="add-button" />
						
						<input type="button" value="Voltar"
							onclick = "window.location.href='../'; return false;"
							class="add-button"
							/>		
						
						</td>
											
						
					</tr>
					
				</tbody>
			</table>
		</form:form>
		<br>
	</div>
</body>

</html>






