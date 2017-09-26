<#--分页组件-->
<#macro pagination url param="">
    <#if page.list?? && page.total gt 0>
    <ul class="pagination">
        <li>
            <#if page.hasPreviousPage>
                <a href="${url}?p=${page.prePage}<#if param?has_content>&${param}</#if>">&lt;</a>
            <#else>
                <a href="javascript:">&lt;</a>
            </#if>
        </li>

        <#list page.navigatepageNums as nav>
            <li <#if nav == page.pageNum>class="active"</#if>>
                <a href="${url}?p=${nav}<#if param?has_content>&${param}</#if>">${nav}</a>
            </li>
        </#list>

        <li class="last-pagination">
            <#if page.hasNextPage>
                <a href="${url}?p=${page.nextPage}<#if param?has_content>&${param}</#if>">&gt;</a>
            <#else>
                <a href="javascript:">&gt;</a>
            </#if>
        </li>
    </ul>
    </#if>
</#macro>



<#--计算时间-->
<#macro relative_date datetime=.now>
    <#assign ct = (.now?long-datetime?long)/1000>
    <#if ct gte 31104000><#--n年前-->${(ct/31104000)?int}年前
        <#t><#elseif ct gte 2592000><#--n月前-->${(ct/2592000)?int}个月前
        <#t><#elseif ct gte 86400*2><#--n天前-->${(ct/86400)?int}天前
        <#t><#elseif ct gte 86400><#--1天前-->昨天
        <#t><#elseif ct gte 3600><#--n小时前-->${(ct/3600)?int}小时前
        <#t><#elseif ct gte 60><#--n分钟前-->${(ct/60)?int}分钟前
        <#t><#elseif ct gt 0><#--n秒前-->${ct?int}秒前
        <#t><#else>刚刚
    </#if>
</#macro>




<#--截取字符串, 超出部分用省略号代替-->
<#macro substring str len default=''>
    <#if str?? && str != ''>
        <#if str?length gt len>
        ${str?substring(0, len)}...
        <#else>
        ${str}
        </#if>
    <#else>
    ${default}
    </#if>
</#macro>