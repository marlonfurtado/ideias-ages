$(document).ready(function() {
    mask();

	$("#form-cadastro-idealizador").submit(function (event) {
		event.preventDefault();

        var user = {};
        user.name = $("#name").val();
        user.email = $("#email").val();
        user.phone = $("#phone").unmask().val();
        user.cpf = $("#cpf").unmask().val();
        user.password = $("#password").val();

		$.ajax({
			type: "POST",
			url: "/api/accounts/idealizer/register",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(user),
			success: function (data) {
				if (data.success) {
                    alert("Cadastro efetuado com sucesso.");
				} else {
					alert("Erro ao efetuar cadastro.");
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