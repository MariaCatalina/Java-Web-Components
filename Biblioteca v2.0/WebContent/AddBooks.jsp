
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ include file="pageJSP.jsp"%>
<%@ page import="java.util.*"%>
<%@page import="model.Author"%>

<%-- jsp-ul creează pagina de adăugare a cărtilor --%>
<br />

<div style="text-align: center; box-sizing: 2px; align: center">

	<%-- buton pentru a adauga o carte --%>

	<form action="CarteAdaugata" method="get">
		Autor: <select name="chooseAnAuthor">
			<option value="*">Choose an option</option>

			<c:forEach items="${listA}" var="var">

				<option value="${var.index}">${var.firstName}
					${var.lastName}</option>

			</c:forEach>

		</select> <br /> <br /> Title: <input type="text" name="Title"
			value="${ book.titlu}"><br />
		<br />

		<c:choose>
			<c:when test="${book.nrExemplare == '0' }">
					Number of copies: <input type="text" name="noOfCopies">
				<br />
			</c:when>
			<c:otherwise>
					Number of copies: <input type="text" name="noOfCopies"
					value="${book.nrExemplare }">
				<br />
			</c:otherwise>

		</c:choose>

		<br />
		<button type="submit" style="width: 8em;  height: 2em; text-align:center;
							border: 3px;">ADD</button>
	</form>
</div>
</body>
</html>