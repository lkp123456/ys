
<#include "/header.ftl">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
                <h2>${vod.title}</h2>
                <img src="${vod.postUrl!}" class="img-responsive" onerror="this.src='https://extraimage.net/images/2018/09/09/3a56ab0b4fb273d8bf90f40f87dbedac.gif'">
                <h2>${vod.name!}</h2>
                <p>${vod.content!}</p>
				<img src="${vod.screenshotUrl!}" class="img-responsive" onerror="this.src='https://extraimage.net/images/2018/09/09/11392693c0d3a7418acd536856c2522d.gif'">
			</div>

		</div>
        <a href="/showDownloadLinks/${vod.id?string["0"]}.html"><button type="button" class="btn btn-primary btn-lg btn-block">查看下载地址</button></a>
	</div>
	<!-- /container -->
<#include "/footer.ftl">
