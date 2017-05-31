$(document).ready(function() {
	
	$("body").on("click", ".status", function() {
		var $obj = $(this);
		var cpf = $obj.data("id");
		var statusAtual = $obj.html();

		var user = {};
		user.cpf = cpf;

		// Ativar ou Inativar
		statusAtual == "Ativar" ? (user.active = true) : (user.active = false);    	
		
		if (confirm("Você tem certeza que deseja alterar o registro " + cpf + "?")) {

			$.ajax({
				type: "PUT",
				url: "./api/accounts/analyst/status",
				contentType: "application/json;charset=UTF-8",
				data: JSON.stringify(user),
				success: function (data) {
					if (data.success) {    					
						alert("Status do perfil alterado.");
						location.reload();
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