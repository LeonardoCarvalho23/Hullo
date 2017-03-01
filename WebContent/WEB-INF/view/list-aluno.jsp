<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>Lista de Alunos</title>
	
	<!-- referencia ao CSS do spring -->
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css "/>
		
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Lista de usuarios</h2>		
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			
			<!-- add out html table here -->
			
			<table>
				<tr>
					<th>Nome</th>
					<th>Sobreome</th>
					<th>Email</th>
					<th>Data Cadastro</th>
				</tr>
				
				<!-- loop over and print usuarios -->
				<c:forEach var="tempUsuario" items="${usuarios}">
					
					<tr> 
						<td>${tempUsuario.nome_usuario} </td>
						<td> ${tempUsuario.sobrenome_usuario} </td>
						<td> ${tempUsuario.email_usuario} </td>
						<td> ${tempUsuario.dt_insert_usuario} </td>
					</tr>
					
				</c:forEach>
					
			</table>
			<br>
			
			<!-- clicar no botao chama Controller mapping -->
			<input type="button" value="Voltar"
				onclick = "window.location.href='main'; return false;"
				class="add-button"
			/> <!-- puxa layout do CSS -->
			
		</div>
	</div>


</body>

</html>