<#-- @ftlvariable name="postPage" type="org.springframework.data.domain.Page<com.bianjp.blog.entity.Post>" -->
<#-- @ftlvariable name="pageLink" type="java.lang.String" -->
<#import "components/post.ftl" as components>
<#import "../helper/pagination.ftl" as pagination>

<@layout.layout pageTitle='' showSidebar=true>
  <main class="post-list">
    <@components.postList postPage.content/>

    <div class="pagination-container">
      <@pagination.pagination postPage.number + 1, postPage.totalPages, pageLink/>
    </div>
  </main>
</@layout.layout>
