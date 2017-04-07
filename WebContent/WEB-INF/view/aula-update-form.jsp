<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
<title>Editar Aula</title>
<title>Editar Aula</title>
<!--  Basic jquery and Bootstrap -->
<script src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/javascript/cadastro.js"></script>
<!-- CSS Custom -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css?v=29" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Editar aula</h2>
		</div>
	</div>
	
	<div id="container">
	${errorMessage}
		
		<form:form action="updateAula" modelAttribute="aula" method="POST" >
					
						<p><label>Nome:</label>
						<form:input path="nm_aula" value="${aula.nm_aula}" maxlength="45" required="true"/></p>
						
						<p><label>Número:</label>
						<form:input path="numero_aula" value="${aula.numero_aula}" type="number" min="1" max="5" 
						required="true" placeholder="1"/></p>
						
						<p><label>Índice:</label>
						<form:input path="indice_aula" value="${aula.indice_aula}" required="true"/></p>

						<p><label>Revisão:</label>
						<form:input path="revisao_aula" value="${aula.revisao_aula}" 
						required="true" maxlength="5"/></p>
						
						<p><label>Conteúdo:</label>
						<form:input path="conteudo_aula" value="${aula.conteudo_aula}" required="true" maxlength="5" /></p>
										
									
						
						<label></label>
						<input type="submit" value="Salvar" class="add-button" />
						
						<input type="button" value="Voltar"
							onclick = "window.history.go(-1); return false;"
							class="add-button"
							/>		
						<br>
						*Campos obrigatórios
			
		</form:form> 
		<br>
	</div>
</body>

</html>






