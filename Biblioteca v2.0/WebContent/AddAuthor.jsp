<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="pageJSP.jsp"%>

<br>
<center>

	<form action="AddAuthor" method="get">
		First Name: <input type="text" name="FirstName" value="${author.firstName }"><br> 
		Last Name: <input type="text" name="LastName" value="${author.lastName }"><br>

		<br>

		<button type="submit">ADD</button>
	</form>
</center>
