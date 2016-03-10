<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Возвращение диска</title>
</head>
<body>
<h1>Список дисков, которые необходимо вернуть</h1>
	<table style="width:100%; border: 1px solid black;">	
		<tr>
			<td style="background:#F08080">Название</td>
			<td style="background:#DAA520">Описание</td>
			<td style="background:#98FB98">Владелец</td>
			<td style="background:#66FFFF"></td>
		</tr>
		<c:if test="${not empty takenDiscs}">
		<c:forEach var="disc" items="${takenDiscs}">
			<tr>
				<td style="background:#F08080">${disc.title}</td>
				<td style="background:#DAA520">${disc.description}</td>
				<td style="background:#98FB98">${disc.user.name}</td>
				<td style="background:#66FFFF">
					<form action="returndisccompleted" method="POST">
				       <input type="hidden" name="id" value="${disc.id}"/>
				       <input type="submit" value="Вернуть диск"/>
				    </form>	
				</td>
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