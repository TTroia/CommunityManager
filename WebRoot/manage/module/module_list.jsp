<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>" />
</head>
<body class="page-body">

<table class="table table-hover table-bordered" style="text-align:center">
	<thead>
		<tr>
			<td class="th30 ">
				&nbsp;&nbsp;
			</td>
			<td class="th30 ">
				菜单级别
			</td>
			<td class="th30 ">
				模块名称
			</td>
			<td class="th30 ">
				菜单url
			</td>
			<td class="th30 ">
				菜单排序
			</td>
			<td class="th30 ">操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${resultList }" var="result" varStatus="i">
			<tr style="height:40px;<c:if test=" ${result.level==1 } ">background: #C1FFC1;</c:if>">
				<td>
					<c:if test="${result.level==1 }"><b>+</b></c:if>
					<c:if test="${result.level==2 }"><b>-</b></c:if>
				</td>
				<td>
					<c:if test="${result.level==1 }">一级菜单</c:if>
					<c:if test="${result.level==2 }">二级菜单</c:if>
				</td>
				<td>${result.name }</td>
				<td>${result.url }</td>
				<td>${result.orderBy }</td>
				<td>
						<c:if test="${result.level==1 }">
  							<button type="button" class="btn btn-info btn-xs" onclick="show_add_dialog('${result.id}','${result.name }')">添加子菜单</button> 
  						</c:if>
  						<button type="button" class="btn btn-info btn-xs" onclick="show_edit_dialog('${result.id}','${result.partentName }')" >修改</button>
  						<button onclick="show_pro('${result.id}')" type="button" class="btn btn-info btn-xs" title="确定删除吗？" style="margin-right: 0px;" id="${result.id }delPro"
      						data-container="body" data-toggle="popover" data-placement="bottom" data-html="true" data-content="<div style='text-align:right'><button type='button' class='btn btn-info btn-xs' onclick='del(${result.id });'>确认</button></div>">
      						删除</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- 添加模态框-start -->
<div class="modal fade" id="addModal" >
   <div class="modal-dialog">
      <div class="modal-content" style="border-style:solid; border-width:1px; border-color:#000;background-color:#DCDCDC" >
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               添加子菜单
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/module!add.action' />" role="form" onsubmit="return false" class="form-horizontal" id="addForm">
          		<input type="hidden" name="partentId" id="partentId"/>
	          	 <input type="hidden" name="level" id="level" value="2"/>
          		<div class="form-group">
      				<label for="fatherName" class="col-sm-2 control-label">父级菜单</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="fatherName" readonly="readonly" style="height:30px" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="menuName" class="col-sm-2 control-label">菜单名称</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="menuName" name="name" style="height:30px" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="url" class="col-sm-2 control-label">菜单URL</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="url" name="url" style="height:30px" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="orderBy" class="col-sm-2 control-label">排序位置</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="orderBy" name="orderBy" style="height:30px" >
      				</div>
  				</div>
          	</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" onclick="addSumbit()">
             	  提交
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 添加模态框-end -->

<!-- 编辑模态框-start -->
<div class="modal fade" id="editModal" >
   <div class="modal-dialog">
      <div class="modal-content" style="border-style:solid; border-width:1px; border-color:#000;background-color:#DCDCDC" >
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               	编辑
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/module!update.action' />" role="form" onsubmit="return false" class="form-horizontal" id="editForm">
          		<input type="hidden" id="edit_id" name="id" value="${module.id }"/>
          	    <input type="hidden" name="level" id="edit_level" value="${module.level }"/>
          	    <input type="hidden" name="partentId" id="edit_partentId" value="${module.partentId }"/>
          		<div class="form-group">
      				<label for="fatherName" class="col-sm-2 control-label">父级菜单</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_fatherName" readonly="readonly" style="height:30px" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="menuName" class="col-sm-2 control-label">菜单名称</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_name" name="name" value="${module.name }" style="height:30px" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="url" class="col-sm-2 control-label">菜单URL</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_url" name="url" value="${module.url }" style="height:30px" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="orderBy" class="col-sm-2 control-label">排序位置</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_orderBy" name="orderBy" value="${module.orderBy }" style="height:30px" >
      				</div>
  				</div>
          	</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" onclick="editSumbit()">
             	  提交
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- 编辑模态框-end -->

</body>

<script>
	
function show_pro(id){
	$('#'+id+'delPro').popover();
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
	  					$("#my_chart").load("<c:url value='/module!list.action'/>",{},function(){$.blockUI();$.unblockUI();});
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}});
	}
	
	function del(id){
	var a=id+'delPro';
	$('#'+a).popover('hide');
				$.ajax({
	  			url:"<c:url value='/module!delete.action' />",
	  			dataType:"json",
	  			type:"post",
	  			data:{"id":id},
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					 $('#'+a).popover('destroy');
	  					 $("#my_chart").load("<c:url value='/module!list.action'/>");
	  				}
	  				else if(data['retcode']==1){
	  					$.dialog.tips(data['retmess'],2,"tips.gif");
	  				}
	  				return false;
				}});
			
		}
	
	
	function show_add_dialog(id,name){
	$('#addSuc').alert();
	jQuery('#addModal').modal('show', {backdrop: 'fade'});
	$('#fatherName').val(name);
	$('#partentId').val(id);
	}
	
	function show_edit_dialog(id,name){
		$.ajax({
	  			url:"<c:url value='/module!edit.action' />",
	  			dataType:"json",
	  			type:"post",
	  			data:{"id":id},
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					$('#edit_fatherName').val(data.partentName); 
	  					$('#edit_partentId').val(data.partentId);
	  					$('#edit_id').val(data.id);
	  					$('#edit_level').val(data.level);
	  					$('#edit_name').val(data.name);
	  					$('#edit_url').val(data.url);
	  					$('#edit_orderBy').val(data.orderBy);
	  				}
	  				else if(data['retcode']==1){
	  					$.dialog.tips(data['retmess'],2,"tips.gif");
	  				}
	  				return false;
			}});
		$('#editModal').modal('show', {backdrop: 'fade'});
	}
	
	function editSumbit(){
		$.ajax({
	  			url:$("#editForm").attr("action"),
	  			dataType:"json",
	  			type:"post",
	  			data:$("#editForm").serialize(),
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					$('#editModal').modal('hide');
	  					$('.modal-backdrop').fadeOut();
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/module!list.action'/>",{},function(){$.blockUI();$.unblockUI();});
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}});
	}
</script>
</html>