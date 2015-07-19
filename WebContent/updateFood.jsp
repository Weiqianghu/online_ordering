<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="updateFoodModal_${sto.food_ID}" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="myUpdateModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myUpdateModalLabel">修改商品信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<div class="col-sm-2 control-label">商品名称</div>
					<div class="col-sm-10">
						<!-- <input type="hidden" id="update_food_id_${sto.food_ID}" value="${sto.food_ID}"> -->
						<input type="text" class="form-control" id="update_food_name_${sto.food_ID}"
							placeholder="请输入商品名" value="${sto.name}">
					</div>
					<br> <br> <br>
					<div class="col-sm-2 control-label">商品价格</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="update_food_price_${sto.food_ID}"
							placeholder="请输入商品价格" value="${sto.price}">
					</div>
					<br>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" onclick="update_Food(${sto.food_ID})">修改</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>