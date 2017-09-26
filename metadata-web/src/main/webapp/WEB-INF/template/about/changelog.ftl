<#assign title="变更日志">

<@override name="style">
<link rel="stylesheet" href="/static/app/css/about.css" type="text/css"/>
</@override>

<@override name="content">

<ul class="timeline">

    <!-- timeline time label -->
    <li class="time-label">
        <span class="bg-red">
            2017-9-13
        </span>
    </li>
    <!-- /.timeline-label -->

    <!-- timeline item -->
    <li>
        <!-- timeline icon -->
        <i class="fa fa-envelope bg-blue"></i>
        <div class="timeline-item">
            <span class="time"><i class="fa fa-clock-o"></i> 09:28</span>

            <h3 class="timeline-header">
                <span class="text-info">诞生</span>
            </h3>

            <div class="timeline-body">
                此时此刻，这一个扫描工具正式诞生了。
            </div>

            <div class="timeline-footer">
                <a class="btn btn-primary btn-xs">更多 >></a>
            </div>
        </div>
    </li>
    <!-- END timeline item -->

</ul>

</@override>

<@override name="script">
<#--<script src="/static/app/js/index.js"></script>-->
</@override>

<@extends name="../layout/lte-default.ftl"/>

