<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 <table  border="0" cellpadding="0" cellspacing="0" class="table table-bordered table-hover" id="table" style="text-align:center">
			<thead>	
			<tr>
					<td class="th30 " >
						序号
					</td>
					<td class="th30 " >
						活动名称
					</td>
					<td class="th30 " >
						社团名称
					</td>
					<td class="th30 " >
						申请者
					</td>
					<td class="th30 " >
						申请时间
					</td>
					<td class="th30 " >
						举办地点
					</td>
					<td class="th30 " >
						指导教师
					</td>
					<td class="th30 " >
						系社联意见
					</td>
					<td class="th30 " >
						系审批人
					</td>
					<td class="th30 " >
						系审批时间
					</td>
					<td class="th30 " >
						校社联意见
					</td>
					<td class="th30 " >
						校审批人
					</td>
					<td class="th30 " >
						校审批时间
					</td>
					<td>
						批复
					</td>
					<td class="th30 " >
						完成状态
					</td>
					<td class="th30 " >
						申请书
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
							${result.com_name}
						</td>
						<td>
							${result.applyUser}
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.applyDate}" />
						</td>
						<td>
							${result.location}
						</td>
						<td>
							${result.teacher}
						</td>
						<td>
							<c:if test="${result.applyState==1 }">
								<img src="<c:url value='/img/yes.png' />"  alt="系社联已审批"/>
							</c:if>
							<c:if test="${result.applyState==0 }">
								<img src="<c:url value='/img/no.png' />"  alt="系社联已驳回"/>
							</c:if>
						</td>
						<td>
							${result.approveUser}
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.approveDate}" />
						</td>
						<td>
							<c:if test="${result.applyState2==1 }">
								<img src="<c:url value='/img/yes.png' />"  alt="校社联已审批"/>
							</c:if>
							<c:if test="${result.applyState2==0 }">
								<img src="<c:url value='/img/no.png' />"  alt="校社联已驳回"/>
							</c:if>
						</td>
						<td>
							${result.approveUser2}
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.approveDate2}" />
						</td>
						<td>
							${result.attr }
						</td>
						
						<td>
							<c:if test="${result.completeState==1 }">
								<img src="<c:url value='/img/complete.png' />"  alt="活动已完成"/>
							</c:if>
						</td>
						<td>
							<a href="javascript:;" onclick="downloadf('${fn:replace(result.tableAdd,'\\','/') }')">${fn:substringAfter(result.tableAdd, "upload\\")} </a>
						</td>
						<td>
							<c:if test="${user.roleId==3||user.roleId==0 }">
								<button type="button" class="btn btn-info btn-xs" onclick="show_edit_dialog2('${result.id}','${result.name }','${result.location }','${result.teacher }','${fn:replace(result.tableAdd,'\\','\\\\') }','${result.holdDate }')";>修改</button>&nbsp;&nbsp;
								<button onclick="show_pro('${result.id}')" type="button" class="btn btn-info btn-xs" title="确定删除吗？" style="margin-right: 0px;" id="${result.id }delPro" 
      							data-container="body" data-toggle="popover" data-placement="bottom" data-html="true" data-content="<div style='text-align:right'><button type='button' class='btn btn-info btn-xs' onclick='del(${result.id });'>确认</button></div>">
      							删除</button>&nbsp;&nbsp;
      							<c:if test="${result.completeState!=1 }">
      								<button type="button" class="btn btn-info btn-xs" onclick="activity_complete('${result.id}','${result.completeState }')">完成</button>
      							</c:if>
      							<c:if test="${result.completeState==1 }">
      							    <button type="button" class="btn btn-info btn-xs" onclick="activity_complete('${result.id}','${result.completeState }')">取消</button>
      							</c:if>&nbsp;&nbsp;
      							<button type="button" class="btn btn-info btn-xs" onclick="upload('${result.id}')">上传word</button>
      						</c:if>
      						<c:if test="${user.roleId==1||user.roleId==0 }">
      							<c:if test="${result.applyState==1}">
									<button type="button" class="btn btn-info btn-xs" onclick="xiao_ok('${result.id}','${result.applyState2 }')">确认</button>&nbsp;&nbsp;
									<button type="button" class="btn btn-info btn-xs" onclick="xiao_no('${result.id}','${result.applyState2 }')">不准</button>
									<c:if test="${result.applyState2==0}">.
										<button type="button" class="btn btn-info btn-xs" onclick="remark('${result.id}')">批复</button>
									</c:if>
								</c:if>
							</c:if>
							<c:if test="${user.roleId==2||user.roleId==0 }">
									<button type="button" class="btn btn-info btn-xs" onclick="xi_ok('${result.id}','${result.applyState }')">同意</button>&nbsp;&nbsp;
									<button type="button" class="btn btn-info btn-xs" onclick="xi_no('${result.id}','${result.applyState }')">不准</button>
									<c:if test="${result.applyState==0}">
										<button type="button" class="btn btn-info btn-xs" onclick="remark('${result.id}')">批复</button>
									</c:if>
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
               修改信息
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/activity!update.action' />" role="form" onsubmit="return false" class="form-horizontal" id="editForm">
          		<input type="hidden" class="form-control" id="edit_id" name="activity.id">
          		<div class="form-group">
      				<label for="edit_name" class="col-sm-2 control-label">活动名称</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_name" name="activity.name" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
      				<label for="edit_name" class="col-sm-2 control-label">举办时间</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_holdDate" name="activity.holdDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
      				<label for="edit_name" class="col-sm-2 control-label">举办地点</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_location" name="activity.location" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
      				<label for="edit_name" class="col-sm-2 control-label">指导教师</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_teacher" name="activity.teacher" style="height:30px">
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
               	添加新活动
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/activity!add.action' />" role="form" onsubmit="return false" class="form-horizontal" id="addForm">
          		<div class="form-group">
      				<label for="edit_name" class="col-sm-2 control-label">活动名称</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_name" name="activity.name" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
      				<label for="edit_name" class="col-sm-2 control-label">举办时间</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_holdDate" name="activity.holdDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
      				<label for="edit_name" class="col-sm-2 control-label">举办地点</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_location" name="activity.location" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
      				<label for="edit_name" class="col-sm-2 control-label">指导教师</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_teacher" name="activity.teacher" style="height:30px">
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

<!-- 添加备注模态框 -start-->
	<div class="modal fade" id="attrModal" >
   <div class="modal-dialog">
      <div class="modal-content" style="border-style:solid; border-width:1px; border-color:#000;background-color:#DCDCDC" >
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               	批复
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/activity!remark.action' />" role="form" onsubmit="return false" class="form-horizontal" id="attrForm">
          		<input type="hidden" class="form-control" id="attr_id" name="activity.id">
          		<div class="form-group">
      				<label for="attr_attr" class="col-sm-2 control-label">批复</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="attr_attr" name="activity.attr" style="height:30px">
      				</div>
  				</div>
          	</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary"  onclick="attrSumbit();">
             	  提交
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 添加备注模态框-end -->

<!-- 上传模态框-start -->
<div class="modal fade" id="uploadModal" >
   <div class="modal-dialog">
      <div class="modal-content" style="border-style:solid; border-width:1px; border-color:#000;background-color:#DCDCDC" >
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               	上传word版申请书
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/activity!upload.action' />" role="form" enctype="multipart/form-data" onsubmit="return false" class="form-horizontal" id="uploadForm">
          		<input type="hidden" class="form-control" id="upload_id" name="activity.id">
          		<div class="form-group">
      				<label for="upload_file" class="col-sm-2 control-label">选择文件:</label>
      				<div class="col-sm-10">
         				<input type="file" class="form-control" id="upload_file" name="file" style="height:30px">
      				</div>
  				</div>
          	</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary"  onclick="uploadSumbit();">
             	  上传
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 下载模态框-end -->
		
		
<script>
function show_pro(id){
	$('#'+id+'delPro').popover();
}

function del(id){
	var a=id+'delPro';
	$('#'+a).popover('hide');
				$.ajax({
	  			url:"<c:url value='/activity!delete.action' />",
	  			dataType:"json",
	  			type:"post",
	  			data:{"activity.id":id},
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					 $('#'+a).popover('destroy');
	  					 $("#my_chart").load("<c:url value='/activity!list.action'/>");
	  				}
	  				else if(data['retcode']==1){
	  					$.dialog.tips(data['retmess'],2,"tips.gif");
	  				}
	  				return false;
				}
				});
			
}

function remark(id){
	$('#attr_id').val(id);
	$('#attrModal').modal('show');
}

function attrSumbit(){
	$.ajax({
	  			url:$("#attrForm").attr("action"),
	  			dataType:"json",
	  			type:"post",
	  			//async : false,
	  			data:$("#attrForm").serialize(),
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					$('#attrModal').modal('hide');
	  					$('.modal-backdrop').fadeOut();
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/activity!list.action'/>");
	  					
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			},
			});
}

function show_edit_dialog2(id,name,location,teacher,tableAdd,holdDate){
		$('#edit_id').val(id);
		$('#edit_name').val(name);
		$('#edit_holdDate').val(holdDate);
		$('#edit_location').val(location);
		$('#edit_teacher').val(tableAdd);
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
	  					$("#my_chart").load("<c:url value='/activity!list.action'/>");
	  					
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
	  					$("#my_chart").load("<c:url value='/activity!list.action'/>",{},function(){$.blockUI();$.unblockUI();});
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}});
			
	}
//校社联确认
function xiao_ok(id,applyState2){
	$.ajax({
		url:"<c:url value='/activity!xiao_ok.action'/>",
		dataType:"json",
		type:"post",
		data:{"activity.id":id,"activity.applyState2":applyState2},
		error : function() {
			return false;
		},
		success:function(data){
	  				if(data['retcode']==0){
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/activity!list.action'/>");
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}
	});
}

//校社联驳回
function xiao_no(id,applyState2){
	$.ajax({
		url:"<c:url value='/activity!xiao_no.action'/>",
		dataType:"json",
		type:"post",
		data:{"activity.id":id,"activity.applyState2":applyState2},
		error : function() {
			return false;
		},
		success:function(data){
	  				if(data['retcode']==0){
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/activity!list.action'/>");
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}
	});
}
//系社联确认
function xi_ok(id,applyState){
	$.ajax({
		url:"<c:url value='/activity!xi_ok.action'/>",
		dataType:"json",
		type:"post",
		data:{"activity.id":id,"activity.applyState":applyState},
		error : function() {
			return false;
		},
		success:function(data){
	  				if(data['retcode']==0){
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/activity!list.action'/>");
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}
	});
}

//系社联驳回
function xi_no(id,applyState){
	$.ajax({
		url:"<c:url value='/activity!xi_no.action'/>",
		dataType:"json",
		type:"post",
		data:{"activity.id":id,"activity.applyState":applyState},
		error : function() {
			return false;
		},
		success:function(data){
	  				if(data['retcode']==0){
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/activity!list.action'/>");
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}
	});
}

//活动完成状态
function activity_complete(id,completeState){
	$.ajax({
		url:"<c:url value='/activity!complete.action'/>",
		dataType:"json",
		type:"post",
		data:{"activity.id":id,"activity.completeState":completeState},
		error : function() {
			return false;
		},
		success:function(data){
	  				if(data['retcode']==0){
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/activity!list.action'/>");
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}
	});
}

//上传word版申请书
function upload(id){
$("#upload_id").val(id);
	$('#uploadModal').modal('show');
}

function uploadSumbit(){
	$("#uploadForm").ajaxSubmit({  
   			 type:"post",  
   			 dataType:"json",   
   			 url:"<c:url value='/activity!upload.action' />", 
   			 success:function(data){
      			 if(data['retcode']==0){
      				 $('#addModal').modal('hide');
      				 	showSucc();
	  					$('.modal-backdrop').fadeOut();
						$("#my_chart").load("<c:url value='/activity!list.action'/>");
					}
			 	else if(data['retcode']==1){
			 			showError();
			 			$('.modal-backdrop').fadeOut();
					}
   				},
   				error:function(){
   				}
			}); 
}

function downloadf(url) {
	window.location.href="<c:url value='/activity!download.action?url=' />"+url;
}


</script>	
