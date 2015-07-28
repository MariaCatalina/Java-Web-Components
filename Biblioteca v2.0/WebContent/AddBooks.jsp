

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

	<%@ include file="pageJSP.jsp" %>
	<%@ page import="java.util.*" %>
<%@page import="model.Author"%>
	
	<%-- jsp-ul creează pagina de adăugare a cărtilor --%>	
<br><br>
	<div style="text-align:center" >
	<p class="center">
			
		<br><br><br>
		<%-- buton pentru a adauga o carte --%>

	<jsp:useBean id="AuthorsListAttribute" type="java.util.ArrayList<Author>" scope="request"></jsp:useBean>
			
			<jsp:getProperty property="*" name="AuthorsListAttribute"/>
			

		<form action="CarteAdaugata" method="get">
			Autor:<br>

			<c:forEach items="${AuthorsListAttribute}" var="var">
				
				${var.firstName} 
				
			</c:forEach>
	
		
			<button type="submit">adaugă 
			</button>
		</form>
	</div>
