$(function () {
    //top nav bar
    var navBar=$('#topNavBar');
    if(navBar.length > 0){
        navBar.load('/category/navbar');
    }

    //1.back to top
    var scrollTimeout;
    $(window).scroll(function() {
        clearTimeout(scrollTimeout);
        if ($(window).scrollTop() > 400) {
            scrollTimeout = setTimeout(function() {
                $('#scroll-top:hidden').fadeIn()
            }, 100);
        } else {
            scrollTimeout = setTimeout(function() {
                $('#scroll-top:visible').fadeOut()
            }, 100);
        }
    });
});

$(document).ready(function () {
    // Base.sh();
});

var Base = new Base();
function Base() {
    // this.sh = function () {
    //     var DEFAULT_STYLE = {
    //         "java": "brush: java;",
    //         "bash": "brush: bash;",
    //         "shell": "brush: shell;",
    //         "c": "brush: c;",
    //         "C": "brush: c;",
    //         "c++": "brush: cpp;",
    //         "C++": "brush: cpp;",
    //         "js": "brush: js;",
    //         "javascript": "brush: js;",
    //         "css": "brush: css;",
    //         "xml": "brush: xml;",
    //         "html": "brush: html;",
    //         "sql": "brush: sql;",
    //         "php": "brush: php;",
    //         "py": "brush: python;",
    //         "python": "brush: python;",
    //         "ruby": "brush: ruby;"
    //     };
    //     SyntaxHighlighter.config.bloggerMode = true;
    //     SyntaxHighlighter.config.tagName = "sh";
    //     SyntaxHighlighter.defaults["collapse"] = true;
    //     SyntaxHighlighter.defaults["title"] = "[default]";
    //     var $codes = $("pre.sh");
    //     $codes.each(function (i, e) {
    //         var $e = $(e);
    //         var className = $e.attr("class") || "default";
    //         SyntaxHighlighter.defaults["title"] = "[" + className + "]";
    //         var styleClass = DEFAULT_STYLE[className];
    //         if (!!styleClass) {
    //             $e.addClass(styleClass)
    //         } else {
    //             $e.addClass("brush: xml;")
    //         }
    //         SyntaxHighlighter.defaults["collapse"] = true;
    //         SyntaxHighlighter.highlight($e)
    //     })
    // };
};
