<#-- @ftlvariable name="posts" type="java.util.List<com.bianjp.blog.entity.Post>" -->
<#import "components/post.ftl" as components>

<@layout.layout pageTitle="" containerClass="posts">
  <@components.postList posts/>
</@layout.layout>
