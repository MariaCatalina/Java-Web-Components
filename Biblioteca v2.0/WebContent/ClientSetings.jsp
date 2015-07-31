
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
	
	<em>Change your name: </em> <br><br>
	
	<form action="Setings" method="post">
		
		<input type="hidden" name="userID" value="${userData.id }">
		First Name: <input type="text" name="FirstNameC" value="${userData.firstName }"><br> 
		Last Name: <input type="text" name="LastNameC" value="${userData.lastName }"><br>

		<br>

		<button type="submit" style=" width: 8em;  height: 2em; text-align:center;
									border: 3px;">Save</button>
	</form>
</center>
</body>
</html>