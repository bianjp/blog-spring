function form2JSON(form) {
  form = $(form);
  var obj = {};
  form.serializeArray().forEach(function (pair) {
    obj[pair.name] = pair.value;
  });
  return JSON.stringify(obj);
}

// Using AJAX to submit form
(function () {
  var $form = $('form.ajax-submit');
  if (!$form.length) {
    return;
  }

  var $button = $form.find('.actions button[type=submit]');
  var $message = $form.find('.actions .message');

  $form.on('submit', function (event) {
    event.preventDefault();

    $button.addClass('loading');
    if ($message.is('.visible')) {
      $message.transition('slide down');
    }

    $.ajax({
      url: $form.attr('action'),
      method: $form.find('#_method').val() || 'POST',
      data: form2JSON($form),
      contentType: 'application/json',
      processData: false,
      dataType: 'json',
      timeout: 5000,
      success: function (data) {
        if (data.code === 0) {
          $message.removeClass('error').addClass('success').text('Saved successfully');
        } else {
          $message.removeClass('success').addClass('error').text(data && data.msg || 'Failed to save');
        }
      },
      error: function (xhr, textStatus) {
        $message.removeClass('success').addClass('error');
        if (xhr.status >= 500) {
          $message.text('Failed to save: server error');
        } else {
          $message.text('Failed to save: ' + textStatus + ', http status: ' + xhr.status);
        }
      },
      complete: function () {
        $button.removeClass('loading');
        $message.transition('slide down');
      }
    });
  });
}());
