<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@include file="Header.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%-- jsp-ul afiseaza tabelul de cărți in ordinea introdusă, pentru user --%>


<br>
<form action="index" method="post">
	<input type="image" src="images/return1.jpg" width="100" height="67">
</form>

<form action="ListaCartiUser" method="get">
	<input type="hidden" name="selSort" value="selDupaAutor" />
	<button type="submit">Sortare după autor</button>

	<input type="checkbox" name="tipA" value="asc">Crescator <input
		type="checkbox" name="tipA" value="desc">Descrescator
</form>

<form action="ListaCartiUser" method="get">
	<input type="hidden" name="selSort" value="selDupaTitlu" />
	<button type="submit">Sortare după titlu</button>

	<input type="checkbox" name="tipT" value="asc">Crescator <input
		type="checkbox" name="tipT" value="desc">Descrescator
</form>

<center>
	<table style="width: 80%" border="2">
		<tr>
			<th>Autor</th>
			<th>Titlu</th>
			<th>Număr total de exemplare</th>
			<th>Număr de exemplare imprumutate</th>
			<th>*Acțiune*</th>
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
						<button type="submit">Împrumută</button>
					</form>
				</th>
			</tr>
		</c:forEach>
	</table>
</center>
</body>
</html>