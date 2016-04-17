<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="p" uri="/query-tags" %>
 <table  border="0" cellpadding="0" cellspacing="0" class="table table-bordered table-hover" id="table" style="text-align:center">
			<thead>	
			<tr>
					<td class="th30 " >
						序号
					</td>
					<td class="th30 " >
						用户编号
					</td>
					<td class="th30 " >
						用户名
					</td>
					<td class="th30 " >
						用户角色
					</td>
					<td class="th30 " >
						所属院系
					</td>
					<td class="th30 " >
						所属社团
					</td>
					<td class="th30 " >
						联系电话
					</td>
					<td class="th30 " >
						qq
					</td>
					<td class="th30 " >
						创建时间
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
							${result.userId}
						</td>
						<td>${result.username }</td>
						<td>${result.roleName }</td>
						<td>${result.partof }</td>
						<td>${result.communityName }</td>
						<td>${result.phone }</td>
						<td>${result.qq }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${result.createDate}" /></td>
						<td>
							<button type="button" class="btn btn-info btn-xs" onclick="show_edit_dialog('${result.userId}')" >修改</button>&nbsp;&nbsp;
							<button onclick="show_pro('${result.userId}')" type="button" class="btn btn-info btn-xs" title="确定删除吗？" style="margin-right: 0px;" id="${result.userId }delPro"
      						data-container="body" data-toggle="popover" data-placement="bottom" data-html="true" data-content="<div style='text-align:right'><button type='button' class='btn btn-info btn-xs' onclick='del(${result.userId });'>确认</button></div>">
      						删除</button>
						</td>
					</tr>
				</c:forEach>
				<p:dynamicPage form="searchform" resultDiv="result"  colspan="13" hasGo="true"></p:dynamicPage>
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
               修改用户信息
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/user!update.action' />" role="form" onsubmit="return false" class="form-horizontal" id="editForm">
          		<div class="form-group">
      				<label for="userId" class="col-sm-2 control-label">用户编号</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_userId" name="user.userId" value="${user.userId }" readonly="readonly" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="username" class="col-sm-2 control-label">用户名</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_username" name="user.username" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="url" class="col-sm-2 control-label">用户角色</label>
      				<div class="col-sm-10">
         				<select id="edit_roleId" name="user.roleId" class="form-control">
         					<option value="0">-选择角色-</option>		
							<c:forEach items="${roleList }" var="role">
									<option value="${role.roleId }" <c:if test="${role.roleId==user.roleId }">selected="selected"</c:if>>${role.name }</option>							
							</c:forEach>
         				</select>
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="pwd" class="col-sm-2 control-label">密码</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_pwd" name="user.pwd" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="phone" class="col-sm-2 control-label">手机</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_phone" name="user.phone" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="QQ" class="col-sm-2 control-label">QQ</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_QQ" name="user.QQ" >
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
               添加新用户
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/user!add.action' />" role="form" onsubmit="return false" class="form-horizontal" id="addForm">
          		<div class="form-group">
      				<label for="userId" class="col-sm-2 control-label">账号</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_userId" name="user.userId" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="username" class="col-sm-2 control-label">昵称</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_username" name="user.username" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="url" class="col-sm-2 control-label">用户角色</label>
      				<div class="col-sm-10">
         				<select id="add_roleId" name="user.roleId" class="form-control">
         					<option value="0">-选择角色-</option>		
							<c:forEach items="${roleList }" var="role">
									<option value="${role.roleId }" <c:if test="${role.roleId==user.roleId }">selected="selected"</c:if>>${role.name }</option>							
							</c:forEach>
         				</select>
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="url" class="col-sm-2 control-label">所属院系</label>
      				<div class="col-sm-10">
         				<select id="add_partof" name="user.partof" class="form-control">
         					<option value="0">-选择院系-</option>		
							<c:forEach items="${partofList }" var="part">
									<option value="${part }" >${part }</option>							
							</c:forEach>
         				</select>
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="pwd" class="col-sm-2 control-label">密码</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_pwd" name="user.pwd" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="phone" class="col-sm-2 control-label">手机</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_phone" name="user.phone" >
      				</div>
  				</div>
  				<div class="form-group">
  					<label for="QQ" class="col-sm-2 control-label">QQ</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="add_QQ" name="user.qq" >
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
