<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@include file="pageJSP.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%-- jsp-ul afiseaza tabelul de cărți in ordinea introdusă, pentru user --%>


<div
	style="position: relative; float: right; width: 900px; margin-right: 20px;">
	
	<form action="ListaCartiUser" method="get">
		<input type="hidden" name="selSort" value="selDupaAutor" />
		<button type="submit" style=" width: 8em;  height: 2em; text-align:center;
				border: 3px;">Sort by author</button>

		<input type="checkbox" name="tipA" value="asc" >Crescator <input
			type="checkbox" name="tipA" value="desc">Descrescator
	</form>

	<form action="ListaCartiUser" method="get">
		<input type="hidden" name="selSort" value="selDupaTitlu" />
		<button type="submit" style=" width: 8em;  height: 2em; text-align:center;
					border: 3px;">Sort by title</button>

		<input type="checkbox" name="tipT" value="asc">Crescator <input
			type="checkbox" name="tipT" value="desc">Descrescator
	</form>

	<br>
	<br>

		<table width="100%" border="2">
			<tr>
				<th>Index</th>
				<th>Autor</th>
				<th>Titlu</th>
				<th>No of copies</th>
				<th>No of borrowed copies</th>
				<th>*Acțiune*</th>
			</tr>

			<%int count = 1; %>
			<c:forEach items="${listaCompleta}" var="book">
				<tr>
					<th><%=count ++ %> </th>
					<th>${book.autor.firstName}  ${book.autor.lastName}</th>
					<th>${book.titlu}</th>
					<th>${book.nrExemplare}</th>
					<th>${book.nrExemplareImprumutate}</th>
					<th>
						<form action="CarteImprumutata" method="get">
							<input type="hidden" name="bookIndexI" value="${book.index}" />
							<button type="submit" style=" width: 8em;  height: 1.5em; text-align:center;
									border: 2px;">Împrumută</button>
						</form>
					</th>
				</tr>
			</c:forEach>
		</table>

	
</div>
</body>
</html>