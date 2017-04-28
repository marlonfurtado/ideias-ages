$(document).ready(function() {
    var $loadingWrapper = $("#loadingWrapper");

	$('#cpf').mask('999.999.999-99');
	$("#phone").mask('(99) 99999-9999');

    var user = store.get("user");

    if (user === undefined || user === null)
        document.location = "/login.jsp";
    else {
        $loadingWrapper.remove();
    }
	
	$("#form-cadastro-analista").submit(function () {

		var user = {};
		user.name = $("#name").val();
		user.email = $("#email").val();
		user.phone = $("#phone").val();
		user.cpf = $("#cpf").val();
		user.password = $("#password").val();

		$.ajax({
			type: "POST",
			url: "/api/accounts/analyst/register",
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