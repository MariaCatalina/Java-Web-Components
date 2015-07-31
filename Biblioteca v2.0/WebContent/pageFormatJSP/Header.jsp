<%@page import="services.ClientService"%>
<%@page import="servlets.ClientSetings"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

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
	<div style="position:relative; margin-right: 20px; text-align: right; line-height: 25px;">
	
	
		<strong>Bine ai venit! <br>
			
				<c:if test="${not empty userName.firstName}">
					${ userName.firstName} 
				</c:if>
				<c:if test="${not empty userName.lastName}">
					 	 ${userName.lastName} <br/>
				</c:if>

			 Ești logat ca: <%= request.getAttribute("role")%>
			<br> Email: <%=request.getAttribute("email") %>
		</strong>
	
	<br>
	<form action="pageFormatJSP/logoutPage.jsp" method="get">
		<input type="submit" style="float: right; width: 10em;  height: 2em; text-align:center;
									border: 3px" value="Logout" />
	</form>

	</div>

