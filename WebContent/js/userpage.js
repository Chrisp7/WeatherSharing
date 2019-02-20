prefix = "/WeatherSharing";

$(document).ready(function($) {

	$("#ontime-photo").click(function() {

		$("#history-photo").css('background', '#fff');
		$("#ontime-photo").css('background', '#3385FF');
		$("#ontime-photo").css('color', '#fff');
		$("#history-photo").css('color', '#000');
		$("#history").css('display', 'none');
		$("#local").css('display', 'block');
	});
	$("#history-photo").click(function() {
		$("#ontime-photo").css('background', '#fff');
		$("#history-photo").css('background', '#3385FF');
		$("#history-photo").css('color', '#fff');
		$("#ontime-photo").css('color', '#000');
		$("#local").css('display', 'none');
		$("#history").css('display', 'block');
	});

	// 登陆或验证
	$("#logout").click(function() {
		$.post(prefix + "/logout.do");
	});
	$("#history-photo").click(function() {
		$.post(prefix + "/history.do", function(data) {
			var str = "";
			if (data[0].status == -2) {
				alert(data[0].message);
			} else {
				$("#history").html("");
				for (var i = 1; i < data.length; i++) {
					$("#history").append('<li class="pic-item"><img src="'+data[i]+'" class="time-pic-item-a"></li>');

				}
			}
		}, "json");
	});

	$("#ontime-photo").click(function() {
		var currentCity = $("#currentCity").html();
		$.post(prefix + "/realTime.do", {
			currentCity: currentCity
		},function(data) {
			var str = "";
			if (data[0].status == -2) {
				alert(data[0].message);
			} else {
				$("#local").html("");
				for (var i = 1; i < data.length; i++) {
					$("#local").append('<li class= "pic-item"><img src="'+data[i]+'" class="time-pic-item-a"><input type="button" class="bad"><input type="button" class="good"></li>');
					
				}
			}
		}, "json");
	});

});