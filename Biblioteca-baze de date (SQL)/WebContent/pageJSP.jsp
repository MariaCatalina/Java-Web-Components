<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file="Header.jsp" %>
	
	<%-- jsp-ul contine prima pagină --%>
	
	<% if( request.getAttribute("role").equals("bibliotecar")) { %>
	
		<br>
		<p style="text-indent:5em" >
		<strong><em> Meniu: </em></strong> 
		
		<ul>
		<li><a href="AddBooks.jsp">Adaugă carte noua</a></li>
		<li><a href="ListaCarti">Listează carțile disponibile</a></li>
		<li><a href="ListaCartiImprumutate">Listează carțile împrumutate</a></li>
		</ul>
	
	<%} else { %>
		<br>
		<p style="text-indent:5em" >
		
		<strong><em> Meniu: </em></strong> 
		
		<ul>
		<li><a href="ListaCartiUser">Listează carțile disponibile</a></li>
		<li><a href="ListaCartiImprumutateUser">Listează carțile împrumutate</a></li>
		</ul>
	 
	<%} %>
</body>
</html>