<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/pageFormatJSP/pageJSP.jsp"%>

<br>
<center>

	<form action="${pageContext.request.contextPath}/AddAuthor" method="post">
		First Name: <input type="text" name="FirstName" value="${author.firstName }"><br> 
		Last Name: <input type="text" name="LastName" value="${author.lastName }"><br>
		<br/>

		<button type="submit" style=" width: 8em;  height: 2em; text-align:center;
						border: 3px;">ADD</button>
	</form>
</center>
