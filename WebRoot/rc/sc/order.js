// 软件备忘
function viewRemark(orderid){
	var c = "<table width='450px' border='0' cellpadding='0' cellspacing='1' class='tab011' style='margin-left: 1px'>";
	c = c+"<tr><td align='right' valign='top' class='b12' width='15%'>标记：</td><td style='color: black; font-weight: normal;' width='85%'><input name='flag' type='radio' class='flag' value='0' checked='checked'/>无&nbsp;&nbsp;&nbsp;<input name='flag' type='radio' value='1'  class='flag'/><img src='/rc/im/red.png'/>&nbsp;&nbsp;&nbsp;<input name='flag' type='radio' value='2' class='flag' /><img src='/rc/im/blue.png'/>&nbsp;&nbsp;&nbsp;<input name='flag' type='radio' value='3'  class='flag'/><img src='/rc/im/green.png'/>&nbsp;&nbsp;&nbsp;<input name='flag' type='radio' value='4'  class='flag'/><img src='/rc/im/yellow.png'/>&nbsp;</td>";
	c = c+"</tr>";
	c = c+"<tr><td align='right' valign='top' class='b12' width='15%'>说明：</td><td style='color: black; font-weight: normal;' width='85%'><textarea rows='6' cols='40' id='remark' name='remark'></textarea></td>";
	c = c+"</tr>";
	c = c+"</table>";

	var flagorderid = document.getElementById("flag"+orderid);
	var remarkorderid = document.getElementById("remark"+orderid);
	var sellerId = "";
	if($("#sellerId")){
		sellerId = $("#sellerId").val();
	}
	var dialog = $.dialog({
		lock: true,
	    title:'软件备忘',
	    content:c,
	    width: '450px',
        height:'100px',
        max:false,
    	min:false,
    	init: function () {
			
			if(flagorderid.value=="0"){
				$("input[name=flag]:eq(0)").attr("checked",'checked'); 
			}else if(flagorderid.value=="1"){
				$("input[name=flag]:eq(1)").attr("checked",'checked'); 
			}else if(flagorderid.value=="2"){
				$("input[name=flag]:eq(2)").attr("checked",'checked'); 
			}else if(flagorderid.value=="3"){
				$("input[name=flag]:eq(3)").attr("checked",'checked'); 
			}else if(flagorderid.value=="4"){
				$("input[name=flag]:eq(4)").attr("checked",'checked'); 
			}
			var orderRemark = document.getElementById("remark");
    		orderRemark.value = remarkorderid.value;
        },
    	button: [{
            name: '保存',
            focus:true,
            callback: function () {
        		var orderRemark = document.getElementById("remark");
        		var orderId = orderid;
        		var d = document.getElementById("date"+orderId);
        		var orderFlag = $('input[name="flag"]:checked').val();
        		var param = {"sellerId":sellerId,"orderId":orderId,"gmtPayment":d.value,"orderFlag":orderFlag,"orderRemark":orderRemark.value};
        		var orderRemarkTemp = orderRemark.value;
        		$.ajax({
          			url:"/dingdan/dingdan!updateOrderRemark.action",
          			dataType:"json",
          			type:"post",
          			data:param,
          			success:function(data){
               				var commentFlag = $("#commentFlag"+orderId);
               				commentFlag.text(orderRemarkTemp);
               				commentFlag.attr("class","commentFlag"+orderFlag);
               				flagorderid.value = orderFlag;
               				remarkorderid.value = orderRemarkTemp;
               				this.close();
           			}
           		});
            }
        },{
            name: '取消',
            callback: function () {
            	this.close();
            }
        }]
	});
}
// 发货
var sendErrorDia  ;
function sendWuliuSigle(sellerId,orderId,gmt,flag){
	var sellerId = "";
	if($("#sellerId")){
		sellerId = $("#sellerId").val();
	}
	sendErrorDia =  $.dialog({
		id:'sendId',
		fixed:true,
	    lock: true,
		title:'发货',
	    content: 'url:/print/print!sendSigleInJsp.action?sellerId='+sellerId+'&orderId='+orderId+'&gmtCreate='+gmt+'&include='+flag,
	    width:'560px',
	    height:'180px',
	    fixed: true,
	    button: [
	        {
	            name: '发送',
	            id:'sendBtn',
	            callback: function(){
	            	this.button({
	                    id:'sendBtn',
	                    name: '正在发货',
	                    disabled: true });
	            	  this.content.sumbit();
	                return false;
	            },
	            focus: true
	},{
		 name: '取消',
         callback: function(){
        	 this.close();
             return false;
         },
         focus: false
	}]});
}
// 发货 弹出 alert 需要重置发货
function sendError(){
	
	if(sendErrorDia){
		sendErrorDia.button({
            id:'sendBtn',
            name: '发送',
            disabled: false,
            fixed:true});
	}
}

// 发货成功后 关闭对话框
function sendOver(){
	if(sendErrorDia){
	
		sendErrorDia.close();
	}
	
}

/*选择 全选 和反选 物流*/
function checkAllWl(obj){	
	var info = "";
	if($(obj).attr("checked")=="checked"){// 
		$("input[name='orderIds']:checkbox").each(function(){
			var orderId = $(this).attr("alt");
			var temp = $("#peihuo_"+orderId).html();
			if(temp.indexOf("x.png")>0){
					$(this).attr("checked", "checked");
				}else{
					var str = $(this).val().split("_");
					info = info +str[0]+"&nbsp;&nbsp;已打印"+"<br/>  ";
				}
			});
	 }else{
		$("input[name='orderIds']:checkbox").each(function(){
						$(this).attr("checked",false);
			});
		}
	if(info !=""){
		info= "<font color='red'>"+info+"<br/>这些订单没有被选中！"+"</font>";
		$.dialog.alert(info,"tips.gif").title("提示");
	}
}

/*选择 全选 和反选 快递*/
function checkAllExp(obj){	
	var info = "";
	if($(obj).attr("checked")=="checked"){// 
		$("input[name='orderIds']:checkbox").each(function(){
				var orderId = $(this).attr("alt");
				var temp = $("#exp_"+orderId).html();
				if(temp.indexOf("x.png")>0){
					$(this).attr("checked", "checked");
				}else{
					info = info +orderId+"&nbsp;&nbsp;已打印"+"<br/>  ";
				}
			});
	 }
	else{
		$("input[name='orderIds']:checkbox").attr("checked",false);
		}
  if(info !=""){
		info= "<font color='red'>"+info+"<br/>这些订单没有被选中！"+"</font>";
		$.dialog.alert(info,"tips.gif").title("提示");
	}
}
// 选择快递单已打印
function  checkExpPrint(obj){
	if($(obj).attr("checked")=="checked"){// 
		$("input[name='orderIds']:checkbox").each(function(){
			var orderId = $(this).attr("alt");
			var temp = $("#exp_"+orderId).html();
			if(temp.indexOf("j.png")>0){
				$(this).attr("checked", "checked");
			}
			});
	 }else{
		$("input[name='orderIds']:checkbox").attr("checked",false);
		}
}
// 批打印快递单
function printExpAll(){
	var orderIds = "";
	$("input[name='orderIds']:checked").each(function(index){
			// var temp = $(this).val().split("-");
			orderIds = orderIds+$(this).val()+",";//+"_"+buyer[1]+",";
	});
	var logisticsCompanyNo = $("#logisticsCompanyNo").val();
	if(logisticsCompanyNo=='qxzkd'){
		$.dialog.tips('请选择快递单类型!',1,'error.gif');
		return ;
	}
	if(orderIds ==""){
		$.dialog.tips('请选择快递单类型!',1,'error.gif');
		return ;
	}
	orderIds = orderIds.substring(0,orderIds.length-1);
	var sellerId = "";
	if($("#sellerId")){
		sellerId = $("#sellerId").val();
	}
	window.open("/print/print!megerPrintExpress.action?orderIds="+orderIds+"&logisticsCompanyNo="+logisticsCompanyNo+"&sellerId="+sellerId);
}

// 打印配货单 flag为1 是图片 0 是文字
function printAll(flag){
	var orderIds = "";
	$("input[name='orderIds']:checked").each(function(){
			orderIds = orderIds+$(this).val()+",";
	});
	if(orderIds ==""){
		$.dialog.tips('请选择要打印的订单！',1,'error.gif');
		return ;
	}
	var sellerId = "";
	if($("#sellerId")){
		sellerId = $("#sellerId").val();
	}
	orderIds = orderIds.substring(0,orderIds.length-1);
	
	window.open("/print/print!megerPrint.action?orderIds="+orderIds+"&flag="+flag+"&sellerId="+sellerId);
}

 
  // 直接打印 选择打印机
function checkPrinter(obj){
	 
	if($(obj).attr("checked")=="checked"){
		LODOP=getLodop(document.getElementById('LODOP2'),document.getElementById('LODOP_EM2')); 
		var iPrinterCount=LODOP.GET_PRINTER_COUNT();
		var select = document.createElement('select');
		for(var i=0;i<iPrinterCount;i++){
			var option=document.createElement('option');
			option.innerHTML=LODOP.GET_PRINTER_NAME(i);
			option.value=i;
			select.appendChild(option);
		}
		document.getElementById("dayinji").appendChild(select);
	  }else{
		  $("#dayinji").html("");
	  }
  }

/* 列表页面进入批量发货的页面*/
function sendAllInJsp(){
	
	var s = $("#logisticsCompanyNo").val();
	if(s=='qxzkd'){
		$.dialog.tips('请选择物流公司!',1,'error.gif');
		return false ;
	}
	var orderIds = "";
	$("input[name='orderIds']:checked").each(function(index){
			orderIds = orderIds+$(this).val()+",";//+"_"+buyer[1]+",";
	});
	if(orderIds ==""){
		$.dialog.tips('请选择要发货的快递单!',1,'error.gif');
		return false;
	}
	var sellerId = "";
	if($("#sellerId")){
		sellerId = $("#sellerId").val();
	}
	orderIds = orderIds.substring(0,orderIds.length-1);
	window.open("/print/print!sendAll.action?orderIds="+orderIds+"&logisticsCompanyNo="+s+"&sellerId="+sellerId);
	
}
// 修改打印状态
function updatePrint(obj,printFlag){
	
	var print = 1;
	// 如果是j
	if($(obj).html().indexOf("x.png")<0){
		
		print = 0;
	}
	var sellerId = "";
	if($("#sellerId")){
		sellerId = $("#sellerId").val();
	}
	$.blockUI(mes);
	  $.ajax({
	   	type: 'post',	
	   	dataType:'text',
	   	url: "/print/print!update.action",	
	   	data: "orderId="+$(obj).attr("title")+"&print="+print+"&printFlag="+printFlag+"&sellerId="+sellerId,
	   	success: function(date){
	   	var json = eval('(' + date + ')');
	    var res = json.success;
	    if(res==1){
	    	$.unblockUI();
	    	$.dialog.tips("更新成功！",1,"success.gif");
	    	if(print ==1)
	   		  $(obj).html("<img src='/rc/im/j.png'/>"); 
	    	else{
	    	 $(obj).html("<img src='/rc/im/x.png'/>");
	    	}
	     }
	   	} 
	  });	
}

// 买家所有订单
function seachAllBuyerOrder(buyerId){
	$("#buyerId").val(buyerId);
	$("#isAllBuyer").val("1");
	$('#search').trigger("click");
}

//发货
function seachWuliu(orderId,logisticsName,logisticsOrderNo){
	var sellerId = "";
	if($("#sellerId")){
		sellerId = $("#sellerId").val();
	}
	var logisticsName1 = encodeURI(encodeURI(logisticsName));
	 $.dialog({
		id:'sendId',
		fixed:true,
	    lock: true,
		title:'查询物流',
	   content: 'url:/print/print!searhWuliuDetial.action?sellerId='+sellerId+'&orderId='+orderId+'&logisticsName='+logisticsName1+'&logisticsOrderNo='+logisticsOrderNo,
	    width:'700px',
	    height:'450px'
		});
}