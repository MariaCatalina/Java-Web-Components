<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ include file="/pageFormatJSP/pageJSP.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>


<%-- jsp-ul afișează listă de cărti imprumutate de catre user-ul curent --%>

<div
	style="position: relative; float: right; width: 900px; margin-right: 20px;">

<br/>
	<table width="100%" border="2">
		<tr>
			<th>Index</th>
			<th>Author</th>
			<th>Title</th>
			<th>Borrow Date</th>
			<th>* Action *</th>
		</tr>
	
		<%int count = 1; %>
		<c:forEach items="${listaCartiSpUser}" var="bBook">

			<tr>
				<th><%=count ++ %>
				<th>${bBook.book.autor.firstName} ${bBook.book.autor.lastName}</th>
				<th>${bBook.book.title}</th>
				<th>${bBook.date }</th>
				<th>
					<form action="${pageContext.request.contextPath}/ReturneazaCarte" method="get">
						<input type="hidden" name="bookIndexR" value="${bBook.id}" />
						<button type="submit" style=" width: 8em;  height: 1.5em; text-align:center;
									border: 2px;">Return</button>
					</form>
				</th>
			</tr>

		</c:forEach>
	</table>

</div>
</body>
</html>