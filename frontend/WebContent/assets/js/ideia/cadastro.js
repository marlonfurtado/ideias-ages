$(function() {
	var $form = $("#form-cadastro-ideia");

	var $relacao = $("#relacao");
	var $area = $("#area");

    $form.bind("submit", function (event) {
		event.preventDefault();

		if (!areRequiredFieldsFilled()) {
			$relacao.trigger("focus");
			alert("Por favor, preencha todos os campos obrigatórios do formulário de ideia.");
			return false;
		}

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

    function areRequiredFieldsFilled() {
    	if (getFieldValue($relacao) == 0)
    		return false;

        if (getFieldValue($area) == 0)
            return false;

    	return true;
	}

	function getFieldValue(obj) {
    	return $.trim(obj.val());
	}
});