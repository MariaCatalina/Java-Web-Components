<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Biblioteca online</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	
	<%-- jsp-ul contine prima pagină --%>
	
	<% if(request.isUserInRole("bibliotecar")) { %>
		<br>
		
		<p style="text-indent:5em" >
		<strong><em> Meniu: </em></strong> 
		
		<ul>
		<li><a href="AddBooks.jsp">Adauga carte noua</a></li>
		<li><a href="DeleteBook.jsp">Sterge carte</a></li>
		<li><a href="Liste.jsp">Listeaza cartile disponibile</a></li>
		<li><a href="ListaCartiUser.jsp">Listeaza cartile imprumutate</a></li>
		</ul>
	
		
	<%} else { %>
		<br>
		<p style="text-indent:5em" />
		
		<strong><em> Meniu: </em></strong> 
		
		<ul>
		<li><a href="Liste.jsp">Listeaza cartile disponibile</a></li>
		<li><a href="CartiImprumutate.jsp">Listeaza cartile imprumutate</a></li>
		</ul>
	 
	<%} %>
	
</body>
</html>