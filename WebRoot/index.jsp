<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en" class="login-content" data-ng-app="materialAdmin">
 <head>
  <meta charset="UTF-8"> 
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>邯郸学院社团管理系统登录</title>

  <link rel="shortcut icon" type="image/x-icon" href="login_style/favicon.ico" />
  <!-- Vendor CSS -->
  <link href="login_style/css/material-design-iconic-font/css/material-design-iconic-font.min.css" rel="stylesheet" type="text/css">
  <!-- CSS -->
  <link href="login_style/css/app.min.1.css" rel="stylesheet" type="text/css">
  
  <script>	
		$(function () {
            var hname = $.cookie("hostname");
            var hpassword = $.cookie("hostpassword");
            if (hname && hpassword) {
                $("#userId").val(hname);
                $("#password").val(hpassword);
                $("#savaPwd").attr("checked","checked");
            }
        });
		function login(){
			var userId=$("#userId").val();
			var password = $("#password").val();
			var authImg = $("#authImg").val();
			if($.trim(userId)==""){
				alert("用户名不能为空");
				$("#userId").focus();
				return false;
			}
			if($.trim(password)==""){
				alert("密码不能为空");
				$("#password").focus()
				return false;
			}
			/*if($.trim(authImg)==""){
				alert("验证码不能为空");
				$("#authImg").focus();
				return false;
			}*/
			$.ajax({
	  			url:"<%=basePath%>login!login.action",
	  			dataType:"json",
	  			type:"post",
	  			data:{"userId":userId,"password":password,"authImg":authImg},
	  			error : function() {
					return false;
				},
	  			success:function(data){
	  				if(data['retcode']==0){
	  					if($("#savaPwd").attr("checked")=="checked") {
			                var hname = $("#userId").val();
			                var hpassword = $("#password").val();
			                $.cookie("hostname", hname, { expires: 7 });
			                $.cookie("hostpassword", hpassword, { expires: 7 });
		           		 }else{
		           			$.cookie("hostname", null, { path: '/' }); 
		           			$.cookie("hostpassword", null, { path: '/' }); 
		           		 }
	  					window.location.href='<%=basePath %>login!goMian.action'
	  				}
	  				else if(data['retcode']==1){
	  					alert(data['retmess']);
	  				}
	  				return false;
	  				}
				});
			}
			
		function keydown(e){
			var currKey=0,e=e||event;
	  		if(e.keyCode==13){
	  			login();
	  		}
	  	}
		</script>
 </head>
 <body class="login-content" data-ng-controller="loginCtrl as lctrl" style="z-index=-1">
	 
	 <div style="z-index=1;display:inline;position:absolute; top:18%; left:5%;">
		<img src="login_style/01.png" class="img-responsive" style="display:inline;vertical-align:-50%;"/>
		<h1 style="display:inline;"><font face="华文隶书">邯郸学院</font>&nbsp;<span><font face="黑体">社团管理系统</font></h1>
	 </div>
    <div class="lc-block" id="l-login" data-ng-class="{'toggled':lctrl.login === 1}">
    	<h1 class="lean">Login</h1>
	<form id="otam" name="otam" name=login action="login" method=post target="_top">
    	<div class="input-group m-b-20">
    		<span class="input-group-addon">
    			<i class="zmdi zmdi-account"></i>
    		</span>
    		<div class="fg-line">
    			<input name="userId" id="userId" type="text" class="form-control" placeholder="Username" />
    		</div>
    	</div>

        <div class="input-group m-b-20">
    		<span class="input-group-addon">
    			<i class="zmdi zmdi-male"></i>
    		</span>
    		<div class="fg-line">
    			<input name="password" id="password" type="password" class="form-control" placeholder="Password"/>
    		</div>
    	</div>
    </form>	
    	<div class="clearfix"></div>
    	
    	
        
        <div class="btn btn-login btn-danger btn-float" onclick="login();">
        	<i class="zmdi zmdi-arrow-forward" onclick="login();"></i>
        </div>
        
        
    </div>
    


    
 </body>
 	
 	<script src="login_style/js/bower_components/jquery/dist/jquery.min.js"></script>
 	<script src="rc/sc/jq/jquery.cookie.js"></script>
	<script src="login_style/js/log.js"></script>
	<!-- Angular -->
	<script src="login_style/js/bower_components/angular/angular.min.js"></script>
	<script src="login_style/js/bower_components/angular-resource/angular-resource.min.js"></script>
	<script src="login_style/js/bower_components/angular-animate/angular-animate.min.js"></script>
	
	
	<!-- Angular Modules -->
	<script src="login_style/js/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
	<script src="login_style/js/bower_components/angular-loading-bar/src/loading-bar.js"></script>
	<script src="login_style/js/bower_components/oclazyload/dist/ocLazyLoad.min.js"></script>
	<script src="login_style/js/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
	
	<!-- Common js -->
	<script src="login_style/js/bower_components/angular-nouislider/src/nouislider.min.js"></script>
	<script src="login_style/js/bower_components/ng-table/dist/ng-table.min.js"></script>
	
	<!-- Placeholder for IE9 -->
	<!--[if IE 9 ]>
	    <script src="js/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
	<![endif]-->
	<!-- App level -->
	<script src="login_style/js/app.js"></script>
	<script src="login_style/js/controllers/main.js"></script>
	<script src="login_style/js/controllers/ui-bootstrap.js"></script>
	
	
	<!-- Template Modules -->
	<script src="login_style/js/modules/form.js"></script>
</html>