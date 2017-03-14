<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>

<div id="wrapper" >
		<div id="header">
			<h2>Main Page do ADM do Hullo</h2>		
		</div>
	</div>

	<!-- referencia ao CSS do spring -->
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css "/>

</head>

<body>
	<br><br>
			<!-- ver lista de usuarios -->
			<input type="button" value="Listar Usuarios"
				onclick = "window.location.href='../adm/listaUsuarios'; return false;"
				class="add-button"
			/> 
			
			<br><br>
			
			
			<!-- ver lista de alunos -->
			<input type="button" value="Listar Alunos"
				onclick = "window.location.href='../adm/listaAlunos'; return false;"
				class="add-button"
			/> 
			
			<br><br>
			
			<!-- ver lista de professores -->
			<input type="button" value="Listar Professores"
				onclick = "window.location.href='../adm/listaProfessores'; return false;"
				class="add-button"
			/> 
			
</body>

</html>