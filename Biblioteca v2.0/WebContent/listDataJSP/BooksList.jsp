<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>



<%@ include file="/pageFormatJSP/pageJSP.jsp"%>
<%@ page import="java.util.*"%>

<%-- jsp-ul afiseaza lista de carti pentru admin --%>

<div style="position:relative; float: right; width: 905px; margin-right: 20px;">
		<table width="100%" border="2">
		<tr>	
			<th>Index </th>
			<th>Author</th>
			<th>Title</th>
			<th>No of copies</th>
			<th>No of borrowed copies</th>
			<th>* Action *</th>
		</tr>
	
		<% int count = 1; %>
		<c:forEach items="${listaCompleta}" var="book">

			<tr>	
				<th><%= count ++ %> </th>
				<th>${book.autor.firstName} ${book.autor.lastName }</th>
				<th>${book.title}</th>
				<th><c:out value="${book.noCopies}" /></th>
				<th>${book.noBorrowedCopies}</th>
				<th>
					<form action="${pageContext.request.contextPath}/ActionButtonBook" method="GET">
							
							<select name="actionBook">
							<option value="*" > Choose an option </option>
							<option value="sterge" >  Delete </option>
							<option  value="modifica" > Change </option>
							
							</select>
							<input type="hidden" name="bookId" value="${book.id}" />
							<button type="submit" style=" width: 8em;  height: 1.5em; text-align:center;
									border: 2px;">Submit</button>
						</form>
					</th>
			</tr>

		</c:forEach>

	</table>
</div>
</body>
</html>