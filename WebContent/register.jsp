<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE>注册用户</TITLE>
<link href="css/login_regist.css" type="text/css" rel="stylesheet">
<link rel="shortcut icon" href="img/chi.ico"/>
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<script src="http://www.w3school.com.cn/jquery/jquery-1.11.1.min.js"
	type="text/javascript"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
	var username_ok=0,password_ok=0,password_again_ok=0,email_ok=0,yanzheng_ok=0;
	function isExists(obj) {//验证用户名是否重复
		var username = $("#username").val();
		var location = window.location.pathname;
		var strangCode = "~!@#$%^&*()+{}:\"\".,/?<>: ''[]\\￥（）“”。，";
		var temp;
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
		if (username.length == 0){
			if($("#empty_username").text()!="请输入用户名")
				$("#empty_username").text("请输入用户名");
			$("#empty_username").removeClass("hide");
			$("#username").css("border-color", "#ff0000");
		}
		else
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
						$("#username").css("border-color","#66ff00");
						$("#empty_username").addClass("hide");
						username_ok=1;
					}
				},
				error : function() {
					alert("验证出错");
				}
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
		else{
			$("#password_again").css("border-color", "#ff0000");
			$("#empty_password_again").addClass("hide");
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
	
	function btnReg(){//提交函数
		var username=$("#username").val();
		var password=$("#password").val();
		var email=$('#mail').val();
		var username_color=$("#username").css("border-color");
		var password_color=$("#password").css("border-color");
		var password_again_color=$("#password_again").css("border-color");
		//var right_color="rgb(102, 255, 0)";
		/*if(username_color!=right_color || password_color!=right_color || password_again_color!=right_color 
			|| email_ok==0 || yanzheng_ok==0)*/
		if(username_ok!=1 || password_ok!=1 || password_again_ok!=1 ||  email_ok!=1 || yanzheng_ok!=1){
			alert("请正确填写信息");
			return false;
		}
		else
		$.ajax({
			type:"post",
			url:"AddUserServlet",
			dataType:"json",
			data:"username="+username+"&password="+password+"&email="+email,
			beforeSend : function(){
				$("#regist_button").val("正在提交");
			},
			success:function(reg){
				var register=eval(reg);
				if(register['reg']=="ok"){
					alert("注册成功");
					location.href = "login.jsp";
				}
				else{
					$("#regist_button").val("注册出错");
					$("#regist_button").css("background-color","#ff0000");
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){alter("注册出错");}
		});
	}
	
</script>
</HEAD>
<body>
	<a href="SelectStoreServlet">
	<img src="img/login-logo.png" style="margin: 5px 0 5px 50px;" />
	</a>
	<br>
	<div class="container">
		<img src="img/regist-picture.jpg"
			style="margin: 60px 50px 0 100px; float: right" />
		<div class='panel_regist'>
			<div class="panel-hd" id="login-panel-hd">
				<h2 class="title" style="font-weight: bold; margin: 0 0 10px 0">注册</h2>
			</div>

			<div class="panel-bd">
				<form method="post">
					<div class="form-item">
						<input class="inputbox" name="username" type="text"
							placeholder="用户名" id='username' onblur="isExists(this)"
							onclick="username_onclick()"  maxlength="20">
					</div>
					<div class="username-error-tip hide" id="empty_username" >请输入用户名</div>

					<div class="form-item" style="margin: 0 0 15px 0">
						<input class="inputbox" name="password" type="password"
							placeholder="密码" id='password' onblur="check_pswd()"
							onclick="password_onclick()" maxlength="20">
					</div>
					<div class="password-error-tip hide" id="empty_password" >请输入密码</div>

					<div class="form-item" style="margin: 0 0 15px 0">
						<input class="inputbox" name="password" type="password"
							placeholder="确认密码" id='password_again'
							onblur="check_pswd_again()" onclick="password_again_onclick()" maxlength="20">
					</div>

					<div class="form-item" style="margin: 0 0 15px 0">
						<input class="inputbox_email" name="mail" type="text"
							placeholder="注册邮箱" id='mail'>
							<input type="button" value="获取" class="g-btn-submit" title="点击获取验证码"
							style="width:95px;height: 40px;border:none;margin-left:5px;" id="get_yzm"/>
					</div>
					
					<div class="email-error-tip hide" id="email_error">邮箱已注册</div>
					<div class="yanzheng-error-tip hide" id="yanzheng_error">验证错误</div>
					<div class="form-item" style="margin: 0 0 25px 0;">
						<input class="inputbox" name="yzm" type="text"
							placeholder="邮箱验证码" id='mail_again' style="width:180px;">
							 <input type="button" value="验证" class="g-btn-submit" style="width:95px;height: 40px;border:none;margin-left:5px;" id="yz"/>
					</div>
					<div class="password_again-error-tip hide"
						id="empty_password_again">密码不一致</div>
					
					<input type="button" class="g-btn g-btn-l g-btn-submit" id="regist_button"
						style="margin-bottom: 20px" value="注册" onclick="btnReg()">
					
					<div style="float: right; font-size: 14px; color: #3199e8">
						<a href="login.jsp">登陆</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
	var time;
    function ttime(){
    	time--;
		$("#get_yzm").val("重新发送("+time+"s)");
		
		if(time>0){
			setTimeout("ttime()", 1000);
		}else{
			$("#get_yzm").prop("disabled","");
			$("#get_yzm").val("重新获取");
		}
	}
    
    $("#get_yzm").click(function(){
    	var email = $("#mail").val();
    	
    	if (email=='') {
   		 $("#email_error").text("邮箱不能为空");
   		 $("#email_error").removeClass("hide");
   		 $('#mail').css("border-color", "#ff0000");
   	 }
   	 else if (email != '' && !/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(email)){
   		 $("#email_error").text("邮箱格式不正确");
   		 $("#email_error").removeClass("hide");
   		 $('#mail').css("border-color", "#ff0000");
   	 }
   	 else{
   	 $.ajax({
				type : "post",
				url : "ValidateEmailServlet",
				data : {email:email},
				success : function(res) {
					if(res=="ok"){
						$('#mail').css("border-color", "#66ff00");
						$("#email_error").addClass("hide");
						var tk=$(this);
						tk.prop("disabled","disabled");
						tk.val("重新发送(60s)");
						time=60;
						ttime();
						email_ok=1;
					}else if(res=="exist"){
						if($("#email_error").text()!="邮箱已注册") $("#email_error").text("邮箱已注册");
						$('#mail').css("border-color", "#ff0000");
						$("#email_error").removeClass("hide");
					}else alert("error");
				},
				error : function() {
					alert("邮箱验证出错");
				}
			});
   	 }
    });
	$("#yz").click(function(){
		$.ajax({
			type:"post",
			url:"ValidateServlet",
			data:{email: $("#mail").val(), testcode: $("#mail_again").val()},
			success: function(res){
				if(res=="ok"){
					$('#mail_again').css("border-color","#66ff00");
					$("#yanzheng_error").addClass("hide");
					yanzheng_ok=1;
				}
				else if(res=="fail"){
					$('#mail_again').css("border-color","#ff0000");
					$("#yanzheng_error").removeClass("hide");
				}
			},
			error: function(){
				alert("验证码验证出错");
			}
		});
	});
	</script>
</body>
</html>