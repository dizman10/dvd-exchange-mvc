<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Добавление диска</title>
</head>
<body>
	<div><h2>${message}</h2></div>
	<div>${details}</div>
	<div>
	<div>Добавление нового диска</div>
	<form action="addnewdisccompeted" method="post">
	<label for="field1"><span>Название </span><input type="text" name="title" value="" /></label>
	<label for="field2"><span>Описание </span><input type="text" name="description" value="" /></label>
	
	<label><span>&nbsp;</span><input type="submit" value="Добавить" /></label>
	</form>
	<br/><br/>
	<form action="index" method="post">
		<input type="submit" value="На главную" />
	</form>
	</div>
</body>
</html>