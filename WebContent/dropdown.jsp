<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="container dropdown" style="margin-bottom: 50px;left:257px;top:57px;position:absolute;">
       <a class="btn dropdown-toggle" data-toggle="dropdown" href="#" id="dropdown_item">搜索内容<span class="caret"></span></a>
       <ul class="dropdown-menu">
           <li><a onclick="choose(this.text)">商铺名称</a></li>
           <li><a onclick="choose(this.text)">风味口味</a></li>
           <li><a onclick="choose(this.text)">商铺状态</a></li>
           <li><a href="SelectStoreServlet">显示所有</a></li>
       </ul>
</div>