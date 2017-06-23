$(document).ready(function() {
	
	$("body").on("click", ".status", function() {
		var $obj = $(this);
		var cpf = $obj.data("id");
		var statusAtual = $obj.html();

		var user = {};
		user.cpf = cpf;

		statusAtual == "Ativar" ? (user.active = true) : (user.active = false);

		var toggle = user.active == true ? "enable" : "disable";

		if (confirm("Você tem certeza que deseja alterar o registro " + cpf + "?")) {

			$.ajax({
				type: "PUT",
				url: "./api/users/" + removeDotsAndDashes(cpf) + "/" + toggle,
				contentType: "application/json;charset=UTF-8",
				data: JSON.stringify(user),
				success: function (data) {
					if (data.success) {    					
						modal.show("Alterar status", data.message);
						$('#myModal').on('hide.bs.modal', function () {
							location.reload();
						})
												
					} else {
	                	modal.show("Alterar status", data.message);
					}
				},
				error: function () {
					modal.show("ERRO", "Erro ao enviar informações para o servidor.");
				}
			});
		}
	});

	function removeDotsAndDashes(str) {
		return str.toString().replace(/[.-\s()]/g, '');
	}
});