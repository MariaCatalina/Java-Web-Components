<%@page import="model.MyBook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/pageFormatJSP/pageJSP.jsp"%>
		
	<%-- jsp-ul pria datele adăugate si actualizează baza de date --%>
	
		<br><br><br>
		<div style="text-align:center" >
			
		<c:choose>
			<c:when test="${bookVerification == 'corect' }">
				<em>You added a new book. You can see the list of books from menu! </em>
			</c:when>
			
			<c:when test="${bookVerification == 'incorect' }">
				<em>Data entered wrong! Try again! </em>
			</c:when>
	
		</c:choose>
		
			
		</div>
