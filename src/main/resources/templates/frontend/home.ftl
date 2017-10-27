<#-- @ftlvariable name="posts" type="java.util.List<com.bianjp.blog.entity.Post>" -->
<#import "layout/layout.ftl" as layout>
<#import "components/post_meta.ftl" as components>

<@layout.layout pageTitle="" containerClass="posts">
  <#list posts as post>
  <section class="ui very padded segment post">
    <h2 class="ui header"><a href="/posts/${post.prettyUrl}">${post.title}</a></h2>
    <@components.postMeta post/>
    <div class="ui divider"></div>
    <div class="post-content">${post.excerpt}</div>
    <a class="read-more" href="/posts/${post.prettyUrl}">Read more >></a>
  </section>
  </#list>
</@layout.layout>
