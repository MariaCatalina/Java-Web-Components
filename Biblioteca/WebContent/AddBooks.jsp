<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="Header.jsp" %>
	
	<%@ page import="java.util.*" %>
	
	<center>
	<p class="center">
		Ai adaugat o carte noua <br> <br>
		
		Autor: <%=(String)request.getParameter("Autor") %> <br>
		Titlu: <%=(String)request.getParameter("Titlu") %> <br>
		
		
	<form name="form" method="GET" >
		<input type="submit" value="return" formaction="index">
	</form>
		
	</p>
	</center>
</body>
</html>