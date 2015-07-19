<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="img/chi.ico"/>
<link
	href="bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrapb.min.js"
	type="text/javascript"></script>
	<link href="css/register.css" rel="stylesheet">
<script>
	function cancel_order(order_time) {
		$.ajax({
			type : "post",
			url : "CancelOrderlistServlet",
			dataType : "json",
			data : "order_time=" + order_time,
			success : function(res) {
				var response = eval(res);
				if (response['res'] == "handle") {
					alert("您的订单已经被处理，不能退货！");
				} else if (response['res'] == "ok") {
					alert("您的订单已经被取消");
				} else {
					alert("您的订单已经是取消状态了");
				}
				document.location.reload();
			},
			error : function() {
				alert("error");
			}
		});

	}
	function comment(order_time, i) {
		$.ajax({
			type : "post",
			url : "BeforeAddCommentServlet",
			dataType : "json",
			data : "order_time=" + order_time,
			success : function(res) {
				var response = eval(res)
				if (response['res'] == "ok") {
					$("#modal_trigger_" + i).click();
				} else {
					alert("因本次订单的状态您不能评论");
				}
			},
			error : function() {
				alert("error");
			}
		});
	}
	
	function countChar(textareaName, spanName) {
		var new_length = 50 - document.getElementById(textareaName).value.length;
		if (new_length >= 0)
			document.getElementById(spanName).innerHTML = new_length;
		else {
		}
	}
	function submit_comment(i, order_time) {
		var content = $('#comment_area_' + i).val();
		var score = $('#comment_score_' + i).val();
		$.ajax({
			type : "post",
			url : "AddCommentServlet",
			dataType : "json",
			data : "content=" + content + "&order_time=" + order_time
					+ "&score=" + score,
			success : function(res) {
				var response = eval(res)
				if (response['res'] == "ok") {
					$('#comment_area_' + i).val("");
					alert("感谢您的评论");
				} else if (response['res'] == "notcomment") {
					alert("评论失败");
				}
			},
			error : function() {
				alert("error");
			}
		});
	}
	function submit_complain(i, user_telephone,order_time){
		var content = $('#complain_area_' + i).val();
		$.post("ComplaintStoreServlet",{complaint_reason:content,user_telephone:user_telephone,order_time:order_time},
			function(result){
			if(result=="ok")
			alert("投诉已经提交给管理员");
			else if(result=="fail") alert("投诉失败");
			else alert("不能多次投诉");
		});
	}
</script>
<title>My Order</title>
</head>
<body style="overflow: -Scroll; overflow-x: hidden">
<a id="nav_top" onclick="pageScroll()" style="cursor: pointer; display: inline;border:1px solid #eef7fa;left:1200px" title="回到页头"><span>▲</span></a>
	<%@include file="daohanglan.jsp"%>
	<div class="span12"
		style="position: relative; color: gold; left: 30px;">
		<h3>
			<%=username + " 的饿单"%>
		</h3>
	</div>
	<c:if test="${param['empty']=='no'}">
	<c:forEach items="${userorder_list}" var="sto" varStatus="i">
		<div class="dindan"
			style="border: 1px solid #bfbfbf; width: 980px; margin: 20px auto;">
			<li style="background: #f5f5f5; padding: 8px 0;"><span><input
					type="checkbox" style="vertical-align: text-top;" />&nbsp;
					${sto.order_time}</span><span>订单状态：${sto.order_state}</span><span>电话：${sto.telephone}</span><span>地址：${sto.address}</span>
			<div style="float: right; margin-right: 10px; cursor: pointer;"
					onclick="cancel_order('${sto.order_time}')">取消订单</div></li>
			<li class="_mainbox">
				<div
					style="display: inline-block; width: 711px; vertical-align: middle">
					<c:forEach items="${sto.foodlist}" var="stoo">
						<zdy>
						<ul>
							<img src="data/foodpicture/${stoo.food_ID}.jpg" style="width: 65px; height: 70px; margin-top: 13px;"
								alt="${stoo.name}" />
						</ul>
						<ul style="width: 250px;">${stoo.name}</ul>
						<ul>${stoo.price}元*${stoo.num}份</ul>
						<ul style="width: 220px;">${stoo.store_name}（电话：${stoo.store_telephone}）</ul>
						</zdy>
					</c:forEach>
				</div>
				<div class="hou"
					style="display: inline-block; vertical-align: middle; border-left: 1px solid #e8e8e8;">
					<div style="height: 50%; margin-bottom: -20px;"></div>
					<ul>合计：${sto.total_price}元
					</ul>
					<ul class="sov" style="padding-left: 20px; cursor: pointer">
						<div title="我要评论" onclick="comment('${sto.order_time}','${i.index}')">我要评论</div>
						<div title="投诉卖家" data-toggle="modal" data-target="#myComplainModal_${i.index}">投诉卖家</div>
					</ul>
				</div>
			</li>
		</div>
		<div id="modal_trigger_${i.index}" data-target="#Modal_${i.index}"
			data-toggle="modal" style="display: none"></div>
		<div id="Modal_${i.index}" class="modal fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<b>我的评论</b>
						</h4>
					</div>
					<div class="modal-body"></div>
					<form align="right" class="score_me">
						<textarea
							style="margin: 0 20px; width: 530px; margin-bottom: 10px;"
							id="comment_area_${i.index}" class="form-control" cols=90 rows=4
							placeholder="请输入评论" maxlength=50
							onkeydown='countChar("comment_area_${i.index}","counter_${i.index}");'
							onkeyup='countChar("comment_area_${i.index}","counter_${i.index}");'></textarea>
						<div style="float:right;margin-right:50px;">
						         <span style="font-size: 14px;" id="counter_${i.index}">0</span>
						         <span style="font-size: 14px; ">/50</span>
							</div>
						<div style="text-align: left; margin-left: 20px;"
							class="video_score">
							评分： <img src="img/star_orange.gif" title="1"> <img
								src="img/star_orange.gif" title="2"> <img
								src="img/star_orange.gif" title="3"> <img
								src="img/star_orange.gif" title="4"> <img
								src="img/star_orange.gif" title="5">

						</div>
						<input type="hidden" name="score" class="myscore" value="5"
							id="comment_score_${i.index}" />
					</form>
					<div class="modal-footer" style="border: none;!important">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="">取消</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							onclick="submit_comment('${i.index}','${sto.order_time}')">提交</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		
		
		
		<div id="myComplainModal_${i.index}" class="modal fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<b>投诉卖家</b>
						</h4>
					</div>
					<div class="modal-body"></div>
					<form align="right" class="score_me">
						<textarea
							style="margin: 0 20px; width: 530px; margin-bottom: 10px;"
							id="complain_area_${i.index}" class="form-control" cols=90 rows=4
							placeholder="请输入投诉原因" maxlength=50
							onkeydown='countChar("complain_area_${i.index}","complain_counter_${i.index}");'
							onkeyup='countChar("complain_area_${i.index}","complain_counter_${i.index}");'></textarea>
						<span style="font-size: 14px;" id="complain_counter_${i.index}">0</span>
						<span style="font-size: 14px; margin-right: 30px;">/50</span>
					</form>
					<div class="modal-footer" style="border: none;!important">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="">取消</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							onclick="submit_complain('${i.index}','${sto.telephone}','${sto.order_time}')">提交</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>	
		
	</c:forEach>
</c:if>
<c:if test="${param['empty']=='yes'}">
<div class="container-fluid" style="position:relative;top:30px;" align="center">
	<div class="row-fluid">
		<div class="span12">
			<div class="hero-unit" style="width:460px;">
			<img src="img/lai.jpg" style="width:120px;"/>				
					<span style="font-size:24px;margin-left:30px;">亲，来一份外卖吧！</span>

				<br><br>
				
				<br>
				<p>
					<a class="btn btn-primary btn-large" href="SelectStoreServlet">我要点单</a>
				</p>
				<br>
			</div>
		</div>
	</div>
</div>
</c:if>
	<script>
		$(".video_score img").hover(
				function() {
					$(this).prevAll("img").prop("src", "img/star_orange.gif");
					$(this).nextAll("img").prop("src", "img/star_white.gif");
					$(this).prop("src", "img/star_orange.gif");
				},
				function() {
					var temp = $(this).parent().nextAll(".myscore").val();
					$(this).parent().children().prop("src",
							"img/star_white.gif");
					for (var i = 0; i < Math.floor(temp); i++) {
						$(this).parent().children().eq(i).prop("src",
								"img/star_orange.gif");
					}
				});

		$(".video_score img").click(function() {
			$(this).prevAll("img").prop("src", "img/star_orange.gif");
			$(this).nextAll("img").prop("src", "img/star_white.gif");
			$(this).prop("src", "img/star_orange.gif");
			var temp = $(this).attr("title");
			$(this).parent().nextAll(".myscore").val(temp);
		});

		$(document).ready(function() {
			$(".hou").height(function() {
				return $(this).prev().height()
			});
		});
	</script>
<style>
.dindan li {
	list-style-type: none;
}

.dindan li span {
	margin-left: 30px;
	color: #363636;
}

._mainbox {
	padding: 5px 10px;
}

._mainbox ul {
	display: inline-block;
	padding-left: 0;
	min-width: 110px;
	text-align: center;
	vertical-align: middle;
}

zdy {
	border-bottom: 1px solid #e8e8e8;
	line-height: 80px;
	width: 100%;
	padding: 40px 0;
}

.sov div:nth-child(1) {
	background: #ffe4d0;
	padding: 3px 6px;
	margin: 3px 0;
	color: #E5511D;
	border: 1px solid;
	border-color: #F0CAB6;
}

.sov div:nth-child(2) {
	font-size: 13px;
	margin-top: 5px;
	color: grey;
}

.sov div:hover {
	color: #f40;
}

.score_me img {
	cursor: pointer;
}
</style>

</body>
</html>
