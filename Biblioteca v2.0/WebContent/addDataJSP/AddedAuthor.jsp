
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ include file="AddAuthor.jsp"%>

<%-- jsp-ul pria datele adăugate si actualizează baza de date --%>

<br>
<br>
<br>
<div style="text-align: center">

	<%
			String firstName = (String)request.getParameter("FirstName"); 
			String lastName = (String)request.getParameter("LastName"); 
	%>

		<c:choose>
			<c:when test="${ExistaAutor == 'exista' }">
				<em>The author already exist in the data base!</em>
			</c:when>
			
			<c:when test="${ExistaAutor == 'NUexista' }">
				<em>You have entered the author: <%=firstName %> <%=lastName %></em>
			</c:when>
			
			<c:when test="${ExistaAutor == 'dateInvalide' }">
				<em>Data entered wrong! Try again! </em>
			</c:when>
		</c:choose>
			
	

</div>
