<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE>登陆</TITLE>
<link rel="shortcut icon" href="img/chi.ico"/>
<link href="css/login_regist.css" type="text/css" rel="stylesheet" />
<script src="jquery-1.8.0.js" type="text/javascript">
<script src="http://www.w3school.com.cn/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<style>
.tk{
display:none;
}
</style>
<script type="text/javascript">
	var email_ok=0,password_ok=0;//三个判定变量
	function btnReg() {
		var username = $("#username").val();
		var password = $("#password").val();
		var whichone = $("input[name='radio']:checked").val();
		var remember = document.getElementById('remember').checked;
		if (username.length == 0) {
			$("#empty_username").removeClass("hide");
		} else if (password.length == 0) {
			$("#empty_password").removeClass("hide");
		} else if (username.length != 0 && password.length != 0) {
			$.ajax({
				type : "post",
				url : "loginServlet",
				dataType : "json",
				data : "username=" + username + "&password=" + password
						+ "&whichone=" + whichone + "&remember=" + remember,
				success : function(login) {
					var response = eval(login);
					if (response['login'] == "error") {
						alert("用户名或密码错误");
					} else if (response['login'] == "ok" && whichone=="user" && username != "admin") {
						location.href = "SelectStoreServlet";
					}
					else if(response['login'] == "ok" && username == "admin"){
						location.href = "AdminServlet";
					}
					else if(response['login'] == "ok" && whichone=="shop"){
						location.href="StoreFoodInfoServlet";
					}
				},
				error : function() {
					alert("验证出错");
				}
			});
		}
	}
	function username_onclick(){
		$("#empty_username").addClass("hide");
	}
	function password_onclick(){
		$("#empty_password").addClass("hide");
	}
	function setCookie(name,value) 
	{ 
	    var Days = 30; 
	    var exp = new Date(); 
	    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
	    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
	} 

	function getCookie(name) 
	{ 
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	 
	    if(arr=document.cookie.match(reg))
	 
	        return unescape(arr[2]); 
	    else 
	        return null; 
	} 

	function delCookie(name) 
	{ 
	    var exp = new Date(); 
	    exp.setTime(exp.getTime() - 1); 
	    var cval=getCookie(name); 
	    if(cval!=null) 
	        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
	} 

	delCookie('username');
	delCookie('password');
	delCookie('whichone');
	function forget_passport(){
		$('.tk').show();
	}
</script>
</HEAD>
<BODY>
<a href="SelectStoreServlet">
	<img src="img/login-logo.png" style="margin: 5px 0 5px 50px;" />
	</a>
	<br>
	<div class="container">
		<img src="img/login-picture.jpg"
			style="margin: 60px 0 0 50px; float: left ;display: block;" />
		<div class='panel_login'>
			<div class="panel-hd" id="login-panel-hd">
				<h2 class="title" style="font-weight: bold; margin: 0 0 10px 0">登录</h2>
			</div>

			<div class="panel-bd">
				<form method="post">
					<div class="form-item">
						<input class="inputbox" name="username" type="text"
							placeholder="用户名" id='username' onclick="username_onclick()">
					</div>
					<div class="username-error-tip hide" id="empty_username">请输入用户名</div>
					<div class="form-item" style="margin: 0 0 15px 0">
						<input class="inputbox" name="password" type="password"
							placeholder="密码" id='password' onclick="password_onclick()">
					</div>
					<div class="password-error-tip hide" id="empty_password">请输入密码</div>
					<div>
						<div class="controls">
							<input id="user_button" type="radio" name="radio" value="user" checked="checked">订餐用户
							<input id="shop_button" type="radio" name="radio" value="shop">商铺用户
						</div>
					</div>
					<label class="login-checkbox" style="margin: 15px 0 15px 0">
						<input class="login-remember" type="checkbox" id="remember" value="remember">
						下次自动登录
					</label>
					<input type="button" class="g-btn g-btn-l g-btn-submit"
						style="margin-bottom: 20px" onclick="btnReg()" value="登陆">
					<div style="float: right; font-size: 14px; color: #3199e8">
						<a href="javascript:forget_passport();">忘记密码</a>&nbsp;&nbsp;&nbsp;</a><a href="register.jsp">免费注册</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div style="width:100%;height:100%;position:fixed;background:rgba(0,0,0,0.3);top:0;" class="tk"></div>
	<div style="width:500px;z-index:990;position:fixed;margin-left:-250px;top:100px;left:50%;background:#fff;"  class="tk">
	<h2 style="text-align:center;">找回密码</h2>
	<form>
	<div style="width:300px;margin:0 auto;">
		<input type="email" style="padding: 9.5px 10px;height: 42px;width: 300px;font-size: 14px;line-height: 1.5;
		margin:10px auto;"  value="" placeholder="输入邮箱" name="email" id="email"/>
	</div>
	<div style="width:300px;margin:0 auto;"><input type="text" style="padding: 9.5px 10px;height: 42px;width: 160px;
	font-size: 14px;line-height: 1.5;margin:10px auto;"  value="" placeholder="输入验证码" name="yzm" id="yzm"/>
		<input type="button"style="width:100px;height:42px;background-color: #3199e8;
		color: #fff;border:none;margin-left:20px;"  value="获取" id="huoqu">
	</div>
	<div style="width:300px;margin:0 auto;"><input type="password" style="padding: 9.5px 10px;height: 42px;width: 300px;
		font-size: 14px;line-height: 1.5;margin:10px auto;"  value="" placeholder="重置密码" name="password" id="password_reset" maxlength="20" /></div>
	
	<div style="width:300px;margin:0 auto;">
						<input type="button" class="g-btn g-btn-l g-btn-submit"
						style="width:100px;margin:10px 20px;display:inline-block;"  value="确认" id="ok_button">
						<input type="button" class="g-btn g-btn-l g-btn-submit"
						style="width:100px;margin:10px 20px;display:inline-block;"  value="取消" onclick="$('.tk').hide()"></div>
						</form>
	</div>
	<script>
	var time;
	$('#huoqu').click(function(){
		var email = $("#email").val();
		if(email=='') $("#email").css("border-color","#ff0000");
		else if(email != '' && !/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(email)) 
			$("#email").css("border-color","#ff0000");
		else
		$.ajax({
			type : "post",
			url : "ValidateUpdatePasswordServlet",
			data : {email : email},
			success : function(res){
				if(res=="ok"){
					$("#email").css("border-color","#66ff00");
					email_ok=1;
					$("#huoqu").prop("disabled","disabled");
					time=60;
					ttime();
				}
				else if(res=="fail"){
					$("#email").css("border-color","#ff0000");
				}
				else alert("返回值错误");
			},
			
		});
	});
	$('#password_reset').focusout(function(){
		if($(this).val().length<6){
			$(this).css("border-color","#ff0000");
		}
		else{
			$(this).css("border-color","#66ff00");
			password_ok=1;
		}
	});
	$('#ok_button').click(function(){
		if(email_ok!=1 || password_ok!=1){
			alert("请正确填写信息");
		}
		else
		$.ajax({
			type : "post",
			url :"ValidateServlet",
			data : {email : $("#email").val(),testcode : $('#yzm').val()},
			success : function(res){
				if(res=="ok"){
					$.post("UpdateValidatePasswordServlet",{email : $("#email").val(),password : $('#password_reset').val()},function(){
						location.href="login.jsp"
					});

				}
				else if(res=="fail"){
					$('#yzm').css("border-color","#ff0000");
				}
				else alert("验证返回值出错");
			},
			error : function(){
				alert("验证error");
			}
		});
	});
	  function ttime(){
	    	time--;
			$("#huoqu").val("重新发送("+time+"s)");
			
			if(time>0){
				setTimeout("ttime()", 1000);
			}else{
				$("#huoqu").prop("disabled","");
			}
		}
		

	</script>
</BODY>
</HTML>