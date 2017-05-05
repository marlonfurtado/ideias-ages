$(document).ready(function() {
    var $loadingWrapper = $("#loadingWrapper");

    mask();

    var user = store.get("user");

    if (user === undefined || user === null || user.cpf === null)
        document.location = "/system/login.jsp";
    else {
        $loadingWrapper.remove();
    }
	
	$("#form-cadastro-analista").submit(function (event) {
		event.preventDefault();

		var user = {};
		user.name = $("#name").val();
		user.email = $("#email").val();
		user.phone = $("#phone").unmask().val();
		user.cpf = $("#cpf").unmask().val();
		user.password = $("#password").val();

		$.ajax({
			type: "POST",
			url: "/api/accounts/analyst/register",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(user),
			success: function (data) {
				if (data.success) {
                    alert("Analista cadastrado com sucesso");
                    document.location = "/system/"
				} else {
					alert("Erro ao Cadastrar");
                    mask();
				}
			},
			error: function () {
                mask();
            }
		});
	});

    function mask() {
        $("#cpf").mask("999.999.999-99");
        $("#phone").mask("(99) 99999-9999");
    }
});