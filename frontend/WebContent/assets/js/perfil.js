$(document).ready(function () {
    var $name = $("#name");
    var $phone = $("#phone");
    var $email = $("#email");
    var $actualPassword = $("#actual-password");
    var $password = $("#new-password");
    var $password2 = $("#confirm-password");

    $("#form-perfil").submit(function (event) {
        var perfil = {};

        //check if the user wants to change the password
        if ($.trim($password.val()) != "") {
            //check if the passwords are the same
            if ($password.val() != $password2.val()) {
                alert("As senhas informadas não conferem. Por favor, certifique-se que ambas senhas estão iguais.");

                $password2.val("").trigger("focus");
                return false;
            }

            perfil.password = $password.val();
        }

        perfil.name = $name.val();
        perfil.email = $email.val();
        perfil.phone = removeDotsAndDashes($phone.val());
        perfil.passwordToValidate = $actualPassword.val();

        $.ajax({
            type: "PUT",
            url: "./api/accounts/analyst/edit",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(perfil),
            success: function (data) {
                if (data.success) {
                    alert("Perfil editado com sucesso.");
                    window.location.href = "./";
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("Erro ao enviar informações para o servidor.");
            }
        });

        return false;
    });

    function removeDotsAndDashes(str) {
        return str.toString().replace(/([.-\s()])/g, '');
    }

    function loadData() {
        $.get("./api/auth/me", function (data) {
            $name.val(data.name);
            $email.val(data.email);
            $phone.val(data.phone);

            //load the mask
            $phone.mask("(99) 99999-9999");
        });
    }

    function validatePassword(data) {
        if ($actualPassword != data.passwod) {
            alert("Senha inválida");
        }
    }

    loadData();
});