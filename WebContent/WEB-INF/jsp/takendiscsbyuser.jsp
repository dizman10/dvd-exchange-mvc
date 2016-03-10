<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cписок дисков, которые вы взяли</title>
</head>
<body>
	<h1>Cписок дисков, которые вы взяли:</h1>
	<h2>${message}</h2>
	<table style="width:100%; border: 1px solid black;">	
		<tr>
			<td style="background:#F08080">Название</td>
			<td style="background:#DAA520">Описание</td>
			<td style="background:#98FB98">Владелец</td>
		</tr>
		<c:if test="${not empty takenDiscs}">
		<c:forEach var="disc" items="${takenDiscs}">
			<tr>
				<td style="background:#F08080">${disc.title}</td>
				<td style="background:#DAA520">${disc.description}</td>
				<td style="background:#98FB98">${disc.user.name}</td>
			</tr>
		</c:forEach>
		</c:if>
	</table>
	<br/><br/>
	<form action="index" method="post">
		<input type="submit" value="На главную" />
	</form>
</body>
</html>