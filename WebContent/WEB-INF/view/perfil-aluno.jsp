<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
	<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<title>Perfil do Aluno</title>
</head>
<body>

<h1>Dados do aluno </h1>
<p>Nome: ${usuario.nome_usuario}</p>
<p>Sobrenome: ${usuario.sobrenome_usuario}</p>
<p>E-mail: ${usuario.email_usuario}</p>
<p>Senha: ${usuario.senha_usuario}</p>
<p>CPF: ${usuario.cpf_usuario}</p>
<p>Sexo: ${usuario.sexo_usuario}</p>
<fmt:formatDate value="${usuario.data_nascimento_usuario}" var="dateString" pattern="dd/MM/yyyy" />
<p>Nascimento: ${dateString}</p>
<p>Telefone: ${usuario.telefone_usuario}</p>
<p>Profissão: ${usuario.profissao_usuario} </p>

<form:form action="../aluno/showFormUpdateAluno" method="POST">
<input type="submit" name="Editar" value="Editar">

</form:form>

<form:form action="../aluno/inactivateAluno" method="POST">
<input type="submit" name="Inativar" value="Inativar" onclick="if(!(confirm('Tem certeza que deseja inativar seu usuário?')))return false">

</form:form>

</body>
</html>

