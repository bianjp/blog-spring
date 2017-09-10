<#-- @ftlvariable name="post" type="com.bianjp.blog.entity.Post" -->
<#import "layout/layout.ftl" as layout>

<@layout.layout pageTitle=post.title>
  <article class="ui very padded segment">
    <h1 class="ui header">${post.title}</h1>
    <div class="post-meta-items">
      <div class="item">
        <i class="calendar icon" title="Posted on"></i>${post.publishDate.toString('MMM d, YYYY')}
      </div>
      <div class="item">
        <i class="edit icon" title="Last updated on"></i>${post.updatedAt.toString('MMM d, YYYY zz')}
      </div>
    </div>
    <div class="ui divider"></div>
    <div class="post-content">${post.contentHtml}</div>
  </article>
</@layout.layout>
