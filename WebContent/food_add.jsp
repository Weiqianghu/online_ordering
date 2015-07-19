<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" import="com.cn.food.*"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="shortcut icon" href="img/chi.ico"/>
<script src="jquery-1.8.0.js" type="text/javascript"></script>
<link href="css/register.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="tinymce/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    language:"zh_CN",
    theme: "modern",
    plugins: [
        "advlist autolink lists link image charmap print preview hr anchor pagebreak",
        "searchreplace wordcount visualblocks visualchars code fullscreen",
        "insertdatetime media nonbreaking save table contextmenu directionality",
        "emoticons template paste textcolor colorpicker textpattern"
    ],
    toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
    toolbar2: "print preview media | forecolor backcolor emoticons",
    image_advtab: true,
    templates: [
        {title: 'Test template 1', content: 'Test 1'},
        {title: 'Test template 2', content: 'Test 2'}
    ]
});
</script>
<style>

form h3{
font-size:14px;
}
#main{
width:730px;
margin:5px auto;
padding:20px;
background:#f5f6fa;
}
form input[type="text"],form .pic{
height:25px;width:200px;
margin-right:70px;
}
.button input{
background:#1386c9;
color:#fff;
border:none;
padding:7px 10px;
margin:3px 15px;
}
.introduce{
color:#000;
border-bottom:1px solid #d2d3d7;
margin-top:30px;
}
#mceu_45{
display:none;
}
</style>
</head>
<body>
<%@include file="daohanglan.jsp" %>
<div id="main">
<form method="post" enctype="multipart/form-data" action="AddFoodServlet">
           <div class="introduce">基本信息</div>
           <h3>菜肴名称：<input type="text" name="food_name"/>菜肴价格：<input type="text" name="food_price"/></h3>
           <h3>菜肴图片：<input type="button" value="添加图片" onclick="$('#tp').click();" class="pic"/>菜肴口味：<input type="text" name="food_taste"/></h3>
           <input type="file" id="tp" name="tp" class="hide" accept="image/*"/>
           <div class="introduce">详细介绍</div>
           <h3><textarea name="introduction" style="width:100%;height:200px;" name="introduction"></textarea></h3>
           <h3 style="text-align:right;" class="button"><input type="submit" value="开始销售"/></h3>
</form>
</div>

</body>
</html>