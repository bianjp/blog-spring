<#-- @ftlvariable name="posts" type="java.util.List<com.bianjp.blog.entity.Post>" -->
<#import "layout/layout.ftl" as layout>

<@layout.layout pageTitle="" containerClass="posts">
  <#list posts as post>
  <section class="ui very padded segment post">
    <h2 class="ui header"><a href="/posts/${post.prettyUrl}">${post.title}</a></h2>
    <div class="post-meta-items">
      <div class="item">
        <i class="calendar icon" title="Posted on"></i>${post.publishDate.toString('MMM d, YYYY')}
      </div>
      <div class="item">
        <i class="edit icon" title="Last updated on"></i>${post.updatedAt.toString('MMM d, YYYY')}
      </div>
    </div>
    <div class="ui divider"></div>
    <div>${post.excerpt}</div>
    <a class="read-more" href="/posts/${post.prettyUrl}">Read more >></a>
  </section>
  </#list>
</@layout.layout>
