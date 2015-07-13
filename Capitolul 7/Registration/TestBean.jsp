<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

 
<jsp:useBean id="person" type="foo.Employee" class="foo.Employee">
	<jsp:setProperty name="person" property="*" />
</jsp:useBean>

Person is: <jsp:getProperty property="name" name="person"/>
<br>
ID is: <jsp:getProperty property="empID" name="person"/>


<%-- Dog's name is: ${person.dog.name}
--%>
</body>
</html>