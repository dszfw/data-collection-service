$(document).ready(function () {

    var table = $("#fieldsTable");

    table.dataTable({
        "ajax": {
            "url": "/api/fields?active=false",
            "dataSrc": ""
        },
        "pageLength": 15,
        "columns": [
            {"data": "id"},
            {"data": "aLabel"},
            {"data": "fieldType"},
            {"data": "required"},
            {"data": "active"},
            {
                "render": function (data, type, full, meta) {
                    var editLink = "<a href=\"" + "/fields/" + full.id + "\">Edit</a>",
                        deleteLink = "<a class=\"deleteFieldLink\" href=\"#\">Delete</a>";
                    return editLink + " " + deleteLink;
                }
            }
        ]
    });


    var tableAPI = table.DataTable(),
        id,
        tr;

    table.find("tbody").on("click", '.deleteFieldLink', function () {
        tr = $(this).parents('tr');
        id = tableAPI.row(tr).data().id;
        delDilog.modal('show');
    });

    var delDilog = $("#deleteConfirmationModal");
    delDilog.find(".btn-primary").on("click", function(){
        $.ajax({
            method: "DELETE",
            url: "/api/fields/" + id,
            success: function () {
                tableAPI
                    .row(tr)
                    .remove()
                    .draw();
                delDilog.modal('hide');
            },
            error: function () {
                console.log("#TODO fail deleteFieldLink");
            }
        });
    });

});