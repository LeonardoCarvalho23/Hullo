<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perfil do professor</title>
</head>
<body>
<h1>Dados do professor</h1>
<p>Nome: ${usuario.nome_usuario}</p>
<p>Sobrenome: ${usuario.sobrenome_usuario}</p>
<p>E-mail: ${usuario.email_usuario}</p>
<p>Senha: ${usuario.senha_usuario}</p>
<p>CPF: ${usuario.cpf_usuario}</p>
<p>CNPJ: ${usuario.cnpj_usuario}</p>
<p>Sexo: ${usuario.sexo_usuario}</p>
<p>Nascimento: ${usuario.data_nascimento_usuario}</p>
<p>Telefone: ${usuario.telefone_usuario}</p>
<p>Profissão: ${usuario.profissao_usuario}</p>




</body>
</html>