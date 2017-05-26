$(function() {
	var $form = $("#form-cadastro-ideia");

    $form.bind("submit", function (event) {
		event.preventDefault();

		$.ajax({
			type: "POST",
            //TODO: define new url
			//url: "./api/accounts/analyst/register",
			contentType: "application/json;charset=UTF-8",
			data: $form.serializeArray(),
			success: function (data) {
				if (data.success) {
                    alert("Parabéns! Sua ideia foi cadastrada com sucesso. Em breve, nossos analistas retornarão após análise.");
                    document.location = "./";
				}
				else
					alert(data.message);
			}
		});
	});
});