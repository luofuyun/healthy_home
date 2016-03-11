<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="zh-cn">

<head>
<style type="text/css">
BODY {background-image: URL(image.jpg);
}
</style>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="icon" href="image/favicon.ico">

<title>金惠家健康管理系统</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">

</head>
<body>
	<div class="container">
	<form class="form-signin" action="login">
	
	<h2 class="form-signin-heading">金惠家健康管理系统</h2>
		${message}
		<input class="form-control" name="accountName" type="email" value="" placeholder="登录邮箱" required 
			autofocus>
		<input class="form-control" name="password" type="password" value="" placeholder="密码" required>
		
		<!-- <div class="checkbox"><label> <input type="checkbox" value="remember-me">Remember me</label></div> -->
		<button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
		<a class="btn btn-block" href="register.jsp">注册</a>
		
	</form>
	</div><!-- /container -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="js/ie10-viewport-bug-workaround.js"></script>
	<!-- <script src="js/index.js"></script> -->
</body>
</html>
