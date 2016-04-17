/**
 * 常用方法 -- 需要jquery.form插件的支持
 * 
 * @author zy
 */ 
;(function($) {
	
	/**
	 * 判断对象是否存在
	 * 
	 * @see $("#textId").exist()
	 * @return true or false
	 */
	$.fn.exist = function(type) {

		flag = $(this).attr(type) || $(this).attr("id") || $(this).attr("name");
		
		if (flag) {
			return true;
		} else {
			return false;
		}
	};
	
	/**
	 * 判断拥有checked、selected、readOnly属性的真假
	 * 
	 * @see $("#textId").propAttr("checked");
	 * @return true or false
	 */
	$.fn.propAttr = function(type) {

		try {
			flag = $(this).prop(type);
		} catch (e) {
			flag = $(this).attr(type);
		}
		return flag;
	};
	
	/**
	 * 获取当前JQuery版本号
	 * 
	 * @see $.version
	 * @return jquery's version number
	 */
	jQuery.version = jQuery.jquery = $.fn.jquery;
	
	/**
	 * @see $("#textId").trimText();
	 * @return object value
	 */
	$.fn.trimText = function() {
		if ($(this).val()) $(this).val($.trim($(this).val()));
		return $(this).val();
	};

	/**
	 * @see $("#textId").trimTextAll();
	 * @return object value
	 */
	$.fn.trimTextAll = function() {

		if ($(this).val()) {
			$(this).val($.trim($(this).val()).replace(/\s{2,}/g, ' '));
		}
		return $(this).val();
	};

	/**
	 * @see $("#textId").isEmpty();
	 * @return boolean(false:null; true:right)
	 */
	$.fn.isEmpty = function() {

		if ($(this).val() == undefined || $(this).val() == null) {
			return false;
		}

		if ($.trim($(this).val()) == "") {

			return false;
		}

		return true;
	};

	/**
	 * @see $("#textId").isNum();
	 * @return boolean(false:error; true:right)
	 */
	$.fn.isNum = function() {

		var s = $(this).val() ? $(this).val() : "";
		
		if (s) {
			
			if (isNaN(s)) {
				return false;
			}
			return true;
		}
		return false;
	};

	/**
	 * @see $("#textId").isDate();
	 * @return boolean(false:error; true:right)
	 */
	$.fn.isDate = function() {

		var s = $(this).val() ? $(this).val() : "";

		if (s) {

			var pattern = /^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s(((0?[0-9])|([1-2][0-3]))\:([0-5]?[0-9])((\s)|(\:([0-5]?[0-9])))))?$/;

			if (pattern.exec(s)) {

				return true;
			}
		}
		return false;
	};

	/**
	 * @see $("#textId").isPostalCode();
	 * @return boolean(false:error; true:right)
	 */
	$.fn.isPostalCode = function() {

		var s = $(this).val() ? $(this).val() : "";

		if (s) {

			if (/^[0-9]{6}$/.test(s)) {

				return true;
			}
		}
		return false;
	};

	/**
	 * @see $("#formId").isMail();
	 * @return boolean(false:error; true:right)
	 */
	$.fn.isMail = function() {

		var rep = /^\w+([\.])*([-+.]\w+)*\w*@\w+([-.]\w+)*.\w+([-.]\w+)*$/;

		if ("" == $(this).val() || 5 > $(this).val().length || 50 < $(this).val().length) {
			return false;
			
		} else if(rep.test($(this).val())){
			
			return true;
		}
		return false;
	};

	/**
	 * @see $("#formId").serializeObject();
	 * @return json object
	 */
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};

	/**
	 * @see $("#objId").submitForm(option);
	 * 
	 * @param option 查询的参数
	 * @return void
	 */
	$.fn.submitForm = function(options) {
		
		$(this).ajaxForm(options).submit();
		
		return false;
	};
	
	/**
	 * @see $("#objId").queryForm(option);
	 * 
	 * @param option 查询的参数
	 * @return void
	 */
	$.fn.queryForm = function(options) {

		$(options.target).load(this.attr("action"), this.serializeObject(), function (data) {
			options.success.call(this, arguments);
		});

		return false;
	};

	/**
	 * @see $("#objId").setDynamicPage(pageNo, targetId, pageId);
	 * @return void
	 */
	$.fn.setDynamicPage = function(pageNo, targetId, pageId, callBefore, callBehind) {

		if (isElement(targetId)) {
			
			get(pageId).value = pageNo;
			
			try {
				$("#" + targetId + " *").attr("disabled", "disabled");

				callBefore.evalObj().call(window, targetId);
			} catch (e) {
			}
			$(this).queryForm( {
				target : "#" + targetId,
				error : function() {
					alert("\u64cd\u4f5c\u5f02\u5e38,\u8bf7\u5237\u65b0\u540e\u91cd\u8bd5!");
					return false;
				},
				success : function(data, status) {
					setTimeout(function() {
						try {
							callBehind.evalObj().call(window, targetId);
						} catch (e) {
						}
					}, 1);
				}
			});
		} else {
			alert("\u53c2\u6570\u5f02\u5e38");
		}

		return $;
	};

	/**
	 * 内部方法，外部不可以用
	 */
	var chkedArr = [];

	var fillin = function (tbody) {

		$("#" + tbody).find("input[name=checkboxId]").each(function() {
			var existMark = false;
			var index = 0;

			for ( var i in chkedArr) {
				if (chkedArr[i] == $(this).val()) {

					existMark = true;
					index = i;
					break;
				}
			}
					
			var flag = false;

			try {
				flag = $(this).prop("checked");
			} catch (e) {
				flag = $(this).attr("checked");
			}

			if (true == flag) {

				if (!existMark) {
					// 不存在，操作效仿unshift操作
					chkedArr.reverse();
					chkedArr.push($(this).val());
					chkedArr.reverse();
				}
			} else {
				if (existMark) {
					
					// 存在
					chkedArr.splice(index, 1);
				}
			}
		});
	};

	var refill = function (tbody) {
			
		$("#" + tbody).find("input[name=checkboxId]").each(function() {

			for ( var i in chkedArr) {

				if (chkedArr[i] == $(this).val()) {

					$(this).attr("checked", true);
					chkedArr.splice(i, 1);
					break;
				} else {
					$(this).attr("checked", false);
				}
			}
		});

		var values = chkedArr.toString().replace(/,+/g, ",");
		
		if ($("#checkedId").attr("name")) {
			$("#checkedId").val(values);

		} else {
			var obj = $("<input type=hidden id=checkedId name=checkedId value='"
					+ values + "'/>");
			$("#" + tbody).append(eval(obj));
		}
	};
	
	/**
	 * 将对象绑定propertychange事件，兼容IE、firefox
	 * 
	 * @see $("#objId").propertychange(function () {});
	 * @return void
	 */
	$.fn.propertychange = function () {

		var args=arguments, proxyFn = function( event ) {

			event.preventDefault();
			if (0 < args.length)
			return args[0].apply( this, arguments ) || false;
		};
		
		try {
			return get(this.attr("id")).addEventListener("input", proxyFn ,false);
		} catch (e) {
			return $(this).bind("propertychange", proxyFn);
		}
	};

	var d1 = "", d2 = "";
	$.fn.checkStart = function () {
		
		if (d1 != $(this).val()) {
			d1 = $(this).val();
			$.checkd(d1, d2);
		}
	};
	
	$.fn.checkEnd = function () {
		
		if (d2 != $(this).val()) {
			d2 = $(this).val();
			$.checkd(d1 , d2);
		}
	};

	$.checkd = function (sdv, edv) {
			
		if (!compareDate(sdv, edv)) return false;
	};
	
})(jQuery);

String.prototype.evalObj = function () {
	return eval("("+this+")");
};
/**
 * 获取元素对象
 * 
 * @see get("elementId");
 * @return javascript object
 */
var get = function(id) {
	return document.getElementById(id) || null;
};

/**
 * 获取指定名称的一组元素对象
 * 
 * @see getElmtName("elementName");
 * @return javascript object
 */
var getElmtName = function(tag, obj) {
	if (obj == null) {
		return document.getElementsByName(tag);
	} else {
		return obj.getElementsByName(tag);
	}
};

/**
 * 获取指定标签名称的一组元素对象
 * 
 * @see getTagName("elementName");
 * @return javascript object
 */
var getTagName = function(tag, obj) {
	if (obj == null) {
		return document.getElementsByTagName(tag);
	} else {
		return obj.getElementsByTagName(tag);
	}
};

/**
 * 判断元素是否存在
 * 
 * @see isElement("elementId");
 * @return boolean(false:error; true:right)
 */
var isElement = function(id) {

	if (get(id))
		return true;
	else
		return false;
};

/**
 * 判断元素是否存在
 * 
 * @see isObject(object);
 * @return boolean(false:error; true:right)
 */
var isObject = function(obj) {

	return (obj instanceof Object);
};


/**
 * 创建元素
 * 
 * @see cNode("div", {id:'elementId',name:'elementName'});
 * @return boolean(false:error; true:right)
 */
var cNode = function(c,opt){
	var b
		,melement=document.createElement(c)
		,setting={
				"class":function(){
					melement.className=opt["class"]
				},
				style:function(){
					k(melement,opt.style)
				}
			};
	for(attr in opt)
		if(setting[attr])
			setting[attr]();
		else 
			melement.setAttribute(attr,opt[attr]);
	
	return melement
};

/**
 * 日期校验
 * 
 * @parameter yyyy-dd-mm
 * @return boolean(false:error; true:right)
 */
var compareDate = function(sDate, eDate) {

	if (sDate != "" && eDate != "") {
		var strdt1 = sDate.replace(/-|\//g, "/");
		var strdt2 = eDate.replace(/-|\//g, "/");

		var dt1 = new Date(Date.parse(strdt1));
		var dt2 = new Date(Date.parse(strdt2));

		if (dt1 > dt2) {
			//开始时间不能晚于结束时间
			return false;
		}
	}
	return true;
};

/**
 * 金额
 * @param obj
 * @param event
 * @return
 */
var moneyPre = function(obj, event) {
	var event = event ? event : window.event;
	var k = event.keyCode;
	var v = obj.value;

	switch (k) {
	case 189:
	case 109:
		if (v.indexOf("-") == -1) {
			if (v.indexOf("-") == 0) {
				return;
			}
		}
		obj.value = v.replace(/-/g, '');
		obj.value = "-" + obj.value;
		if (window.event)
			event.returnValue = false;
		else
			event.preventDefault();
		break;
	case 190:
	case 110:
		if (v.indexOf(".") != -1) {
			if (window.event)
				event.returnValue = false;
			else
				event.preventDefault();
		}
		break;
	case 35:
	case 36:
	case 46:
	case 8:
	case 9:
		break;
	case 13:
		event.keyCode = 9;
		break;
	default:
		if (event.shiftKey
				&& ((k >= 48 && k <= 57) || (k >= 96 && k <= 105) || (k >= 37 && k <= 40))) {
			if (window.event)
				event.returnValue = false;
			else
				event.preventDefault();

		} else if ((k >= 48 && k <= 57) || (k >= 96 && k <= 105)
				|| (k >= 37 && k <= 40)) {
			return;
		}

		if (window.event)
			event.returnValue = false;
		else
			event.preventDefault();
	}
};

/**
 * 仅数字
 */
var numberPre = function(obj, event) {
	var event = event ? event : window.event;
	var k = event.keyCode;
	var ks = event.shiftKey;
	var v = obj.value;
	switch (k) {
	case 35:
	case 36:
	case 46:
	case 8:
	case 9:
		break;
	case 13:
		event.keyCode = 9;
		break;
	default:

		if (event.shiftKey
				&& ((k >= 48 && k <= 57) || (k >= 96 && k <= 105) || (k >= 37 && k <= 40))) {

			if (window.event)
				event.returnValue = false;
			else
				event.preventDefault();
		} else if ((k >= 48 && k <= 57) || (k >= 96 && k <= 105)
				|| (k >= 37 && k <= 40)) {
			return;
		}

		if (window.event)
			event.returnValue = false;
		else
			event.preventDefault();
	}
};