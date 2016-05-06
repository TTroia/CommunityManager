<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" class="no-js">
  <head>
  	<%@ include file="/common/taglibs.jsp"%>
    <meta  charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <link rel="shortcut icon" href="<c:url value='/img/favicon.html'/>">

    <title>邯郸学院社团管理系统</title>
	<link type="text/css" rel="stylesheet" href="<c:url value='/assets/css/fonts/font-awesome/css/font-awesome.css'/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>" />
   	<link type="text/css" rel="stylesheet" href="<c:url value='/assets/css/bootstrap-reset.css'/>" />
   	<link type="text/css" rel="stylesheet" media="screen" href="<c:url value='/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css'/>" />
   	<link type="text/css" rel="stylesheet" href="<c:url value='/css/owl.carousel.css'/>" />
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/style.css'/>" />
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/style-responsive.css'/>" />
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/iosOverlay.css'/>" />
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/mail.css'/>" />
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/component.css'/>" />
	<script type="text/javascript">
		function getSecondMenu(id){
			$.ajax({
	  			url:"<c:url value='/model!getUserMenuList.action?partentId=' />"+id,
	  			dataType:"json",
	  			type:"post",
	  			data:{"id":id},
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					$.dialog.tips(data['retmess'],2,"tips.gif");
	  				}
				}});
		}
	
	function load_chart(url){
		$("#my_chart").load(url);
	}
	function setmenu(url){	
		$.ajax( {
        url: url, //这里是静态页的地址
        type: "POST", //静态页用get方法，否则服务器会抛出405错误
        success: function(data){
            $("#my_chart").html(data);
        }
		});
	}
	
	//退出登录
	function exit(){
	$.ajax({
		url:"<c:url value='/login!loginOut.action' />",
		type:"POST",
		success:function(data){
			alert("成功退出！");
		}
	});
	}

$(function () 
      {
      	$("#showmail").click(function(a){
			$("#allCity").slideDown(300);
		});
		$("#closeMail").click(function(a){
			$("#allCity").slideUp(300);
		});
       $("[data-toggle='popover']").popover();
       $( '#cd-dropdown' ).dropdown();
      });
      
      function changeMail(title,content,id){
      	$.ajax({
	  			url:"<c:url value='/mail!isRead.action' />",
	  			dataType:"json",
	  			type:"post",
	  			data:{"notice.id":id},
	  			error : function() {
	  			alert('0');
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					 $('#'+id+'_li').remove();
	  				}
	  				else if(data['retcode']==1){
	  					//$.dialog.tips(data['retmess'],2,"tips.gif");
	  				}
	  				return false;
				}});
      	$('#mailContent').html('<h3>'+title+'</h3>');
      	$('#mailContent').append('<div><p>'+content+'</p></div>');
      	
      }
	</script>
	
  </head>

  <body>
  <div class="md-modal md-effect-13" id="modal-13">
			<div class="md-content">
				
				<div id="mailContent">
				</div>
				<div>
				<button class=" md-close btn btn-primary">Close me!</button>
				</div>
		</div>
  </div>
  
  
  <div class="selcity" id="allCity" style="display:none;text-align:center">
	<table style="background:#7e2725">
		<tbody>
			<tr>
				<c:forEach items="${ noticeList}" var="no" begin="0" end="5">
						<td class="md-trigger md-setperspective" data-modal="modal-13" onclick="changeMail('${no.title}','${no.content}','${no.id }')">
							<a href="#">${no.fromUser}:${no.title }<c:if test="${no.isRead==0 }">&nbsp;&nbsp;&nbsp;<i id="${no.id }_li" class="icon-exclamation-sign" >&nbsp;</i></c:if></a>
						</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${ noticeList}" var="no" begin="6" end="12">
						<td class="md-trigger md-setperspective" data-modal="modal-13" onclick="changeMail('${no.title}','${no.content}','${no.id }')">
							<a href="#">${no.fromUser}:${no.title }<c:if test="${no.isRead==0 }">&nbsp;&nbsp;&nbsp;<i id="${no.id }_li" class="icon-exclamation-sign" >&nbsp;</i></c:if></a>
						</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${ noticeList}" var="no" begin="13" step="19">
						<td class="md-trigger md-setperspective" data-modal="modal-13" onclick="changeMail('${no.title}','${no.content}','${no.id }')">
							<a href="#">${no.fromUser}:${no.title }<c:if test="${no.isRead==0 }">&nbsp;&nbsp;&nbsp;<i id="${no.id }_li" class="icon-exclamation-sign" >&nbsp;</i></c:if></a>
						</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${ noticeList}" var="no" begin="20" step="25">
						<td class="md-trigger md-setperspective" data-modal="modal-13" onclick="changeMail('${no.title}','${no.content}','${no.id }')">
							<a href="#">${no.fromUser}:${no.title }<c:if test="${no.isRead==0 }">&nbsp;&nbsp;&nbsp;<i id="${no.id }_li" class="icon-exclamation-sign" >&nbsp;</i></c:if></a>
						</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${ noticeList}" var="no" begin="26" step="32">
						<td class="md-trigger md-setperspective" data-modal="modal-13" onclick="changeMail('${no.title}','${no.content}','${no.id }')">
							<a href="#">${no.fromUser}:${no.title }<c:if test="${no.isRead==0 }">&nbsp;&nbsp;&nbsp;<i id="${no.id }_li" class="icon-exclamation-sign" >&nbsp;</i></c:if></a>
						</td>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	<img id="closeMail" style="cursor:pointer" src="<c:url value="/img/mailBox_up.gif"/>"/>
</div>
  <section id="container" class="">
      <!--header start-->
      <header id="header" class="header white-bg">
            <div class="sidebar-toggle-box">
                <div data-original-title="Toggle Navigation" data-placement="right" class="icon-reorder tooltips"></div>
            </div>
            <!--logo start-->
            <a href="#" class="logo"><font face="华文隶书">邯郸学院</font>&nbsp;<span><font face="黑体">社团管理系统</font></span></a>
            <!--logo end-->
            <div class="nav notify-row" id="top_menu">
                <!--  notification start -->
                <ul class="nav top-menu">
                    <!-- inbox dropdown start-->
			<button id="showmail"type="button" class="btn btn-primary">
				<i class="icon-envelope-alt" ></i>&nbsp;收件箱
			</button>
			
			<!-- inbox dropdown end -->
                </ul>
                <!--  notification end -->
            </div>
            <div class="top-nav ">
                <!--search & user info start-->
                <ul class="nav pull-right top-menu">
                    <!-- user login dropdown start-->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="username"><font style="color:red">欢迎：</font>${user.username }</span>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <div class="log-arrow-up"></div>
                            <li onclick="show_password_edit_dialog('${user.userId}','${user.pwd }')"><a href="#"><i class="icon-cog"></i> 修改密码</a></li>
                            <li><a href="index.jsp" onclick="exit()"><i class="icon-key"></i> 登出</a></li>
                        </ul>
                    </li>
                    <!-- user login dropdown end -->
                </ul>
                <!--search & user info end-->
            </div>
        </header>
      <!--header end-->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu">
                  <li class="active">
                      <a class="" href="login!goMian.action">
                          <i class="icon-dashboard"></i>
                          <span>首页</span>
                      </a>
                  </li>
                  
                  <c:forEach items="${oneList }" var="vo">
                  	<li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon-book"></i>
                          <span>${vo.name }</span>
                          <span class="arrow"></span>
                      </a>
                      
                      	<ul class="sub">
                      	 <c:forEach items="${vo.secondList }" var="vi">
                          <li>
                          	<a class="" href="javascript:onclick=load_chart('${vi.url }') ">
								<span class="title" id="main_center" >${vi.name }</span>
							</a>
                          </li>
                          </c:forEach>
                      	</ul>
                      
                  	</li>
                  </c:forEach>
                  
                  
                  <li>
                      <a class="" href="javascript:onclick=load_chart('<c:url value='mail!list.action'/>')">
                          <i class="icon-envelope"></i>
                          <span >站内信 </span>
                      </a>
                  </li>
                  
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      <!--main content start-->
      <section id="main-content">
          <div class="wrapper" id="my_chart">
              <!--state overview start-->
              <div class="row state-overview">
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol terques">
                              <i class="icon-user"></i>
                          </div>
                          <div class="value">
                              <h1>${personCount }</h1>
                              <p>总社团人数</p>
                          </div>
                      </section>
                  </div>
                 
              </div>
              <!--state overview end-->

  


          </div>
      </section>
      <!--main content end-->
  </section>
  
  <!-- 修改密码模态框-start -->
<div class="modal fade" id="passwordModal" >
   <div class="modal-dialog">
      <div class="modal-content" style="border-style:solid; border-width:1px; border-color:#000;background-color:#DCDCDC" >
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               	修改密码
            </h4>
         </div>
         <div class="modal-body">
          	<form method="post" action="<c:url value='/user!updatePwd.action' />" role="form" onsubmit="return false" class="form-horizontal" id="pwd_editForm">
          		<input type="hidden" id="edit_userid" name="user.userId" value="${user.userId }"/>
          		<div class="form-group">
      				<label for="password" class="col-sm-2 control-label">新密码</label>
      				<div class="col-sm-10">
         				<input type="text" class="form-control" id="edit_password" name="user.pwd" placeholder="${user.pwd }" >
      				</div>
  				</div>
          	</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" onclick="pwd_editSumbit()">
             	  提交
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- 修改密码模态框-end -->

    <!-- js placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="<c:url value='/assets/js/jquery.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/jquery-1.8.3.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/bootstrap.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/jquery.scrollTo.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/jquery.nicescroll.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/jquery.sparkline.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/owl.carousel.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.customSelect.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/common-scripts.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/sparkline-chart.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/easy-pie-chart.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/iosOverlay.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/custom.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/classie.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modalEffects.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/rc/sc/util.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/rc/sc/jq/plus/jquery.form-3.25.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/rc/sc/Date97/WdatePicker.js'/>"></script>
  <script>

      //owl carousel

      $(document).ready(function() {
          $("#owl-demo").owlCarousel({
              navigation : true,
              slideSpeed : 300,
              paginationSpeed : 400,
              singleItem : true

          });
      });

      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });
      
      function show_password_edit_dialog(id,pwd){
		$.ajax({
	  			url:"<c:url value='/user!edit_pwd.action' />",
	  			dataType:"json",
	  			type:"post",
	  			data:{"userId":id,"pwd":pwd},
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					$('#edit_userid').val(data.userId); 
	  					$('#edit_password').val(data.pwd);
	  				}
	  				else if(data['retcode']==1){
	  					$.dialog.tips(data['retmess'],2,"tips.gif");
	  				}
	  				return false;
			}});
		jQuery('#passwordModal').modal('show', {backdrop: 'fade'});
	  }

	function pwd_editSumbit(){
		$.ajax({
	  			url:$("#editForm").attr("action"),
	  			dataType:"json",
	  			type:"post",
	  			data:$("#pwd_editForm").serialize(),
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					$('#passwordModal').modal('hide');
	  					//$('#editSuc').show();
	  					//setTimeout(function(){$("#editSuc").alert('close');},2000);
	  					exit();
	  					window.location.href="index.jsp";
	  				}
	  				else if(data['retcode']==1){
	  					//$('#addFai').show();
	  					//setTimeout(function(){$("#editFai").alert('close');},2000);
	  				}
	  				return false;
			}});
	}
  </script>
  </body>
</html>
