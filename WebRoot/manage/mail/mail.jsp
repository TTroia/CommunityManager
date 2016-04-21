<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="p" uri="/query-tags" %>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/flyaway.min.css'/>" />
<script type="text/javascript" src="<c:url value='/assets/js/jquery-1.8.3.min.js'/>"></script>
</head>
<body class="page-body">

	<div style="width:100%;height:100%;">

		<div class="well well-lg" style="width:50%;margin:10% auto;">
			<form class="bs-example bs-example-form" role="form" id="sendForm" action="<c:url value='/mail!sendMail.action'/>">
				<div class="panel panel-default" style="width:25%;float:left" id="panel-touser">
					<div class="panel-heading" id="panel-select-heading">
						<h3 class="panel-title">选择收件人</h3>
					</div>
					<div class="panel-body">
						<select multiple class="form-control" style="height:300px" name="toUsers" id="selectToUser">
							<c:forEach items="${userList }" var="user">
								<option value="${user.userId }">${user.username }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div  class="panel panel-default" style="width:73%;margin-left:27%;" id="panel-mail">
					<div class="panel-heading" id="panel-mail-heading">
						<h3 class="panel-title">发送新的站内信</h3>
					</div>
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon">标题</span> 
							<input id="title" type="text" class="form-control" placeholder="请输入标题" name="notice.title" style="height:30px">
						</div>
						<br />
						<textarea id="content" class="form-control" rows="13" name="notice.content"></textarea>
					</div>
				</div>
				<div style="float:right">
					<img alt="发送" src="<c:url value="/img/Paper_Plane.png" />" onclick="send()" style="cursor:pointer" id="plane" >
				</div>
			</form>
		</div>
	</div>
	<button type="button" style="display:none" class="animate" id="flyplane"></button>
<script>
	function send(){
		if($('#title').val()==""){
			$('#title').css("border-color","red");
			flash('#title',8,10,100);
			return false;
		}
		if($('#content').val()==""){
			$('#content').css("border-color","red");
			flash('#content',8,10,100);
			return false;
		}
	$.ajax({
			url : $("#sendForm").attr("action"),
			dataType : "json",
			type : "post",
			data : $("#sendForm").serialize(),
			error : function() {
				return false;
			},
			success : function(data) {
				if (data['retcode'] == 0) {
					$('#flyplane').click();
					$('#panel-heading').append("<div id='sendError'  ><font color='green'>发送成功</font></div>");
					setTimeout(function(){$("#my_chart").load("<c:url value='/mail!list.action'/>",
							{}, function() {
							});},1000);
					
				} else if (data['retcode'] == 1) {
					//alert($('#selectToUser').val());
					if($('#selectToUser').val()==null){
						$('#panel-select-heading').append("<div id='sendError'  ><font color='red'>发送失败，请选择收件人</font></div>");
						$('#panel-touser').css("border-color","red");
						flash('#panel-touser',8,10,100);
					}else {
						$('#panel-mail-heading').append("<div id='sendError'  ><font color='red'>发送失败，请检查网络连接</font></div>");
						$('#panel-mail').css("border-color","red");
						flash('#panel-mail',8,10,100);
					}
					
					
				}
				return false;
			}
		});
	}
	
	function animate(x) {
    $('#plane').removeClass('float shadow').addClass('flyaway ' + x).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
        $('#plane').addClass('shadow float').removeClass('flyaway ' + x);
    });
};

$(document).ready(function() {
    $('.animate').click(function(e) {
        e.preventDefault();
        var anim = "pushOut";
        animate(anim);
    });
});

function flash(obj,time,wh,fx)
{ 
	$(function(){
	var $panel = $(obj);
	var offset = $panel.offset()-$panel.width();
	var x= offset.left;
	var y= offset.top;
	for(var i=1; i<=time; i++){
		if(i%2==0)
		{
			$panel.animate({left:'+'+wh+'px'},fx);
		}else
		{
			$panel.animate({left:'-'+wh+'px'},fx);
		}
			
	}
	$panel.animate({left:0},fx);
	$panel.offset({ top: y, left: x });
		
	})
}
</script>

</body>
</html>