<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home - Aluno</title>
</head>
<body>
<h1>Dados do aluno</h1>
<p>Nome: ${usuario.nome_usuario}</p>
<p>Sobrenome: ${usuario.sobrenome_usuario}</p>
<p>E-mail: ${usuario.email_usuario}</p>
<p>Senha: ${usuario.senha_usuario}</p>
<p>CPF: ${usuario.cpf_usuario}</p>
<p>Sexo: ${usuario.sexo_usuario}</p>
<p>Nascimento: ${usuario.data_nascimento_usuario}</p>
<p>Telefone: ${usuario.telefone_usuario}</p>
<p>Profiss�o: ${usuario.profissao_usuario}</p>
<form:form action="showFormUpdateAluno" modelAttribute="usuario" method="POST">
<form:hidden path="id_usuario"/>
<input type="submit" name="Editar" value="Editar">
</form:form>
</body>
</html>