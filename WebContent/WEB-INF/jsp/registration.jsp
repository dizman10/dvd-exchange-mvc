<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Регистрация</title>
</head>
<body>
	<div class="form-style-2">
	<div><h2>${message}</h2></div>
	<div><h2>${details}</h2></div>
	<div class="form-style-2-heading">Регистрация</div>
	<form action="registrationcompleted" method="post">
	<label for="field1"><span>Имя <span class="required">*</span></span><input type="text" class="input-field" name="name" value="" /></label>
	<label for="field2"><span>Пароль <span class="required">*</span></span><input type="password" class="input-field" name="password" value="" /></label>
	
	<label><span>&nbsp;</span><input type="submit" value="Зарегистрироваться" /></label>
	</form>
	<br/><br/>
	<form action="index" method="post">
		<input type="submit" value="На главную" />
	</form>
	</div>
</body>
</html>