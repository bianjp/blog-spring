<#-- @ftlvariable name="tagName" type="java.lang.String" -->
<#-- @ftlvariable name="tag" type="com.bianjp.blog.entity.Tag" -->
<#-- @ftlvariable name="page" type="org.springframework.data.domain.Page<com.bianjp.blog.entity.Post>" -->
<#import "../components/post.ftl" as components>
<#import "../../helper/pagination.ftl" as pagination>

<@layout.layout pageTitle="Tag: ${tagName}" containerClass="posts-of-tag">
  <#if !tag??>
    <h1 class="ui title">Tag not found: ${tagName}</h1>
  <#else>
    <h1 class="ui title">Tag: <span class="">${tag.name}</span></h1>
    <@components.postList page.content/>

    <div class="pagination-container">
      <@pagination.pagination page.number + 1, page.totalPages, "/tags/${tagName?url}?page={page}"/>
    </div>
  </#if>
</@layout.layout>
