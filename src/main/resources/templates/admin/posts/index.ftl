<#-- @ftlvariable name="postPage" type="org.springframework.data.domain.Page<com.bianjp.blog.entity.Post>" -->
<#-- @ftlvariable name="pageLink" type="java.lang.String" -->
<#import "../../helper/pagination.ftl" as helper>
<@adminLayout.layout pageTitle="Posts">
<h2>Posts</h2>

<table class="ui table">
  <thead>
  <tr>
    <th>Title</th>
    <th>Status</th>
    <th>Published At</th>
    <th>Created At</th>
    <th>Last Updated At</th>
    <th>Operations</th>
  </tr>
  </thead>

  <tbody data-delete-api="/admin/posts/{id}">
    <#list postPage.content as post>
    <tr data-id="${post.id}">
      <td><a href="/admin/posts/${post.id}/edit">${post.title}</a></td>
      <td>${post.status.text?capitalize}</td>
      <td>${post.publishDate.toString("yyyy/MM/dd")}</td>
      <td>${post.createdAt.toString("yyyy/MM/dd HH:mm")}</td>
      <td>${post.updatedAt.toString("yyyy/MM/dd HH:mm")}</td>
      <td class="operations">
        <a target="_blank" href="/posts/${post.prettyUrl}">View</a>
        <a href="#" class="delete">Delete</a>
      </td>
    </tr>
    </#list>
  </tbody>

  <tfoot>
  <tr>
    <th class="pagination-container" colspan="6">
      <@helper.pagination currentPage=postPage.number + 1
                          totalPages=postPage.totalPages
                          pageLink=pageLink/>
    </th>
  </tr>
  </tfoot>
</table>

  <#include "_delete_confirmation_modal.ftl">
</@adminLayout.layout>
