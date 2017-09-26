//IE8内核console未定义
if (!window.console || !console.log){
    try{
        var names = ["log", "debug", "info", "warn", "error", "assert", "dir", "dirxml", "group", "groupEnd", "time", "timeEnd", "count", "trace", "profile", "profileEnd"];
        window.console = {};
        for (var i = 0; i < names.length; ++i){
            window.console[names[i]] = function() {}
        }
    }catch(e){}
}
// ---------------
// 原生对象扩展
// Array
Array.prototype.indexOf = function (item, from) {
    var length = this.length >>> 0;
    for (var i = (from < 0) ? Math.max(0, length + from) : from || 0; i < length; i++) {
        if (this[i] === item) return i;
    }
    return -1;
};
Array.prototype.contains = function (item, from) {
    return this.indexOf(item, from) != -1;
};
Array.prototype.erase = function (item) {
    for (var i = this.length; i--;) {
        if (this[i] === item) this.splice(i, 1);
    }
    return this;
};
Array.prototype.empty = function () {
    this.length = 0;
    return this;
};
Array.prototype.isEmpty = function() {
    return !this || this.length <= 0;
};

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d H:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //12小时制
        "H+": this.getHours(),                   //24小时制
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

//String
String.prototype.startWith = function (str) {
    var reg = new RegExp("^" + str);
    return reg.test(this);
};

// ---------------
// 函数命名空间
var App = App || {};

App._create_modal = function(is_iframe, opt){
    var modalHtml = ''
        + '<div class="modal -fade"><div class="modal-dialog"><div class="modal-content">'
        + '<div class="modal-header"><a href="javascript:;" class="close" data-dismiss="modal" aria-hidden="true" title="关闭">&times;</a><h4 class="modal-title">&nbsp;</h4></div>'
        + '<div class="modal-body"></div>'
        + '<div class="modal-footer"><button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">确定</button>&nbsp;<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button></div>';
    + '</div></div></div>';

    var modal;
    if (is_iframe === true){
        if (!this._modal_dialog_el) this._modal_dialog_el = $(modalHtml).appendTo(document.body).addClass('-modal-iframe');
        modal = this._modal_dialog_el;
        var iframe = modal.find('.modal-body iframe');
        if (iframe.length == 0) $('<iframe class="iframe" frameborder="0" width="100%" height="100%"></iframe>').appendTo(modal.find('.modal-body'));
    }else{
        if (!this._modal_alert_el) this._modal_alert_el = $(modalHtml).appendTo(document.body).addClass('-modal-alert');;
        modal = this._modal_alert_el;
    }

    if (opt.title) modal.find('.modal-header h4').html(opt.title);
    //去掉modal-body左右内边距，ie8下2,4,2,4布局错乱
    if(opt.clearLRPadding){ modal.find('.modal-body').css({paddingLeft:0,paddingRight:0});}
    if(opt.paddingTop) { modal.find('.modal-body').css('padding-top', opt.paddingTop);}

    return modal;
};
// 弹出窗口
App.dialog = function(opt){
    opt.width = opt.width || 720;
    opt.height = opt.height || 380;
    opt.footer = opt.footer == false ? false : true;
    var dlg = this._create_modal(true, opt);
    WXC.dialog_size(dlg, opt);
    if (opt.footer === true) dlg.find('.modal-footer').removeClass('hide');
    else dlg.find('.modal-footer').addClass('hide');

    var iframe = dlg.find('.modal-body iframe');
    if ((iframe.attr('src') !== opt.url) || !opt.noReload) { /* 设置noReload参数为true则不重新加载iframe页面 */
        iframe.attr('src', 'about:blank');
        var randomInt = Math.floor(Math.random()*100000);
        var forceRefreshSuffix = (opt.url.indexOf("?") != -1) ? "&efghij=" + randomInt : "?efghij=" + randomInt;
        /* 加上随机数后缀，解决IE浏览器下iframe加载同一URL始终会缓存的问题！！ */
        setTimeout(function() {
            iframe.attr('src', opt.url + forceRefreshSuffix);
        }, 400);
    }
//	if(typeof opt.noMask =='undefined'){
//		dlg.modal({ backdrop:'static',show:true });
//		return dlg;
//	}
//	else{
//		dlg.modal({ backdrop:false, show:true });
//		return dlg;
//	}
    dlg.modal({ backdrop:(typeof opt.noMask =='undefined')?'static':false,show:true });
    return dlg;
    //dlg.modal({ backdrop:'static', show:true });
};
// 调整弹出窗口大小(位置)
App.dialog_size = function(dlg, opt){
    if (opt.width) dlg.find('.modal-dialog').css({ 'width':opt.width });
    if (opt.top) dlg.find('.modal-dialog').css({ 'margin-top':opt.top });
    if (opt.height) {
        dlg.find('.modal-body').css('height', opt.height);
        dlg.find('.modal-body').css('max-height', opt.height);
    }
};

// 获取URL参数
App.urlp = function(key){
    var qstring = window.location.search;
    if (!qstring) return (!key || key == '*') ? {} : null;
    qstring = qstring.replace(/%20/g, ' ');
    qstring = qstring.substr(1); // remove ?
    var param = qstring.split('&');
    var map = new Object();
    for (var i = 0, j = param.length; i < j; i++){ var pl=param[i].split('='); map[pl[0]] = pl[1]; }
    return (!key || key == '*') ? map : map[key];
};

// 字符串替换全部
App.replace = function(str, a, b){
    str += '';
    return str.replace(new RegExp(a, 'g'), b || '');
};
//是否IE
App.isIE = function(){
    return ! - [1,] || navigator.userAgent.indexOf('like Gecko') > -1;
};
//是否Safari
App.isSafari = function() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    return userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") < 1 ;
};
//阻止事件冒泡
App.cancelBubble = function(event) {
    if (event && event.stopPropagation) {event.stopPropagation(); } else { window.event.cancelBubble = true;}
};
//获取按键编码
App.enterCode = function(event) {
    var theEvent = event || window.event;
    return theEvent.keyCode || theEvent.which || theEvent.charCode;
};
//获取浏览器高度
App.windowHeight = function() {
    return window.innerHeight || document.documentElement.clientHeight;
};

// 转义html、xml
App.escape = function(val){
    if (!!!val) return '';
    val += '';
    val = WXC.replace(val, '&', '&amp;');
    val = WXC.replace(val, '<', '&lt;');
    val = WXC.replace(val, '>', '&gt;');
    val = WXC.replace(val, '"', '&quot;');
    val = WXC.replace(val, "'", '&apos;');
    return val;
};
// 反转义html、xml
App.unescape = function(val){
    if (!!!val) return '';
    val += '';
    val = WXC.replace(val, '&amp;', '&');
    val = WXC.replace(val, '&lt;', '<');
    val = WXC.replace(val, '&gt;', '>');
    val = WXC.replace(val, '&quot;', '"');
    val = WXC.replace(val, "&apos;", "'");
    return val;
};

/**检查输入密码时候大小写，页面要引入layer.js*/
App.checkCapsLock = function (event) {
    var e = event || window.event;
    var o = e.target || e.srcElement;
    var keyCode = e.keyCode || e.which; // 按键的keyCode
    if (keyCode >= 65 && keyCode <= 90) {
        layer.tips('<i class="fa fa-bell-o"></i>大写锁定开启', o, {guide: 1, isGuide: false});
    } else {
        try {
            layer.closeAll();
        } catch (e) {
        }
    }
};

