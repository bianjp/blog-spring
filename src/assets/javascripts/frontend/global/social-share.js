$(function () {
  for (key in jsSocials.shares) {
    if (jsSocials.shares.hasOwnProperty(key)) {
      jsSocials.shares[key].logo = jsSocials.shares[key].logo.replace('fa-', '').replace('fa', 'icon');
    }
  }
  jsSocials.shares.facebook.logo += ' f';

  $("#social-share").jsSocials({
    shares: ["twitter", "facebook", "googleplus", "linkedin", "pinterest"],
    // Do not include query string and hash
    url: window.location.protocol + '//' + window.location.host + window.location.pathname,
    text: document.title,
    showLabel: false,
    showCount: false,
  });
});
