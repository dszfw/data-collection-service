$(document).ready(function () {

    var table = $("#responsesTable"),
        columns = [];

    $.get("/api/fields?active=true", function(resp) {
        if(resp.length === 0) return;
        resp.forEach(function(val, i) {
            var x = {
                "title": val.aLabel,
                "data": val.aLabel,
                "defaultContent": "N/A"
            };
            columns.push(x);
        });
        table.dataTable({
            "ajax": {
                "url": "/api/responses",
                "dataSrc": ""
            },
            "columns": columns
        });
    });

});