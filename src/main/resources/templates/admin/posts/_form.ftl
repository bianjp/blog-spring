<#-- @ftlvariable name="tags" type="java.util.Set<com.bianjp.blog.entity.Tag>" -->
<#-- @ftlvariable name="post" type="com.bianjp.blog.entity.Post" -->
<form action="/admin/posts${post.newRecord?then("", "/${post.id}")}"
      class="ui form ajax-submit post">
  <input type="hidden" id="_method" value="${post.newRecord?then("POST", "PUT")}">
  <div class="field">
    <label for="title">Title</label>
    <input type="text" name="title" id="title" placeholder="Title" required autofocus value="${post.title!''}">
  </div>
  <div class="field">
    <label for="slug">Slug</label>
    <input type="text" name="slug" id="slug" placeholder="Post identifier in URL" required value="${post.slug!''}">
  </div>
  <div class="field">
    <label for="content">Content</label>
    <textarea name="content" id="content" rows="20" required>${post.content!''}</textarea>
  </div>

  <div class="inline field">
    <label for="tags">Tags</label>
    <div class="ui fluid multiple search selection dropdown" id="tags-input">
      <input type="hidden" name="tagsText" value="<#list post.tags as tag>${tag.name}<#sep>,</#list>">
      <i class="dropdown icon"></i>
      <div class="default text">Comma separated tags</div>
      <div class="menu">
      <#list tags as tag>
        <div class="item" data-value="${tag.name}">${tag.name}</div>
      </#list>
      </div>
    </div>
  </div>

  <div class="inline fields">
    <label>Status</label>
  <#if post.newRecord || post.draft>
    <div class="field">
      <div class="ui radio checkbox">
        <input type="radio" name="statusText" value="draft" checked>
        <label>Draft</label>
      </div>
    </div>
    <div class="field">
      <div class="ui radio checkbox">
        <input type="radio" name="statusText" value="published">
        <label>Published</label>
      </div>
    </div>
  <#else>
    <div class="field">
      <div class="ui radio checkbox">
        <input type="radio" name="statusText" value="published" ${post.published?then("checked", "")}>
        <label>Published</label>
      </div>
    </div>
    <div class="field">
      <div class="ui radio checkbox">
        <input type="radio" name="statusText" value="unpublished" ${post.published?then("", "checked")}>
        <label>Unpublished</label>
      </div>
    </div>
  </#if>
  </div>

  <div class="actions">
    <div class="ui hidden message"></div>
    <button type="submit" class="ui submit primary button">Save</button>
    <button type="reset" class="ui reset button">Reset</button>
  </div>
</form>
