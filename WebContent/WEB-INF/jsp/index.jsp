<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Главная</title>

</head>
<body>
<div class="form-style-2">
	<div><h1>Система обмена дисками<h1></div>
	<div><h2>Здравствуйте, ${user}</h2></div>
	<div><a href="addnewdisc">Добавить диск</a></div>
	<div><a href="freediscs">Cписок свободных дисков (которые можно взять)</a></div>
	<div><a href="returndisc">Отдать диск</a></div>
	<div><a href="takendiscsbyuser">Cписок дисков, взятых мной</a></div>
	<div><a href="takendiscsfromuser">Список дисков, которые взяли у меня</a></div>
	<div><a href="logout">Выйти</a></div>
</div>
</body>
</html>