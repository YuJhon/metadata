<#assign title="mysql">

<@override name="style">
<link rel="stylesheet" href="/static/app/css/about.css" type="text/css"/>
</@override>

<@override name="content">

<#--<p class="bg-primary">填写数据库配置信息，点击按钮执行即可</p>-->

<form class="form-horizontal">
    <div class="form-group">
        <label for="host-input" class="col-xs-2 control-label">Host</label>
        <div class="col-xs-8">
            <input type="text" class="form-control" id="host-input" placeholder="127.0.0.1">
        </div>
    </div>

    <div class="form-group">
        <label for="port-input" class="col-xs-2 control-label">Port</label>
        <div class="col-xs-8">
            <input type="text" class="form-control" id="port-input" placeholder="3306">
        </div>
    </div>

    <div class="form-group">
        <label for="database-input" class="col-xs-2 control-label">DataBase</label>
        <div class="col-xs-8">
            <input type="text" class="form-control" id="database-input" placeholder="database" required="required">
        </div>
    </div>

    <div class="form-group">
        <label for="username-input" class="col-xs-2 control-label">Username</label>
        <div class="col-xs-8">
            <input type="text" class="form-control" id="username-input" placeholder="root">
        </div>
    </div>

    <div class="form-group">
        <label for="password-input" class="col-xs-2 control-label">Password</label>
        <div class="col-xs-8">
            <input type="password" class="form-control" id="password-input" placeholder="123456">
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-2">
            <button class="btn btn-info btn-lg" id="execute">Execute</button>
        </div>
    </div>

</form>


</@override>

<@override name="script">

<script>

    $(function () {

        $("#execute").on('click', function () {
            var host = $("#host-input").val();
            var port = $("#port-input").val();
            var database = $("#database-input").val().trim();
            var username = $("#username-input").val();
            var password = $("#password-input").val();
//
            if (!database) {
                layer.msg("database 不可以为空!");
                return false;   //如果是 return true; 页面会重新加载
            }

            alert("开始执行 ajax!");
            $.ajax({
                type: 'POST',
                url: "/metadata/mysql/execute",
                timeout: 30000, //30S
                data: {
                    host: host,
                    port: port,
                    database: database,
                    username: username,
                    password: password
                },
                dataType: 'json',
                success: function (result) {
                    console.log(result)
                    alert("success");
                },
                error: function (result) {
                    alert("error");
//                    App.sysErrorMsg();
                }
            });

        });
            
    });

</script>

</@override>

<@extends name="../layout/lte-default.ftl"/>

