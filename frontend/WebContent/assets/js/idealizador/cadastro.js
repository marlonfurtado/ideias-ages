$(document).ready(function() {
	$("#cpf").mask("999.999.999-99");
	$("#phone").mask("(99) 99999-9999");

	$("#btn-cadastrar").submit(function () {
		var user = {};
		user.cpf = $("#cpf").val();
		user.password = $("#password").val();

		$.ajax({
			type: "POST",
			url: "/api/accounts/idealizer/register",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(user),
			success: function (data) {
				if (data.success) {
					window.location.href = "/";
				} else {
					alert("Erro ao cadastrar idealizador");
				}
			}
		});
	});
});