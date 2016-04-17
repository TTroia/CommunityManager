/**
 * 日期常量
 */
Date.MODE = {
	"Year" : "y",
	"Month" : "M",
	"Date" : "d",
	"Hours" : "h",
	"Minutes" : "m",
	"Seconds" : "s",
	"MilliSeconds" : "ms",
	"TIME" : "TIME",
	"DATETIME" : "DATETIME"
};

/**
 * 对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
 * 可以用 1-2 个占位符 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04 
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04 
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04 
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.pattern = function(fmt) {
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f"
								: "\u5468")
								: "")
								+ Date.prototype.settings.week[this.getDay()
										+ ""]);
	}

	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
};

/**
 * 两个日期进行比较
 * (new Date()).compareTo(date1)
 * 
 * @Param dt
 *            相较的日期 String,或Date
 * @param type
 *            比较类型 只比较日期(默认);Date.TIME：只比较时间；Date.DATETIME：日期时间全比较；
 * 
 * @return {Number} -1:小于；0:相等；1：大于
 */
Date.prototype.compareTo = function(dt, type) {

	var anotherDt;
	if (dt instanceof Date) {

		anotherDt = dt;
	} else if (typeof dt == "string") {

		anotherDt = dt.parseToDate();
	}

	if (!anotherDt) {

		return undefined;
	}

	switch (type) {
	case Date.MODE.DATETIME:
		return (this == anotherDt ? 0 : (this < anotherDt ? -1 : 1));
		break;
	case Date.MODE.TIME:
		return (this.toTimeString() == anotherDt.toTimeString() ? 0
				: (this < anotherDt ? -1 : 1));
		break;
	default:
		return (this.toDateString() == anotherDt.toDateString() ? 0
				: (this < anotherDt ? -1 : 1));
	}
};

/**
 * 增加天数
 * (new Date()).addDate(90);
 * 
 * @param number
 *            天数
 * @return Date
 */
Date.prototype.addDate = function(number) {

	return this
			.operation(Date.MODE.Date, number, Date.prototype.settings.o.ADD);
};

/**
 * 增加月数
 * (new Date()).addMonth(12);
 * 
 * @param number
 *            月数
 * @return Date
 */
Date.prototype.addMonth = function(number) {

	return this.operation(Date.MODE.Month, number,
			Date.prototype.settings.o.ADD);
};

/**
 * 增加年数
 * (new Date()).addYear(100);
 * 
 * @param number
 *            年数
 * @return Date
 */
Date.prototype.addYear = function(number) {

	return this
			.operation(Date.MODE.Year, number, Date.prototype.settings.o.ADD);
};

/**
 * 减少天数
 * (new Date()).subDate(31);
 * 
 * @param number
 *            天数
 * @return Date
 */
Date.prototype.subDate = function(number) {

	return this
			.operation(Date.MODE.Date, number, Date.prototype.settings.o.SUB);
};

/**
 * 减少月数
 * (new Date()).subMonth(31);
 * 
 * @param number
 *            月数
 * @return Date
 */
Date.prototype.subMonth = function(number) {

	return this.operation(Date.MODE.Month, number,
			Date.prototype.settings.o.SUB);
};

/**
 * 减少年数
 * (new Date()).subYear(31);
 * 
 * @param number
 *            年数
 * @return Date
 */
Date.prototype.subYear = function(number) {

	return this
			.operation(Date.MODE.Year, number, Date.prototype.settings.o.SUB);
};

/**
 * 日期时间的计算
 * 
 * @param type 
 * @param number
 * @param operator
 * @return Date Object
 */
Date.prototype.operation = function(type, number, operator) {

	var d = this;
	var o = Date.prototype.settings;

	if (o.k[type] && typeof number == "number") {

		switch (operator) {
		case '+':
		case '-':
			break;
		default:
			return d;
		}
		try {
			eval("d.set" + o.k[type] + "(d.get" + o.k[type] + "()" + operator
					+ ((o.n[type] || 1) * number) + ")");
		} catch (e) {
		}
	}

	return d;
};

/**
 * 计算两日期相差的日期年月日等
 */
Date.prototype.dateDiff = function(interval, dt) {

	var anotherDt;
	if (dt instanceof Date) {

		anotherDt = dt;
	} else if (typeof dt == "string") {

		anotherDt = dt.parseToDate();
	}

	if (!anotherDt) {

		return undefined;
	}

	var d = this, t = d.getTime(), t2 = anotherDt.getTime(), i = {};

	i["y"] = anotherDt.getFullYear() - d.getFullYear();
	i["q"] = i["y"] * 4 + Math.floor(anotherDt.getMonth() / 4)
			- Math.floor(d.getMonth() / 4);
	i["M"] = i["y"] * 12 + anotherDt.getMonth() - d.getMonth();
	i["w"] = Math.floor((t2 + 345600000) / (604800000))
			- Math.floor((t + 345600000) / (604800000));
	i["d"] = Math.floor(t2 / 86400000) - Math.floor(t / 86400000);
	i["h"] = Math.floor(t2 / 3600000) - Math.floor(t / 3600000);
	i["m"] = Math.floor(t2 / 60000) - Math.floor(t / 60000);
	i["s"] = Math.floor(t2 / 1000) - Math.floor(t / 1000);
	i["ms"] = anotherDt.getTime() - d.getTime();

	return i[interval];
};

/**
 * 日期字符串 转为 日期Date对象
 * 
 * @return Date's object
 */
String.prototype.parseToDate = function() {

	if (this) {

		var dt = new Date(Date.parse(this.replace(/-|\//g, "/")));
		if ("NaN" != dt)
			return dt;
	}
	return undefined;
};

/* -- 勿改 -- */
Date.prototype.settings = {
	"k" : {
		"y" : "FullYear",
		"q" : "Month",
		"M" : "Month",
		"w" : "Date",
		"d" : "Date",
		"h" : "Hours",
		"m" : "Minutes",
		"s" : "Seconds",
		"ms" : "MilliSeconds"
	},
	"n" : {
		"q" : 3,
		"w" : 7
	},
	"o" : {
		"ADD" : "+",
		"SUB" : "-"
	},
	"week" : {
		"0" : "\u65e5",
		"1" : "\u4e00",
		"2" : "\u4e8c",
		"3" : "\u4e09",
		"4" : "\u56db",
		"5" : "\u4e94",
		"6" : "\u516d"
	}
};
/* -------- */
try {
	(function($) {

		/**
		 * 将字符串日期（2012-12-3）格式化为Date类型
		 * 
		 * @see $("#textId").parseToDate()
		 * @return javascript的Date对象
		 */
		$.fn.parseToDate = function() {

			return $(this).val().parseToDate();
		};
	})(jQuery);
} catch (e) {/* 方法需要JQuery支持 */
}