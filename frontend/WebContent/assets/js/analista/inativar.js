$(document).ready(function() {
	
	$("body").on("click", ".status", function() {
		var $obj = $(this);
		var cpf = $obj.data("id");
		var statusAtual = $obj.html();

		var user = {};
		user.cpf = cpf;

		statusAtual == "Ativar" ? (user.active = true) : (user.active = false);    	
		
		if (user.active == true){
			$("#btn-ativar").addClass("hidden");
			$("#btn-inativar").removeClass("hidden");
		} else {
			$("#btn-inativar").addClass("hidden");
			$("#btn-ativar").removeClass("hidden");
		}

		
		if (confirm("Você tem certeza que deseja alterar o registro " + cpf + "?")) {

			$.ajax({
				type: "PUT",
				url: "./api/accounts/analyst/status",
				contentType: "application/json;charset=UTF-8",
				data: JSON.stringify(user),
				success: function (data) {
					if (data.success) {    					
						alert("Status do perfil alterado.");
					} else {
						alert(data.message);
					}
				},
				error: function () {
					alert("Erro ao enviar informações para o servidor.");
				}
			});

		}

	});
	
});