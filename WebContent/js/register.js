function city() {
	$("#city").citySelect({
		url : "js/city.min.js",
		prov : "北京", // 省份
		city : "东城区", // 城市
		nodata : "none" // 当子集无数据时，隐藏select
	});
}

$(document).ready(function($) {
	city();
	$("#confirmPassWord").change(function(){
		var pd=$("#passWord").val();
		var pd2=$("#confirmPassWord").val();
		if(pd!=pd2){
			alert("两次密码错误！请重新输入！");
			$("#passWord").val("");
			$("#confirmPassWord").val("");
		}
		
	});
	
	/*
	$("#careCityNum").change(function(){
		var selectedNum=$(this).children('option:selected').val();
				
	});
	*/
});