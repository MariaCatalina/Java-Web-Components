<%@page import="services.ClientService"%>
<%@page import="servlets.ClientSetings"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
<head>
<title>Biblioteca online</title>
</head>
<body>

	<!-- textul din dreapta care apare pe fiecare pagina -->
	<br><br>
	<p style="text-align: right;line-height: 25px;">
	
		<% 
		String email = (String) request.getAttribute("email");
		
		services.ClientService cService = new ClientService();

		/* acceseaza vechile date din memorie */
		model.User user = cService.getUserData(email);
		%>
	
		<strong>Bine ai venit! <br>
			<%=user.getFirstName() %>  <%=user.getLastName() %>	<br>
			 Ești logat ca: <%= request.getAttribute("role")%>
			<br> Email: <%=request.getAttribute("email") %>

		</strong>
	</p>
	<br>
	<form action="logoutPage.jsp" method="get">
		<input type="submit" style="float: right; width: 10em;  height: 2em; text-align:center;
									border: 3px" value="Logout" />
	</form>

	

