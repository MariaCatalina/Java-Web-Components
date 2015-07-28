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
<br><br><br>
<center>

		<table border="2" style="width: 80%;">
			<tr>
				<th>Index</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>* Actiune *</th>
			</tr>
<%-- 
		<jsp:useBean id="AuthorsListAttribute" type= "java.util.ArrayList<model.Author>" scope="application"/>
			<jsp:setProperty name="AuthorsListAttribute" property = "*" />
			
			<jsp:getProperty property="*" name="AuthorsListAttribute"/>

	<jsp:useBean id="AuthorsListAttribute" type="java.util.ArrayList<model.Author>" scope="request"></jsp:useBean>
	<jsp:setProperty name="AuthorsListAttribute" property = "*" />
--%>
	
			<% int count = 1; %>
			<c:forEach items="${AuthorsListAttribute}" var="var">

				<tr>
					<th><%=count ++ %></th>
					<th>${var.firstName}</th>
					<th>${var.lastName }</th>
					<th>
						<form action="ActionButton" method="get">
							<select name="actiune">
							<option value="*" > Choose an option </option>
							<option value="sterge" >  Șterge </option>
							<option  value="modifica" > Modifica </option>
							
							</select>
							<input type="hidden" name="authorIndex" value="${var.index}" />
							<button type="submit">Submit</button>
						</form>
					</th>
				</tr>

			</c:forEach>

		</table>
</center>