
<#include "/header.ftl">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
                <h2>${vod.title}</h2>
                <img src="${vod.postUrl}" class="img-responsive" alt="Responsive image">
                <h2>${vod.name}</h2>
                <p>${vod.content}</p>
				<img src="${vod.screenshotUrl}" class="img-responsive" alt="Responsive image">
			</div>

		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Panel title</h3>
					</div>
					<div class="panel-body">
						Panel content
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- /container -->
<#include "/footer.ftl">
