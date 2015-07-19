<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%
		String store_ID = (String) request.getAttribute("store_ID");
	%>
	<%
		String store_name = (String) request.getAttribute("store_name");
	%>
	<%
		String store_state = (String) request.getAttribute("store_state");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%="评论一下" + " " + store_name%></title>
<link href="css/register.css" rel="stylesheet">
<link rel="shortcut icon" href="img/chi.ico"/>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
	<script type="text/javascript">
	$().ready(function(){
		$("img").error(function () {
		    $(this).unbind("error").attr("src", "img/touxiang.gif");
		});
	});
	</script>
	<style>
	.tabbable{
	min-height:500px;
	}
	</style>
</head>
<body style="overflow: -Scroll; overflow-x: hidden">
<a id="nav_top" onclick="pageScroll()" style="cursor: pointer; display: inline;border:1px solid #eef7fa;left:1200px" title="回到页头"><span>▲</span></a>
	<%@include file="daohanglan.jsp"%>
	<div class="container-fluid" id="shop_name">
		<div class="row-fluid">
			<div class="span12"
				style="position: relative; color: gold; text-align:center;">
				<h3>
					<%="欢迎光临" + "\t" + store_name%>
				</h3>
			</div>
		</div>
	</div>
	<div class="container-fluid" >
		<div class="row-fluid">
			<div class="span12">
				<div class="tabbable" id="tabs-648965">
					<ul class="nav nav-tabs"  style="width:1200px;margin:0 auto;" >
						<li><a href="#shangpin" data-toggle="tab"
							onclick="goto_shangpin('<%=store_ID%>','<%=store_name%>','<%=store_state%>')">商品</a></li>
						<li class="active"><a href="#pinglun" data-toggle="tab">评论</a></li>
					</ul>
					<c:forEach items="${comment_list}" var="sto">
					<div style="margin:0 auto;width:1200px;" class="tk">
					<li>
					<div><img src="data/usertouxiang/${sto.username}.jpg" style="width:80px;height:80px;"class="img"/></div>
					<div style="text-align:center;margin-top:5px;">${sto.username}</div>
					
					</li>
					<li style="color:#555;width:1000px;">
					<div style="background:#fff7f7;padding:8px">评分：${sto.score}</div>
					<div style="min-height:80px;margin:8px 5px;">内容：${sto.content}</div>
					<div style="font-size:13px;color:#888;margin-left:5px;">时间：${sto.comment_time}</div>
					</li>
					</div>
					</c:forEach>
					<style>
					.tk{
					border-bottom:1px solid #ccc;
					}
											.tk li{
											display:inline-block;
											vertical-align:top;padding:13px 5px;border:none;
											margin:0 20px;
											}
											.tk li .img{
											-moz-border-radius: 40px;      /* Gecko browsers */
                                            -webkit-border-radius: 40px;   /* Webkit browsers */
                                            border-radius:40px;            /* W3C syntax */
											}
					</style>
					<!-- temp -->
					
					<!-- jieshu -->
				</div>
			</div>
		</div>
	</div>
	
	<script>
		function goto_basket() {
			window.document.location.href = "ShowBasketServlet";
		}
		function goto_shangpin(store_ID, store_name, store_state) {
			window.document.location.href = "StoreFoodServlet?store_ID="
					+ store_ID + "&store_name="
					+ store_name + "&store_state="
					+ store_state;
		}
	</script>
</body>
</html>