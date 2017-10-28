<#-- @ftlvariable name="posts" type="java.util.List<com.bianjp.blog.entity.Post>" -->
<#import "components/post.ftl" as components>

<@layout.layout pageTitle='' showSidebar=true>
  <main class="post-list">
    <@components.postList posts/>
  </main>
</@layout.layout>
