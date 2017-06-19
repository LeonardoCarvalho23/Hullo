<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Perfil Aluno</title>
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
		<div id="container" class="well form-horizontal"
			style="height: 600px; position: relative;">

			<h3>Perfil de aluno</h3>
                
                    <p>Nome: ${usuario.nome_usuario}</p>
                    <p>Sobrenome: ${usuario.sobrenome_usuario}</p>
                    <p>E-mail: ${usuario.email_usuario}</p>
                    <p>CPF: ${usuario.cpf_usuario}</p>
                    <p>Sexo: ${usuario.sexo_usuario}</p>
                    <fmt:formatDate value="${usuario.data_nascimento_usuario}"
                        var="dateString" pattern="dd/MM/yyyy" />
                    <p>Nascimento: ${dateString}</p>
                    <p>Telefone: ${usuario.telefone_usuario}</p>
                    <p>Profissão: ${usuario.profissao_usuario}</p>
                    <p>Estado: ${usuario.cidade.estado.nm_estado}</p>
                    <p>Cidade: ${usuario.cidade.nm_cidade}</p>
                    

                    <form:form action="../usuario/getUsuario" modelAttribute="usuario"
                        method="POST">
                        <form:hidden path="email_usuario" value="${usuario.email_usuario}" />
                        <form:hidden path="senha_usuario" value="${usuario.senha_usuario}" />
                        <input type="submit" class="btn btn-primary" style="float: left; margin: 10px;" name="Voltar" value="Voltar">
                    </form:form>

                    <form:form action="../aluno/showFormUpdateAluno" method="POST">
                        <input type="submit" class="btn btn-primary" style="float: left; margin: 10px;" name="Editar" value="Editar">
                    </form:form>

                    <form:form action="../aluno/inactivateAluno" method="POST">
                        <input type="submit" class="btn btn-primary" style="float: left; margin: 10px;" name="Inativar" value="Inativar"
                            onclick="if(!(confirm('Tem certeza que deseja inativar seu usuário?')))return false">
                    </form:form>

		</div>
		<div id="footer">
			<p style="display: block; margin: auto; padding: 10px;"
				align="center">Copyright © 2017 Hullo. Todos os direitos
				reservados.</p>

		</div>
	</div>
</body>

</html>