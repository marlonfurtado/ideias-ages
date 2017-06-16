$(function() {
    var $cpf = $("#cpf");

    $("#forgotPasswordLink").click(function (event) {
        event.preventDefault();

        var user = {};
        user.cpf = removeDotsAndDashes($cpf.val());

        
        $.ajax({
            type: "POST",
            url: "./api/auth/recoverPassword",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(user),
            success: function (data) {
                if (data.success) {
                	alert(data.message);
                } else {
                    alert(data.message);
                }
            }
        });
    });

    function removeDotsAndDashes(str) {
        return str.toString().replace(/([.-])/g, '');
    }
});