
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
<title>Insert title here</title>
</head>
<body>

	<%@ include file="Header.jsp" %>
	<%@ page import="java.util.*" %>
	<%@ page import="java.io.*" %>

	
	<br>
	<form action="index" method="POST">
		<input type="image" src="images/return1.jpg"  width="100" height="67">
	</form>

	<center>
	<table style="width:80%" border="2">
  	<tr>
  		
    	<th>Autor</th>
    	<th>Titlu</th> 
    	<th>Data împrumutării</th>
  	</tr>
  	<c:forEach items="${listaCartiUser}" var="user">
  		
			<tr> 
				<th>${user.book.autor}</th> 
				<th>${user.book.titlu}</th>
				<th>${user.date }</th>	
				<th> 
					<form action="Returneaza.jsp" method="post"> 
						<input type="hidden" name="bookIndexR" value="${user.book.index }" />
						<button type="submit">Returnează </button> 
					</form>
				 </th>
			</tr>
	</c:forEach>
		</table>
	</center>
	
</body>
</html>