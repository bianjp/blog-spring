<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Login - ${blog.title}</title>
  <link rel="icon" href="/favicon.png">
  <link rel="stylesheet" href="https://cdn.bootcss.com/semantic-ui/2.2.13/semantic.min.css">
  <link rel="stylesheet" href="${assetPath('login.css')}">
</head>
<body>
  <div class="login-container">
    <h2 class="ui teal header">Login</h2>
    <form action="/login" method="POST" class="ui form">
      <div class="ui segment">
        <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="username" placeholder="Username" required autofocus>
          </div>
        </div>
        <div class="field">
          <div class="ui left icon input">
            <i class="lock icon"></i>
            <input type="password" name="password" placeholder="Password" required>
          </div>
        </div>
        <div class="field">
          <div class="ui checkbox">
            <input type="checkbox" name="remember-me" class="hidden" tabindex="0">
            <label>Remember me</label>
          </div>
        </div>

        <#if hasError>
          <div class="ui error visible message">Incorrect username or password!</div>
        </#if>

        <button type="submit" class="ui fluid large teal submit button">Login</button>
      </div>
    </form>

    <div class="back-to-home">
      <a href="/">Back to homepage</a>
    </div>
  </div>

  <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.bootcss.com/semantic-ui/2.2.13/semantic.min.js"></script>
  <script>
    $('.ui.checkbox').checkbox();
  </script>
</body>
</html>
