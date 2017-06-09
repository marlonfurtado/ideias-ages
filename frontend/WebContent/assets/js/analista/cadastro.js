$(function() {
	$('#cpf').mask('999.999.999-99');
	$("#phone").mask('(99) 99999-9999');
	
	$("#form-cadastro-analista").submit(function (event) {
		event.preventDefault();

		var user = {};
		user.name = $("#name").val();
		user.email = $("#email").val();
		user.phone = removeDotsAndDashes($("#phone").val());
		user.cpf = removeDotsAndDashes($("#cpf").val());
		user.password = $("#password").val();

		$.ajax({
			type: "POST",
			url: "./api/accounts/analyst/register",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(user),
			success: function (data) {
				if (data.success) {
					alert(data.message);
					
                    document.location = "./";
				}
				else {
					utils.criaModal("Cadastro", data.message);
				}
			}
		});
	});

    function removeDotsAndDashes(str) {
        return str.toString().replace(/[.-\s()]/g, '');
    }
	
});