
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

<%@ include file="pageJSP.jsp"%>

<%-- jsp-ul afiseaza datele pentru a modifica o carte, autorul default este cel preluat din tabel --%>

<br/>
<br/>

<center>
	<em>Edit book: </em>
	<br/>	
	<form action="modifiedBook" method="get">
	
		<select name="chooseBook">
	
			<option value="${modifyBook.autor.index }">Choose an author</option>

			<c:forEach items="${listAuthors}" var="var">

				<option value="${var.index}">${var.firstName}
					${var.lastName}</option>

			</c:forEach>
			
		</select> <input type="hidden" name="bookIndexM" value="${modifyBook.index }">
		Title: <input type="text" name="TitleMod"
			value="${modifyBook.titlu }"><br> <br>

		<button type="submit" style=" width: 8em;  height: 1.5em; text-align:center;
									border: 2px;">Save</button>
	</form>
</center>