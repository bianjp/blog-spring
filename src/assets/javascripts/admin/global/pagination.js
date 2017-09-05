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
