<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/static/images/favicon.ico">

    <title>天堂电影院</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">


    <link href="/static/css/index.css" rel="stylesheet">


    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
</head>

<body>

<!-- Fixed navbar -->
<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index.html">
                天堂电影院
            </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <#list categorys as category>
                    <#if requestUri==category.getUri()>
                        <li class="active">
                    <#else><li>
                    </#if>
                    <a href="${category.getUri()}">${category.getName()}</a>
                </li>
            </#list>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <!--
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form> -->
                <form class="navbar-form navbar-left" action="/search.html" method="post">
                    <div class="input-group">
                        <input type="text" class="form-control" name="vodName" placeholder="Search for...">
                        <span class="input-group-btn">
								<button class="btn btn-info" type="submit">搜索</button>
							</span>
                    </div>
                </form>
                <button type="button" class="btn  btn-success navbar-btn">Sign in</button>
                <button type="button" class="btn btn-primary navbar-btn">Log in</button>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>
