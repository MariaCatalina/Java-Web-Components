<%@page import="model.MyBook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file="AddBooks.jsp" %>
		
	<%-- jsp-ul pria datele adăugate si actualizează baza de date --%>
	
		<br><br><br>
		<div style="text-align:center" >
			
		<%
			String autor = (String)request.getParameter("Autor"); 
			String titlu = (String)request.getParameter("Titlu"); 
			String nrE = (String)request.getParameter("Numar");
			MyBook b = new MyBook();
		%>
	
		<%	/* actualizare bază de date */
			/* vefificare dacă datele nu sunt null */
			if(!autor.isEmpty() && !titlu.isEmpty() && !nrE.isEmpty() && b.verificaNrExemplare(nrE)){	
		%>	
				<em>Ai adăugat o carte nouă </em><br> <br>
				
				<em>Autor: <%=autor %> </em> <br>
				<em>Titlu: <%=titlu %> </em><br>
				
		<% } else if(autor.isEmpty() || titlu.isEmpty() || nrE.isEmpty()){ %>
			
				<em>Datele introduse nu sunt valide.<br> Încearcă din nou!</em>
		
		<% } else %>	
		
				<% if(!b.verificaNrExemplare(nrE)) { %>
					<em>Nu ai introdus un număr valid!<br> Încearcă din nou!</em>
				<% } %>
			
		</div>
