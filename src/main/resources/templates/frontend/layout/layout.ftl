<#macro layout pageTitle bodyClass="" containerClass="">
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title><#if pageTitle?has_content>${pageTitle} - </#if>${blog.title}</title>
  <link rel="icon" href="${assetPath('favicon.png')}">
  <link rel="stylesheet" href="https://cdn.bootcss.com/semantic-ui/2.2.13/semantic.min.css">
  <link rel="stylesheet" href="${assetPath('application.css')}">
</head>
<body class="${bodyClass}">
<header class="ui top fixed borderless menu">
  <div class="ui container">
    <a class="item"><img src="${assetPath('logo.png')}" alt="LOGO"></a>
    <a class="item ${(request.requestUri == "/")?then("active", "")}" href="/">Home</a>
    <a class="item ${(request.requestUri == "/about")?then("active", "")}"" href="/about">About</a>
  </div>
</header>

<main class="ui container ${containerClass}">
  <#nested>
</main>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/semantic-ui/2.2.13/semantic.min.js"></script>
</body>
</html>
</#macro>
