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
            successNoty(enabled ? "common.enabled" : "common.disabled");
        },
        error: function () {
            $(chkbox).prop("checked", !enabled);
        }
    });
}

$(function () {
    buildAjaxUrl();
    datatableApi = $('#datatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "isComplete",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "data": "title"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    }));
});