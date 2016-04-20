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
						修改人
					</td>
					<td class="th30 " >
						修改时间
					</td>
					<td class="th30 " >
						目的
					</td>
					<td class="th30 " >
						花费
					</td>
					<td class="th30 " >
						余额
					</td>
					<td class="th30 " >操作</td>
				</tr>
			</thead>
		    <tbody>
				<c:forEach items="${resultList }" var="result" varStatus="i">
					<tr style="height:40px;" >
						<td>
							${i.index+1 }
						</td>
						<td>
							${result.editUser}
						</td>
						<td>
							${result.editDate}
						</td>
						<td>
							${result.purpose}
						</td>
						<td>
							${result.spend}
						</td>
						<td>
							${result.balance}
						</td>
						<td>
							<c:if test="${resultList.size()==i.index+1 }">
							<button type="button" class="btn btn-info btn-xs" onclick="show_edit_dialog2('${result.id}','${result.purpose }','${result.spend }','${result.balance }')";>修改</button>&nbsp;&nbsp;
							<button onclick="show_pro('${result.id}')" type="button" class="btn btn-info btn-xs" title="确定删除吗？" style="margin-right: 0px;" id="${result.id }delPro" 
      						data-container="body" data-toggle="popover" data-placement="bottom" data-html="true" data-content="<div style='text-align:right'><button type='button' class='btn btn-info btn-xs' onclick='del(${result.id });'>确认</button></div>">
      						删除</button>
      						</c:if>
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
               修改记录
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/finance!update.action' />" role="form" onsubmit="return false" class="form-horizontal" id="editForm">
          		<input type="hidden" class="form-control" id="edit_id" name="finance.id">
          		<div class="form-group">
      				<label for="edit_purpose" class="col-sm-2 control-label">目的</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_purpose" name="finance.purpose" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
      				<label for="edit_spend" class="col-sm-2 control-label">花费</label>
      				<div class="col-sm-10">
      					<select class=" .form-control-static" name="way">
      						<option value=0 >收入</option>
      						<option value=1 >支出</option>
      					</select>
         				<input type="text" class="form-control" id="edit_spend" name="finance.spend" style="height:30px">
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
               	添加记录
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/finance!add.action' />" role="form" onsubmit="return false" class="form-horizontal" id="addForm">
          		<div class="form-group">
      				<label for="add_purpose" class="col-sm-2 control-label">目的</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_purpose" name="finance.purpose" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
      				<label for="add_spend" class="col-sm-2 control-label">花费</label>
      				<div class="col-sm-10">
      					<select class=" .form-control-static" name="way">
      						<option value=0 >收入</option>
      						<option value=1 >支出</option>
      					</select>
         				<input type="text" class="form-control" id="add_spend" name="finance.spend" style="height:30px">
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
	  			url:"<c:url value='/finance!delete.action' />",
	  			dataType:"json",
	  			type:"post",
	  			data:{"finance.id":id},
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					 $('#'+a).popover('destroy');
	  					 $("#search").click();
	  				}
	  				else if(data['retcode']==1){
	  					$.dialog.tips(data['retmess'],2,"tips.gif");
	  				}
	  				return false;
				}});
			
}
function show_edit_dialog2(id,purpose,spend){
		$('#edit_id').val(id);
		$('#edit_purpose').val(purpose);
		$('#edit_spend').val(spend);
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
	  					$("#search").click();
	  					
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
	  					$("#search").click();
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}});
			
	}		
</script>	
