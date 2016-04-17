<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>" />
</head>
<body class="page-body">
<nav class="navbar navbar-default" role="navigation" >
   <div style="margin-top:10px">
      <form class="navbar-form navbar-left" role="search" id="searchform" method="post" action="<c:url value='/community!_list.action' />">
      <input type="hidden" id="searchformpn" name="pn" value="${page.pageNo }"/>
         <div class="form-group">
            <input type="text" class="form-control" name="community.name" placeholder="Search" style="height:30px">
         </div>
      </form>    
   </div>
   <button class="btn" id="search" >查询</button>
   <div style="margin-top:10px;float:right;">
	<button class="btn btn-add" onclick="show_add_dialog()">添加新社团 »</button>
   </div>
</nav>
<div id="result">

</div>
		
		
<script>
;(function($){
	$('#search').unbind('click').bind('click',function(){
		$('#searchform input').each(function(){
			$(this).trimText();
		});
	
	$('#searchform').queryForm({
		target : "#result",
		error : function(){
			$.dialog.alert("网络连接异常，请重新登录");
			return false;
		},
		success : function(data) {
				return false;
		}
	});
	});
	
	
	$('#search').click();
})(jQuery);



$(function () 
      { $("[data-toggle='popover']").popover();
      });
      
function del(id){
	var a=id+'delPro';
	$('#'+a).popover('hide');
				$.ajax({
	  			url:"<c:url value='/community!delete.action' />",
	  			dataType:"json",
	  			type:"post",
	  			data:{"community.id":id},
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					 $('#'+a).popover('destroy');
	  					 $("#my_chart").load("<c:url value='/community!list.action'/>");
	  				}
	  				else if(data['retcode']==1){
	  					$.dialog.tips(data['retmess'],2,"tips.gif");
	  				}
	  				return false;
				}});
			
}
function show_edit_dialog2(id,name,president,num,teacher,pre_phone,description,partof){
		$('#edit_id').val(id);
		$('#edit_name').val(name);
		$('#edit_president').val(president);
		$('#edit_num').val(num);
		$('#edit_teacher').val(teacher);
		$('#edit_phone').val(pre_phone);
		$('#edit_description').val(description);
		$('#edit_partof').val(partof);
		$('#editModal').modal('show');
	}
 
	
	function editSumbit(){
		$.ajax({
	  			url:$("#editForm").attr("action"),
	  			dataType:"json",
	  			type:"post",
	  			//async : false,
	  			data:$("#editForm").serialize(),
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					$('#editModal').modal('hide');
	  					$('.modal-backdrop').fadeOut();
	  					$('#editSuc').show();
	  					setTimeout(function(){$("#editSuc").alert('close');},2000);
	  					$("#my_chart").load("<c:url value='/community!list.action'/>");
	  					
	  				}
	  				else if(data['retcode']==1){
	  					$('#addFai').show();
	  					setTimeout(function(){$("#editFai").alert('close');},2000);
	  				}
	  				return false;
			},
			});
			
	}
	
function show_add_dialog(){
	jQuery('#addModal').modal('show');
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
	  					$('#addSuc').show();
	  					setTimeout(function(){$("#addSuc").alert('close');},2000);
	  					$("#my_chart").load("<c:url value='/community!list.action'/>",{},function(){$.blockUI();$.unblockUI();});
	  				}
	  				else if(data['retcode']==1){
	  					$('#addFai').show();
	  					setTimeout(function(){$("#addFai").alert('close');},2000);
	  				}
	  				return false;
			}});
			
	}		
</script>	
</body>
</html>