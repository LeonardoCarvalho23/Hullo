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
		
		<form:form action="newAluno" modelAttribute="usuario" method="POST">
					
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
						<td><label>CPF:</label></td>
						<td><form:input path="cpf_usuario" /></td>
					</tr>
										
					<tr>
						<td><label>Senha:</label></td>
						<td><form:input path="senha_usuario" /></td>
					</tr>
					
					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email_usuario" /></td>
					</tr>	
									
					<tr>
						<td><label>Sexo:</label></td>
						<td><form:input path="sexo_usuario" /></td>
					</tr>
					
					<tr>
						<td><label>Data de Nascimento:</label></td>
						<td><form:input path="data_nascimento_usuario" /></td>
					</tr>
					
					<tr>
						<td><label>Telefone:</label></td>
						<td><form:input path="telefone_usuario" /></td>
					</tr>
					
					<tr>
						<td><label>Profissao:</label></td>
						<td><form:input path="profissao_usuario" /></td>
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






