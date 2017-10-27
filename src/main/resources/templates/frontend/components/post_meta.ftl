<#macro postMeta post>
<div class="post-meta-items">
  <div class="item">
    <i class="calendar icon" title="Posted on"></i>${post.publishDate.toString('MMM d, YYYY')}
  </div>
  <div class="item">
    <i class="edit icon" title="Last updated on"></i>${post.updatedAt.toString('MMM d, YYYY zz')}
  </div>
  <div class="item">
    <#list post.tags as tag>
      <span class="ui label">${tag.name}</span>
    </#list>
  </div>
</div>
</#macro>
