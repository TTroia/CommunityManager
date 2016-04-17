;
(function($) {

	function get(i) {
		return i&&document.getElementById(i);
	}

	var init_info, idArr, focusClass, blurClass, mode = "click", info;

	$.fn.proxy = function() {

		if ("click" == mode) {
			return;
		}

		var thisInfo = $(this).data("info");
		id = $(this).attr("id");
		relid = thisInfo.target;

		if (thisInfo.hasUrl) {

			$.tab.sendAjax(id, relid);
		} else {
			$("#" + id).swapTab();
		}

		return $;
	};

	$.fn.swapTab = function() {

		var thisInfo = $(this).data("info"), id = $(this).attr("id"), ids = $(
				this).data("ids");

		for ( var i in ids) {

			$("#" + ids[i]).removeClass(focusClass);
			$("#" + ids[i]).addClass(blurClass);
		}

		$(this).removeClass(blurClass);
		$(this).addClass(focusClass);

		try {
			$.unblockUI();
		} catch (e) {
		}
		return $;
	};

	$.fn.passParam = function(p) {

		p = p || {};

		var thisInfo = $(this).data("info");

		if ("object" == typeof (p)) {
			thisInfo.parameter = p;
		}
		$(this).data("info", thisInfo);
	};

	$.kcsMenu = {
		info : {
			css : {
				focus : "",
				blur : ""
			},
			mode : "click",
			// 取出的信息是否缓存，
			data : {}
		},
		def_options : {

			cache : false,
			target : {},
			action : null,
			parameter : {},
			callback : null,
			cleanObj : null,
			times : 1,
			hasUrl : {}
		},
		initMenu : function(settings) {

			settings = settings || {};

			if (!settings.css.focus) {
				settings.css.focus = "";
			}

			if (!settings.css.blur) {
				settings.css.blur = "";
			}

			var otptions = $.extend( {}, $.kcsMenu.info, settings);

			if (otptions.data) {

				init_info = [], idArr = [];

				$.each(otptions.data, function(id, v) {

					if (get(id)) {

						v = $.extend( {}, $.kcsMenu.def_options, v);

						if (v.action) {

							v.hasUrl = true;

						} else {

							v.hasUrl = false;
						} // END IF

						if (v.times) {

							if ("auto" == $.trim(v.times).toLowerCase()) {

								v.times = $.trim(v.times).toLowerCase();

							} else {
								if (isNaN(v.times)) {

									v.times = 1;

								} else {

									v.times = Math.abs(v.times);
								}
								v.count = 0;
							}
						} // END IF

						if (!get(v.target)) {
							v.target = null;
						}

						if (!get(v.cleanObj)) {
							v.cleanObj = null;
						}

						init_info.push( {
							"id" : id,
							"data" : v
						});

						$("#" + id).data("info", v);
						idArr.push(id);

						if (v.parameter
								&& "[object Object]" == typeof (v.parameter)) {

							return;
						}

					}

				}); // end EACH

				if (0 != init_info.length) {

					for ( var i in idArr) {

						$("#" + idArr[i]).data("ids", idArr);
					}

					if (otptions.css) {
						focusClass = (otptions.css.focus).replace(
								/[,#!@%&()]/g, " ").replace(/\s{2,}/g, " ");
						blurClass = (otptions.css.blur).replace(/[,#!@%&()]/g,
								" ").replace(/\s{2,}/g, " ");
					}

					if (otptions.mode) {

						if ("click" != $.trim(otptions.mode).toLowerCase()) {
							mode = "proxy";
						}
					}

					$.kcsMenu.bindEvent();
				}

			} // end IF

			return false;
		},

		bindEvent : function() {

			while (0 != init_info.length) {

				info = init_info.pop();
				o = get(info.id);

				if (o) {
					$o = $(o);
					$o.css("cursor", "pointer");

					if ("proxy" == mode) {

						return;
					}

					if (info.data.hasUrl) {

						var relid = info.data.target;

						$o.unbind("click").bind("click", function(event) {

							$.kcsMenu.sendAjax(this, relid);
						});
					} else {
						$o.unbind("click").bind("click", function() {
							$(this).swapTab();
						});
					}
				} // END IF
	} // END WHILE

},

sendAjax : function(thisobj, relid) {

	var $o = $(thisobj), $relo = $("#" + relid), thisInfo = $o.data("info");

	if (true == $relo.data("state") || thisInfo.times <= thisInfo.count) {

		if (thisInfo.cache) {

			var ajaxData = $(thisobj).data("html"), abc = ajaxData.responseText;
			
			if (thisInfo.callback) {

				if (!thisInfo.callback(ajaxData)) {

					return false;
				}
			}
			$relo.html(abc);
		}

		$(thisobj).swapTab();
		return false;
	}

	$.ajax( {
		cache : false,
		type : "post",
		url : thisInfo.action,
		data : thisInfo.parameter,
		dataType : "html",
		complete : function(data, status) {
			setTimeout(function() {
				try {
					$.unblockUI();
				} catch (e) {
					alert("error:\n" + e);
				}

				if (thisInfo.callback) {

					if (!thisInfo.callback(data, status)) {

						return false;
					}
					$("#" + relid).html(data.responseText);
				} else {

					if ("error" != status) {

						$("#" + relid).html(data.responseText);
					}
				}

				if (thisInfo.cleanObj) {

					$("#" + thisInfo.cleanObj).html("");
				}

				if (thisInfo.cache) {
					$(thisobj).data("html", data);
				}

				if (thisobj) {
					$o.swapTab();
				} else {
					return false;
				}

				if ("auto" == thisInfo.times) {

					$("#" + relid).data("state", !1);
				} else {

					thisInfo.count++;
					$o.data("info", thisInfo);
				}
			}, 1);
		},
		error : function() {
			alert("network error!");

		}

	});

}

	};

	jQuery.initMenu = function(o) {
		$.kcsMenu.initMenu(o);
	};

	jQuery.fn.extend( {
		initMenu : function(o) {
			$.kcsMenu.initMenu(o);
		},
		passParam : function(o) {
			$.kcsMenu.passParam(o);
		}
	});
})(jQuery);