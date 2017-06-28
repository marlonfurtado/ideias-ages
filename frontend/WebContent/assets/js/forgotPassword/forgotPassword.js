$(function() {
    var $cpf = $("#cpf");

    $("#forgotPasswordLink").click(function (event) {
        event.preventDefault();

        var user = {};
        user.cpf = removeDotsAndDashes($cpf.val());

        
        $.ajax({
            type: "POST",
            url: "./api/auth/recoverPasswordRequest",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(user),
            success: function (data) {
                if (data.success) {
                	modal.show("Senha", data.message);
                } else {
                    modal.show("Senha", data.message);
                }
            }
        });
    });

    function removeDotsAndDashes(str) {
        return str.toString().replace(/([.-])/g, '');
    }
});