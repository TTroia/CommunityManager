<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>" />
 <script type="text/javascript" src="<c:url value='/js/Chart.js'/>"></script>
 <style>
    canvas {
        -moz-user-select: none;
        -webkit-user-select: none;
        -ms-user-select: none;
    }
    </style>
</head>
<body class="page-body">
	<c:forEach items="${partofList }" var="po">
		<canvas id="myChart" width="400" height="400" style="background:gray"></canvas>
	</c:forEach>

<div style="width: 75%;">
        <canvas id="canvas"></canvas>
    </div>
    <button id="randomizeData">Randomize Data</button>
    <script>
    var randomScalingFactor = function() {
        return (Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100);
    };
    var randomColorFactor = function() {
        return Math.round(Math.random() * 255);
    };
    var randomColor = function() {
        return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',.7)';
    };

    var barChartData = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [{
            label: 'Dataset 1',
            backgroundColor: "rgba(220,220,220,0.5)",
            yAxisID: "y-axis-1",
            data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
        }, {
            label: 'Dataset 2',
            backgroundColor: "rgba(151,187,205,0.5)",
            yAxisID: "y-axis-2",
            data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
        }, {
            label: 'Dataset 3',
            backgroundColor: [randomColor(), randomColor(), randomColor(), randomColor(), randomColor(), randomColor(), randomColor()],
            yAxisID: "y-axis-1",
            data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
        }]

    };
    window.onload = function() {
        var ctx = document.getElementById("canvas").getContext("2d");
        window.myBar = Chart.Bar(ctx, {
            data: barChartData, 
            options: {
                responsive: true,
                hoverMode: 'label',
                hoverAnimationDuration: 400,
                stacked: false,
                title:{
                    display:true,
                    text:"Chart.js Bar Chart - Multi Axis"
                },
                scales: {
                    yAxes: [{
                        type: "linear", // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                        display: true,
                        position: "left",
                        id: "y-axis-1",
                    }, {
                        type: "linear", // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                        display: true,
                        position: "right",
                        id: "y-axis-2",
                        gridLines: {
                            drawOnChartArea: false
                        }
                    }],
                }
            }
        });
    };

    $('#randomizeData').click(function() {
        $.each(barChartData.datasets, function(i, dataset) {
            if (Chart.helpers.isArray(dataset.backgroundColor)) {
                dataset.backgroundColor= [randomColor(), randomColor(), randomColor(), randomColor(), randomColor(), randomColor(), randomColor()];
            } else {
                dataset.backgroundColor= randomColor();
            }

            dataset.data = [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()];

        });
        window.myBar.update();
    });
    </script>
</body>
</html>