<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>



<%@ include file="Header.jsp"%>
<%@ page import="java.util.*"%>

<%-- jsp-ul creează pagina de adăugare a cărtilor --%>

<%-- butonul de return --%>
<form action="index" method="post">
	<input type="image" src="images/return1.jpg" width="90" height="55">
</form>

<br>

<center>

	<table border="2" style="width: 80%;">
		<tr>
			<th>Autor</th>
			<th>Titlu</th>
			<th>Număr total de exemplare</th>
			<th>Număr de exemplare împrumutate</th>
			<th>*Actiune*</th>
		</tr>

		<c:forEach items="${listaCompleta}" var="book">

			<tr>
				<th>${book.autor}</th>
				<th>${book.titlu}</th>
				<th><c:out value="${book.nrExemplare}" /></th>
				<th>${book.nrExemplareImprumutate}</th>
				<th>
					<form action="CarteStearsa" method="get">
						<input type="hidden" name="bookIndex" value="${book.index}" />
						<button type="submit">Șterge</button>
					</form>
			</tr>

		</c:forEach>

	</table>
</center>
</body>
</html>