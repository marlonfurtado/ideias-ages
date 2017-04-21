$(function() {
    $("#formLogin").submit(function () {
        var user = {};
        user.cpf = $("#cpf").val();
        user.password = $("#password").val();

        $.ajax({
            type: "POST",
            url: "/api/login",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(user),
            success: function (data) {
                if (data.success) {
                    window.location.href = "/";
                } else {
                    alert("Erro ao logar");
                }
            }
        });

        return false;
    });
});