<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

	<%@ include file="Header.jsp" %>
	<%@ page import="java.util.*" %>
	
	<%-- jsp-ul creează pagina de adăugare a cărtilor --%>	
	
	<%-- butonul de return --%>
	<form action="index" method="post" >
		<input type="image" src="images/return1.jpg"  width="90" height="55">
	</form>
	
	<div style="text-align:center" >
	<p class="center">
			
		<br><br><br>
		<%-- buton pentru a adauga o carte --%>
		
		<form action="CarteAdaugata" method="post">
			Autor: <input type="text" name="Autor"  value= "${book.autor }" >
			Titlu: <input type="text" name="Titlu" value="${book.titlu }"><br>
			
			<c:choose>
				<c:when test="${book.nrExemplare == '0' }" >
					Nr. Exemplare: <input type="text"  name="Numar" ><br>
				</c:when>
				<c:otherwise>
					Nr. Exemplare: <input type="text"  name="Numar" value="${book.nrExemplare }"><br>
				
				</c:otherwise>
			
			</c:choose>
			<button type="submit">adaugă 
			</button>
		</form>
	</div>
