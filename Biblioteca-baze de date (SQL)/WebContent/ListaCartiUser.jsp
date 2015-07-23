<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%-- jsp-ul creeaza pagina cu tablelul unde sunt afișate datele despre carțile împrumutate --%>

<%@ include file="Header.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<br>
<form action="index" method="post">
	<input type="image" src="images/return1.jpg" width="100" height="67">
</form>

<center>
	<table style="width: 80%" border="2">
		<tr>

			<th>Autor</th>
			<th>Titlu</th>
			<th>Email client</th>
			<th>Data împrumutării</th>
		</tr>

		<c:forEach items="${listaImp}" var="user">

			<tr>
				<th>${user.book.autor}</th>
				<th>${user.book.titlu}</th>
				<th>${user.userEmail}</th>
				<th>${user.date}</th>

			</tr>
		</c:forEach>
	</table>
</center>
</body>
</html>