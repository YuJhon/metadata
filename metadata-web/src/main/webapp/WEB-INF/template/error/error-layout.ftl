<@override name="body">

<div class="error-container text-center">
    <div class="well">
        <h1 class="grey lighter smaller">
            <span class="blue bigger-125">
                <i class="ace-icon fa fa-sitemap"></i>
                错误
            </span>
        </h1>

        <hr/>

        <@block name = "error" />

        <hr/>

        <div class="space"></div>

        <div class="center">
            <a href="javascript:history.back();" class="btn btn-grey">
                <i class="ace-icon fa fa-arrow-left"></i>
                返回
            </a>
            <a href="/dashboard" class="btn btn-primary">
                <i class="ace-icon fa fa-tachometer"></i>
                工作台
            </a>
        </div>
    </div>
</div>

</@override>

<@extends name="../layout/layout.ftl"/>