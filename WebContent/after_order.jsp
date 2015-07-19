<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="img/chi.ico"/>
<link href="css/register.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<%@include file="daohanglan.jsp" %>
<div class="container-fluid" style="position:relative;top:30px;" align="center">
	<div class="row-fluid">
		<div class="span12">
			<div class="hero-unit" style="width:460px;background-color:#BEBEBE">
				<font size="30px" align="center">
				<b>
					订单已完成!
				</b>
				</font>
				<br><br>
				<p>
					等待商铺处理您的订单后，订单才会生效.
				</p>
				<p>
					感谢您使用 “吃了么” 希望我们的服务能让您满意O(∩_∩)O~~
				</p>
				<br>
				<p>
					<a class="btn btn-primary btn-large" href="SelectStoreServlet">返回首页 »</a>
				</p>
				<br>
			</div>
		</div>
	</div>
</div>
</body>
</html>