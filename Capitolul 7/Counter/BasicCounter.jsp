<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simple Counter</title>

<%@ page import="foo.*" %>

</head>
<body>

<%! int doubleCount(){
		count = count * 2;
		return count;
	}
	%>
<%! count = 1; %>
The page double count is:
	<%= doubleCount() %>
</body>
</html>