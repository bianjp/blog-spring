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
        <a class="ui label" href="/tags/${tag.name?url}">${tag.name}</a>
      </#list>
    </div>
  </div>
</#macro>

<#macro postListItem post>
  <section class="ui very padded segment post">
    <h2 class="ui header"><a href="/posts/${post.prettyUrl}">${post.title}</a></h2>
    <@postMeta post/>
    <div class="ui divider"></div>
    <div class="post-content">${post.excerpt?no_esc}</div>
    <a class="read-more" href="/posts/${post.prettyUrl}">Read more >></a>
  </section>
</#macro>

<#macro postList posts>
  <#list posts as post>
    <@postListItem post/>
  </#list>
</#macro>
