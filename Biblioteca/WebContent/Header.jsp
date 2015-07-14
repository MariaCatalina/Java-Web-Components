<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Header</title>
</head>
<body>
	<!--  <center> <body background="images/header.jpg" /> </center> -->
	
<!-- textul din dreapta care apare pe fiecare pagina -->
	<p align="right"  />
	<strong>Bine ai venit! <br>
	
	<% if (request.isUserInRole("bibliotecar")) { %>
		Esti logat ca: Bibliotecar <br>
		Email: <%=request.getUserPrincipal().getName() %>
	
	<%} else { %>
		Esti logat ca: Client <br>
		Email: <%=request.getUserPrincipal().getName() %>
	
	<%} %>
	</strong>
	</p>

</body>
</html>