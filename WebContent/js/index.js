

function log_reg(){
	//登录注册js
	$("#login").click(function() {		
		$("#hidearea").css('display', 'block');
		$("#mask").css('display', 'block');
		$(".login-form").slideDown(1000);
	});
	$(".cancel").click(function() {
		$(".login-form").css('display', 'none');
		$("#mask").css('display', 'none');
		$("#hidearea").css('display', 'none');

	});
}
function flex(){
	var wh=$(window).height();
	$(".object-1").css("height",wh);
	$(".object-2").css("height",wh);
	$(".object-3").css("height",wh);
	$(".object-4").css("height",wh);
	$("#mask").css("height",wh);
}

 function moveScroll(){
 	var initTop = 0;
 	$(window).scroll(function(){
 		var scrollTop = $(document).scrollTop();
 		if(scrollTop > initTop){
 			alert("下"); 
 		} else {
 			alert("上"); 
 		}
 		initTop = scrollTop;
 	});
 }

 function mouseWheel(){
 	var wh2=$(window).height();
 	$("body").bind("mousewheel",function(event,delta){
 		var speed = 800;
 		var dir = delta>0?"up":"down";
 		if($(this).scrollTop()%wh2==0){
 			if(delta<0)

 				$("body").animate({scrollTop: "+="+wh2}, speed);
 			else
 				$("body").animate({scrollTop: "-="+wh2}, speed);
 		}
 		return false;
 	})
 }

 function scrollfun(stand_height){
 	//alert(stand_height);
 	$(window).scroll(function() {
 		//alert("hello");
        //获取窗口的滚动条的垂直位置 
        var s=$(window).scrollTop();
        //当窗口的滚动条的垂直位置大于页面的最小高度时 
        if ( s> stand_height) {
        	$("#innerimg1").animate({opacity: '1'},700,function(){
        		s=$(window).scrollTop();
        		//alert(s);
        		if(s>stand_height*2){
        			$("#innerimg2").animate({opacity: '1'},700,function(){
        				s=$(window).scrollTop();
        				if(s>stand_height*3){
        					$("#innerimg3").animate({opacity: '1'},700);
        				}
        			});
        		}
        	});
        }
    });
 }





 $(document).ready(function($) {
 	var stand_height = $(window).height()-1;
 	flex();
 	$(window).resize(function() {	

 		flex();
 	});
 	log_reg();
 	mouseWheel();

 	scrollfun(stand_height);
/*
	var url = 'http://chaxun.1616.net/s.php?type=ip&output=json&callback=?&_=' + Math.random();
          $.getJSON(url, function(data) {
              alert(data.Ip);
         });

*/

});
