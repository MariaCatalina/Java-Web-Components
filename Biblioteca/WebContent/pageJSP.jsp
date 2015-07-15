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
		<br>
		<p style="text-indent:5em" >
		
		<strong> Meniu: </strong> 
		
		<ul>
		<li><a href="AddBooks.jsp">Adauga carte noua</a></li>
		<li><a href="DeleteBook.jsp">Sterge carte</a></li>
		<li><a href="Liste.jsp">Listeaza cartile disponibile</a></li>
		<li><a href="#">Listeaza cartile imprumutate</a></li>
		</ul>
		
		</p>
		
	
	<%} else { %>
		<br>
		<p style="text-indent:5em" >
		
		<strong> Meniu: </strong> 
		
		<ul>
		<li><a href="Liste.jsp">Listeaza cartile disponibile</a></li>
		<li><a href="#">Listeaza cartile imprumutate</a></li>
		</ul>
		
		</p>
	 
	<%} %>
	
</body>
</html>