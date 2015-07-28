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
	
<br>

<center>
	<table style="width: 80%" border="2">
		<tr>

			<th>Autor</th>
			<th>Titlu</th>
			<th>Nume client</th>
			<th>Data împrumutării</th>
		</tr>

		<c:forEach items="${listaImp}" var="list">

			<tr>
				<th>${list.book.autor.firstName} ${list.book.autor.lastName}</th>
				<th>${list.book.titlu}</th>
				<th>${list.user.firstName} ${list.user.lastName}</th>
				<th>      </th>

			</tr>
		</c:forEach>
	</table>
</center>
</body>
</html>