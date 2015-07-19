<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" import="com.cn.food.*"
    pageEncoding="utf-8" isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Shop Info</title>
<link rel="shortcut icon" href="img/chi.ico"/>
<link href="http://cdn.bootcss.com/twitter-bootstrap/3.0.2/css/bootstrap.min.css" rel="stylesheet">
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=xbd7kUGoWOPLIzU3amV2uL5Y"></script>
<script type="text/javascript">
<%Double longitude = (Double)request.getAttribute("longitude"); %>
<%Double latitude = (Double)request.getAttribute("latitude"); %>
var jingdu=<%=longitude %>,weidu=<%=latitude %>;
function goto_StoreIndexServlet(){
	window.document.location.href = "StoreFoodInfoServlet";
}
function goto_StoreOrderServlet(){
	window.document.location.href = "StoreOrderListServlet";
}
function update_shop_info(){
	var new_shop_name = $('#update_shop_name').val();
	var new_shop_taste = $('#update_shop_taste').val();
	var new_shop_price = $('#update_shop_price').val();
	var new_shop_address = $('#update_shop_address').val();
	var new_shop_telephone = $('#update_shop_telephone').val();
	if(jingdu=='') alert("请在地图上标记");
	if(new_shop_name.length==0 || new_shop_taste.length==0 || new_shop_price.length==0) ;
	else if(new_shop_taste.length>5)
		alert("输入的口味过长");
	else{
		window.document.location.href = "UpdateStoreInfoServlet?new_shop_name="+new_shop_name+"&new_shop_taste="
		+new_shop_taste+"&new_shop_price="+new_shop_price+"&new_shop_telephone="+new_shop_telephone+"&new_shop_address="
		+new_shop_address+"&longitude="+jingdu+"&latitude="+weidu;
	}
}
function update_shop_state(){
	var state = $('#update_shop_state').text();
	if(state == "休息中")
		$('#update_shop_state').text("营业中");
	else
		$('#update_shop_state').text("休息中");
	window.document.location.href = "ChangeState" ;
}
function shop_state_mouseover(){
	var state = document.getElementById("update_shop_state").innerHTML;
	if(state=="休息中")
		$('#update_shop_state').attr("title","营业之前请仔细填写信息");
	else $('#update_shop_state').attr("title","单击切换状态");
}
function update_shop_password(old_password1){
	var old_password_input= $('#old_password').val();
	var new_password = $('#new_password').val();
	var new_password_again = $('#new_password_again').val();
	var old_password = old_password1;
	if(old_password != old_password_input)
		alert("原密码不正确");
	else if(new_password !=new_password_again)
		alert("新密码不一致");
	else{
		//window.document.location.href = "UpdateStorePasswordServlet?new_password="+encodeURI(encodeURI(new_password)) ;
		window.document.location.href = "UpdateStorePasswordServlet?new_password="+new_password;
	}
}
function bj(){
	$('#bjspxx').click();
}
$().ready(function(){
	$("img").error(function () {
	    $(this).unbind("error").attr("src", "img/touxiang.gif");
	});
});
</script>
<style>
#tx_output{
	background:url('img/changetouxiang.gif');
}
#tx_tp{
	height:120px;
}
#tx_tp div{
	float:left;
}
.tx_ta{
	width:80px;height:80px;
	//border:1px solid #777;
	margin:5px 30px;
	cursor:pointer;
}
.anchorBL{  
	display:none;  
}  
</style>
</head>
<body style="overflow:-Scroll;overflow-x:hidden">
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
					<li>
						<a href="#shangpin" data-toggle="tab" onclick="goto_StoreIndexServlet()">我的商品</a>
					</li>
					<li>
						<a href="#dingdan" data-toggle="tab" onclick="goto_StoreOrderServlet()">我的订单</a>
					</li>
					<li class="active">
						<a href="#xinxi" data-toggle="tab" onclick="">我的信息</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane " id="shangpin">
					</div>
					<div class="tab-pane" id="dingdan">
					</div>
					<div class="tab-pane active" id="xinxi" style="width:100%;text-align:center;">
					<jsp:useBean id="list_xinxi" class="com.cn.store.StoreBean" scope="session"/>
					<div style="border:2px solid #d1e1ef;width:980px;min-height:100px;margin:10px auto;">
					    <div style="background:#f5f7f9;width:230px;height:420px;display:inline-block;vertical-align:top;">
					    <img data-toggle="modal" data-target="#myUpdateUserTouxiang" onclick="" alt="" title="修改头像"
					    style="width: 80px;height: 80px;margin:50px auto 0 auto;border:2px solid #fff;cursor: pointer" src="data/touxiang/<%=username%>.jpg"/>
					    <br/>
					    <div style="color: #2c76c0;margin:10px auto;"><%=store_name %></div>
					    </div>
					    <div style="display:inline-block;width:740px;height:100%;">
					    <div style="float:right;margin:5px 5%;"><img src="http://pr.bdimg.com/static/princess/img/edit_btn_27aeb908.png"/><a href="javascript:bj()">编辑</a></div>
					      <div style="height:2px;background:#eee;margin:30px 5% 10px 5%;width:90%;"></div>
					      <style>
					       .table-hover td{
					           border:0!important;
					       }
					      </style>
					      <table class="table table-hover"
									style="position: relative; width: 600px; top:25px" align="center">
									<tr>
										<td>商铺名称</td>
										<td><a><jsp:getProperty name="list_xinxi" property="store_name" /></a></td>
									</tr>
									<tr>
										<td>商铺口味</td>
										<td><a><jsp:getProperty name="list_xinxi" property="taste" /></a></td>
									</tr>
									<tr>
										<td>起送价格</td>
										<td><a><jsp:getProperty name="list_xinxi" property="lowest_consume" /></a></td>
									</tr>
									<tr>
										<td>联系电话</td>
										<td><a><jsp:getProperty name="list_xinxi" property="store_telephone" /></a></td>
									</tr>
									<tr>
										<td>商铺地址</td>
										<td><jsp:getProperty name="list_xinxi" property="address" /></td>
									</tr>
									<tr>
										<td>商铺状态</td>
										<td><button title="单击切换状态" onmouseover="shop_state_mouseover()" onclick="update_shop_state()" id="update_shop_state"><jsp:getProperty name="list_xinxi" property="state" /></button></td>
									</tr>
									<tr>
										<td>销售总量</td>
										<td><jsp:getProperty name="list_xinxi" property="order_number" /></td>
									</tr>
									<tr>
										<td>营业总额</td>
										<td><jsp:getProperty name="list_xinxi" property="balance" /></td>
									</tr>
									<tr>
										<td>修改密码</td>
										<td><button title="单击修改密码" data-toggle="modal" data-target="#myUpdateShopPasswordModal">修改密码</button></td>

									<div id="myUpdateShopPasswordModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">修改登录密码</h4>
												</div>
												<div class="modal-body">
													<form class="form-horizontal" role="form">
														<div class="col-sm-2 control-label">原密码</div>
														<div class="col-sm-10">
															<input type="text" class="form-control"
																id="old_password" placeholder="请输入原密码">
														</div>
														<br>
														<br>
														<br>
														<div class="col-sm-2 control-label">新密码</div>
														<div class="col-sm-10">
															<input type="password" class="form-control"
																id="new_password" placeholder="请输入新密码">
														</div>
														<br>
														<br>
														<br>
														<div class="col-sm-2 control-label">确认密码</div>
														<div class="col-sm-10">
															<input type="password" class="form-control"
																id="new_password_again" placeholder="请确认新密码">
														</div>
														<br>
													</form>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">取消</button>
													<button type="button" class="btn btn-primary"
														onclick="update_shop_password( '${list_xinxi.store_password}' )">修改</button>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
								</tr>
								<tr>
			<div id="myUpdateUserTouxiang" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">修改商铺头像</h4>
												</div>
												<div class="modal-body" id="tx_tp">
													<div class="tx_ta">
													<img style="width: 80px;height: 80px;" src="data/touxiang/<%=username%>.jpg"/>
													</div>
													<div style="font-size:30px;padding-top:20px;">-></div>
													<div class="tx_ta" id="tx_output" onclick="$('#file1').click()"></div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">取消</button>
													<button type="button" class="btn btn-primary"
														onclick="if($('#file1').val()!==''){$('#tx_sub').submit();}">修改</button>
												</div>
												<div class="hide">
												<form action="UploadTouxiangServlet" enctype="multipart/form-data" method="post" id="tx_sub">
													选择文件<input type="file" name="file1" id="file1" accept="image/*"/>
													<input type="submit" name="upload" value="上传">
												</form>
												<script>
												function setImagePreview(input,output,img) {          
												    var docObj=document.getElementById(input);           
												    var imgObjPreview=document.getElementById(img);  
												    if(docObj.files && docObj.files[0]){  
												        var i=0;
												        while(docObj.files[i]){
												            $("#"+output).append('<img class="p_img" id="view'+i+'"/>');
												            imgObjPreview=document.getElementById(img+i);
												            imgObjPreview.style.display = 'block';                          
												            imgObjPreview.style.width = '80px';                          
												            imgObjPreview.style.height = '80px'; 
												            imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
												            i++;
												        }
												     }else{                          
												         //IE锟铰ｏ拷使锟斤拷锟剿撅拷                          
												         docObj.select();                          
												         var imgSrc = document.selection.createRange().text;                          
												         var localImagId = document.getElementById(output);  
												         //锟斤拷锟斤拷锟斤拷锟矫筹拷始锟斤拷小                          
												         localImagId.style.width = "80px";                          
												         localImagId.style.height = "80px";  
												        //图片锟届常锟侥诧拷捉锟斤拷锟斤拷止锟矫伙拷锟睫改猴拷缀锟斤拷伪锟斤拷图片  
												        try{                                  
												            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";                                  
												            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;                          
												        }catch(e){                                  
												            alert("锟斤拷锟较达拷锟斤拷图片锟斤拷式锟斤拷锟斤拷确锟斤拷锟斤拷锟斤拷锟斤拷选锟斤拷!");                                  
												            return false;                          
												        }  
												            imgObjPreview.style.display = 'none';                          
												            document.selection.empty();                  
												        }                  
												            return true;          
												}
												$("#file1").change(function(){
  												      setImagePreview("file1","tx_output","view");
   												     $("#_bq").append('<div class="p_img" id="cancel" onclick="imageEmpty()"><img src="/Public/img/chat/cancel.gif"/>取消</div>');
  												  });
												</script>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									
		</tr>
								</table> 
								<div style="margin-bottom:30px;width:100%;"></div>
					    </div>
					</div>
						<div class="bs-example hide" style="padding-bottom: 24px;" align="center">
								<button class="btn btn-primary btn-lg" data-toggle="modal"
									data-target="#myUpdateShopModal" style="position:relative;" id="bjspxx">编辑商铺信息
								</button>
								
						</div>
						<div id="myUpdateShopModal" class="modal fade" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<h4 class="modal-title" id="myModalLabel">编辑商铺信息</h4>
										</div>
										<div class="modal-body">
											<form class="form-horizontal" role="form">
												<div class="col-sm-2 control-label">商铺名称</div>
												<div class="col-sm-10">
													<input type="text" class="form-control"
														id="update_shop_name" placeholder="请输入商铺名称" value="${list_xinxi.store_name}">
												</div>
												<br><br>
												<div class="col-sm-2 control-label">商铺口味</div>
												<div class="col-sm-10">
													<input type="text" class="form-control"
														id="update_shop_taste" placeholder="请输入商铺口味 （不多于5个字）" value="${list_xinxi.taste}">
												</div>
												<br><br>
												<div class="col-sm-2 control-label">起送价格</div>
												<div class="col-sm-10">
													<input type="text" class="form-control"
														id="update_shop_price" placeholder="请输入起送价格" value="${list_xinxi.lowest_consume}">
												</div>
												<br><br>
												<div class="col-sm-2 control-label">联系电话</div>
												<div class="col-sm-10">
													<input type="text" class="form-control"
														id="update_shop_telephone" placeholder="请输入联系电话" value="${list_xinxi.store_telephone}">
												</div>
												<br><br>
												<div class="col-sm-2 control-label">店铺地址</div>
												<div class="col-sm-10">
													<input type="text" class="form-control" style="width:80%;margin-right:5px;display:inline"
														id="update_shop_address" placeholder="请输入店铺地址" value="${list_xinxi.address}">
													<input id="address_search_button" type="button" style="width:70px;display:inline" value="搜索">
												</div>
												<br>
												<div id="container" style="width: 350px; height: 350px ;margin:30px 0 0 100px"></div>
												<script type="text/javascript">
													var map = new BMap.Map("container");
													var point = new BMap.Point(121.500078, 30.848849);
													map.centerAndZoom(point, 15);
													map.addEventListener("click", function(e) {
														map.clearOverlays();
														var mkr = new BMap.Marker(new BMap.Point(e.point.lng,e.point.lat), {
															raiseOnDrag: true,
														});
														map.addOverlay(mkr);
														jingdu = e.point.lng;
														weidu = e.point.lat;
													});
													map.enableScrollWheelZoom();
													map.addControl(new BMap.ScaleControl());
													map.addControl(new BMap.NavigationControl());
													
													$('#address_search_button').click(function(){
														var address = $('#update_shop_address').val();
														var myGeo = new BMap.Geocoder();
														myGeo.getPoint(address,function(point){
															if(point==null) alert("地址未找到");
															else{
															map.clearOverlays();
															//alert(point.lng+"   "+point.lat);
															var marker = new BMap.Marker(new BMap.Point(point.lng,point.lat), {
																raiseOnDrag: true,
															});
															map.addOverlay(marker);
															jingdu = point.lng;
															weidu = point.lat;
															}
															},"上海");
														
													});
														
												</script>
											</form>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<button type="button" class="btn btn-primary" onclick="update_shop_info()">修改</button>
										</div>
									</div><!-- /.modal-content -->							
								</div><!-- /.modal-dialog -->
							</div>
								
					 </div>
			</div>
		</div>
	</div>
</div>
</body>