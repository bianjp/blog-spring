<#-- @ftlvariable name="post" type="com.bianjp.blog.entity.Post" -->
<#import "components/post_meta.ftl" as components>

<@layout.layout pageTitle=post.title>
  <article class="ui very padded segment">
    <h1 class="ui header">${post.title}</h1>
    <@components.postMeta post/>
    <div class="ui divider"></div>
    <div class="post-content">${post.contentHtml}</div>
  </article>

  <#include "included/disqus.ftl">
</@layout.layout>
