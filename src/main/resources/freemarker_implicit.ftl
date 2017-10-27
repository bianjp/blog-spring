[#ftl]
[#-- @implicitly included --]
[#-- @ftlvariable name="post" type="com.bianjp.blog.entity.Post" file="templates/frontend/included/disqus.ftl" --]
[#-- @ftlvariable name="currentUser" type="com.bianjp.blog.entity.User" --]
[#-- @ftlvariable name="blog" type="com.bianjp.blog.config.BlogProperties" --]
[#-- @ftlvariable name="request" type="org.springframework.web.servlet.support.RequestContext" --]

[#function assetPath path][/#function]
[#function javascript lib][/#function]
[#function stylesheet lib][/#function]

[#import "templates/helper/asset-helper.ftl" as assetHelper]
[#import "templates/frontend/layout/layout.ftl" as layout]
[#import "templates/admin/layout/layout.ftl" as adminLayout]
