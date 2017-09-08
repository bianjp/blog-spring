<#macro layout pageTitle="" bodyClass="" containerClass="">
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>${pageTitle} - ${blog.title}</title>
  <link rel="icon" href="${assetPath('favicon.png')}">
  <link rel="stylesheet" href="https://cdn.bootcss.com/semantic-ui/2.2.13/semantic.min.css">
  <link rel="stylesheet" href="${assetPath('admin.css')}">
</head>
<body class="${bodyClass}">
<header class="page-header">
  <div class="logo-container">
    <img class="logo" src="${assetPath('logo.png')}" alt="LOGO">
    <span class="title">Blog</span>
  </div>
  <div class="ui dropdown">
    <div class="text">${currentUser.username}</div>
    <i class="dropdown icon"></i>
    <div class="menu">
      <a class="item" href="/admin/profile">Profile</a>
      <a class="item" href="/admin/change-password">Change password</a>
      <a class="item" href="/logout">Logout</a>
    </div>
  </div>
</header>
<aside class="page-sidebar ui vertical inverted borderless menu">
  <a class="item ${(request.requestUri == "/admin")?then("active", "")}" href="/admin">Dashboard</a>
  <a class="item ${(request.requestUri == "/admin/posts")?then("active", "")}" href="/admin/posts">Posts</a>
  <a class="item ${(request.requestUri == "/admin/posts/drafts")?then("active", "")}" href="/admin/posts/drafts">Drafts</a>
  <a class="item ${(request.requestUri == "/admin/posts/new")?then("active", "")}" href="/admin/posts/new">New Post</a>
</aside>

<main class="page-main ${containerClass}">
  <#nested/>
</main>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="${assetPath('admin.js')}"></script>
</body>
</html>
</#macro>
