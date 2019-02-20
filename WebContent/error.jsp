<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/error.css">
</head>
<body>
	<div id="container">
		<img src="./pic/regret.png">
		<div id="log">
			<h2>${message}</h2>
			<div id="form" class="login-form">
			<form method="post" action="login.do">
				<ul>
					<li class="item" style="font-size:20px; color:#000; text-align:left; margin-top:10px; text-align:center;">重新登录</li>
					<li class="item"><input type="text" name="username" class="intype" placeholder="用户名"></li>
					<li class="item"><input type="password" name="passwd" class="intype" placeholder="密码"></li>
					<li class="item"></li>
					<li class="item"><input type="submit" class="uv_button" value="登录" style="height=40px;"></li>
				</ul>
			</form>
		</div>
		</div>
	</div>
</body>
</html>