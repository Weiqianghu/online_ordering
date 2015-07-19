<%@ page language="java" import="java.util.*" import="com.cn.store.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>吃了么</title>
<script src="http://www.w3school.com.cn/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=xbd7kUGoWOPLIzU3amV2uL5Y"></script>
<link rel="shortcut icon" href="img/chi.ico"/>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.css" rel="stylesheet">
<link href="css/register.css" rel="stylesheet">
<script>
console.log("好吃不过饺子，好用不如吃了么,全球200万美食等你来点！");
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode==27){ // 按 Esc 
        //要做的事情
      }
    if(e && e.keyCode==113){ // 按 F2 
         //要做的事情
       }            
     if(e && e.keyCode==13){ // enter 键
         //要做的事情
         $("#myd").click();
         
    }
}; 
function mouseout(id){
	//$("#map").attr("display","none");
	$("#map").remove();
}
function mouseover(id){
	var jingdu = document.getElementById(id+"_longitude").innerHTML;
	var weidu = document.getElementById(id+"_latitude").innerHTML;
	$("#shop_box_"+id).after("<div id='map'></div>");
	show_map(jingdu,weidu);
}
function show_map(jingdu,weidu){
	var map = new BMap.Map("map");
	var PointA = new BMap.Point(121.511078, 30.841849);
	map.centerAndZoom(PointA, 15);
	var PointB = new BMap.Point(jingdu, weidu);
	var distance_ = map.getDistance(PointA,PointB) + "";
	var distance = distance_.substr(0, distance_.lastIndexOf('.'))+"米";
	var infowindow = new BMap.InfoWindow(distance,{width:0,height:0,title:"距离"});
	map.openInfoWindow(infowindow,PointB);
	var polyline = new BMap.Polyline([PointA,PointB],{strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});
	map.addOverlay(polyline);
    }
$().ready(function(){
	$("img").error(function () {
	    $(this).unbind("error").attr("src", "img/cai.jpg");
	});
});
</script> 
<style>
#nav_top{
left:1060px;
}
</style>
</head>
<body>
<%Integer supportcount = (Integer)request.getAttribute("supportcount"); %>
<%@include file="daohanglan.jsp" %>
<div id="left_box">
    <div id="left_title">今日推荐<div style="float:right;cursor:pointer;" id="hide_tuijian">隐藏</div></div>
    <m>
    <k>
    <c:forEach items="${listrecommend}" var="stoc" begin="0" end="9">
    <v>
    <div class="left_recommend" onclick="location='StoreFoodServlet?store_ID=${stoc.store_ID}&store_name=${stoc.store_name}&store_state=${stoc.store_state}'">
    <img style="width:100%;height:100%;" src="data/foodpicture/${stoc.food_ID}.jpg"/>
    <div class="left_food">${stoc.food_name}</div>
    </div>
    <div class="left_money">￥${stoc.food_price}<span>销量：${stoc.sales_volume}</span></div>
    </v>
     </c:forEach>
     </k>
     <a id="hyp">换一批</a>
     </m>
</div>
<div>
<script>
var pclick=0;
$("#hide_tuijian").click(function(){
if(pclick==0){
	$(".left_food").hide(0);
	$("m").slideUp();
	
	$(this).html("显示");
	pclick=1;
}
else if(pclick==1){
	
	$("m").slideDown();
	$(".left_food").show(0);
	$(this).html("隐藏");
	pclick=0;
}
	
});
var px=0;
$("v").eq(px).show(0);
$("v").eq(px+1).show(0);

$("#hyp").click(function(){
	px+=2;
	cd=px%10;
	$("v").hide(0);
	$("v").eq(cd).show(0);
	$("v").eq(cd+1).show(0);
});

</script>
<div id="center_box">
        <div class="_box" id="search">
        <div><a  class="_choose" title="商铺名称">店名</a><a title="风味口味">口味</a><a title="商铺状态">状态</a><a title="显示所有" href="SelectStoreServlet">全部</a></div>
        <form action="SearchStoreServlet" method="get">
            <div><input name="content" type="text"/><input type="submit" value="搜索" id="myd"/></div>
            <input type="hidden" name="item" id="item" value="商铺名称"/>
        </form>
    </div>
    <script>
        $("#search a").click(function(){
            $("._choose").removeClass("_choose");
            $(this).addClass("_choose");
            $("#item").val($(this).attr("title"));
        });
    </script>
    <div class="_box" style="min-height: 450px;">
        <div class="_title"><a href="SelectStoreServlet?condition=默认">默认&nbsp;<i class="fa fa-long-arrow-down"></i></a><a href="SelectStoreServlet?condition=人气">人气&nbsp;<i class="fa fa-long-arrow-down"></i></a><a href="SelectStoreServlet?condition=评分">评分&nbsp;<i class="fa fa-long-arrow-down"></i></a></div>
        <c:forEach items="${list}" var="sto">
        <c:if test="${sto.state=='营业中'}">
			<div class="_content" onmouseover="mouseover('${sto.store_ID}')" onmouseout="mouseout('${sto.store_ID}')" style="background: #fff" id='shop_box_${sto.store_ID}' onclick="location='StoreFoodServlet?store_ID=${sto.store_ID}&store_name=${sto.store_name}&store_state=${sto.state}'">
		</c:if>
		<c:if test="${sto.state=='休息中'}">
			<div class="_content" onmouseover="mouseover('${sto.store_ID}')" onmouseout="mouseout('${sto.store_ID}')" style="background: #f3f3f3" id='shop_box_${sto.store_ID}' onclick="location='StoreFoodServlet?store_ID=${sto.store_ID}&store_name=${sto.store_name}&store_state=${sto.state}'">
		</c:if>
            <li><img style="width: 65px;height: 65px;margin-top: 5px;" src="data/touxiang/${sto.store_ID}.jpg"/></li>
            <li class="_introduce">
                <div>${sto.store_name}</div>
                <div>口味：${sto.taste}，起送价格：${sto.lowest_consume}，订单数：${sto.order_number}</div>
                <div>目前状态：${sto.state}，地址：${sto.address }</div>
                <div id="${sto.store_ID}_longitude" class="box_hide">${sto.longitude}</div>
                <div id="${sto.store_ID}_latitude" class="box_hide">${sto.latitude}</div>
            </li>
            <li class="_star">
                <div><a href="StoreFoodServlet?store_ID=${sto.store_ID}&store_name=${sto.store_name}&store_state=${sto.state}">查看</a></div>
                <div>评分：
                <ppp id="ge${sto.store_ID}"></ppp> ${sto.store_score}分
                </div>
                <script>
                for(var i=0;i<parseInt(${sto.store_score});i++){
                	$("#ge${sto.store_ID}").append('<img src="img/star_orange.gif">');
                }
                for(var i=0;i<5-parseInt(${sto.store_score});i++){
                	$("#ge${sto.store_ID}").append('<img src="img/star_white.gif">');
                }
                </script>
            </li>
        </div><!-- enen-->
        </c:forEach>
        	<%
		String show = (String) request.getAttribute("show");
	%>
        <div class="addit" id="mmss" style="display:<%=show%>">更多</div>
</div>
        
        <script>
        function getPar(par){
            //获取当前URL
            var local_url = document.location.href; 
            //获取要取得的get参数位置
            var get = local_url.indexOf(par +"=");
            if(get == -1){
                return false;   
            }   
            //截取字符串
            var get_par = local_url.slice(par.length + get + 1);    
            //判断截取后的字符串是否还有其他get参数
            var nextPar = get_par.indexOf("&");
            if(nextPar != -1){
                get_par = get_par.slice(0, nextPar);
            }
            return get_par;
        }
        function getcondition(){
        	var temp=getPar("condition");
        	if(temp==false||temp==="%E9%BB%98%E8%AE%A4"){
        		return "默认";
        	}else if(temp==="%E4%BA%BA%E6%B0%94"){
        		return "人气";
        	}else if(temp==="%E8%AF%84%E5%88%86"){
        		return "评分";
        	}
        }
        var timeclick=1;
        $(document).ready(function () {
        $("#mmss").click(function(){
        	qsb=$(this);
        	$.post(
        			"MoreStoreServlet",
        			{
        				condition:getcondition(),
        				clicktimes:timeclick
        			},
        			function(data){
        				if(data!=false&&data!==""){
            			while(data.indexOf("#")!==-1){
            				var i1=data.indexOf("#");
            				var temp=data.substring(0,i1);
            				var data=data.substring(i1+1);
            				var id=JSON.parse(temp).store_ID;
            				var mbalance=JSON.parse(temp).balance;
            				var mlow=JSON.parse(temp).lowest_consume;
            				var mnum=JSON.parse(temp).order_number;
            				var 	mstate=JSON.parse(temp).state;
            				var mname=JSON.parse(temp).store_name;
            				var 	mpsd=JSON.parse(temp).store_password;
            				var 	mscore=JSON.parse(temp).store_score;
            				var 	mphone=JSON.parse(temp).store_telephone;
            				var mtaste=JSON.parse(temp).taste;
            				var weidu=JSON.parse(temp).latitude;
            				var jingdu=JSON.parse(temp).longitude;
            				var address=JSON.parse(temp).address;
            				var  bb;
            				if(mstate==="休息中"){
            					bb="f3f3f3";
            				}else{
            					bb="fff";
            				}
            				var vo;
            				vo='<div class="_content" style="background: #'+bb+'" id="shop_box_'+id+'" onmouseover="mouseover(\''+id+'\')" onmouseout="mouseout(\''+id+'\')" " onclick="location=\'StoreFoodServlet?store_ID='+id+'&store_name='+mname+'&store_state='+mstate+'\'"><li><img style="width: 65px;height: 65px;margin-top: 5px;" src="data/touxiang/'+id+'.jpg"/></li><li class="_introduce"><div>'+mname+'</div><div>口味：'+mtaste+'，起送价格：'+mlow+'，订单数：'+mnum+'</div><div>目前状态：'+mstate+'，地址：'+address+'</div><div id="'+id+'_longitude" class="box_hide">'+jingdu+'</div><div id="'+id+'_latitude" class="box_hide">'+weidu+'</div></li><li class="_star"><div><a href="StoreFoodServlet?store_ID='+id+'&store_name='+mname+'&store_state='+mstate+'">查看</a></div><div>评分：<ppp id="ge'+mnum+'">';
            				

            	                    for(var i=0;i<parseInt(mscore);i++){
            	                    	vo+='<img src="img/star_orange.gif">';
            	                    }
            	                    for(var i=0;i<5-parseInt(mscore);i++){
            	                    	vo+='<img src="img/star_white.gif">';
            	                    }
            	                    vo+='</ppp> '+mscore+'分 </div></li>';
            	                    qsb.before(vo);
            	                    //$("#bxbx").append(vo);
            	                    $("img").error(function () {
            	                	    $(this).unbind("error").attr("src", "img/cai.jpg");
            	                	});  
            			}
            			

            			timeclick++;
            		}
        		    });
        });
        });
        </script>
    </div>
</div>
<div id="right_box">
    <div class="_box2">
        <div class="_title">今日推荐</div>
    </div>
</div>
<a id="nav_top" onclick="pageScroll()" style="cursor: pointer; display: inline;border:1px solid #eef7fa;" title="回到页头"><span>▲</span></a>
</body>
</html>