<%@page import="model.Author"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<%-- jsp-ul creaza pagina cu lista de autori --%>

<%@ include file="/pageFormatJSP/pageJSP.jsp"%>

<div style="position:relative; float: right; width: 890px; margin-right: 20px;">
	<table width="100%" border="2">
	
			<tr>
				<th>Index</th>
				<th>Name</th>
				<th>* Action *</th>
			</tr>
	
			<% int count = 1; %>
			<c:forEach items="${AuthorsListAttribute}" var="var">

				<tr>
					<th><%=count ++ %></th>
					<th>${var.firstName} ${var.lastName }</th>
					<th>
						<form action="ActionButton" method="post">
							<select name="actiune">
							<option value="*" > Choose an option </option>
							<option value="sterge" > Delete </option>
							<option  value="modifica" > Change </option>
							
							</select>
							<input type="hidden" name="authorIndex" value="${var.id}" />
							<button type="submit" style=" width: 6em;  height: 1.5em; text-align:center;
									border: 2px;">Submit</button>
						</form>
					</th>
				</tr>

			</c:forEach>

		</table>
</div>
</body>
</html>