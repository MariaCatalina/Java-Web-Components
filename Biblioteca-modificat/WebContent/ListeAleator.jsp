<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Biblioteca online</title>
</head>
<body>

	<%@include file="Header.jsp" %>
	<%@ page import="java.util.*" %>
	<%@ page import="java.io.*" %>
	
	<%-- jsp-ul afiseaza tabelul de cărți in ordinea introdusă --%>
	

	<br>
	<form action="index" method="get">
		<input type="image" src="images/return1.jpg"  width="100" height="67">
	</form>

		<ul>
		<li><a href="ListeAutor.jsp">Afișare după autor</a></li>
		<li><a href="ListeTitlu.jsp">Afișare după titlu</a></li>
		</ul>

	<center>
		<table style="width:80%" border="2">
  	<tr>
    	<th>Autor</th>
    	<th>Titlu</th> 
    	<th>Număr total de exemplare</th>
    	<th>Număr de exemplare imprumutate</th>
    	<th>         </th>
  	</tr>

		<c:forEach items="${listaCompleta}" var="book">
		<tr>
			<th>${book.autor}</th> 
			<th>${book.titlu}</th>
		 	<th>${book.nrExemplare}</th>
			<th>${book.nrExemplareImprumutate}</th> 	
			<th>
				<form action="CarteImprumutata" method="get"> 
					<input type="hidden" name="bookIndexI" value="${book.index}" />
					<button type="submit">Imprumuta </button> 
				</form>
			</th>
		</tr>
		</c:forEach>
		</table>
		</center>
</body>
</html>