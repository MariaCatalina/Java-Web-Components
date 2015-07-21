<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Biblioteca online</title>
</head>


	<%@include file="Header.jsp"%>
	<%@ page import="java.util.*"%>
	<%@ page import="java.io.*"%>

	<%-- jsp-ul afișează listă de cărti imprumutate de catre useru-ul curent --%>
	
		<br>
	<form action="index" method="get">
		<input type="image" src="images/return1.jpg"  width="100" height="67">
	</form>

  	<center>
  	
	<table style="width:80%" border="2">
  	<tr>
    	<th>Autor</th>
    	<th>Titlu</th> 
    	<th>Data imprumutarii</th>
    	<th>*Actiune*</th>
  	</tr>
  	<c:forEach items="${listaCartiSpUser}" var="user">
	
		<tr> 
			<th>${user.book.autor}</th> 
			<th>${user.book.titlu}</th>
		 	<th>${user.date } </th> 
		 	<th>
				<form action="ReturneazaCarte" method="get"> 
					<input type="hidden" name="bookIndexR" value="${user.book.index}" />
					<button type="submit">Returneaza </button> 
				</form>
			</th>
		</tr>
		
	</c:forEach>
	</table>
	</center>
</body>
</html>