$(document).ready(function () {

    var fieldType = $("#fieldType"),
        options = $("#optionsBlock"),
        optionsInputs = options.find("#optionsInputs"),
        fieldForm = $("#fieldForm");

    // for "edit" mode
    if (optionsInputs.children().length) {
        options.show();
    }

    fieldType.change(function() {
        refresh();
    });

    fieldForm.on("submit", function() {
        onSubmitCallback(fieldForm);
        return false;
    });

    failCallback = function(resp) {
        $(".errorMessage").text(resp.responseText);
        $(".errorNotification").show();
    };

    $("#cancelButton").on("click", function() {
        window.location.replace("/fields");
    });

    $('#addOptionButton').on("click", function() {
        createOption();
    });

    $('.delOptionButton').on("click", removeOption);

    function refresh() {
        optionsInputs.empty();
        var typeValue = fieldType.val();
        switch (typeValue) {
            case "LINE":
            case "TEXTAREA":
            case "DATE":
            case "CHECK_BOX":
                options.hide();
                optionsInputs.empty();
                break;
            case "RADIO_BUTTON":
            case "COMBO_BOX":
                createOption();
                createOption();
                options.show();
        }
    }

    function removeOption() {
        $(this).parents(".row").remove();
    }

    function createOption() {
        var input = $(
            '<div class="form-group row">' +
                '<div class="form-group col-md-6">' +
                    '<input type="text" class="form-control" name="options[]">' +
                '</div>' +
            '</div>'
        );

        var removeButton = $(
            '<button type="button" class="delOptionButton btn btn-sm btn-default">' +
                '<span class="glyphicon glyphicon-trash"></span>' +
            '</button>'
        );
        removeButton.on("click", removeOption);

        var removeButtonWrapper = $('<div class="col-md-1"></div>');
        input.append(removeButtonWrapper.append(removeButton));
        input.appendTo(optionsInputs);
    }

});