<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" import="com.cn.system.*"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Shop Index</title>
<link rel="shortcut icon" href="img/chi.ico"/>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/register.css" rel="stylesheet">
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<style>
#k{
width:800px;
margin:30px auto;
padding:100px 0;
background:#f7f7f7;
font-size:30px;
font-weight:500;

}
#k a{
font-size:15px;
padding-left:30px;
}
</style>
</head>
<body>
<%@include file="daohanglan.jsp" %>
<div id="k"><img src="img/404error.png" />您访问的页面不存在
<a href="SelectStoreServlet">返回首页</a></div>
</body>
</html>