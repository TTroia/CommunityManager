<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>" />
 <script type="text/javascript" src="<c:url value='/js/echarts.min.js'/>"></script>
 <style>
    canvas {
        -moz-user-select: none;
        -webkit-user-select: none;
        -ms-user-select: none;
    }
    </style>
</head>
<body class="page-body">

<div style="width: 100%;text-align:center;background:gray" >
	<h1>财务分析</h1>
        <div  id="main" style="width: 100%;height:800px;margin:0 auto;"></div>
    </div>
    <button id="r" onclick="ax()">刷新数据</button>
    <button id="a" onclick="show()">显示</button>
    <script>
	var lable=new Array();
	var ldata=new Array();
	function ax(){
		$.ajax({
	  			url:"<c:url value='/financeAnalysis!pie.action' />",
	  			dataType:"json",
	  			type:"post",
	  			error : function() {
	  				alert("数据请求失败！刷新重试");
					return false;
				},
	  			success:function(data){
	  					 for(var key in data){
	  					 	lable.push(key);
	  					 	ldata.push(data[key]);
	  					 }
	  				return false;
				}
				});
	}
	
	function show(){
		/*var option = {


    title: {
        text: '收支情况图',
        left: 'center',
        top: 20,
        textStyle: {
            color: '#ccc'
        }
    },

    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },

    visualMap: {
        show: false,
        min: 80,
        max: 600,
        inRange: {
            colorLightness: [0, 1]
        }
    },
    series : [
        {
            name:'金额',
            type:'pie',
            radius : '55%',
            center: ['50%', '50%'],
            data:[
                {value:ldata[0], name:'支出'},
                {value:ldata[1], name:'收入'},

            ].sort(function (a, b) { return a.value - b.value}),
            roseType: 'angle',
            label: {
                normal: {
                    textStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                }
            },
            itemStyle: {
                normal: {
                    color: '#c23531',
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};*/
	
var option = {
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            data:[
                {value:ldata[0], name:'收入'},
                {value:ldata[1], name:'支出'},
            ]
        }
    ]
};

	
var myChart = echarts.init(document.getElementById('main'));
 myChart.setOption(option);
	}
    </script>
</body>
</html>