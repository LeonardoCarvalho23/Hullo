<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>Lista de M�dulos</title>
	
	<!-- referencia ao CSS do spring -->
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css "/>
		
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Lista de M�dulos</h2>		
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			
			<!-- add out html table here -->
			
			<table>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Indice</th>
					<th>Ativo</th>
					<th>Data Cria��o</th>
					<th>Data �ltima Atualiza��o</th>
				</tr>
				
				<!-- loop over and print usuarios -->
				<c:forEach var="tempModulo" items="${modulos}">
					
					<tr>
						<td>${tempModulo.id_modulo} </td> 
						<td>${tempModulo.nm_modulo} </td>
						<td>${tempModulo.indice_modulo} </td>
						<td>${tempModulo.ativo_modulo} </td>
						<td>${tempModulo.dt_insert_modulo} </td>
						<td>${tempModulo.dt_last_update_modulo} </td>
					</tr>
					
				</c:forEach>
					
			</table>
			<br>
			
			<!-- clicar no botao chama Controller mapping -->
			<input type="button" value="Voltar"
				onclick = "window.location.href='../main'; return false;"
				class="add-button"
			/> <!-- puxa layout do CSS -->
			
		</div>
	</div>


</body>

</html>