$(document).ready(function() {
	
	$("body").on("click", ".status", function() {
		var $obj = $(this);
		var cpf = $obj.data("id");
		var statusAtual = $obj.html();

		var user = {};
		user.cpf = cpf;

		// Ativar ou Inativar
		statusAtual == "Ativar" ? (user.active = true) : (user.active = false);    	

		var toggle = user.active == true ? "enable" : "disable";

		if (confirm("Você tem certeza que deseja alterar o registro " + cpf + "?")) {

			$.ajax({
				type: "PUT",
				url: "./api/users/" + cpf + "/" + toggle,
				contentType: "application/json;charset=UTF-8",
				data: JSON.stringify(user),
				success: function (data) {
					if (data.success) {    					
						modal.show("Alterar status", data.message);
						$('#myModal').on('hide.bs.modal', function () {
							location.reload();
						})
					
					} else {
	                	modal.show("Mudar status", data.message);
					}
				},
				error: function () {
					modal.show("ERRO", "Erro ao enviar informações para o servidor.");
				}
			});
		}
	});
	
});