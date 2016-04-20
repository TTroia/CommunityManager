﻿/**
 * jQuery :  城市联动插件
 * @author   XiaoDong <cssrain@gmail.com>
 *			 http://www.cssrain.cn
 * @example  $("#test").senderProvinceCity();
 * @params   暂无
 */
$.fn.ProvinceCity = function(provice,city,eara){
	var _self = this;
	//定义3个默认值
	_self.data("senderProvince",[provice, provice]);
	_self.data("senderCity",[city, city]);
	_self.data("senderArea",[eara, eara]);
	//插入3个空的下拉框
	_self.append("<select name='senderProvince' id='senderProvince'></select>");
	_self.append("<select name='senderCity' id='senderCity'></select>");
	_self.append("<select name='senderArea' id='senderArea'></select>");
	//分别获取3个下拉框
	var $sel1 = _self.find("select").eq(0);
	var $sel2 = _self.find("select").eq(1);
	var $sel3 = _self.find("select").eq(2);
	//默认省级下拉
	if(_self.data("senderProvince")){
		$sel1.append("<option value='"+_self.data("senderProvince")[1]+"'>"+_self.data("senderProvince")[0]+"</option>");
	}
	$.each( GP , function(index,data){
		$sel1.append("<option value='"+data+"'>"+data+"</option>");
	});
	//默认的1级城市下拉
	if(_self.data("senderCity")){
		$sel2.append("<option value='"+_self.data("senderCity")[1]+"'>"+_self.data("senderCity")[0]+"</option>");
	}
	//默认的2级城市下拉
	if(_self.data("senderArea")){
		$sel3.append("<option value='"+_self.data("senderArea")[1]+"'>"+_self.data("senderArea")[0]+"</option>");
	}
	//省级联动 控制
	var index1 = "" ;
	$sel1.change(function(){
		//清空其它2个下拉框
		$sel2[0].options.length=0;
		$sel3[0].options.length=0;
		index1 = this.selectedIndex;
		if(index1==0){	//当选择的为 “请选择” 时
			if(_self.data("senderCity")){
				$sel2.append("<option value='"+_self.data("senderCity")[1]+"'>"+_self.data("senderCity")[0]+"</option>");
			}
			if(_self.data("senderArea")){
				$sel3.append("<option value='"+_self.data("senderArea")[1]+"'>"+_self.data("senderArea")[0]+"</option>");
			}
		}else{
			$.each( GT[index1-1] , function(index,data){
				$sel2.append("<option value='"+data+"'>"+data+"</option>");
			});
			$.each( GC[index1-1][0] , function(index,data){
				$sel3.append("<option value='"+data+"'>"+data+"</option>");
			})
		}
	}).change();
	//1级城市联动 控制
	var index2 = "" ;
	$sel2.change(function(){
		$sel3[0].options.length=0;
		index2 = this.selectedIndex;
		$.each( GC[index1-1][index2] , function(index,data){
			$sel3.append("<option value='"+data+"'>"+data+"</option>");
		})
	});
	return _self;
};