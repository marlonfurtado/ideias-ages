$(document).ready(function() {
	$("#cpf").mask("999.999.999-99");
	$("#phone").mask("(99) 99999-9999");

	$("#form-cadastro-idealizador").submit(function (event) {
		event.preventDefault();

        var user = {};
        user.name = $("#name").val();
        user.email = $("#email").val();
        user.phone = removeDotsAndDashes($("#phone").val());
		user.cpf = removeDotsAndDashes($("#cpf").val());
        user.password = $("#password").val();

		$.ajax({
			type: "POST",
			url: "./api/auth/singup",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(user),
			success: function (data) {
				if (data.success) {
					modal.show("Cadastro", data.message);
					$('#myModal').on('hide.bs.modal', function () {
						window.location.href = "./";
					})

				} else {
					modal.show("Cadastro", data.message);
				}
			},
			error: function () {
				modal.show("ERRO", "Erro ao enviar informações para o servidor.");
			}
		});
	});
	
    function removeDotsAndDashes(str) {
        return str.toString().replace(/[.-\s()]/g, '');
    }
    
});