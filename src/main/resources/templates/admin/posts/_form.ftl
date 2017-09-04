<#-- @ftlvariable name="post" type="com.bianjp.blog.entity.Post" -->
<form action="/admin/posts${post.newRecord?then("", "/${post.id}")}"
      method="${post.newRecord?then("POST", "PUT")}"
      class="ui form ajax-submit">
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

<#if post.newRecord || post.draft>
  <div class="inline field">
    <div class="ui checkbox">
      <input type="checkbox" name="publish" id="publish" value="true" class="hidden" tabindex="0">
      <label>Publish immediately</label>
    </div>
  </div>
<#else>
  <div class="inline field">
    <div class="ui checkbox">
      <input type="checkbox" name="publish" id="publish" value="true" class="hidden" tabindex="0">
      <label>Published</label>
    </div>
  </div>
</#if>
  <div class="actions">
    <div class="ui hidden message"></div>
    <button type="submit" class="ui submit primary button">Save</button>
    <button type="reset" class="ui reset button">Reset</button>
  </div>
</form>
