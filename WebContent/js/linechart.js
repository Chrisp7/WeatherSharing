var width=490,
height=210,
margin={left:50, top:30, right:20, bottom:20},
g_width=width-margin.left-margin.right,
g_height=height-margin.top-margin.bottom;

//svg
var svg=d3.select("#chart").append("svg:svg")//svg:是命名空间，再加一个svg就表示svg元素
//width,height
.attr("width",width)
.attr("height", height)


var g=d3.select("svg")
.append("g").attr("transform", "translate("+margin.left+","+margin.top+")")


var scale_x=d3.scale.linear()//分别指定x，y的缩放输入输出范围，并且是一种线性的缩放。
.domain([0,2])//指定输入的范围
.range([0,g_width])//指定输出范围

var scale_y=d3.scale.linear()
.domain([0,d3.max(data)])//指定输入的范围
.range([g_height,0])//指定输出范围,这里通过把范围调个个儿的方式，让Y轴能够从下往上数


//我们要通过line_generator来实现把data中的数据依次取出来，因此我们通过一个函数来实现
var line_generator=d3.svg.line()
.x(function(d,i){return scale_x(i);})//0,1,2,3,4,5//d表示数组中的元素值，第二个参数i表示每个数值的对应的下标
.y(function(d){return scale_y(d);})//1,3,5..

d3.select("g").append('path').attr('d', line_generator(data));//d=PATH DATA缩写

//绘制坐标轴
var x_axis=d3.svg.axis().scale(scale_x).tickValues([0, 1, 2]),
y_axis=d3.svg.axis().scale(scale_y).orient("left");

g.append("g")
.call(x_axis)
.attr("transform", "translate(0,"+g_height+")");

g.append("g")
.call(y_axis)
.append("text")
.text("温度(℃)")
.attr('transform', 'rotate(-90)')
.attr('text-anchor', 'end')
.attr('dy', '1em');


//在浏览器中的开发者工具中可以看到他实际是这样的一个代码
//<g transform ="translate(50,30)>"
//<path d="M1,0L20,40L40,50L100,100L0,200"></path>
//</g>
//这个代码意思是从起点M(1,0)画一条直线，到（20,40）的坐标点，接着，依次下去。