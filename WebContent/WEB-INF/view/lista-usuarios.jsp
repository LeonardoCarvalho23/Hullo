<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>Lista de Clientes</title>
</head>

<body>

	<div id="wrapper">
		<div id=""header>
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
				</tr>
				
				<!-- loop over and print usuarios -->
				<c:forEach var="tempUsuario" items="${usuarios}">
					
					<tr> 
						<td>${tempUsuario.nome_usuario} </td>
						<td> ${tempUsuario.sobrenome_usuario} </td>
						<td> ${tempUsuario.email_usuario} </td>
					</tr>
					
				</c:forEach>
					
			</table>
						
		</div>
	</div>


</body>

</html>