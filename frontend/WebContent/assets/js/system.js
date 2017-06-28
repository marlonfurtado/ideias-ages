$(function() {
    var $logoutAction = $("#logoutAction");

    $logoutAction.unbind("click").bind("click", function() {
        $.get("./api/auth/logout", function() {
            Cookies.remove("userName");
            Cookies.remove("userRole");
            Cookies.remove("userCpf");

            document.location = document.location;
        });
    });

	$.get("./api/auth/me", function (data) {
		if (data === undefined || data.cpf === null) {
			modal.show("Sua sessão expirou", "Faça login novamente.");

			Cookies.remove("userName");
			Cookies.remove("userRole");
			Cookies.remove("userCpf");

			$('#myModal').on('hide.bs.modal', function () {
				window.location.href = "./";
			})
		} else {
			$("#userNameContainer").text(data.name);
		}
	});

	function loadVersion() {
		$.ajax({
			type: "GET",
			url: "./api/version",
			contentType: "application/text;charset=UTF-8",
			success: function (data) {
				console.log(data);
				$('#version').html(data);
				console.log("PASSOU")
   		},
			error: function (data) {
   				console.log(data);
				modal.show("Erro", "Falha ao requisitar informações da API.");
            }
		});
	}

	loadVersion()
});
