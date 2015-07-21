<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>Header</title>
	
	<!-- textul din dreapta care apare pe fiecare pagina -->
	
	<p align="right" >
	<strong>Bine ai venit! <br>
	
		Esti logat ca: <%= request.getAttribute("role")%> <br>
		Email: <%=request.getAttribute("email") %>
	
	</strong>
</p>
