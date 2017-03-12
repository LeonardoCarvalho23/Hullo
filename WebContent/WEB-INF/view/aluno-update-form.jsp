<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Editar aluno</title>

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
			<h2>Editar aluno</h2>
		</div>
	</div>
	
	<div id="container">
		
		<form:form action="saveAluno" modelAttribute="usuario" method="POST">
			<!-- campo oculto com o id para ele não perder o contexto/id -->
			<form:hidden path="id_usuario" />
			<table>
				<tbody>
					<tr>
						<td><label>Nome:</label></td>
						<td><form:input path="nome_usuario" value="${usuario.nome_usuario}"/></td>
					</tr>
					
					<tr>
						<td><label>Sobrenome:</label></td>
						<td><form:input path="sobrenome_usuario" value="${usuario.sobrenome_usuario}"/></td>
					</tr>
					
					
					<tr>
						<td><label>CPF:</label></td>
						<td><form:input path="cpf_usuario" value="${usuario.cpf_usuario}"/></td>
					</tr>
										
					<tr>
						<td><label>Senha:</label></td>
						<td><form:input path="senha_usuario" value="${usuario.senha_usuario}"/></td>
					</tr>
					
					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email_usuario" value="${usuario.email_usuario}"/></td>
					</tr>	
									
					<tr>
						<td><label>Sexo:</label></td>
						<td><form:input path="sexo_usuario" value="${usuario.sexo_usuario}"/></td>
					</tr>
					
					<tr>
						<td><label>Data de Nascimento:</label></td>
						<td><form:input path="data_nascimento_usuario" value="${usuario.data_nascimento_usuario}"/></td>
					</tr>
					
					<tr>
						<td><label>Telefone:</label></td>
						<td><form:input path="telefone_usuario" value="${usuario.telefone_usuario}"/></td>
					</tr>
					
					<tr>
						<td><label>Profissao:</label></td>
						<td><form:input path="profissao_usuario" value="${usuario.profissao_usuario}"/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Atualizar" class="add-button" />
						
						<input type="button" value="Voltar"
							onclick = "window.history.go(-1); return false;"
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






