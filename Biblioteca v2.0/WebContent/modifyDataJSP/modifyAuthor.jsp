
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<%@ include file="/pageFormatJSP/pageJSP.jsp"%>

	
<br>
<center>

	<form action="modifyedAuthor" method="get">
		
		<input type="hidden" name="authorIndexM" value="${modifyAuthor.id }">
		First Name: <input type="text" name="FirstNameM" value="${modifyAuthor.firstName }"><br> 
		Last Name: <input type="text" name="LastNameM" value="${modifyAuthor.lastName }"><br>

		<br>

		<button type="submit">Save</button>
	</form>
</center>
</body>
</html>