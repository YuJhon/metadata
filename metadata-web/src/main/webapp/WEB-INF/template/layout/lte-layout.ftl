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



    <#--====================================== libs ======================================-->
    <link rel="stylesheet" href="${ctx}/static/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/lib/fontAwesome/css/font-awesome.min.css"/>


    <#--====================================== admin-lte ======================================-->
    <link rel="stylesheet" href="${ctx}/static/lib/adminlte/dist/css/AdminLTE.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/lib/adminlte/dist/css/skins/skin-blue.min.css"/>


    <#--====================================== self ======================================-->
    <link rel="stylesheet" href="${ctx}/static/app/css/app.css"/>
    <link rel="stylesheet" href="${ctx}/static/app/css/base.css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">


    <@block name="page-css"/>
</head>


<body class="hold-transition skin-blue sidebar-mini">

    <@block name="body"/>

</body>




<#--====================================== libs ======================================-->
<script src="${ctx}/static/lib/jquery/jquery-2.2.4.min.js"></script>
<script src="${ctx}/static/lib/bootstrap/bootstrap.min.js"></script>


<#--====================================== admin-lte ======================================-->
<script src="${ctx}/static/lib/adminlte/dist/js/adminlte.min.js"></script>

<#--====================================== self ======================================-->
<script src="${ctx}/static/app/js/app.js"></script>
<script src="${ctx}/static/app/js/base.js"></script>

<@block name="page-js"/>
</body>
</html>