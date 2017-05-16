<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
<title>Detalhes Aula Realizada</title>

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
			<img class="logo"
				src="${pageContext.request.contextPath}/resources/images/logo200v2.png"
				width="100px">
		</div>
		<div id="container">
			<fieldset>
				<legend>Aula Realizada</legend>            
    			
										
					<div class="form-group">
						
						<p>Data Nome da aula: ${nomeAula}</p>						
						<p>Data Realização da aula: ${aulaRealizada.dt_inicio_aula_realizada}</p>
						<p>Escute sua aula:: ${aulaRealizada.url_gravacao_aula_realizada}</p>
						
						<audio controls>
  							<source src=" ${aulaRealizada.url_gravacao_aula_realizada}" type="audio/ogg">
 							<source src=" ${aulaRealizada.url_gravacao_aula_realizada}" type="audio/mpeg">
							
						</audio>
						
						
						
					</div>
					
		
				<!-- Botões -->
				<div class="form-group">
						<label class="col-md-4 control-label"></label>
						<div class="col-md-4">							
							<input type="button" class="btn btn-primary" value="Voltar"
								onclick="window.location.href='../aluno/showAulaAluno'; return false;" />
						</div>
						
				</div>
			</fieldset>.
		</div>
	</div>
	
	<div id="footer">
		<p style="display: block; margin: auto; padding: 10px;" align="center">Copyright
			© 2017 Hullo. Todos os direitos reservados.</p>
	</div>
</body>

</html>