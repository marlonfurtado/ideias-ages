$(document).ready(function() {
	var $name = $("#name");
	var $phone = $("#phone");
	var $email = $("#email");
	var $password = $("#password");
	var $password2 = $("#password2");

	$("#form-perfil").submit(function (event) {
        var user = {};

		//check if the user wants to change the password
		if ($.trim($password.val()) != "") {
			//check if the passwords are the same
			if ($password.val() != $password2.val()) {
				alert("As senhas informadas não conferem. Por favor, certifique-se que ambas senhas estão iguais.");

				$password2.val("").trigger("focus");
				return false;
			}

            user.password = $password.val();
		}

        user.name = $name.val();
        user.email = $email.val();
        user.phone = removeDotsAndDashes($phone.val());

		$.ajax({
			type: "PUT",
			url: "./api/accounts/analyst/edit",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(user),
			success: function (data) {
				if (data.success) {
                    alert("Perfil editado com sucesso.");
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
    	$.get("./api/auth/me", function(data) {
    		$name.val(data.name);
    		$email.val(data.email);
    		$phone.val(data.phone);

    		//load the mask
            $phone.mask("(99) 99999-9999");
		});
	}

	loadData();
});