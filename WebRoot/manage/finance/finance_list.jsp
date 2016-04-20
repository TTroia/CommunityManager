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
      <form class="navbar-form navbar-left" role="search" id="searchform" method="post" action="<c:url value='/finance!_list.action' />">
      <input type="hidden" id="searchformpn" name="pn" value="${page.pageNo }"/>
         <div class="form-group">
            <input type="text" class="form-control" name="nature.name" placeholder="Search" style="height:30px">
         </div>
      </form>    
   </div>
   <button class="btn" id="search" >查询</button>
   <div style="margin-top:10px;float:right;">
   	<button class="btn btn-add" onclick="show_add_dialog()">添加新记录 »</button>
	
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

</script>	
</body>
</html>