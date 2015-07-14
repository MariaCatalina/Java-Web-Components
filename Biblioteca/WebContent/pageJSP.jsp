<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Biblioteca online</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	
	
	<% if (request.isUserInRole("bibliotecar")) { %>
		
		<%-- buton pentru a adauga o carte --%>
		<form action="AddBooks.jsp" method="GET">
			Autor: <input type="text" name="Autor">
			Titlu: <input type="text" name="Titlu">
			<button type="submit" >adauga
			</button>
		</form>
		
	<%} else { %>
		sunt client 
	<%} %>
	
</body>
</html>