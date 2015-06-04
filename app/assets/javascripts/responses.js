$(document).ready(function () {

    var table = $("#responsesTable"),
        dataTable,
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
        dataTable = table.DataTable({
            "ajax": {
                "url": "/api/responses",
                "dataSrc": ""
            },
            "columns": columns
        });
    });

    var WS = window.MozWebSocket ? MozWebSocket : WebSocket;
    // TODO Fix hardcoded URL
    var dateSocket = new WS(webSocketUrl);

    dateSocket.onmessage = function (event) {
        dataTable.row.add(JSON.parse(event.data)).draw();
    };

});