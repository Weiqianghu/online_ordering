<%@ page language="java" import="java.util.*" import="com.cn.store.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>吃了么</title>
<link rel="shortcut icon" href="img/chi.ico"/>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/admin_index.css" rel="stylesheet">
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/Chart.js"></script>
<script type="text/javascript">
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
</script>
</head>
<body style="overflow:-Scroll;overflow-x:hidden">


	<%@include file="daohanglan.jsp"%>
	<div class="span12"
		style="position: relative; color: #FFC376; left: 30px;">
		<h3>系统管理员</h3>
		<h4>你拥有查看和修改用户名、密码以及删除账户的功能</h4>
	</div>
	<hr>
	<div style="min-width:1400px">
		<div class="left_box">
			<ul style="margin-left:-40px;">
				<li class="li" style="margin: 12px 0 5px 0"><a href="AdminServlet?a=1">查看所有用户</a></li>
				<li class="li"><a href="AdminServlet?b=1">查看所有商铺</a></li>
				<li class="li"><a href="ShowComplaintStoreServlet?c=1">受理投诉</a></li>
				<li class="li"><a href="ShowMonthOrderNumServlet?year=2014">信息统计</a></li>
				<li class="li"><a href="ShowFoodOrderNumServlet?m=1">商品销量前十</a></li>
				<li class="li"><a href="ShowStoreOrderNumServlet?k=1">商铺销量前十</a></li>
			</ul>
		</div>
		<div class="right_box">
		<div id="user_table" style="display: none" class="flame">
		<table class="table table-hover"
			style="position: relative; width: 800px; margin:10px auto 10px auto">
			<thead>
				<tr style="background-color: #FFCCFF; font-size:16px">
					<th width="200px">用户名（订餐用户）</th>
					<th width="200px">密码</th>
					<th>修改</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${user_list}" var="sto">
					<tr style="font-size:15px">
						<td><a href="#">${sto.username}</a></td>
						<td><a href="#">${sto.password}</a></td>
						<td><button data-toggle="modal"
								data-target="#adminUpdateUserModal_${sto.username}" class="change_button">修改</button></td>
						<td><button onclick="delete_user('${sto.username}')" class="delete_button">删除</button></td>
						<div id="adminUpdateUserModal_${sto.username}" class="modal fade" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">修改用户名、密码</h4>
									</div>
									<div class="modal-body">
										<form class="form-horizontal" role="form">
											<div class="col-sm-2 control-label">新用户名</div>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="admin_username_${sto.username}"
													placeholder="请输入新用户名">
											</div>
											<br> <br> <br>
											<div class="col-sm-2 control-label">新密码</div>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="admin_password_${sto.username}"
													placeholder="请输入新密码">
											</div>
											<br>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary"
											onclick="update_user('${sto.username}')">修改</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div id="shop_table" style="display: none" class="flame">
		<table class="table table-hover"
			style="position: relative; width: 800px; margin:10px auto 10px auto">
			<thead>
				<tr style="background-color: #FFCCFF; font-size:16px">
					<th width="200px">用户名（商铺用户）</th>
					<th width="200px">密码</th>
					<th>修改</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${store_list}" var="sto">
					<tr style="font-size:15px">
						<td><a href="#">${sto.store_ID}</a></td>
						<td><a href="#">${sto.store_password}</a></td>
						<td><button data-toggle="modal"
								data-target="#adminUpdateStoreModal_${sto.store_ID}" class="change_button">修改</button></td>
						<td><button onclick="delete_shop('${sto.store_ID}')" class="delete_button">删除</button></td>
						<div id="adminUpdateStoreModal_${sto.store_ID}" class="modal fade" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">修改商铺用户名、密码</h4>
									</div>
									<div class="modal-body">
										<form class="form-horizontal" role="form">
											<div class="col-sm-2 control-label">store_ID</div>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="admin_store_ID_${sto.store_ID}"
													placeholder="请输入新store_ID">
											</div>
											<br> <br> <br>
											<div class="col-sm-2 control-label">新密码</div>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="admin_store_password_${sto.store_ID}"
													placeholder="请输入新密码">
											</div>
											<br>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary"
											onclick="update_shop('${sto.store_ID}')">修改</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	
	<div id="complain_table" style="display: none" class="flame">
		<table class="table table-hover"
			style="position: relative; width: 900px; margin:10px auto 10px auto">
		<thead>	
			<tr style="background-color: #FFCCFF; font-size:16px">
				<th width="200px">用户投诉</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="sto">
			<tr>
			<td>
			<div style="heigth:200px;width:900px;margin:0 0 0px 0;border:1px solid #000">
				<div style="background-color:#ffafaf">
				<div style="width:160px;display:inline-block;margin: 0 0 0 5px">username: <b>${sto.username}</b></div>
				<div style="width:160px;display:inline-block">store_ID: <b>${sto.store_ID}</b></div>
				<div style="width:180px;display:inline-block">用户电话 : <b>${sto.user_telephone}</b></div>
				<div style="width:180px;display:inline-block">商铺电话 : <b>${sto.store_telephone}</b></div>
				<div style="width:160px;display:inline-block">投诉状态: <b>${sto.state}</b></div>
				</div>
				<hr style="margin:10px 0 10px 0">
				<div style="width:600px;height:40px;margin: 0 0 0 10px;">投诉内容: ${sto.complaint_reason}</div>
				<hr style="margin:10px 0 10px 0">
				<div style="width:240px;display:inline-block;margin: 0 0 0 5px">下单时间: <b>${sto.order_time}</b></div>
				<div style="width:240px;display:inline-block">投诉时间 : <b>${sto.complaint_time}</b></div>
				<c:if test="${sto.state=='未处理' }">
				<div title="${sto.state}" style="width:100px;display:inline-block;margin:0 0 0 200px"><button onclick="handle_complain('${sto.id}','to_user')">给买家</button></div>
				<div title="${sto.state}" style="width:100px;display:inline-block"><button onclick="handle_complain('${sto.id}','to_shop')">给卖家</button></div>
				</c:if>
				<c:if test="${sto.state!='未处理' }">
				<div title="${sto.state}" style="width:100px;display:inline-block;margin:0 0 0 200px"><input type="button" disabled="false" onclick="handle_complain('${sto.id}','to_user')" value="给买家"></div>
				<div title="${sto.state}" style="width:100px;display:inline-block"><input type="button" disabled="false" onclick="handle_complain('${sto.id}','to_user')" value="给卖家"></div>
				</c:if>
			</div>
			</td>
			</tr>
			</c:forEach>
		</tbody>	
			
		</table>
	</div>
	
	
	
		<div id="c3" style="display: none;width:600px;height:450px;padding:20px 20px;" class="flame">
		<canvas id="canvas" height="400" width="600"></canvas>
		</div>
		<div id="c4" style="display: none;width:600px;height:450px;padding:20px 20px;" class="flame">
		<canvas id="canvas1" height="400" width="600"></canvas>
		</div>
		
		<div id="c5" style="display: none;width:600px;height:450px;padding:20px 20px;" class="flame">
		<canvas id="canvas2" height="400" width="600"></canvas>
		</div>
		</div>
		</div>
	
<script>
	 var whichone=null;
	 	$(document).ready(function(){
	 		var from = '${param.from}';
	 		if(from == "user")
	 		{
	 			$('.right_box').css("display","inline-block");
				$('#user_table').attr({
					style : "display:inline"
				});
	 		}
	 		else if(from == "shop"){
	 			$('.right_box').css("display","inline-block");
				$('#shop_table').attr({
					style : "display:inline"
				});
	 		}
	 		else if(from == "complain"){
	 			$('.right_box').css("display","inline-block");
				$('#complain_table').attr({
					style : "display:inline"
				});
	 		}
	 	});
		function user() {
			$('.right_box').css("display","inline-block");
			$('#user_table').attr({
				style : "display:inline"
			});
			whichone="user";
		}
		function shop() {
			//$('.flame').css('display','none');
			$('.right_box').css("display","inline-block");
			$('#shop_table').attr({
				style : "display:inline"
			});
			whichone="shop";
		}
		function complain(){
			$('.right_box').css("display","inline-block");
			$('#complain_table').attr({style : "display:inline"});
		}
		function update_user(username) {
			var new_username = $('#admin_username_'+username).val();
			var new_password = $('#admin_password_'+username).val();
			if(new_username.length==0 ||new_password.length==0)
				alert("用户名和密码都不能为空");
			else
			$.ajax({
				type:"post",
				url:"AdminExistsServlet",
				dataType:"json",
				data:"username="+new_username+"&whichone="+whichone,
				success:function(res){
					var response=eval(res);
					//alert(response['res']);
					if(response['res']=="exist"){
						alert("用户名已经存在")
					}else if(response['res']=="ok"){
						location.href = "AdminUpdateUserServlet?newusername="+new_username+"&password="+new_password+"&username="+username;
					}
				},
				error:function(){alert("error");}
			});
		}
		function update_shop(store_ID) {
			var new_store_ID = $('#admin_store_ID_'+store_ID).val();
			var new_password = $('#admin_store_password_'+store_ID).val();
			if(new_store_ID.length==0 || new_password.length==0)
				alert("store_ID和密码都不能为空");
			else
			$.ajax({
				type:"post",
				url:"AdminExistsServlet",
				dataType:"json",
				data:"username="+new_store_ID+"&whichone="+whichone,
				success:function(res){
					var response=eval(res);
					if(response['res']=="exist"){
						alert("store_ID已经存在");
					}else if(response['res']=="ok"){
						location.href = "AdminUpdateStoreServlet?newstore_ID="+new_store_ID+"&password="+new_password+"&store_ID="+store_ID;
					}
				},
				error:function(){alert("error");}
			});
		}
		function delete_user(username) {
			window.document.location.href = "AdminDeleteUserServlet?username="+username+"&whichone=user";
		}
		function delete_shop(store_ID) {
			window.document.location.href = "AdminDeleteStoreServlet?store_ID="+store_ID+"&whichone=shop";
		}
		function handle_complain(id,to_who){
			window.document.location.href = "HandleComplaintStoreServlet?ID="+id+"&to_who="+to_who+"&whichone=complain"
		}
	</script>
	<div style="width: 50%">
			
		</div>


	<script>
	var randomScalingFactor = function(){ return Math.round(Math.random()*100)};

	var barChartData = {
		labels : [<c:forEach items="${ordernumlist}" var="data">"${data.time}",</c:forEach>],
		datasets : [

			{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : [<c:forEach items="${ordernumlist}" var="data">${data.order_num},</c:forEach>]
			}
		]

	};
	var barChartData1 = {
			labels : [<c:forEach items="${foodordernumlist}" var="data">"${data.food_name}",</c:forEach>],
			datasets : [

				{
					fillColor : "rgba(151,187,205,0.5)",
					strokeColor : "rgba(151,187,205,0.8)",
					highlightFill : "rgba(151,187,205,0.75)",
					highlightStroke : "rgba(151,187,205,1)",
					data : [<c:forEach items="${foodordernumlist}" var="data">${data.order_num},</c:forEach>]
				}
			]

	};
	var barChartData2 = {
			labels : [<c:forEach items="${storeordernumlist}" var="data">"${data.store_name}",</c:forEach>],
			datasets : [

				{
					fillColor : "rgba(151,187,205,0.5)",
					strokeColor : "rgba(151,187,205,0.8)",
					highlightFill : "rgba(151,187,205,0.75)",
					highlightStroke : "rgba(151,187,205,1)",
					data : [<c:forEach items="${storeordernumlist}" var="data">${data.order_num},</c:forEach>]
				}
			]

	};
	
	function bt(){
		var ctx = document.getElementById("canvas").getContext("2d");
		window.myBar = new Chart(ctx).Bar(barChartData, {
			responsive : true
		});
	}
	function bt1(){
		var ctx = document.getElementById("canvas1").getContext("2d");
		window.myBar = new Chart(ctx).Bar(barChartData1, {
			responsive : true
		});
	}
	function bt2(){
		var ctx = document.getElementById("canvas2").getContext("2d");
		window.myBar = new Chart(ctx).Bar(barChartData2, {
			responsive : true
		});
	}
    $().ready(function(){
    	if(GetQueryString("a")>0){

    		user();
        	return;
    		
        	}
    	if(GetQueryString("b")>0){

    		shop();
        	return;
    		
        	}
    	if(GetQueryString("c")>0){
    		complain();
    		return;
    	}
    	if(GetQueryString("year")>0){

    	$('.flame').css('display','none');$('#c3').show();$('.right_box').css('display','inline-block');bt();
    	return;
		
    	}
    	if(GetQueryString("m")>0){
    		$('.flame').css('display','none');$('#c4').show();$('.right_box').css('display','inline-block');bt1();
        	return;
    	}
    	if(GetQueryString("k")>0){
    		$('.flame').css('display','none');$('#c5').show();$('.right_box').css('display','inline-block');bt2();
        	return;
    	}
    	
    });
	</script>

	
</body>
</html>

