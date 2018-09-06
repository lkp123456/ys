<#include "/header.ftl">

<div class="container">
    <div class="row">

        <#list lastVods.getList() as vod>
            <div class="col-xs-6 col-sm-4 col-md-3">
                <a href="/v/${vod.id?string["0"]}.html">
                    <div class="thumbnail">
                        <img src="${vod.postUrl!}" class="img-thumbnail" alt="...">
                        <div class="caption">
                            <h4>${vod.title!}</h4>
                            <h5>更新日期：${vod.publishDate?string('yyyy-MM-dd')!}</h5>
                        </div>
                    </div>
                </a>
            </div>
        </#list>

    </div>

</div>
<!-- /container -->
<nav aria-label="page">
    <ul class="pager">
            <#if lastVods.isHasPreviousPage()>
               <li><a href="${springMacroRequestContext.getRequestUri()}?startPage=${lastVods.getPageNum()-1}">上一页</a> </li>
            </#if>


            <#if lastVods.isHasNextPage()>
            <li><a href="${springMacroRequestContext.getRequestUri()}?startPage=${lastVods.getPageNum()+1}">下一页</a> </li>
            </#if>

    </ul>
</nav>

<#include "/footer.ftl">
