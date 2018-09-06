<#include "/header.ftl">
<div class="container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><h4>下载链接一</h4></div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>来源</th>
                <th>地址</th>
            </tr>
            </thead>
            <tbody>

            <#list downloadUrls as url>
            <tr>
                <td>${url.sourceName!}</td>
                <td>${url.downloadUrl!}</td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>


    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><h4>下载链接二</h4></div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>来源</th>
                <th>地址</th>
            </tr>
            </thead>
            <tbody>

            <#list downloadUrls as url>
            <tr>
                <td>${url.sourceName!}</td>
                <td>${url.magnetUrl!}</td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>

</div>
<!-- /container -->
<#include "/footer.ftl">
