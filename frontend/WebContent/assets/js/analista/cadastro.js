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
			url: "./api/users/",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(user),
			success: function (data) {
				if (data.success) {
					modal.show("Cadastro", data.message);
					$('#myModal').on('hide.bs.modal', function () {
						window.location.href = "./";
					})

				}
				else {
					modal.show("Cadastro", data.message);
				}
			}
		});
	});

    function removeDotsAndDashes(str) {
        return str.toString().replace(/[.-\s()]/g, '');
    }
	
});