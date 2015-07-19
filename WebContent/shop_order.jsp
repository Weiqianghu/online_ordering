<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="img/chi.ico"/>
<title>Insert title here</title>
<link href="css/register.css" rel="stylesheet">
<link
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.2/css/bootstrap.min.css"
	rel="stylesheet">
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script>
	function goto_StoreIndexServlet() {
		window.document.location.href = "StoreFoodInfoServlet";
	}
	function goto_StoreInfoServlet() {
		window.document.location.href = "StoreInfoServlet";
	}
	function handle_order(username,order_time,total_price){
		$.ajax({
			type:"post",
			url:"HandleOrderListServlet",
			dataType:"json",
			data:"username="+username+"&order_time="+order_time+"&total_price="+total_price,////
			success:function(res){
				var response=eval(res);
				if(response['res']=="cancel"){
					alert("这份订单已经被取消了");
				}else if(response['res']=="ok"){
					alert("您已经成功处理订单");
					document.location.reload();
				}else alert("您的订单已经是处理状态了");
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
			alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);}
		});
	}
</script>
<style>
</style>
</head>
<body style="overflow:-Scroll;overflow-x:hidden">
<a id="nav_top" onclick="pageScroll()" style="cursor: pointer; display: inline;border:1px solid #eef7fa;left:1200px" title="回到页头"><span>▲</span></a>
	<%@include file="daohanglan.jsp"%>
	<%
		String store_name = (String) session.getAttribute("store_name");
	%>
	<div class="container-fluid" id="shop_name">
	<div class="row-fluid">
		<div class="span12" style="position:relative;color:gold;margin:0 auto; width:980px;">
		<h3>   </h3>
		<!-- 
			<h3>
				<%=username+" 的商店 :"+store_name %>
			</h3>
			 -->
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="tabbable" id="tabs-648965" style="margin: 0 auto;width: 980px;">
				<ul class="nav nav-tabs" style="width:100%">
						<li><a href="#shangpin" data-toggle="tab"
							onclick="goto_StoreIndexServlet()">我的商品</a></li>
						<li class="active"><a href="#dingdan" data-toggle="tab"
							onclick="">我的订单</a></li>
						<li><a href="#xinxi" data-toggle="tab"
							onclick="goto_StoreInfoServlet()">我的信息</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane" id="shangpin"></div>
						<div class="tab-pane active" id="dingdan">
						<c:forEach items="${storeorder_list}" var="sto" varStatus="i">
							<div style="border:1px solid #bfbfbf;width:980px;margin:20px auto;">
							<li style="background:#f5f5f5;padding:8px 0;"><span><input type="checkbox" style="vertical-align:text-top;" />&nbsp; ${sto.order_time}</span><span>用户：${sto.name}</span><span>电话：${sto.telephone}</span><span>地址：${sto.address}</span><div style="float:right;margin-right:10px;"></div></li>
							<li class="_mainbox">
							<div style="display:inline-block;width:711px;vertical-align:middle">
							<c:forEach items="${sto.foodlist}" var="stoo">
							<zdy>
							<ul><img src="data/foodpicture/${stoo.food_ID}.jpg" style="width:75px;height:80px;"/></ul>
							<ul style="width:250px;">${stoo.name}</ul>
							<ul>${stoo.price}元</ul>
							<ul>${stoo.num}份</ul>
							<ul></ul>
							</zdy>
							</c:forEach>
							</div>
							<div class="hou" style="display:inline-block;vertical-align:middle;border-left:1px solid #e8e8e8;">
							<div style="height:50%;margin-bottom:-20px;"></div>
							<ul>合计：${sto.total_price}元</ul>
							<ul class="sov" style="padding-left:20px;cursor:pointer"><div onclick="handle_order('${sto.username}','${sto.order_time}','${sto.total_price}')">${sto.order_state}</div><div></div></ul>
							</div>
							</li>
							</div>
							</c:forEach>
							<script>
							$(document).ready(function(){$(".hou").height(function(){return $(this).prev().height()});});
							</script>
							<style>

							#dingdan li{
							list-style-type:none;
							}
							#dingdan li span{
							margin-left:30px;
							color:#363636;
							}
							._mainbox{
							padding: 5px 10px;
							}
							._mainbox ul{
							display:inline-block;
							padding-left:0;
							min-width:110px;
							text-align:center;
							vertical-align:middle;
							}
							zdy{
							border-bottom:1px solid #e8e8e8;
							line-height:80px;
							width:100%;
							padding: 40px 0;
							}
							.sov div:nth-child(1){
							background:#ffe4d0;
							padding:3px 6px;
							margin:3px 0;
							color: #E5511D;
							border:1px solid;
                            border-color: #F0CAB6;
							}
							.sov div:nth-child(2){
							 font-size:13px;
							 margin-top:5px;
							 color:grey;
							}
							.sov div:hover{
							color:#f40;
							}
							</style>
						</div>
						<div class="tab-pane" id="xinxi"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>