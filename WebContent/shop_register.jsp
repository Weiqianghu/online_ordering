<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE>注册商铺</TITLE>
<link href="css/login_regist.css" type="text/css" rel="stylesheet">
<link rel="shortcut icon" href="img/chi.ico"/>
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<script src="http://www.w3school.com.cn/jquery/jquery-1.11.1.min.js"
	type="text/javascript"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
var username_ok=0,password_ok=0,password_again_ok=0,shop_name_ok=0,taste_ok=0,price_ok,telephone_ok=0;
	function isExists(obj) {//验证用户名是否重复
		var username = $("#username").val();
		var location = window.location.pathname;
		var strangCode = "~!@#$%^&*()+{}:\"\".,/?<>: '' []\\￥（）“”。，";
		var temp;
		if (username.length == 0) {
			if ($("#empty_username").text() != "请输入用户名")
				$("#empty_username").text("请输入用户名");
			$("#empty_username").removeClass("hide");
			$("#username").css("border-color", "#ff0000");
			return false;
		}
		for (var i = 0; i<username.length; i++ ){
			 temp = username.substring(i,i+1);
		        if (strangCode.indexOf(temp) !=-1)
		        {
		        	$("#empty_username").text("用户名中含有非法字符");
		        	$("#empty_username").removeClass("hide");
					$("#username").css("border-color", "#ff0000");
		        	return false;
		        }
		 }
			$.ajax({
				type : "post",
				url : "ExistsServlet",
				dataType : "json",
				data : "username=" + username + "&location=" + location,
				success : function(res) {
					var response = eval(res);
					if (response['res'] == "exist") {
						$("#empty_username").text("用户名已经存在");
						$("#empty_username").removeClass("hide");
						$("#username").css("border-color", "#ff0000");
					} else if (response['res'] == "ok") {
						$("#username").css("border-color", "#66ff00");
						$("#empty_username").addClass("hide");
						username_ok=1;
					}
				},
				error : function() {
					alert("验证出错");
				}
			});
	}
	function btnReg(){//提交函数
		var username=$("#username").val();
		var password=$("#password").val()
		var shop_name=$("#shop_name").val();
		var taste=$("#taste").val();
		var price=$("#price").val();
		var telephone=$("#telephone").val();
		if(username_ok!=1 || password_ok!=1 || password_again_ok!=1 || shop_name_ok!=1 || taste_ok!=1 || price_ok!=1 || telephone_ok!=1)
		{
			alert("请正确填写信息");
			return false;
		}else
		$.ajax({
			type:"post",
			url:"AddStoreServlet",
			dataType:"json",
			data:"store_ID="+username+"&store_password="+password+"&store_name="+shop_name+"&taste="+taste+"&price="+price+"&store_telephone="+telephone,
			success:function(reg){
				var register=eval(reg);
				if(register['reg']=="ok"){
					alert("注册成功");
					location.href = "login.jsp";
				}
				else{
					alert(reg);
					alert("注册失败");
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){alter("注册出错");}
		});
	}
	function check_pswd() {
		var password = $("#password").val();
		if (password.length == 0) {
			if($("#empty_password").text() == "密码6-20位")
				$("#empty_password").text("请输入密码");
			$("#empty_password").removeClass("hide");
			$("#password").css("border-color", "#ff0000");
		}else if (password.length < 6 || password.length > 20){
			$("#empty_password").text("密码6-20位");
			$("#empty_password").removeClass("hide");
			$("#password").css("border-color", "#ff0000");
		} 
		else {
			$("#password").css("border-color","#66ff00");
			$("#empty_password").addClass("hide");
			password_ok=1;
			return true;
		}
	}
	function check_pswd_again() {
		var password = $("#password").val();
		var password_again = $("#password_again").val();
		if (password == password_again && password_again.length!=0) {
			$("#empty_password_again").addClass("hide");
			$("#password_again").css("border-color", "#66ff00");
			password_again_ok=1;
		} else if(password != password_again && password_again.length!=0){
			$("#empty_password_again").removeClass("hide");
		}
		else 
			$("#password_again").css("border-color", "#ff0000");
	}
	function check_other(obj) {
		var this_id = $(obj).attr("id");
		if (obj.value.length == 0) {
			$(obj).css("border-color", "#ff0000");
		} else {
			$(obj).css("border-color", "#66ff00");
			if(this_id == "shop_name") shop_name_ok=1;
			else if(this_id == "taste") taste_ok=1;
			else if(this_id == "price") price_ok=1;
			else telephone_ok=1;
			return true;
		}
	}
	function username_onclick() {
		$("#empty_username").addClass("hide");
	}
	function password_onclick() {
		$("#empty_password").addClass("hide");
	}
	function password_again_onclick() {
		$("#empty_password_again").addClass("hide");
	}
</script>
</HEAD>
<body>
	<a href="SelectStoreServlet">
	<img src="img/login-logo.png" style="margin: 5px 0 5px 50px;" />
	</a>
	<br>
	<div class="container">
		<img src="img/regist-picture2.jpg"
			style="margin: 60px 40px 0 100px; float: right" />
		<div class='panel_regist'>
			<div class="panel-hd" id="login-panel-hd">
				<h2 class="title" style="font-weight: bold; margin: 0 0 10px 0">注册商铺</h2>
			</div>

			<div class="panel-bd">
				<form method="post">
					<div class="form-item">
						<input class="inputbox" name="username" type="text"
							placeholder="用户名" id='username' onblur="isExists(this)"
							onclick="username_onclick()"  maxlength="30">
					</div>
					<div class="username-error-tip hide" id="empty_username">请输入用户名</div>

					<div class="form-item" style="margin: 0 0 15px 0">
						<input class="inputbox" name="password" type="password"
							placeholder="密码" id='password' onblur="check_pswd()"
							onclick="password_onclick()"  maxlength="20">
					</div>
					<div class="password-error-tip hide" id="empty_password">请输入密码</div>

					<div class="form-item" style="margin: 0 0 15px 0">
						<input class="inputbox" name="password" type="password"
							placeholder="确认密码" id='password_again'
							onblur="check_pswd_again()" onclick="password_again_onclick()"  maxlength="20">
					</div>
					<div class="password_again-error-tip hide"
						id="empty_password_again">密码不一致</div>

					<div class="form-item" style="margin: 0 0 15px 0">
						<input class="inputbox" name="shop_name" type="text"
							placeholder="商铺名称" id='shop_name' onblur="check_other(this)">
					</div>
					<div class="form-item" style="margin: 0 0 15px 0">
						<input class="inputbox" name="taste" type="text"
							placeholder="口味、菜式" id='taste' onblur="check_other(this)">
					</div>
					<div class="form-item" style="margin: 0 0 15px 0">
						<input class="inputbox" name="price" type="text"
							placeholder="起送价格" id='price' onblur="check_other(this)">
					</div>
					<div class="form-item" style="margin: 0 0 25px 0">
						<input class="inputbox" name="telephone" type="text"
							placeholder="联系电话" id='telephone' onblur="check_other(this)">
					</div>
					
					<input type="button" class="g-btn g-btn-l g-btn-submit"
						id="regist_button" style="margin-bottom: 20px" value="注册"
						onclick="btnReg()">
					<div style="float: right; font-size: 14px; color: #3199e8">
						<a href="login.jsp">登陆</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>