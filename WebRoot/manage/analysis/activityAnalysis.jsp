<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>" />
 <script type="text/javascript" src="<c:url value='/js/echarts.simple.min.js'/>"></script>
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
	<h1>活动次数分析</h1>
        <div  id="main" style="width: 100%;height:800px;margin:0 auto;"></div>
    </div>
    <button id="r" onclick="ax()">Randomize Data</button>
    <button id="a" onclick="show()">click</button>
    <script>
	var lable=new Array();
	var ldata=new Array();
	function ax(){
		$.ajax({
	  			url:"<c:url value='/activityAnalysis!top10Com.action' />",
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
		var option = {
            title: {
            	show : true,
                text: '活动次数分析'
            },
            tooltip: {},
            legend: {
                data:['活动次数']
            },
            xAxis: {
                data: lable
            },
            yAxis: {min : '0',name:'活动次数',
            	nameTextStyle:{fontSize:20,fontWeight:'lighter',color:'#FFF8DC'}
            },
            series: [{
                name: '活动次数',
                type: 'bar',
                data: ldata
            }]
        };
	
var myChart = echarts.init(document.getElementById('main'));
 myChart.setOption(option);
	}
    </script>
</body>
</html>