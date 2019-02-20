<%@page import="bean.userInfo"%>
<%@page import="bean.Weather"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="bean.User" />
<jsp:setProperty name="user" property="*" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录后的页面</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>

<script type="text/javascript" src="js/userpage.js"></script>
<link rel="stylesheet" type="text/css" href="css/userpage.css">
</head>
<body> 
	<div id="nav">
		<ul>
			<li class="channel"><img src="pic/weather.png"></li>
			<li class="channel"><a href="./index.html">首页</a></li>
			<li class="channel"><a href="#">个人主页</a></li>
			<li class="channel"><a href="#">关于我们</a></li>
			<li class="channel_right"><a href="/WeatherSharing/index.html"
				id="logout">注销</a></li>
			<li class="channel_right"><span class="login_show"><%=session.getAttribute("login")%>已登录
			</span></li>
		</ul>

	</div>
	<div id="container">
		<div id="launch">
			<span>发布照片~</span>
			<a class="lau_btn" href="upload.html"></a>
			
		</div>
		<div id="show_pannel">
			<div class="personal">
				<%
					userInfo userinfo=(userInfo)session.getAttribute("userinfo");
					String currentCity=(String)session.getAttribute("cityName");
					String careCityWeather=(String)session.getAttribute("careCityWeather");
					String careCity=userinfo.getCareCity();
					String portrait=userinfo.getPortrait();
					
				%>
				<img class="personal-pic" src="<%=portrait%>">
				<div class="info">
					<ul>
						<li class="info-li"><h2><%=session.getAttribute("login")%></h2></li>
						<li class="info-li">当前位置：<span id="currentCity"><%=currentCity %></span></li>
						<li class="info-li">关注城市数：1</li>
					</ul>

				</div>
			</div>
			<div id="chart"><span style="font-weight:bold">未来三天平均气温变化:</span></div>
			<div id="suggestion">
				<p>
				<%ArrayList<Weather> weatherlist=(ArrayList<Weather>)session.getAttribute("weatherlist");
				String weatherTitle=weatherlist.get(1).getTitle(); %>
				<%=weatherTitle %>
				</p>
				<br>
				<h3>生活小贴士</h3>
				<p>
				<%String descriptionTitle=weatherlist.get(2).getTitle(); %>
				<%=descriptionTitle %>
				</p>
			</div>

		</div>
		<div id="main">
			<div class="mod-head">
				<ul>
					<li class="head-li"><input type="button" value="实时照片"
						class="head-btn" id="ontime-photo"
						style="background: #3385ff; color: #fff;"></li>
					<li class="head-li" style="margin-left: 3px;"><input
						type="button" value="上传历史" class="head-btn" id="history-photo"></li>
				</ul>

			</div>
			<div class="general" >
				 <ul id="local">
					
				</ul>
				<!-- <div id="main-footer">

					<span class="page-prev">&lt;</span> <span class="page-current">1</span>
					<a href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">5</a>
					<a href="#">6</a> <a href="#">7</a> <a href="#">199</a> <a href="#">200</a>
					<a href="#" class="page-next">&gt;</a>

				</div>   -->
			</div>
			<div class="general">
				 <ul id="history">
				
				</ul>
				<!-- <div id="main-footer">

					<span class="page-prev">&lt;</span> <span class="page-current">1</span>
					<a href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">5</a>
					<a href="#">6</a> <a href="#">7</a> ... <a href="#">199</a> <a
						href="#">200</a> <a href="#" class="page-next">&gt;</a>

				</div>  -->
			</div>

		</div>
		<div id="focus-district">
			<div class="detail-city">
				<h3 style="margin-bottom:10px;">关注城市</h3>
				<%=careCity %>：<span id="careCityWeather"><%=careCityWeather %></span>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/d3.min.js"></script>
	<script type="text/javascript" src="js/temperatureData.js"></script>
	<script type="text/javascript" src="js/linechart.js"></script>
	
</body>
</html>