<#macro layout pageTitle bodyClass="" containerClass="" showSidebar=false>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title><#if pageTitle?has_content>${pageTitle} - </#if>${blog.title}</title>
  <link rel="icon" href="${assetPath('favicon.png')}">
  <@assetHelper.stylesheets 'semantic-ui'/>
  <@assetHelper.stylesheets 'highlight-js'/>
  <link rel="stylesheet" href="${assetPath('application.css')}">
</head>
<body class="${bodyClass}">
<header class="ui top fixed borderless menu">
  <div class="page-width container">
    <a class="item" href="/"><img src="${assetPath('logo.png')}" alt="LOGO"></a>
    <a class="item ${(request.requestUri == "/")?then("active", "")}" href="/">Home</a>
    <a class="item ${(request.requestUri == "/about")?then("active", "")}" href="/about">About</a>
  </div>
</header>

<main class="page-width page-main ${containerClass} ${showSidebar?then('with-sidebar', '')}">
  <#nested>

  <#if showSidebar>
    <aside class="page-sidebar ui padded segment">
      <section>
        <h2 class="ui header">Tags</h2>
        <div class="ui divider"></div>
        <div class="tag-cloud">
          <#list tagCloud.items as item>
            <a href="/tags/${item.tag.name?url}" style="font-size: ${item.fontSize}rem;">${item.tag.name}</a>
          </#list>
        </div>
      </section>
    </aside>
  </#if>
</main>

<@assetHelper.javascripts 'jquery'/>
<@assetHelper.javascripts 'semantic-ui'/>
<@assetHelper.javascripts 'highlight-js'/>
<script src="${assetPath('application.js')}"></script>
</body>
</html>
</#macro>
