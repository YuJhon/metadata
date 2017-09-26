<#assign title="关于我们">

<@override name="style">
    <link rel="stylesheet" href="/static/app/css/about.css" type="text/css"/>
</@override>

<@override name="content">

    <#--<div class="navbar-fixed-top" id="topNavBar"></div>-->
    <#--<div class="space-50"></div>-->

    <div id="about-us-bg">
        <img src="${ctx}/static/app/img/about/about_bg.jpeg"/>
    </div>

    <div class="content-warp">

        <div class="col-xs-12 info-content">
            <h2>项目信息</h2>
            <p>项目名称:   <span class="blue"><@spring.message "app.name"/></span></p>
            <p>版本信息:   <span>&copy;2015-<@spring.message "app.copyright.year"/>&nbsp;&nbsp;<@spring.message "app.version"/></span></p>
            <p>技术支持:   <span><@spring.message "app.author"/></span></p>
        </div>

        <div class="col-xs-12 contact-content">
            <h2>联系方式</h2>
            <p><i class="ace-icon fa fa-fw fa-phone bigger-150"></i>    13062666053</p>
            <p><i class="ace-icon fa fa-fw fa-wechat green bigger-150"></i>     houbinbinEcho</p>
            <p><i class="ace-icon fa fa-fw fa-envelope red bigger-150"></i>     houbb.echo@gmail.com</p>
            <p><i class="ace-icon fa fa-fw fa-github-alt dark bigger-150"></i>  <a href="https://github.com/houbb">houbb</a></p>
        </div>

    </div>

</@override>

<@override name="script">
<#--<script src="/static/app/js/index.js"></script>-->
</@override>

<@extends name="../layout/lte-default.ftl"/>

