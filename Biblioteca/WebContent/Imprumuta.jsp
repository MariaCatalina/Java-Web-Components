<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@include file="Header.jsp" %>
	<%@ page import="java.util.*" %>
	<%@ page import="java.io.*" %>

	<br>
	<form name="form" method="POST" >
		<input type="image" src="images/return1.jpg"  width="100" height="67"  formaction="Liste.jsp">
	</form>
	<center>
		Ai imprumutat cartea : 
		<%= request.getParameter("bookAutor") %>
	
	</center>
</body>
</html>