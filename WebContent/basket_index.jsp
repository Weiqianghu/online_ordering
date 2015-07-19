<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cn.salesfood.OrderingBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Basket</title>
<link rel="shortcut icon" href="img/chi.ico"/>
<link href="css/register.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.css" rel="stylesheet">
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
var basket_ID="";
function id(id){
	basket_ID+=id+",";
}
</script>
<style>
td{
vertical-align: middle!important;
}
#pi{
background:#eee;
}
#pi td{
border:none!important;
padding:10px 20px;
}
#pi td input{
padding:7px 5px;
}
.fa{
cursor:pointer;
}
</style>
</head>
<body style="overflow: -Scroll; overflow-x: hidden">
	<%@include file="daohanglan.jsp"%>
	<%
		Double totalprice = (Double) request.getAttribute("totalprice");
	%>
	<%
		String name = (String) request.getAttribute("name");
	%>
	<%
		String telephone = (String) request.getAttribute("telephone");
	%>
	<%
		String address = (String) request.getAttribute("address");
	%>
	<%
		String store_ID = (String) request.getAttribute("store_ID");
	%>
	<div class="span12"
		style="position: relative; color: gold; left: 30px;">
		<h3>食物篮子</h3>
	</div>
	<div style="position: relative; top: 20px">
		<table class="table" 
			style="position: relative; width: 1000px;border:2px solid #bfbfbf;overflow:hidden;" align="center">


			
			<tr style="background:#efeae5;width:100%;padding:8px 0;">
			        <td>商品名称</td>
					<td>单价</td>
					<td>数量</td>
					<td>小计</td>
					<td>操作</td>
			</tr>
				<c:forEach items="${basket_list}" var="sto">
					<tr>
						<td>
						<img src="data/foodpicture/${sto.food_ID}.jpg" style="width: 65px; height: 70px; border:none;margin-right:50px;"
								alt="${sto.name}" />
								<a>${sto.name}</a></td>
						<td>${sto.price}</td>
						<td><i class="fa fa-minus-square-o fa-1g" onclick="$(this).next().val($(this).next().val()-1);check_and_update('${sto.basket_ID}');"></i>&nbsp;<input id="num_${sto.basket_ID}" style="width: 30px;text-align:center;"
							value="${sto.num}" onfoucs=""
							onblur="check_and_update('${sto.basket_ID}')" />&nbsp;<i class="fa fa-plus-square-o fa-1g" onclick="$(this).prev().val(parseInt($(this).prev().val())+1);check_and_update('${sto.basket_ID}')"></i></td>
					    <td>${sto.num*sto.price}</td>
						<td><div>
								<SCRIPT>
    							id('${sto.basket_ID}');
    							</SCRIPT>
								<a onclick="delete_order('${sto.basket_ID}')"
									id="delete_button_${sto.basket_ID}">删除</a>
							</div></td>
					</tr>
				</c:forEach>
				<tr style="height:50px;">
					<td>&nbsp;合计</td>
					<td></td>
					<td></td>
					<td><%=totalprice%>元</td>
					<td></td>
				</tr>
			
		</table>
	</div>
	<br>
	<table class="table"
		style="position: relative; width: 500px; top: 25px;" align="center" id="pi">
		<tr style="border-bottom:1px solid #ccc;"><td colspan="2" style="text-align:center;font-size:18px;margin-bottom:20px;">个人信息</td></tr>
		<tr style="height:0px;padding:0;"><td style="height:0px;padding:0;">&nbsp;</td><td></td></tr>
		<tr>
			<td style="position: relative; width: 180px;text-align:right;font-size:15px;">姓名</td>
			<td><input id="order_name" style="width: 240px"
				value="<%=name%>"></td>
		</tr>
		<tr>
			<td style="position: relative; width: 180px;text-align:right;font-size:15px;">电话</td>
			<td><input id="order_telephone" style="width: 240px"
				value="<%=telephone%>"></td>
		</tr>
		<tr>
			<td style="position: relative; width: 180px;text-align:right;font-size:15px;">地址</td>
			<td><input id="order_address" style="width: 240px"
				value="<%=address%>"></td>
		</tr>
		<tr><td>&nbsp;</td><td></td></tr>
	</table>
	<div class="bs-example" style="position: relative; top: 30px"
		align="center">
		<input type="button" class="btn btn-primary btn-lg"
			onclick="submit_check()" value="提交订单" />
		<div id="modal_trigger" data-target="#AddModal" data-toggle="modal"
			style="display: none"></div>
	</div>
	<br/>
	<br/>
	<div id="AddModal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">提示</h4>
				</div>
				<div class="modal-body">
					<h3>是否将这些信息保存到个人信息中</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="submit_cancal()">取消并提交</button>
					<button type="button" class="btn btn-primary" onclick="submit_ok()">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<script type="text/javascript">
var store_ID="<%=store_ID%>";
function delete_order(basket_ID){
	window.document.location.href = "DeleteBasketServlet?basket_ID="+basket_ID+"&store_ID="+store_ID;
}
function check_and_update(basket_ID){
	var basket_ID=basket_ID;
	var number=$('#num_'+basket_ID).val();
	if(number.indexOf(".")>0)
		alert("数量不能使用小数");
	else if(number==0)
		alert("数量不能是零");
	else
		window.document.location.href = "UpdateBasketServlet?basket_ID="+basket_ID+"&num="+number+"&store_ID="+store_ID;
}
function submit_check() {
	var name = $('#order_name').val();
	var telephone = $('#order_telephone').val();
	var address = $('#order_address').val();
	if (name==''||telephone==''||address==''){
		alert("请填写完整信息！");
	}
	else if(name != '<%=name%>' || telephone!='<%=telephone%>' || address!='<%=address%>'){
				$("#modal_trigger").click();
	}
			else{
				$.ajax({
					type: "post",
					url: "OrderingServlet",
					dataType: "json",
					data: "telephone=" + telephone + "&address=" + address + "&totalprice=<%=totalprice%>&basket_ID="+ basket_ID+ "&store_ID=" + store_ID,
					success : function(res) {
						var response = eval(res);
						if (response['res'] == "error") {
							alert("提交出错");
						} else if (response['res'] == "ok") {
							location.href = "after_order.jsp";
						}else if(response['res'] == "less")
							{
							alert("未到起送价格");
							}
						else{
							alert("余额不足，请充值");
						    location.href="UserPageServlet";
						}
							
					},
					error : function() {
						alert("error");
					}
				});
			}
		}
		function submit_ok() {
			var name = $('#order_name').val();
			var telephone = $('#order_telephone').val();
			var address = $('#order_address').val();
			window.document.location.href = "UpdateUserOrderInfoServlet?name="
					+ name + "&telephone=" + telephone + "&address=" + address +"&store_ID=" + store_ID;
		}
		function submit_cancal() {
			var telephone = $('#order_telephone').val();
			var address = $('#order_address').val();
			$.ajax({
				type : "post",
				url : "OrderingServlet",
				dataType : "json",
				data : "telephone=" + telephone + "&address=" + address
						+ "&totalprice=" + <%=totalprice%>+ "&basket_ID="+ basket_ID,//////////////
				success : function(res) {
					var response = eval(res);
					if (response['res'] == "error") {
						alert("提交出错");
					} else if (response['res'] == "ok") {
						location.href = "after_order.jsp";
					} else
						alert("余额不足，请充值");
				},
				error : function() {
					alert("error");
				}
			});
		}
	</script>
</body>
</html>