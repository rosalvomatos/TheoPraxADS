$('.btn-remove').click(function () {
    var value = $(this).parent().find('.key-remove').val();
    $('#form-edit').append($('<input type="hidden" name="LeaderToDelete" value="' + value + '"/>'));
    removePortlet(this);
});
$('#add-item').click(function () {
    var item_per_row = $("#item-per-row").val();
    $('<div class="col-md-' + (12 / item_per_row) + '">' +
        '<div class="portlet light bordered">' +
        '<div class="portlet-title">' +
        '<div class="caption">' +
        '<span class="caption-subject bold uppercase font-blue-steel searchable">' +
        'Novo Elemento ' +
        '</span>' +
        '</div>' +
        '</div>' +
        '<div class="portlet-body">' +
        '<div class="course-content">' +
        '<div class="row">' +
        '<div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">' +
        '<input type="text" class="form-control" name="LeaderToInsert"/>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="course-buttons">' +
        '<div class="row">' +
        '<br />' +
        '<div class="col-md-12">' +
        '<a class="btn btn-danger btn-xs btn-remove-new">Excluir</a>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>').insertBefore($(this).parent().parent().parent());
});
$('#general-item-list').on('click', '.btn-remove-new', function () {
    removePortlet(this);
});

function removePortlet(item) {
    $(item).parent().parent().parent().parent().parent().parent().remove();
}
$('#form-edit').submit(function (e) {
    e.preventDefault();
    var keyElements = $('.leader-content-list-key');
    var valueElements = $('.leader-content-list-value');
    $(keyElements).each(function (index, element) {
        $('#form-edit').append($('<input type="hidden" name="LeaderContentList[' + index + '].Chave" value="' + $(element).val() + '"/>'));
    });
    $(valueElements).each(function (index, element) {
        $('#form-edit').append($('<input type="hidden" name="LeaderContentList[' + index + '].Valor" value="' + $(element).val() + '"/>'));
    });
    $(this).submit();
});