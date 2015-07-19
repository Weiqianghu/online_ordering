<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" import="com.cn.food.*"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Shop Index</title>
<link href="css/register.css" rel="stylesheet">
<link rel="shortcut icon" href="img/chi.ico"/>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="jquery-1.8.0.js" type="text/javascript">
<script src="js/ajaxfileupload.js" type="text/javascript">
</script><script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
function add_Food(){
	var food_name = $('#add_food_name').val();
	var food_price = $('#add_food_price').val();
	if(food_name.length==0 || food_price.length==0) ;
	else{
	    window.document.location.href = "AddFoodServlet?food_name="+food_name+"&&food_price="+food_price;
		//window.document.location.href = "AddFoodServlet?food_name="+encodeURI(encodeURI(food_name))+"&food_price="+encodeURI(encodeURI(food_price));
	}
}
function update_Food(id){
	var food_ID = id;
	var food_name = $('#update_food_name_'+id).val();
	var food_price = $('#update_food_price_'+id).val();
	if(food_name.length==0 || food_price.length==0);
	else{
		window.document.location.href = "UpdataFoodServlet?food_ID="+food_ID+"&food_name="+food_name+"&food_price="+food_price;
		//window.document.location.href = "UpdateFoodServlet?food_ID="+encodeURI(encodeURI(food_ID))+"&food_name="+encodeURI(encodeURI(food_name))+"&food_price="+encodeURI(encodeURI(food_price));
	}
}
function delete_Food(id){
	var food_ID = id;
	window.document.location.href = "DeleteFoodServlet?food_ID="+food_ID;
	//window.document.location.href = "DeleteFoodServlet?food_ID="+encodeURI(encodeURI(food_ID));
}
function goto_StoreInfoServlet(){
	window.document.location.href = "StoreInfoServlet";
}
function goto_StoreOrderServlet(){
	window.document.location.href = "StoreOrderListServlet";
}
$().ready(function(){
	$("img").error(function () {
	    $(this).unbind("error").attr("src", "img/cai.jpg");
	});
});
</script>
<style>
.addit{
cursor:pointer;
background:#f5f5f5;
border:1px solid #e6e6e6;
}
.addit:hover{
background:#ededed;
}
.table>tbody>tr>td{
vertical-align:middle;
}
</style>
</head>
<body style="overflow:-Scroll;overflow-x:hidden" >
<%@include file="daohanglan.jsp" %>
<% String store_name = (String)session.getAttribute("store_name");%>
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
					<li class="active">
						<a href="#shangpin" data-toggle="tab" onclick="">我的商品</a>
					</li>
					<li>
						<a href="#dingdan" data-toggle="tab" onclick="goto_StoreOrderServlet()">我的订单</a>
					</li>
					<li>
						<a href="#xinxi" data-toggle="tab" onclick="goto_StoreInfoServlet()">我的信息</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="shangpin">
						<div class="bs-example" style="padding-bottom: 24px;">
						        <div></div>
								<button class="btn btn-primary btn-lg hide" data-toggle="modal"
									data-target="#myAddModal" id="addStore">添加商品</button>
						</div>
							<div id="myAddModal" class="modal fade" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<h4 class="modal-title" id="myModalLabel">填信息，加商品</h4>
										</div>
										<div class="modal-body">
											<form id="AddFoodServlet" class="form-horizontal" role="form" action="AddFoodServlet" method="post" enctype="multipart/form-data">
												<div class="col-sm-2 control-label">商品名称</div>
												<div class="col-sm-10">
													<input type="text" class="form-control" name="food_name"
														id="add_food_name" placeholder="请输入商品名">
												</div>
												<br><br><br>
												<div class="col-sm-2 control-label">商品价格</div>
												<div class="col-sm-10">
													<input type="text" class="form-control" name="food_price"
														id="add_food_price" placeholder="请输入商品价格">
												</div>
												<br><br><br>
												<div class="col-sm-2 control-label">商品图片</div>
												<div class="col-sm-10">
													<input type="file" class="form-control" name="food_touxiang"
														id="add_food_touxiang">
												</div>
												<br>
											    <textarea name="content" style="width:100%"></textarea>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<button type="submit" class="btn btn-primary">添加</button>
										</div>
										</form>
										<script>
										$("#AddFoodServlet").submit(function(){
											var food_name = $('#add_food_name').val();
											var food_price = $('#add_food_price').val();
											if(food_name.length==0 || food_price.length==0){
												return false;
											}
											else{
											   return true;
												//window.document.location.href = "AddFoodServlet?food_name="+encodeURI(encodeURI(food_name))+"&food_price="+encodeURI(encodeURI(food_price));
											}
										});
										</script>
									</div><!-- /.modal-content -->							
								</div><!-- /.modal-dialog -->
							</div>
						<div>
								<table class="table table-hover"
									style="position: relative; width: 980px;" align="center">
									<thead>
										<tr style="background-color: #FFCCFF;">
											<th>商品名称</th>
											<th>商品价格</th>
											<th>销售数量</th>
											<th>修改/删除</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list_shangpin}" var="sto">
											<tr >
												<td><img style="width:50px;height:50px;" src="data/foodpicture/${sto.food_ID}.jpg"/>&nbsp;&nbsp;&nbsp;<a href="StoreFoodServlet?store_ID=<%=username%>&store_name=<%=store_name %>">${sto.name} </a></td>
												<td>${sto.price}</td>
												<td>${sto.sales_volume}</td>
												<td><div><a data-toggle="modal" data-target="#updateFoodModal_${sto.food_ID}">修改</a>/
														<%@include file="updateFood.jsp" %>
														<!-- 以上是更改商品的遮罩 -->
														<a data-toggle="modal" data-target="#deleteFoodModal_${sto.food_ID}">删除</a>
														<%@include file="deleteFood.jsp" %>
														<!-- 以上是删除商品的遮罩 -->
													</div></td>
											</tr>
										</c:forEach>

										<tr>
										    <td  class="addit" colspan="4" style="text-align:center;" onclick="location='FoodAddServlet'">添加商品</td>
										</tr>

									</tbody>
								</table>
						</div>
					</div>
					<div class="tab-pane" id="dingdan">
					</div>
					<div class="tab-pane" id="xinxi">
					</div>
			</div>
		</div>
	</div>
</div>
</body>