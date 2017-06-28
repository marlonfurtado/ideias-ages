$(function() {
    var $cpf = $("#cpf");

    $cpf.mask('999.999.999-99');

    $("#formLogin").submit(function (event) {
        event.preventDefault();

        var user = {};
        user.cpf = removeDotsAndDashes($cpf.val());
        user.password = $("#password").val();

        $.ajax({
            type: "POST",
            url: "./api/auth/login",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(user),
            success: function (data) {
                if (data.success) {
                    $.get("./api/auth/me", function(user) {
                        Cookies.set("userName", user.name);
                        Cookies.set("userRole", user.role);
                        Cookies.set("userCpf", user.cpf);

                        window.location.href = "./";
                    });
                } else {
                	modal.show("Login", data.message);
                }
            }
        });
    });

    function removeDotsAndDashes(str) {
        return str.toString().replace(/([.-])/g, '');
    }
});