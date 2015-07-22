<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!DOCTYPE HTML>
  
<html>
<head>
	<title>Biblioteca online</title>
</head>
<body>

	<!-- textul din dreapta care apare pe fiecare pagina -->
	
	<p style="text-align:right" >
	
		<strong>Bine ai venit! <br>
	
		Ești logat ca: <%= request.getAttribute("role")%> <br>
		Email: <%=request.getAttribute("email") %>
	
		</strong>
	</p>

