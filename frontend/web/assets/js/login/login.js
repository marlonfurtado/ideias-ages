$(function() {
    $("#btnLogin").click(function () {
        var user = new Object();
        user.email = $("#inputEmail").val();
        user.password = $("#inputSenha").val();

        $.ajax({
            type: "POST",
            url: "/modeloRest/api/login",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(user),
            success: function (data) {
                if (data == "sucesso") {
                    window.location.href = "lista.html";
                } else {
                    $('#errorMessageText').html(data);
                    $('#errorMessageDiv').show();
                }
            }
        });
    });
});