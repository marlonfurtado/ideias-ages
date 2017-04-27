$(document).ready(function() {
	$('#cpf').mask('999.999.999-99');
	$("#phone").mask('(99) 99999-9999');
	
	$("#form-cadastro-analista").submit(function () {
		console.log("============");
		var user = {};
		user.name = $("#name").val();
		user.email = $("#email").val();
		user.phone = $("#phone").val();
		user.cpf = $("#cpf").val();
		user.password = $("#password").val();

		$.ajax({
			type: "POST",
			url: "/api/cadastroAnalista",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(user),
			success: function (data) {
				if (data.success) {
					window.location.href = "/";
				} else {
					alert("Erro ao Cadastrar");
				}
			}
		});
	});
	
});