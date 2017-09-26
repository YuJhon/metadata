<#assign ctx="${(rca.contextPath)!''}">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="icon" type="image/ico" href="${ctx}/static/app/img/favicon.ico">

    <title><@spring.message "app.name"/> - ${title!''}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <meta name="renderer" content="webkit">

    <#--css-->
    <link rel="stylesheet" href="${ctx}/static/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/app/css/app.css"/>
    <link rel="stylesheet" href="${ctx}/static/app/css/base.css"/>

    <@block name="page-css"/>
</head>

<div class="main-container" id="main-container">
    <body class="${bodyClass!"no-skin"}">
        <@block name="body"/>
    </div>
</div>

<script src="${ctx}/static/lib/jquery/jquery-2.2.4.min.js"></script>

<script src="${ctx}/static/app/js/app.js"></script>
<script src="${ctx}/static/app/js/base.js"></script>

<@block name="page-js"/>
</body>
</html>