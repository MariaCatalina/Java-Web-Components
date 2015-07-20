<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Biblioteca online</title>
</head>
<body>

	<%@ include file="AddBooks.jsp" %>
		
	<%-- jsp-ul pria datele adăugate si actualizează baza de date --%>
	
		<br><br><br>
		<center>
			
		<%
			String autor = (String)request.getParameter("Autor"); 
			String titlu = (String)request.getParameter("Titlu"); 
			String nrE = (String)request.getParameter("Numar");
		%>
	
		<%	/* actualizare bază de date */
			gestiune.MyBook b = new gestiune.MyBook();
			int nrExemplare;	
			
			/* vefificare dacă datele nu sunt null */
			if(!autor.isEmpty() && !titlu.isEmpty() && !nrE.isEmpty()){	
				nrExemplare = Integer.parseInt(nrE);
				b.setBook(autor,titlu,nrExemplare,0);
				((gestiune.Gestiune) application.getAttribute("gestiune")).addB(b); 
		%>	
				<em>Ai adăugat o carte nouă </em><br> <br>
				
				<em>Autor: <%=autor %> </em> <br>
				<em>Titlu: <%=titlu %> </em><br>
				
		<% } else { %>
				<em>Datele introduse nu sunt valide.<br> Încearcă din nou!</em>
		<% } %>	
			
		</center>
</body>
</html>