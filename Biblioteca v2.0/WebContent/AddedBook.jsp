<%@page import="model.MyBook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file="AddBooks.jsp" %>
		
	<%-- jsp-ul pria datele adăugate si actualizează baza de date --%>
	
		<br><br><br>
		<div style="text-align:center" >
			
		<%
			String autor = (String)request.getParameter("Autor"); 
			String titlu = (String)request.getParameter("LastName"); 

			MyBook b = new MyBook();
		%>
	
			
		</div>
