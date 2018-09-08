
<#include "/header.ftl">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
                <h2>${vod.title}</h2>
                <img src="${vod.postUrl!}" class="img-responsive" alt="Responsive image">
                <h2>${vod.name!}</h2>
                <p>${vod.content!}</p>
				<img src="${vod.screenshotUrl!}" class="img-responsive" alt="Responsive image">
			</div>

		</div>
        <a href="/showDownloadLinks/${vod.id?string["0"]}.html"><button type="button" class="btn btn-primary btn-lg btn-block">查看下载地址</button></a>
	</div>
	<!-- /container -->
<#include "/footer.ftl">
