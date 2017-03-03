<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>

<div id="wrapper" >
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
				onclick = "window.location.href='showFormNewUsuario'; return false;"
				class="add-button"
			/>
			
			<!-- ver lista de usuarios -->
			<input type="button" value="Listar Usuarios"
				onclick = "window.location.href='lista'; return false;"
				class="list-button"
			/> 
			
			<br><br>
			
			<!-- adicionar aluno -->
			<input type="button" value="Novo Aluno"
				onclick = "window.location.href='showFormNewAluno'; return false;"
				class="add-button"
			/>
			
			<!-- ver lista de alunos -->
			<input type="button" value="Listar Alunos"
				onclick = "window.location.href='listaAlunos'; return false;"
				class="list-button"
			/> 
			
			<br><br>
			
			<!-- adicionar professor -->
			<input type="button" value="Novo Professor"
				onclick = "window.location.href='showFormNewProfessor'; return false;"
				class="add-button"
			/>
			
			<!-- ver lista de professores -->
			<input type="button" value="Listar Professores"
				onclick = "window.location.href='listaProfessores'; return false;"
				class="list-button"
			/> 
			
</body>

</html>