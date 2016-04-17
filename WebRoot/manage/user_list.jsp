<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="p" uri="/query-tags" %>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>" />

</head>
<body class="page-body">
<nav class="navbar navbar-default" role="navigation" >
   <div style="margin-top:10px">
      <form class="navbar-form navbar-left" role="search" id="searchform" method="post" action="<c:url value='/user!_list.action' />">
      <input type="hidden" id="searchformpn" name="pn" value="${page.pageNo }"/>
         <div class="form-group">
            <input type="text" class="form-control" name="user.userId" placeholder="Search">
         </div>
      </form>    
   </div>
   <button class="btn" id="search" >查询</button>
   <div style="margin-top:10px;float:right;">
	<button class="btn btn-add" onclick="show_add_dialog()">添加新用户 »</button>
   </div>
</nav>
<div id="result">

</div>

		
<script>
;(function ($) {
	$('#search').unbind("click").bind("click", function() {
		$('#searchform input').each(function () {
			$(this).trimText();
		});
		$('#searchform').queryForm({
			target : "#result",
			error : function() {
				$.dialog.alert("网络连接异常，请重新登录");
				return false;
			},
			success : function(data) {
				return false;
			}
		});
		return false;
	});
	$('#search').click();
})(jQuery);	
function show_pro(id){
	$('#'+id+'delPro').popover();
}
      
function del(id){
	var a=id+'delPro';
	$('#'+a).popover('hide');
				$.ajax({
	  			url:"<c:url value='/user!delete.action' />",
	  			dataType:"json",
	  			type:"post",
	  			data:{"userId":id},
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					 $('#'+a).popover('destroy');
	  					 $("#my_chart").load("<c:url value='/user!list.action'/>");
	  				}
	  				else if(data['retcode']==1){
	  					$.dialog.tips(data['retmess'],2,"tips.gif");
	  				}
	  				return false;
				}});
			
		}
function show_edit_dialog(id){
		$.ajax({
	  			url:"<c:url value='/user!edit.action' />",
	  			dataType:"json",
	  			type:"post",
	  			data:{"userId":id},
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					$('#edit_userId').val(data.userId); 
	  					$('#edit_username').val(data.username);
	  					$('#edit_pwd').val(data.pwd);
	  					$('#edit_phone').val(data.phone);
	  					$('#edit_QQ').val(data.QQ);
	  				}
	  				else if(data['retcode']==1){
	  					$.dialog.tips(data['retmess'],2,"tips.gif");
	  				}
	  				return false;
			}});
		jQuery('#editModal').modal('show');
	}
	
	function editSumbit(){
		$.ajax({
	  			url:$("#editForm").attr("action"),
	  			dataType:"json",
	  			type:"post",
	  			async : false,
	  			data:$("#editForm").serialize(),
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					$('#editModal').modal('hide');
	  					$('.modal-backdrop').fadeOut();
	  					//alert('修改成功');
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/user!list.action'/>");
	  					
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			},
			});
			
	}
	
function show_add_dialog(){
	jQuery('#addModal').modal('show', {backdrop: 'fade'});
}
function addSumbit(){
			$.ajax({
	  			url:$("#addForm").attr("action"),
	  			dataType:"json",
	  			type:"post",
	  			data:$("#addForm").serialize(),
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					$('#addModal').modal('hide');
	  					$('.modal-backdrop').fadeOut();
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/user!list.action'/>",{},function(){$.blockUI();$.unblockUI();});
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}});
	}
</script>		
		
</body>
</html>