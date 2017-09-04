$('.ui.checkbox').checkbox();

// Pagination
(function () {
  var container = $('.ui.table .pagination');
  if (!container.length) {
    return;
  }

  container.find('.prev:not(.disabled)').on('click', function (event) {
    event.preventDefault();
    window.location.href = container.find('.active').prev().attr('href');
  });

  container.find('.next:not(.disabled)').on('click', function (event) {
    event.preventDefault();
    window.location.href = container.find('.active').next().attr('href');
  });
}());

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
      url: $form[0].action,
      method: $form[0].method,
      data: new FormData($form[0]),
      contentType: false,
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
