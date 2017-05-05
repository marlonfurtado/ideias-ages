$(document).ready(function() {
    mask();

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
			url: "./api/accounts/idealizer/register",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(user),
			success: function (data) {
				if (data.success) {
                    alert("Cadastro efetuado com sucesso.");
				} else {
					alert(data.message);
				}
			},
			error: function () {
				mask();
            }
		});
	});
	
    function removeDotsAndDashes(str) {
        return str.toString().replace(/([.-\s()])/g, '');
    }
    
});