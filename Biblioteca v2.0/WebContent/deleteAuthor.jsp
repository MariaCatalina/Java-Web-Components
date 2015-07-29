
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ include file="pageJSP.jsp"%>

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
			<c:when test="${ConfirmDelete == true }">
				<em>The author was removed successfully!</em>
			</c:when>
		
			<c:when test="${ConfirmDelete == false }">
				<em>The author has a book assigned!</em>
			</c:when>
		</c:choose>
</div>
</body>
</html>