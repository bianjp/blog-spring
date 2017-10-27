(function () {
  var form = document.querySelector('.form.post');
  if (!form) {
    return;
  }

  $(form).find('#tags-input').dropdown({
    allowAdditions: true,
  });
})();
