/*globals $*/
; (function($, window, document, undefined) {
    var pluginName = "jqueryAccordionMenu";
    var defaults = {
        speed: 300,
        showDelay: 0,
        hideDelay: 0,
        singleOpen: true,
        clickEffect: true
    };
    function Plugin(element, options) {
        this.element = element;
        this.settings = $.extend({},
        defaults, options);
        this._defaults = defaults;
        this._name = pluginName;
        this.init()
    };
    $.extend(Plugin.prototype, {
        init: function() {
            this.openSubmenu();
            this.submenuIndicators();
            if (defaults.clickEffect) {
                this.addClickEffect()
            }
        },
        openSubmenu: function() {
            $(this.element).children("ul").find("li").bind("click touchstart",
            function(e) {
                e.stopPropagation();
                e.preventDefault();
                if ($(this).children(".submenu").length > 0) {
                    if ($(this).children(".submenu").css("display") == "none") {
                        $(this).children(".submenu").delay(defaults.showDelay).slideDown(defaults.speed);
                        $(this).children(".submenu").siblings("a").addClass("submenu-indicator-minus");
                        if (defaults.singleOpen) {
                            $(this).siblings().children(".submenu").slideUp(defaults.speed);
                            $(this).siblings().children(".submenu").siblings("a").removeClass("submenu-indicator-minus")
                        }
                        return false
                    } else {
                        $(this).children(".submenu").delay(defaults.hideDelay).slideUp(defaults.speed)
                    }
                    if ($(this).children(".submenu").siblings("a").hasClass("submenu-indicator-minus")) {
                        $(this).children(".submenu").siblings("a").removeClass("submenu-indicator-minus")
                    }
                }
                window.location.href = $(this).children("a").attr("href")
            })
        },
        submenuIndicators: function() {
            if ($(this.element).find(".submenu").length > 0) {
                $(this.element).find(".submenu").siblings("a").append("<span class='submenu-indicator'>+</span>")
            }
        },
        addClickEffect: function() {
            var ink, d, x, y;
            $(this.element).find("a").bind("click touchstart",
            function(e) {
                $(".ink").remove();
                if ($(this).children(".ink").length === 0) {
                    $(this).prepend("<span class='ink'></span>")
                }
                ink = $(this).find(".ink");
                ink.removeClass("animate-ink");
                if (!ink.height() && !ink.width()) {
                    d = Math.max($(this).outerWidth(), $(this).outerHeight());
                    ink.css({
                        height: d,
                        width: d
                    })
                }
                x = e.pageX - $(this).offset().left - ink.width() / 2;
                y = e.pageY - $(this).offset().top - ink.height() / 2;
                ink.css({
                    top: y + 'px',
                    left: x + 'px'
                }).addClass("animate-ink")
            })
        }
    });
    $.fn[pluginName] = function(options) {
        this.each(function() {
            if (!$.data(this, "plugin_" + pluginName)) {
                $.data(this, "plugin_" + pluginName, new Plugin(this, options))
            }
        });
        return this
    }
})(jQuery, window, document);

$(function() {

	"use strict";

	prettyPrint();
	
});

function showSucc() {
	iosOverlay({
		text: "Success!",
		duration: 2e3,
		icon: "img/check.png"
	});
	return false;
};

function showError() {
	iosOverlay({
		text: "Error!",
		duration: 2e3,
		icon: "img/cross.png"
	});
	return false;
};

$(document).on("click", "#loading", function(e) {
	var opts = {
		lines: 13, // The number of lines to draw
		length: 11, // The length of each line
		width: 5, // The line thickness
		radius: 17, // The radius of the inner circle
		corners: 1, // Corner roundness (0..1)
		rotate: 0, // The rotation offset
		color: '#FFF', // #rgb or #rrggbb
		speed: 1, // Rounds per second
		trail: 60, // Afterglow percentage
		shadow: false, // Whether to render a shadow
		hwaccel: false, // Whether to use hardware acceleration
		className: 'spinner', // The CSS class to assign to the spinner
		zIndex: 2e9, // The z-index (defaults to 2000000000)
		top: 'auto', // Top position relative to parent in px
		left: 'auto' // Left position relative to parent in px
	};
	var target = document.createElement("div");
	document.body.appendChild(target);
	var spinner = new Spinner(opts).spin(target);
	iosOverlay({
		text: "Loading",
		duration: 2e3,
		spinner: spinner
	});
	return false;
});

$(document).on("click", "#loadToSuccess", function(e) {
	var opts = {
		lines: 13, // The number of lines to draw
		length: 11, // The length of each line
		width: 5, // The line thickness
		radius: 17, // The radius of the inner circle
		corners: 1, // Corner roundness (0..1)
		rotate: 0, // The rotation offset
		color: '#FFF', // #rgb or #rrggbb
		speed: 1, // Rounds per second
		trail: 60, // Afterglow percentage
		shadow: false, // Whether to render a shadow
		hwaccel: false, // Whether to use hardware acceleration
		className: 'spinner', // The CSS class to assign to the spinner
		zIndex: 2e9, // The z-index (defaults to 2000000000)
		top: 'auto', // Top position relative to parent in px
		left: 'auto' // Left position relative to parent in px
	};
	var target = document.createElement("div");
	document.body.appendChild(target);
	var spinner = new Spinner(opts).spin(target);
	var overlay = iosOverlay({
		text: "Loading",
		spinner: spinner
	});

	window.setTimeout(function() {
		overlay.update({
			icon: "img/check.png",
			text: "Success"
		});
	}, 3e3);

	window.setTimeout(function() {
		overlay.hide();
	}, 5e3);

	return false;
});