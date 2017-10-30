var ajaxUrl = "ajax/profile/todo/";
var datatableApi;

function buildAjaxUrl() {
    if (ajaxUrl.search(new RegExp('^ajax/profile/todo/\\d+$')) == -1) {
        var url = window.location.href;
        var regxp = new RegExp("^.*/todo/(\\d+)\\D*");
        var todoId =  url.match(regxp)[1];
        ajaxUrl += todoId + '/';
    }
}

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + id,
        type: "POST",
        data: "enabled=" + enabled,
        success: function () {
            successNoty(enabled ? "Enabled" : "Disabled");
        },
        error: function () {
            $(chkbox).prop("checked", !enabled);
        }
    });
}


function deleteRow(todoId, taskId) {
    $.ajax({
        url: ajaxUrl + taskId,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("Deleted");
        }
    });
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "complete"
            },
            {
                "data": "title"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});