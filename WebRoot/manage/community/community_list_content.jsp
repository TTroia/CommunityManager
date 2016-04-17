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
						社团名称
					</td>
					<td class="th30 " >
						创建时间
					</td>
					<td class="th30 " >
						类型
					</td>
					<td class="th30 " >
						社长
					</td>
					<td class="th30 " >
						现有人数
					</td>
					<td class="th30 " >
						指导教师
					</td>
					<td class="th30 " >
						隶属学院
					</td>
					<td class="th30 " >
						联系电话
					</td>
					<td class="th30 " >
						现有经费
					</td>
					<td class="th30 " >
						历史总人数
					</td>
					<td class="th30 " >
						描述
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
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${result.createDate}" /></td>
						<td>
							<c:if test="${result.nature==1 }">理论学习型 </c:if>
							<c:if test="${result.nature==2 }">学术科技型 </c:if>
							<c:if test="${result.nature==3 }">兴趣爱好型 </c:if>
							<c:if test="${result.nature==4 }">社会公益型 </c:if>
						</td>
						
						<td>${result.president }</td>
						<td>${result.num }</td>
						<td>${result.teacher }</td>
						<td>${result.partof }</td>
						<td>${result.pre_phone }</td>
						<td>${result.funds }</td>
						<td>${result.his_num }</td>
						<td>${result.description }</td>
						<td>
							<button type="button" class="btn btn-info btn-xs" onclick="show_edit_dialog2('${result.id}','${result.name }','${result.president }','${result.num }','${result.teacher }','${result.pre_phone }','${result.description }','${result.partof }')";>修改</button>&nbsp;&nbsp;
							<button  onclick="show_pro('${result.id}')" type="button" class="btn btn-info btn-xs" title="确定删除吗？" style="margin-right: 0px;" id="${result.id }delPro" 
      						data-container="body" data-placement="bottom" data-html="true" data-content="<div style='text-align:right'><button type='button' class='btn btn-info btn-xs' onclick='del(${result.id });'>确认</button></div>">
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
          	<form method="post" action="<c:url value='/community!update.action' />" role="form" onsubmit="return false" class="form-horizontal" id="editForm">
          		<input type="hidden" class="form-control" id="edit_id" name="community.id">
          		<div class="form-group">
      				<label for="edit_name" class="col-sm-2 control-label">社团名称</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_name" name="community.name" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="edit_nature" class="col-sm-2 control-label">社团性质</label>
      				<div class="col-sm-10">
      					<select id="edit_nature" name="community.nature" class="form-control" style="height:30px">
         					<option value="0">-选择性质-</option>		
							<c:forEach items="${natureList }" var="na">
									<option value="${na.id }" <c:if test="${na.id==community.nature }">selected="selected"</c:if>>${na.name }</option>							
							</c:forEach>
         				</select>
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="edit_num" class="col-sm-2 control-label">现有人数</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_num" name="community.num" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="edit_president" class="col-sm-2 control-label">社长</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_president" name="community.president" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="edit_phone" class="col-sm-2 control-label">手机</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_phone" name="community.pre_phone" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="edit_teacher" class="col-sm-2 control-label">指导教师</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_teacher" name="community.teacher" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="edit_partof" class="col-sm-2 control-label">隶属学院</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_partof" name="community.partof" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="edit_description" class="col-sm-2 control-label">简介</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_description" name="community.description" style="height:30px">
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
               	添加新社团
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/community!add.action' />" role="form" onsubmit="return false" class="form-horizontal" id="addForm">
          		<div class="form-group">
      				<label for="add_name" class="col-sm-2 control-label">社团名称</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_name" name="community.name" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="add_nature" class="col-sm-2 control-label">社团性质</label>
      				<div class="col-sm-10">
      					<select id="add_nature" name="community.nature" class="form-control" style="height:30px">
         					<option value="0">-选择性质-</option>		
							<c:forEach items="${natureList }" var="na">
									<option value="${na.id }" <c:if test="${na.id==community.nature }">selected="selected"</c:if>>${na.name }</option>							
							</c:forEach>
         				</select>
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="add_num" class="col-sm-2 control-label">现有人数</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_num" name="community.num" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="add_president" class="col-sm-2 control-label">社长</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_president" name="community.president" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="add_phone" class="col-sm-2 control-label">手机</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_phone" name="community.pre_phone" style="height:30px">
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="add_teacher" class="col-sm-2 control-label">指导教师</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_teacher" name="community.teacher" style="height:30px" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="add_partof" class="col-sm-2 control-label">隶属学院</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_partof" name="community.partof" style="height:30px" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="add_description" class="col-sm-2 control-label">简介</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_description" name="community.description" style="height:30px" >
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
	  					showSucc();
	  					$("#my_chart").load("<c:url value='/community!list.action'/>");
	  					
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
	  					$("#my_chart").load("<c:url value='/community!list.action'/>",{},function(){$.blockUI();$.unblockUI();});
	  				}
	  				else if(data['retcode']==1){
	  					showError();
	  				}
	  				return false;
			}});
			
	}		
</script>	
