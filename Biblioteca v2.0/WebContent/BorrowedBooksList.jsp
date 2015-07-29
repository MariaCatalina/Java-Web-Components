<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>



<%@ include file="pageJSP.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

	<%-- jsp-ul creeaza pagina cu tablelul unde sunt afișate datele despre carțile împrumutate --%>

<div style="position:relative; float: right; width: 890px; margin-right: 20px;">
		<table width="100%" border="2">
		<tr>
			<th>Index</th>
			<th>Author</th>
			<th>Title</th>
			<th>Client name</th>
			<th>Borrow date</th>
		</tr>
	
		<%int count = 1; %>
		<c:forEach items="${listaImp}" var="list">

			<tr>
				<th><%=count ++ %> </th>
				<th>${list.book.autor.firstName} ${list.book.autor.lastName}</th>
				<th>${list.book.titlu}</th>
				<th>${list.user.firstName} ${list.user.lastName}</th>
				<th>${list.date } </th>

			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>