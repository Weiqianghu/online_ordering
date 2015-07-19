<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="img/chi.ico"/>
<title>Insert title here</title>
</head>
<body>
<% 
session.removeAttribute("username");
session.removeAttribute("whichone");
session.removeAttribute("password");
session.removeAttribute("store_name");
session.invalidate();
Cookie cookies[] = request.getCookies();
for(int i=0;i<cookies.length;i++){
	cookies[i].setMaxAge(0);
	response.addCookie(cookies[i]);
}
response.sendRedirect("SelectStoreServlet");
%>
</body>
</html>