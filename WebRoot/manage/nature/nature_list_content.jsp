<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>

 <table  border="0" cellpadding="0" cellspacing="0" class="table table-bordered table-hover" id="table" style="text-align:center">
			<thead>	
			<tr>
					<td class="th30 " >
						序号
					</td>
					<td class="th30 " >
						性质名称
					</td>
					<td class="th30 " >操作</td>
				</tr>
			</thead>
		    <tbody>
				<c:forEach items="${page.resultList }" var="result" varStatus="i">
					<tr style="height:40px;" >
						<td>
							${i.index+1+page.offset }
						</td>
						<td>
							${result.name}
						</td>
						<td>
							<button type="button" class="btn btn-info btn-xs" onclick="show_edit_dialog2('${result.id}','${result.name }')";>修改</button>&nbsp;&nbsp;
							<button onclick="show_pro('${result.id}')" type="button" class="btn btn-info btn-xs" title="确定删除吗？" style="margin-right: 0px;" id="${result.id }delPro" 
      						data-container="body" data-toggle="popover" data-placement="bottom" data-html="true" data-content="<div style='text-align:right'><button type='button' class='btn btn-info btn-xs' onclick='del(${result.id });'>确认</button></div>">
      						删除</button>
							
						</td>
					</tr>
				</c:forEach>
		    </tbody>
		</table>
		
<!-- 修改模态框-start -->
<div class="modal fade" id="editModal" >
   <div class="modal-dialog">
      <div class="modal-content" style="border-style:solid; border-width:1px; border-color:#000;background-color:#DCDCDC" >
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               修改信息
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/nature!update.action' />" role="form" onsubmit="return false" class="form-horizontal" id="editForm">
          		<input type="hidden" class="form-control" id="edit_id" name="nature.id">
          		<div class="form-group">
      				<label for="edit_name" class="col-sm-2 control-label">性质名称</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_name" name="nature.name" style="height:30px">
      				</div>
  				</div>
          	</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary"  onclick="editSumbit();">
             	  提交
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 修改模态框-end -->

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
               	添加新性质
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/nature!add.action' />" role="form" onsubmit="return false" class="form-horizontal" id="addForm">
          		<div class="form-group">
      				<label for="add_name" class="col-sm-2 control-label">性质名称</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_name" name="nature.name" style="height:30px">
      				</div>
  				</div>
          	</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary"  onclick="addSumbit();">
             	  提交
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 添加模态框-end -->
		
		
<script>
function show_pro(id){
	$('#'+id+'delPro').popover();
}
      
function del(id){
	var a=id+'delPro';
	$('#'+a).popover('hide');
				$.ajax({
	  			url:"<c:url value='/nature!delete.action' />",
	  			dataType:"json",
	  			type:"post",
	  			data:{"nature.id":id},
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					 $('#'+a).popover('destroy');
	  					 $("#my_chart").load("<c:url value='/nature!list.action'/>");
	  				}
	  				else if(data['retcode']==1){
	  					$.dialog.tips(data['retmess'],2,"tips.gif");
	  				}
	  				return false;
				}});
			
}
function show_edit_dialog2(id,name){
		$('#edit_id').val(id);
		$('#edit_name').val(name);
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
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/nature!list.action'/>");
	  					
	  				}
	  				else if(data['retcode']==1){
	  					showError();
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
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/nature!list.action'/>",{},function(){$.blockUI();$.unblockUI();});
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}});
			
	}		
</script>	
