<#import "../layout/layout.ftl" as layout>
<@layout.layout pageTitle="Profile">
<h2>Change Password</h2>
<form action="/admin/change-password" class="ui form change-password ajax-submit">
  <input type="hidden" id="transfer-format" value="form">
  <div class="inline field">
    <label for="old-password">Old Password</label>
    <input type="password" name="oldPassword" id="old-password" required autofocus>
  </div>
  <div class="inline field">
    <label for="password">New Password</label>
    <input type="password" name="password" id="password" required>
  </div>
  <div class="inline field">
    <label for="password-confirmation">Repeat New Password</label>
    <input type="password" name="passwordConfirmation" id="password-confirmation" required>
  </div>

  <div class="actions">
    <div class="ui hidden message"></div>
    <button type="submit" class="ui submit primary button">Save</button>
  </div>
</form>
</@layout.layout>
