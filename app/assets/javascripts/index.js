$(document).ready(function () {

    var form = $("#fieldForm"),
        indexContent = $("#indexContent");

    $('.input-group.date').datepicker({});

    form.on("submit", function(){
        $.ajax({
            method: "POST",
            url: "/api/responses",
            //contentType: "application/json",
            //dataType: 'json',
            //data: JSON.stringify(form.serializeJSON()),
            data: form.serialize(),
            success: function() {
                indexContent.empty()
                    .append($("<h1>Thank you for submitting your data</h1>"));
            },
            error: function(xhr) {
                //failCallback(xhr);
                console.log("fail");
            }
        });
        return false;
    });

});