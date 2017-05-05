$(document).ready(function() {
    var $loadingWrapper = $("#loadingWrapper");

	$('#cpf').mask('999.999.999-99');
	$("#phone").mask('(99) 99999-9999');

    var user = store.get("user");

    if (user === undefined || user === null || user.cpf === null)
        document.location = "/login.jsp";
    else {
        $loadingWrapper.remove();
    }
	
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
                    alert("Analista cadastrado com sucesso");
                    document.location = "./";
				} else {
					alert("Erro ao Cadastrar");
				}
			}
		});
	});

    function removeDotsAndDashes(str) {
        return str.toString().replace(/([.-])/g, '');
    }
	
});