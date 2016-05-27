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
        <div  id="main" style="width: 100%;height:800px;margin:0 auto;"></div>
    </div>
    <button id="r" onclick="ax()">刷新数据</button>
    <button id="a" onclick="show()">显示</button>
    <script>
	var lable=new Array();
	var ldata=new Array();
	var sb=" ";
	function ax(){
		$.ajax({
	  			url:"<c:url value='/memberAnalysis!getData.action' />",
	  			dataType:"json",
	  			type:"post",
	  			error : function() {
	  				alert("数据请求失败！刷新重试");
					return false;
				},
	  			success:function(data){
	  				
	  					 for(var key in data){
	  					 	lable.push(key);
	  					 	//ldata.push(data[key]);
	  					 	var map = {value:data[key],name:key};
	  					 	//sb='{value:'+data[key]+', name:'+key+'}';
	  					 	ldata.push(map);
	  					 }
	  					 
	  				return false;
				}
				});
	}
	
	function show(){
		var option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c}人 ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data:lable
    },
    series: [
        {
            name:'访问来源',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:ldata
            
        }
    ]
};

	
var myChart = echarts.init(document.getElementById('main'));
 myChart.setOption(option);
	}
    </script>
</body>
</html>