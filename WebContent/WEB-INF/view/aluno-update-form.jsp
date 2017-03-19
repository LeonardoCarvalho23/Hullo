<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
	<title>Editar aluno</title>
		<!--  Basic jquery and Bootstrap -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css" />
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
		<!--  Datepicker bootstrap plugin -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker3.min.css" />
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/locales/bootstrap-datepicker.pt-BR.min.js" charset="UTF-8"></script>
 <script>
$(function(){
    $('#data_nascimento_usuario').datepicker({
    	format: "dd/mm/yyyy",
    	//dateFormat: "mm/dd/yyyy", 
    	//altField: "#data_nascimento_usuario", 
    	//altFormat: "mm/dd/yyyy",
    	language: "pt-BR"
    });
});
</script> 
</head>
<body>
<input type="text" class="form-control">
	<div id="wrapper">
		<div id="header">
			<h2>Editar aluno</h2>
		</div>
	</div>
	
	<div id="container">
		
		<form:form action="updateAluno" modelAttribute="usuario" method="POST">
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
						<fmt:formatDate type="date" dateStyle="short" value="${usuario.data_nascimento_usuario}" var="dateString" pattern="dd/MM/yyyy" />
						 <td><form:input path="data_nascimento_usuario" value="${dateString}" class="form-control" /></td><!-- class="form-control" -->
						
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






