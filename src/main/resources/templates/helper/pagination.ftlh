<#-- @ftlvariable name="currentPage" type="java.lang.Integer" -->
<#-- @ftlvariable name="totalPages" type="java.lang.Integer" -->
<#-- @ftlvariable name="pageLink" type="java.lang.String" -->
<#macro pagination currentPage totalPages pageLink>
  <#if totalPages == 0>
      No records
      <#return>
  </#if>
  <div class="ui pagination menu">
    <a class="icon item prev ${(currentPage <= 1)?then("disabled", "")}">
      <i class="left chevron icon"></i>
    </a>
    <#list 1..totalPages as page>
      <a class="item ${(page == currentPage)?then("active", "")}" href="${pageLink?replace("{page}", page)}">${page}</a>
    </#list>
    <a class="icon item next ${(currentPage >= totalPages)?then("disabled", "")}">
      <i class="right chevron icon"></i>
    </a>
  </div>
</#macro>
