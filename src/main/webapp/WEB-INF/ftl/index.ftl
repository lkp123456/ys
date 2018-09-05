<#include "/header.ftl">

<div class="container">
    <div class="row">

        <#list vods as vod>
        <div class="col-xs-6 col-sm-4 col-md-3">
            <div class="thumbnail">
                <img src="ss.jpg" class="img-thumbnail" alt="...">
                <div class="caption">
                    <h3>Title</h3>
                    <p>主演：xxx</p>
                    <p>导演：xxx</p>
                </div>
            </div>
        </div>
        </#list>

    </div>



    <nav aria-label="page">
        <ul class="pager">
            <li>
                <!-- <a href="#">上一页</a> -->
            </li>
            <li>
                <a href="#">下一页</a>
            </li>
        </ul>
    </nav>

</div>
<!-- /container -->


<#include "/footer.ftl">
