<#import "../layout/layout.ftl" as layout>
<@layout.layout pageTitle="Profile">
<h2>Profile</h2>
<div class="ui form">
  <div class="inline field">
    <label>Username:</label>
    <span>${currentUser.username}</span>
  </div>
  <div class="inline field">
    <label>Created at:</label>
    <span>${currentUser.createdAt.toString('YYYY-MM-dd HH:mm:ss')}</span>
  </div>
  <div class="inline field">
    <label>Updated at:</label>
    <span>${currentUser.updatedAt.toString('YYYY-MM-dd HH:mm:ss')}</span>
  </div>
</div>
</@layout.layout>
