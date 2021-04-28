<#if blog.disqus.enabled>
<div id="disqus_thread"></div>
<script>
  var disqus_config = function () {
    this.page.url = '${blog.host}/posts/${post.id}';
    this.page.identifier = 'post-${post.id}';
    this.page.title = '${post.title?js_string}';
  };

  (function () { // DON'T EDIT BELOW THIS LINE
    var d = document, s = d.createElement('script');
    s.src = 'https://${blog.disqus.shortname}.disqus.com/embed.js';
    s.setAttribute('data-timestamp', +new Date());
    (d.head || d.body).appendChild(s);
  })();
</script>
<noscript>Please enable JavaScript to view the
  <a href="https://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
</#if>
