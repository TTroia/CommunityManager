/**
 * Plus ： Checkbox Tree
 * Author : zhaoyang
 */
(function ($) {
	$.fn.treeview = function(settings) {
		var chkname = "pri",
			dfop = {
				method: "POST",
				datatype: "json",
				url: false,
				cbiconpath: contextPath + "/rc/sc/jq/themes/treeview/img/tree/",
				oncheckboxclick: false, // 当checkstate状态变化时所触发的事件，但是不会触发因级联选择而引起的变化
				onnodeclick: false,
				cascadecheck: true,
				data: null,
				clicktoggle: true, // 点击节点展开和收缩子节点
				theme: "bbit-tree-arrows" // bbit-tree-lines,bbit-tree-no-lines,bbit-tree-arrows
			};

		$.extend(dfop, settings);
		
		var treenodes = dfop.data, me = $(this), id = me.attr("id");
		if (id == null || id == "") {
			id = "bbtree" + new Date().getTime();
			me.attr("id", id);
		}

		var html = [];
		buildtree(dfop.data, html);
		me.addClass("bbit-tree").html(html.join(""));
		InitEvent(me);
		html = null;
		
		// region
		function buildtree(data, ht) {
			ht.push("<div class='bbit-tree-bwrap'>"); // Wrap ;
			ht.push("<div class='bbit-tree-body'>"); // body ;
			ht.push("<ul class='bbit-tree-root ", dfop.theme, "'>"); // root
			var l = data.length;
			for (var i = 0; i < l; i++) {
				buildnode(data[i], ht, 0, i, i == l - 1);
			}
			ht.push("</ul>"); // root and;
			ht.push("</div>"); // body end;
			ht.push("</div>"); // Wrap end;
		}
		// endregion
		function buildnode(nd, ht, deep, path, isend) {
			
			ht.push("<li class='bbit-tree-node'>");
			ht.push("<div id='", id, "_", nd.id, "' tpath='", path, "' unselectable='on'");
			var cs = [];
			cs.push("bbit-tree-node-el");
			
			if (!nd.hasChildren) {
				cs.push("bbit-tree-node-leaf");
			}
			
			if (nd.classes) { cs.push(nd.classes); }

			ht.push(" class='", cs.join(" "), "'>");
			
			// span indent
			ht.push("<span class='bbit-tree-node-indent'>");
			if (deep == 1) {
				ht.push("<img class='bbit-tree-icon' src='"+contextPath+"/rc/sc/jq/themes/treeview/img/s.gif'/>");
			} else if (deep > 1) {
				ht.push("<img class='bbit-tree-icon' src='"+contextPath+"/rc/sc/jq/themes/treeview/img/s.gif'/>");
				for (var j = 1; j < deep; j++) {
					ht.push("<img class='bbit-tree-elbow-line' src='"+contextPath+"/rc/sc/jq/themes/treeview/img/s.gif'/>");
				}
			}
			ht.push("</span>");
			
			// checkbox
			ht.push("<input type='checkbox' id='", id, "_", nd.id, "_cb' value='",nd.value,"' ",nd.checkstate?'checked=checked':''," ",nd.ChildNodes?'haschildren=true':'haschildren=false'," name=",chkname," paths='",path,"' />");
			
			// a
			ht.push("<a hideFocus class='bbit-tree-node-anchor' tabIndex=1 href='javascript:void(0);'>");
			ht.push("<span unselectable='on'>", nd.text, "</span>");
			ht.push("</a>");
			ht.push("</div>");
			// Child
			if (nd.hasChildren) {
				ht.push("<ul class='bbit-tree-node-ct' style='z-index: 0; position: static; visibility: visible; top: auto; left: auto;'>");
				if (nd.ChildNodes) {
					var l = nd.ChildNodes.length;
					for (var k = 0; k < l; k++) {
						nd.ChildNodes[k].parent = nd;
						buildnode(nd.ChildNodes[k], ht, deep + 1, path + "." + k, k == l - 1);
					}
				}
				ht.push("</ul>");
			} else {
				ht.push("<ul style='display:none;'></ul>");
			}
			
			ht.push("</li>");
			nd.render = true;
		}
		
		function getItem(path) {
			var ap = path.split(".");
			var t = treenodes;
			for (var i = 0; i < ap.length; i++) {
				if (i == 0) {
					t = t[ap[i]];
				} else {
					t = t.ChildNodes[ap[i]];
				}
			}
			return t;
		}
		
		function check(item, state, type) {
			var pstate = item.checkstate;
			if (type == 1) {
				item.checkstate = state;
			} else {// 上溯
				var cs = item.ChildNodes;
				var l = cs.length;

				var ch = true;
				for (var i = 0; i < l; i++) {
					if ((state == true && cs[i].checkstate != true) || state == false && cs[i].checkstate != false) {
						ch = false;
						break;
					}
				}
				if (ch) {
					item.checkstate = state;
				} else {
					item.checkstate = 2;
				}
			}
			// change show **************重点*************
			if (item.render && pstate != item.checkstate) {
				var et = $("#" + id + "_" + item.id + "_cb");
				if (et.length == 1) {
					et.attr("checked", item.checkstate);
				}
			}
		}
		
		function cascadeCheck(ulNode) {
			
			if (ulNode && ulNode.previousSibling && ulNode.previousSibling.childNodes[1]) {
				
				if (ulNode.previousSibling.childNodes[1].nodeName == "INPUT") {
						
					var posArr = "", pos = "";
					
					if ($(ulNode.previousSibling.childNodes[1]).attr("paths").indexOf(".")) {
						posArr = $(ulNode.previousSibling.childNodes[1]).attr("paths").split(".");
						
						for (var i in posArr) {
							if (i == 0) {
								pos += "["+ posArr[i] + "]";
								
							} else {
								pos += "['ChildNodes']["+ posArr[i] + "]";
							}
						}

					} else {
						posArr = $(ulNode.previousSibling.childNodes[1]).attr("paths");
						pos += "["+ posArr + "]";
					}
					
					eval("dfop.data"+pos).checkstate = true;
					
					ulNode.previousSibling.childNodes[1].checked = true;
					
					pos = null;
					cascadeCheck(ulNode.parentNode.parentNode);
				}
			}
		}
		
		// 遍历子节点
		function cascade(fn, item, args) {
			if (fn(item, args, 1) != false) {
				if (item.ChildNodes != null && item.ChildNodes.length > 0) {
					var cs = item.ChildNodes;
					for (var i = 0, len = cs.length; i < len; i++) {
						cascade(fn, cs[i], args);
					}
				}
			}
		}
		
		// 冒泡的祖先
		function bubble(fn, item, args) {
			var p = item.parent;
			while (p) {
				if (fn(p, args, 0) === false) {
					break;
				}
				p = p.parent;
			}
		}
		
		function nodeclick(e) {
			var path = $(this).attr("tpath");
			var et = e.target || e.srcElement;
			var item = getItem(path);
			
			var s = item.checkstate ? false : true;
			var r = true;
			if (dfop.oncheckboxclick) {
				r = dfop.oncheckboxclick.call(et, item, s);
			}
			if (r != false) {
				if (dfop.cascadecheck) {
					// 遍历
					cascade(check, item, s);
					// 上溯
					bubble(check, item, s);
				} else {
					check(item, s, 1);
				}
			}
		}
		
		function asnybuild(nodes, deep, path, ul, pnode) {
			var l = nodes.length;
			if (l > 0) {
				var ht = [];
				for (var i = 0; i < l; i++) {
					nodes[i].parent = pnode;
					buildnode(nodes[i], ht, deep, path + "." + i, i == l - 1);
				}
				ul.html(ht.join(""));
				ht = null;
				InitEvent(ul);
			}
			ul.addClass("bbit-tree-node-ct").css({ "z-index": 0, position: "static", visibility: "visible", top: "auto", left: "auto", display: "" });
			ul.prev().removeClass("bbit-tree-node-loading");
		}
		
		function asnyloadc(pul, pnode, callback) {
			if (dfop.url) {
				var param = builparam(pnode);
				$.ajax({
					type: dfop.method,
					url: dfop.url,
					data: param,
					dataType: dfop.datatype,
					success: callback,
					error: function(e) { alert("error occur!"); }
				});
			}
		}
		
		function builparam(node) {
			var p = [{ name: "id", value: encodeURIComponent(node.id) }
					, { name: "text", value: encodeURIComponent(node.text) }
					, { name: "value", value: encodeURIComponent(node.value) }
					, { name: "checkstate", value: node.checkstate}];
			return p;
		}
		
		function InitEvent(parent) {
			var nodes = $("li.bbit-tree-node>div", parent);
			nodes.each(function(e) {
				$(this).hover(function() {
					$(this).addClass("bbit-tree-node-over");
				}, function() {
					$(this).removeClass("bbit-tree-node-over");
				})
				.click(nodeclick);
			});
			
			$(":checkbox[checked][haschildren=false]").each(function () {
				
				cascadeCheck(this.parentNode.parentNode.parentNode);
			});
		}
		
		function getck(items, c, fn) {
			for (var i = 0, l = items.length; i < l; i++) {
				items[i].checkstate == 1 && c.push(fn(items[i]));
				if (items[i].ChildNodes != null && items[i].ChildNodes.length > 0) {
					getck(items[i].ChildNodes, c, fn);
				}
			}
		}
		
		me[0].t = {
			getSelectedNodes: function() {
				var s = [];
				getck(treenodes, s, function(item) { return item });
				return s;
			},
			getSelectedValues: function() {
				var s = [];
				getck(treenodes, s, function(item) { return item.value });
				return s;
			},
			getCurrentItem: function() {
				return dfop.citem;
			}
		};
		return me;
	};
	
	$.fn.swapClass = function(c1, c2) {
		return this.removeClass(c1).addClass(c2);
	};
	
	// 获取所有选中的节点的Value数组
	$.fn.getTSVs = function() {
		if (this[0].t) {
			return this[0].t.getSelectedValues();
		}
		return null;
	};
	
	// 获取所有选中的节点的Item数组
	$.fn.getTSNs = function() {
		if (this[0].t) {
			return this[0].t.getSelectedNodes();
		}
		return null;
	};
	
	$.fn.getTCT = function() {
		if (this[0].t) {
			return this[0].t.getCurrentItem();
		}
		return null;
	};
})(jQuery)