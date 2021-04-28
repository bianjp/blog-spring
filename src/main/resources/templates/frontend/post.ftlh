<#-- @ftlvariable name="post" type="com.bianjp.blog.entity.Post" -->
<#import "components/post.ftlh" as components>

<@layout.layout pageTitle=post.title>
  <article class="ui very padded segment">
    <h1 class="ui header">${post.title}</h1>
    <@components.postMeta post/>
    <div class="ui divider"></div>
    <div class="post-content">${post.contentHtml?no_esc}</div>
  </article>

  <section>
    <h3 class="ui header">Share this post</h3>
    <div id="social-share"></div>
  </section>

  <#include "included/disqus.ftlh">
</@layout.layout>
