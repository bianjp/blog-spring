(function () {
  var $tbody = $('.page-main > .ui.table tbody');
  if (!$tbody.length) {
    return;
  }

  var deleteApi = $tbody.attr('data-delete-api');
  var $modal = $('#delete-confirmation');
  var $failureModal = $('#delete-failure');

  $modal.modal({
    onApprove: function () {
      var $tr = $modal.data('tr');
      $.ajax({
        url: deleteApi.replace('{id}', $tr.attr('data-id')),
        method: 'DELETE',
        dataType: 'json',
        timeout: 3000,
        success: function (data) {
          if (data && data.code === 0) {
            flashPrompt("Deleted successfully");
            $tr.remove();
          } else {
            $failureModal.find('.content').text(data && data.msg || 'Unknown error');
            $failureModal.modal('show');
          }
        },
        error: function (xhr, textStatus) {
          $failureModal.find('.content').text(xhr.status >= 500 ? 'Server error' : textStatus);
          $failureModal.modal('show');
        }
      });
    }
  });

  $tbody.on('click', '.operations .delete', function (event) {
    event.preventDefault();
    $modal.data('tr', $(this).closest('tr'));
    $modal.modal('show');
  });
}());
