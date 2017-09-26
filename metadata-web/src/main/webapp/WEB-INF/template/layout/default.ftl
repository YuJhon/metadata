<@override name="page-css">
    <@block name="style"/>
</@override>

<@override name="body">
    <div class="navbar-fixed-top" id="topNavBar"></div>
    <div class="space-50"></div>
    <div class="space-30"></div>

    <div class="main-content container" id="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <@block name="content"/>
            </div>
        </div>
    </div>


    <#include "../include/footer.ftl">
    <a id="scroll-top" class="text-center" href="#" title="回顶部" onfocus="this.blur();">↑</a>

</@override>

<@override name="page-js">
    <#--<script src="${ctx}/static/app/js/include/footer.js"></script>-->
    <@block name="script"/>
</@override>

<@extends name="layout.ftl"/>

