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
<title><%=store_name %></title>
<link rel="shortcut icon" href="img/chi.ico"/>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
	<script>
	$().ready(function(){
		$("img").error(function () {
		    $(this).unbind("error").attr("src", "img/cai.jpg");
		});
	});

	</script>
</head>
<style>
#add_success{
position:fixed;
right:-95px;
border:1px solid #4d4dff;
color:#fff;
background:#4d4dff;
padding:5px 15px;
top:80px;
border-radius:5px;
        -webkit-border-top-left-radius:20px;
        -webkit-border-bottom-left-radius:20px;
        -moz-border-radius:10px;
}
</style>
<body style="overflow: -Scroll; overflow-x: hidden">

	<%@include file="daohanglan.jsp"%>
	<div id="add_success">添加成功</div>
	<div style="height:100%;width:100%;position:fixed;background:rgba(0,0,0,0.2);z-index:900;display:none;" class="c100"></div>
	<div style="width:800px;background:#fff;margin-left:-400px;left:50%;top:80px;min-height:200px;position:fixed;z-index:901;display:none;" class="tck">
	<div style="float:right;font-size:20px;color:#777;margin-right:10px;cursor:pointer;" onclick="show_more()">×</div>
	<li><div><img src="" style="width:220px;"/></div><div style="text-align:center;margin:10px 0 0 0" class="c99">水煮鱼(40元)</div></li>
	<li style="width:480px;">
	<div style="width:100%;border-bottom:1px solid #ccc;">详细介绍</div>
	<div class="more">11111</div>
	</li>
	</div>
	<script>
	function show_more(){
		$(".c100").hide();$(".tck").hide();
	}
	</script>
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
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="tabbable" id="tabs-648965">
					<ul class="nav nav-tabs" style="width:1200px;margin:0 auto;">
						<li class="active"><a href="#shangpin" data-toggle="tab"onclick="">商品</a></li>
						<li><a href="#pinglun" data-toggle="tab" onclick="goto_comment('<%=store_ID %>','<%=store_name %>','<%=store_state %>')">评论</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="shangpin">
							<div style="position: relative; top: 20px;width: 1200px;margin:0 auto;">
							<c:forEach items="${list_shangpin}" var="sto">
							     <li style="">
							     <div>
							     <div class="detail" title="${sto.name}(${sto.price}元)">详情</div>
							     <img src="data/foodpicture/${sto.food_ID}.jpg" style="width:220px;height:250px;"/>						     
							     <div class="ix" style="display:none;">${sto.introduction}</div>
							     <input type="hidden" value="${sto.food_taste}" class="fx"/>
							     <div style="margin:5px auto;color:#555;text-align:center;">
							   ${sto.name}<br/>
							                       已累计销售${sto.sales_volume}份
							     </div>
							     <div style="margin-left:20px;"><span style="color:#ff6b4e;font-size:18px;">${sto.price}元</span> 
							     <span style="background:red;color:#fff;padding:5px 15px;margin-left:80px;cursor:pointer" onclick="order(this,'${sto.food_ID}')" 
							     id="order_button_${sto.food_ID}">走一个</span></div>
							     </div>
							    </li>
						   </c:forEach>
						   
						   <div style="height:100px;width:100%;"></div>
								<!-- <table class="table table-hover"
									style="position: relative; width: 800px;" align="center">
									<thead>
										<tr style="background-color: #FFCCFF;">
											<th>商品名称</th>
											<th>价格</th>
											<th>销量</th>
											<th>下单</th>
											<th>详情</th>
										</tr>
									</thead>
									<tbody>
											<tr>
												<td><a title="选我选我">${sto.name}</a></td>
												<td>${sto.price}</td>
												<td>${sto.sales_volume}</td>
												<td><button title="买买买"
														onclick="order('${sto.food_ID}')"
														id="order_button_${sto.food_ID}">来份</button></td>
												<td><a href="javascript:void(false);" class="getit">查看</a></td>
											</tr>
											<tr class="more " style="display:none;">
											    <td style="width:273px;">
											        <img src="http://g.search1.alicdn.com/img/bao/uploaded/i4/om/TB1CFBAGFXXXXcAXpXXSutbFXXX_220x220.jpg_.webp"/>
											    </td>
											    <td  colspan="4" style="width:511px;">
											    <div>dwdefegrdwdefrgwdefegrdwdefrgwdefegrdwdefrgwdefegrdwdefrgwdefegrdwdefrgwdefegrdwdefrgwdefegrdwdefrgwdefegrdwdefrgwdefegrdwdefrgwdefegrdwdefrgr</div>
											    </td>
											</tr>-->
											<style>
											.detail{
											display:none;cursor:pointer;
											position:absolute;margin-top:220px;line-height:30px;z-index:100;background:rgba(0,0,0,0.36);width:220px;text-align:center;color:#fff;
											
											}
											.tck li{
											display:inline-block;
											vertical-align:top;
											margin:30px 10px;
											}
											.tab-pane li{
											display:inline-block;
											vertical-align:top;padding:13px 4px;border:1px solid #fff;
											}
											.tab-pane li:hover{
											border:1px solid #aaa;
											}
											.more img{
											     max-width:210px;
											     padding:10px 0 10px 0;
											}
											 .more li{
											     list-style:none;
											 }
											 .more{
											     padding:10px 5px;
											     max-width:480px;
											     word-wrap: break-word;
											     word-break: normal; 
											 }
											</style>
										
								<script>
											$(".getit").click(function(){
												if($(this).parents("tr").next().css("display")==="none"){
													$(".more").css("display","none");
													$(this).parents("tr").next().show();
												}else{
													$(".more").css("display","none");
												}
												
												
											});
											$(".tab-pane li").hover(function(){
												$(this).find(".detail").css("display","inline");
											},function(){
												$(this).find(".detail").css("display","none");
											});
											$(".detail").click(function(){
												$(".c99").html($(this).prop("title"));
												$(".tck img").prop("src",$(this).next().prop("src"));
												$(".more").html("口味:"+$(this).nextAll(".fx").val()+"<br/>"+$(this).nextAll(".ix").html());
												
												$(".c100").show();$(".tck").show();
											});
								</script>
							</div>
						</div>
						<div class="tab-pane" id="pinglun"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<nav class="navbar navbar-default navbar-fixed-bottom"
		role="navigation">
	<div class="navbar-header navbar-right"
		style="position: relative; right: 20px">
		<a class="navbar-brand" href="#" style="font-size: 23px"
			title="已选择的食物" onclick="goto_basket()"  id="food_basket">食物篮子</a>
	</div>
	</nav>
	<script>
function order(id,food_ID){
	var tis=$(id);
	var a = "<%=store_state%>";
	var whichone = "<%=whichone%>";
	var username = "<%=username%>";
	if(a=="休息中"){
		alert("商铺休息中，无法提供服务");
		button=document.getElementById("order_button_"+food_ID);
		button.disabled=true;
	}
	else if(username=="null")
		{alert("请先登录！"); window.document.location.href = "login.jsp";}
	else if(whichone == "shop" || whichone == "admin")
		alert("您不是订餐用户，不能点餐");
	else{
		var store_ID = "<%=store_ID%>";
		var store_name = "<%=store_name%>";
		var food_ID = food_ID;
		$.ajax({
			type:"post",
			url:"AddBasketServlet",
			dataType: "html",
			data:{store_ID:store_ID,food_ID:food_ID,store_name:store_name},
			success:function(res){
				if(res=="error"){
					location.href ="login.jsp";
				}
				else if(res=="ok"){
					tis.css("background","#ff7878");
					setTimeout("order_ok('order_button_"+food_ID+"')",1000);
					$("#add_success").animate({right:"-2px"});
					$('#food_basket').css("color","#ff9900");
				}
				else {
					alert("下单出错");
				}
			},
			error : function(){
				
				alert("error");
			}
		});
	}
}
function order_ok(id){
	$("#"+id).css("background","#f00");
	$("#add_success").animate({right:"-95px"});
	$('#food_basket').css("color","#5e5e5e");
}
function goto_basket() {
	var store_ID = "<%=store_ID %>";
    window.document.location.href = "ShowBasketServlet?store_ID="+store_ID;
}
function goto_comment(store_ID,store_name,store_state){
	window.document.location.href = "ShowCommentServlet?store_ID="+store_ID+"&store_name="+store_name+"&store_state="+store_state;
}
	</script>
</body>
</html>