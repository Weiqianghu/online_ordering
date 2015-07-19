<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>User Index</title>
<link rel="shortcut icon" href="img/chi.ico"/>
<link href="css/register.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script>
	function update_user_info(username) {
		var name = $('#update_user_name').val();
		var address = $('#update_user_address').val();
		var telephone = $('#update_user_telephone').val();
		var email = $('#update_user_email').val();
		var qq = $('#update_user_qq').val();
		var balance = $('#update_user_balance').val();
		var taste = $('#dropdownMenu1').text(taste);
		var warning = "";
		if (telephone != '' && !/^\d{7,11}$/.test(telephone))
			warning = warning + "电话非法 ";
		if (qq != "" && !/^\d{1,11}$/.test(qq))
			warning = warning + "qq号非法 ";
		if (email != '' && !/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(email))
			warning = warning + "邮箱非法 ";
		if (balance != '' && /^[0-9]*$/.test(balance) && balance > 100000)
			warning = warning + "充值金额过多 ";
		else if (balance != '' && !/^\d+(\.\d+)?$/.test(balance))
			warning = warning + "充值非法 ";
		if(taste=='选择口味') taste='';
		if (warning != '')
			{alert(warning);return false;}
		else {
			$.post("UpdateUserinfoServlet", {
			name : name,
			address : address,
			telephone : telephone,
			email : email,
			qq : qq,
			balance : balance,
		}, function() {
			location.href = "UserPageServlet";
		});
		}
	}
	function update_user_password(old_password1) {
		var old_password_input = $('#old_password').val();
		var new_password = $('#new_password').val();
		var new_password_again = $('#new_password_again').val();
		var old_password = old_password1;
		if (old_password != old_password_input)
			alert("原密码不正确");
		else if (new_password != new_password_again)
			alert("新密码不一致");
		else {
			//window.document.location.href = "UpdateUserPassword?new_password="+encodeURI(encodeURI(new_password)) ;
			window.document.location.href = "UpdateUserPassword?new_password="
					+ new_password;
		}
	}
	function change_img() {
		var img = document.getElementById("img");
		img.src = "/online_ordering/img/after.jpg";
		window.document.location.href = "SupportServlet";
	}
	function choose_taste(taste){
		$('#dropdownMenu1').text(taste);
		$.post("UpdateUserTasteServlet",{taste:taste});
	}
	function click(){
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
</style>
</head>
<body style="overflow: -Scroll; overflow-x: hidden">
	<%
		Integer support = (Integer) request.getAttribute("support");
	%>
	<% 
		String taste = (String)request.getAttribute("taste");
	%>
	<%@include file="daohanglan.jsp"%>
	<div class="container-fluid" id="shop_name">
		<div class="row-fluid">
			<div class="span12"
				style="position: relative; color: gold; left: 30px;">
				<h3>
					<%=username + " 的主页 "%>
				</h3>
			</div>
		</div>
	</div>
	<div class="bs-example" style="padding-bottom: 24px;" align="center">
		<div
			style="border: 2px solid #d1e1ef; width: 980px; min-height: 100px; margin: 10px auto;">
			<div
				style="background: #f5f7f9; width: 230px; height: 420px; display: inline-block; vertical-align: top;">
				<img data-toggle="modal" data-target="#myUpdateUserTouxiang"
					onclick="" title="修改头像" alt=""
					style="width: 80px; height: 80px; margin: 50px auto 0 auto; border: 2px solid #fff; cursor: pointer"
					src="data/usertouxiang/<%=username%>.jpg" /> <br />
				<div style="color: #2c76c0; margin: 10px auto;">${username}</div>
			</div>
			<div style="display: inline-block; width: 740px; height: 100%;">
				<div style="float: right; margin: 5px 5%;">
					<img
						src="http://pr.bdimg.com/static/princess/img/edit_btn_27aeb908.png" /><a
						href="javascript:click()">编辑</a>
				</div>
				<div
					style="height: 2px; background: #eee; margin: 30px 5% 10px 5%; width: 90%;"></div>
				<style>
.table-hover td {
	border: 0 !important;
}
</style>
				<table class="table table-hover"
					style="position: relative; width: 600px; top: 15px" align="center">
					<tr>
						<td>姓名</td>
						<td><a><jsp:getProperty name="user_info" property="name" /></a></td>
					</tr>
					<tr>
						<td>送餐地址</td>
						<td><a><jsp:getProperty name="user_info"
									property="address" /></a></td>
					</tr>
					<tr>
						<td>联系电话</td>
						<td><a><jsp:getProperty name="user_info"
									property="telephone" /></a></td>
					</tr>
					<tr>
						<td>账户金额</td>
						<td title="点击编辑充值"><a><jsp:getProperty name="user_info"
									property="balance" /></a></td>
					</tr>
					<tr>
						<td>e-mail</td>
						<td><a><jsp:getProperty name="user_info" property="email" /></a></td>
					</tr>
					<tr>
						<td>qq</td>
						<td><a><jsp:getProperty name="user_info" property="qq" /></a></td>
					</tr>
					<tr>
						<td>口味偏好</td>
						<td><div class="dropdown">
						<%if(taste!=null) {%>
							<button style="padding:1px 6px 3px 6px;height:29px;width:80px"type="button" 
							class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
									<%=taste %> <span class="caret"></span>
							</button>
						<%} else {%>
						
							<button style="padding:1px 6px 3px 6px;height:29px;width:80px"type="button" 
							class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">选择口味 <span class="caret"></span>
							</button>
						<%} %>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="dropdownMenu1">
								<c:forEach items="${usertastelist}" var="taste" varStatus="i">
								<li role="presentation">
									<a role="menuitem" tabindex="-1" onclick="choose_taste('${taste}')" style="cursor:pointer">${taste}</a></li>
								</c:forEach>
							</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>修改密码</td>
						<td><button title="单击修改密码" data-toggle="modal"
								data-target="#myUpdateUserPasswordModal">修改密码</button></td>
						<div id="myUpdateUserPasswordModal" class="modal fade"
							tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
							aria-hidden="true">
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
												<input type="text" class="form-control" id="old_password"
													placeholder="请输入原密码">
											</div>
											<br> <br> <br>
											<div class="col-sm-2 control-label">新密码</div>
											<div class="col-sm-10">
												<input type="password" class="form-control"
													id="new_password" placeholder="请输入新密码">
											</div>
											<br> <br> <br>
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
											onclick="update_user_password( '${user_info.password}' )">修改</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
					</tr>
					<div id="myUpdateUserTouxiang" class="modal fade" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">修改头像</h4>
								</div>
								<div class="modal-body" id="tx_tp">
									<style>
#tx_tp {
	height: 120px;
}

#tx_tp div {
	float: left;
}

.tx_ta {
	width: 80px;
	height: 80px;
	//border: 1px solid #777;
	margin: 5px 30px;
	cursor:pointer;
}
</style>
									<div class="tx_ta">
										<img
											style="width: 80px; height: 80px; border: 2px solid #fff;"
											src="data/usertouxiang/<%=username%>.jpg" />
									</div>
									<div style="font-size: 30px; padding-top: 20px;">-></div>
									<div class="tx_ta" id="tx_output" onclick="$('#file1').click();"></div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary"
										onclick="if($('#file1').val()!==''){$('#tx_sub').submit();}">修改</button>
								</div>
								<div class="hide">
									<form action="UploadUserTouxiangServlet"
										enctype="multipart/form-data" method="post" id="tx_sub">
										选择文件<input type="file" name="file1" id="file1" accept="image/*"/> <input
											type="submit" name="upload" value="上传">
									</form>
									<script>
										function setImagePreview(input, output,
												img) {
											var docObj = document
													.getElementById(input);
											var imgObjPreview = document
													.getElementById(img);
											if (docObj.files && docObj.files[0]) {
												var i = 0;
												while (docObj.files[i]) {
													$("#" + output)
															.append(
																	'<img class="p_img" id="view'+i+'"/>');
													imgObjPreview = document
															.getElementById(img
																	+ i);
													imgObjPreview.style.display = 'block';
													imgObjPreview.style.width = '80px';
													imgObjPreview.style.height = '80px';
													imgObjPreview.src = window.URL
															.createObjectURL(docObj.files[i]);
													i++;
												}
											} else {
												//IE锟铰ｏ拷使锟斤拷锟剿撅拷                          
												docObj.select();
												var imgSrc = document.selection
														.createRange().text;
												var localImagId = document
														.getElementById(output);
												//锟斤拷锟斤拷锟斤拷锟矫筹拷始锟斤拷小                          
												localImagId.style.width = "80px";
												localImagId.style.height = "80px";
												//图片锟届常锟侥诧拷捉锟斤拷锟斤拷止锟矫伙拷锟睫改猴拷缀锟斤拷伪锟斤拷图片  
												try {
													localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
													localImagId.filters
															.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
												} catch (e) {
													alert("锟斤拷锟较达拷锟斤拷图片锟斤拷式锟斤拷锟斤拷确锟斤拷锟斤拷锟斤拷锟斤拷选锟斤拷!");
													return false;
												}
												imgObjPreview.style.display = 'none';
												document.selection.empty();
											}
											return true;
										}
										$("#file1")
												.change(
														function() {
															setImagePreview(
																	"file1",
																	"tx_output",
																	"view");
															$("#_bq")
																	.append(
																			'<div class="p_img" id="cancel" onclick="imageEmpty()"><img src="/Public/img/chat/cancel.gif"/>取消</div>');
														});
									</script>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
				</table>
				<div style="margin-bottom: 30px; width: 100%;"></div>
			</div>
		</div>
		<table class="hide">
			<tr>
				<td width=200px></td>
				<td width=200px></td>
				<td width=200px>
					<button class="btn btn-primary btn-lg" data-toggle="modal"
						data-target="#myUpdateUserModal" style="position: relative;"
						id="bjspxx">编辑个人信息</button>
				</td>
				<td width=150px></td>
				<td width=200px>
					<%
						if (support == 0) {
					%> <img id="img"
					src="/online_ordering/img/zan.jpg" title="为我们点赞"
					onclick="change_img()"> <%
 	} else if (support == 1) {
 %> <img
					id="img" src="/online_ordering/img/after.jpg" title="您已经点赞了"
					onclick=""> <%
 	}
 %>
				</td>
			</tr>
		</table>
	</div>

	<div id="myUpdateUserModal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑个人信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<div class="col-sm-2 control-label">姓名</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="update_user_name"
								placeholder="请输入您的姓名" value="${user_info.name}">
						</div>
						<br> <br> <br>
						<div class="col-sm-2 control-label">送餐地址</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="update_user_address"
								placeholder="请输入送餐地址" value="${user_info.address}">
						</div>
						<br> <br> <br>
						<div class="col-sm-2 control-label">联系电话</div>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="update_user_telephone" placeholder="请输入起送价格"
								value="${user_info.telephone}">
						</div>
						<br> <br> <br>
						<div class="col-sm-2 control-label">账户金额</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="update_user_balance"
								placeholder="给账户充点钱吧" value="${user_info.balance}">
						</div>
						<br> <br> <br>
						<div class="col-sm-2 control-label">email</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="update_user_email"
								placeholder="请输入email" value="${user_info.email}">
						</div>
						<br> <br> <br>
						<div class="col-sm-2 control-label">QQ</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="update_user_qq"
								placeholder="请输入QQ号码" value="${user_info.qq}">
						</div>
						<br>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="update_user_info('${user_info.username}')">修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<jsp:useBean id="user_info" class="com.cn.users.UsersVo"
		scope="session" />

</body>
</html>