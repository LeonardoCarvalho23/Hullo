<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Hullo</title>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css?v=22" />
		<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
	</head>
<body>
 
<script type="text/javascript">
    $(window).load(function(){
        $('#login-modal').modal('show');
    });
</script>

<div class="modal show" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	  <div class="modal-dialog">
    	  <img class="logo" src="${pageContext.request.contextPath}/resources/images/logo200v2.png" width="200px" >
    	  <p></p>
				<div class="loginmodal-container">
				<p>Digite seu e-mail.</p>
				  <form:form action="sendPassword" modelAttribute="usuario" method="POST">
					<form:input path="email_usuario" placeholder="E-mail"/>
					<input type="submit" name="submit" class="login loginmodal-submit" value="Enviar senha">
				  </form:form>
					
				  <div class="login-help">
					${errorMessage}
				  </div>  
				</div>
			</div>
		  </div>

</body>
</html>