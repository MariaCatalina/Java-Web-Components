<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@ page import="java.util.*" %>
	<%@ page import="java.io.*" %>
	<%@ include file="AddBooks.jsp" %>
	
	<%
		ArrayList<Book> book = new ArrayList<Book>();
		String autor = (String)request.getParameter("Autor");
		String titlu = (String)request.getParameter("Titlu");
		
		Book b = new Book();
		b.setBook(autor, titlu, 5, 0);
		book.add(b);
	%>

	<%
		for(Book c: book)
			out.println(c.getBook());
	%>

</body>
</html>
