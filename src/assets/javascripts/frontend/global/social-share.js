$(function () {
  for (key in jsSocials.shares) {
    if (jsSocials.shares.hasOwnProperty(key)) {
      jsSocials.shares[key].logo = jsSocials.shares[key].logo.replace('fa-', '').replace('fa', 'icon');
    }
  }
  jsSocials.shares.facebook.logo += ' f';

  $("#social-share").jsSocials({
    shares: ["twitter", "facebook", "googleplus", "linkedin", "pinterest"],
    url: window.location.host + window.location.pathname, // Do not include query string and hash
    text: document.title,
    showLabel: false,
    showCount: false,
  });
});
