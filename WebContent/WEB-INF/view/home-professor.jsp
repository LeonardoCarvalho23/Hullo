<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home professor</title>
</head>
<body>
<h1>Perfil do Professor</h1>
<h1>Bem vindo ${usuario.nome_usuario}!</h1>
<h2>Aqui você encontrará todas as suas atividades.</h2>

<form:form action="../professor/showPerfilProfessor" modelAttribute="usuario" method="POST">
<form:hidden path="id_usuario"/>
<input type="submit" name="Perfil" value="Veja seu perfil">
</form:form>


</body>
</html>