<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>


<title>Biblioteca online</title>

	<%@include file="Header.jsp" %>
	<%@ page import="java.util.*" %>
	<%@ page import="java.io.*" %>
	
	<%-- jsp-ul afiseaza tabelul de cărți in ordinea introdusă, pentru user --%>
	

	<br>
	<form action="index" method="get">
		<input type="image" src="images/return1.jpg"  width="100" height="67">
	</form>
	
	<form action="ListaCartiUser" method="get"> 
		<input type="hidden" name="selSort" value="selDupaAutor" />
		<button type="submit">Sortare după autor</button> 
		
		<input type="hidden" name="tip" value="${tip }" />
		<button type="submit">Crescator</button>
		
		<input type="hidden" name="tipD" value="${tip }" />
		<button type="submit">Descrescator</button>
	</form>
	
	<form action="ListaCartiUser" method="get"> 
		<input type="hidden" name="selSort" value="selDupaTitlu" />
		<button type="submit">Sortare după titlu</button> 
	</form>
		
	<center>
		<table style="width:80%" border="2">
  	<tr>
    	<th>Autor</th>
    	<th>Titlu</th> 
    	<th>Număr total de exemplare</th>
    	<th>Număr de exemplare imprumutate</th>
    	<th>*Actiune*</th>
  	</tr>

		<c:forEach items="${listaCompleta}" var="book">
		<tr>
			<th>${book.autor}</th> 
			<th>${book.titlu}</th>
		 	<th>${book.nrExemplare}</th>
			<th>${book.nrExemplareImprumutate}</th> 	
			<th>
				<form action="CarteImprumutata" method="get"> 
					<input type="hidden" name="bookIndexI" value="${book.index}" />
					<button type="submit">Imprumuta </button> 
				</form>
			</th>
		</tr>
		</c:forEach>
		</table>
		</center>
</body>
</html>