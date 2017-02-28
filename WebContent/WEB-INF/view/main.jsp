
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>

<div id="wrapper">
		<div id="header">
			<h2>Main Page do Hullo</h2>		
		</div>
	</div>

	<!-- referencia ao CSS do spring -->
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css "/>

</head>

<body>
	<br><br>
			<!-- adicionar usuario -->
			<input type="button" value="Novo Usuario"
				onclick = "window.location.href='showFormNovoUsuario'; return false;"
				class="add-button"
			/> <!-- puxa layout do CSS -->
			<!-- ver lista de usuarios -->
			<!-- clicar no botao chama Controller mapping -->
			<input type="button" value="Listar Usuarios"
				onclick = "window.location.href='lista'; return false;"
				class="list-button"
			/> <!-- puxa layout do CSS -->
			
</body>

</html>