<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String username=(String)session.getAttribute("username");
String whichone=(String)session.getAttribute("whichone");
if(username==null){
%>
<nav class="navbar" role="navigation" style="background:#ff6b4e;border:none;position:fixed;width:100%;top:0;" >
   <div class="">
      <a class="navbar-brand" href="SelectStoreServlet" style="position:relative;left:15px; font-size:25px;color:#fff;">吃了么</a>
   </div>
   <div>
      <ul class="gee" style="position:relative;right:50px;float:right;">
         <li><a href="login.jsp" style="color:#fff;">用户登陆</a></li>
         <li><a href="register.jsp" style="color:#fff;">用户注册</a></li>
         <li><a href="shop_register.jsp" style="color:#fff;">商铺注册</a></li>
      </ul>
   </div>

</nav>
<% }else if(whichone.equals("user")){ %>
<nav class="navbar navbar-inverse" style="background:#ff6b4e;border:none;position:fixed;width:100%;top:0;" role="navigation">
   <div class="" >
      <a class="navbar-brand" href="SelectStoreServlet" style="position:relative;left:15px;font-size:25px;color:#fff;">吃了么</a>
   </div>
   <div>
      <p class="navbar-text" style="position:relative;margin-left: 60px;color:#fff;">您好 <%=username %></p>
   </div>
   <div>
      <ul class="gee" style="position:relative;right:50px;float:right;">
         <li><a href="UserOrderListServlet" style="color:#fff;">我的饿单</a></li>
         <li><a href="UserPageServlet" style="color:#fff;">我的主页</a></li>
         <li><a href="quit.jsp" style="color:#fff;" >退出</a></li>
      </ul>
   </div>

</nav>
<%} else if(whichone.equals("shop")) {%>
<nav class="navbar" role="navigation" style="background:#ff6b4e;border:none;position:fixed;width:100%;top:0;">
   <div class="">
      <a class="navbar-brand" href="SelectStoreServlet" style="position:relative;left:15px;font-size:25px;color:#fff;">吃了么</a>
   </div>
   <div>
      <p class="navbar-text" style="position:relative;margin-left: 60px;color:#fff;">您好 <%=username %></p>
   </div>
   <div>
      <ul class="gee" style="position:relative;right:50px;float:right;">
         <li><a href="StoreFoodInfoServlet" style="color:#fff;">商铺主页</a></li>
         <li><a href="quit.jsp"style="color:#fff;">退出</a></li>
      </ul>
   </div>
</nav>
<%} else if(whichone.equals("admin")) {%>
<nav class="navbar" role="navigation" style="background:#ff6b4e;border:none;position:fixed;width:100%;top:0;">
   <div class="">
      <a class="navbar-brand" href="SelectStoreServlet" style="position:relative;left:15px;font-size:25px;color:#fff;">吃了么</a>
   </div>
   <div>
      <p class="navbar-text" style="position:relative;margin-left: 60px;;color:#fff;">您好 <%=username %></p>
   </div>
   <div>
      <ul class="gee" style="position:relative;right:50px;float:right;">
      	 <li><a href="AdminServlet" style="color:#fff;">管理员主页</a></li>
         <li><a href="quit.jsp" style="color:#fff;">退出</a></li>
      </ul>
   </div>
</nav>
<%} %>
<div style="width:100%;height:50px;"></div>
<script>
	$(".navbar").css("margin-bottom","1px");
	$("body").css("font-family", "'Microsoft Yahei','Tahoma','SimSun'");
	$().ready(function(){
		$("body").append('<footer style="background: #ccc;"><div class="container" style="line-height:40px; vertical-align:middle; text-align:center">沪ICP备05065083号 &copy; ATA 2012-2022 all rights reserved</div></footer>');
		var footer_m=screen.availHeight-$('footer').offset().top-118;
		if(footer_m>0){
		$("footer").css("marginTop",footer_m+"px");
		}
	});
	function pageScroll(){
	    //把内容滚动指定的像素数（第一个参数是向右滚动的像素数，第二个参数是向下滚动的像素数）
	    window.scrollBy(0,-100);
	    //延时递归调用，模拟滚动向上效果
	    scrolldelay = setTimeout('pageScroll()',100);
	    //获取scrollTop值，声明了DTD的标准网页取document.documentElement.scrollTop，否则取document.body.scrollTop；因为二者只有一个会生效，另一个就恒为0，所以取和值可以得到网页的真正的scrollTop值
	    var sTop=document.documentElement.scrollTop+document.body.scrollTop;
	    //判断当页面到达顶部，取消延时代码（否则页面滚动到顶部会无法再向下正常浏览页面）
	    if(sTop==0) clearTimeout(scrolldelay);
	}
</script>
<!--<div style="width:100%;height:1px;background:#b2d7fe;"></div>-->
<style>
.navbar{
z-index:999;
}
.gee{
	margin:0!important;
}
.gee li{
	
    display: inline-block;
    padding: 15px 8px 13px 8px;
}
body{
background:rgba(199,229,241,0.3);
}
</style>
