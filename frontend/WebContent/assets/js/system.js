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
		if (data.cpf === null) {
			alert("Sua sessão expirou, faça login novamente.");
			Cookies.remove("userName");
			Cookies.remove("userRole");
			Cookies.remove("userCpf");

			document.location = document.location;
		}
	});
 /*		
	function loadVersion() {
			$.get("./api/version", function(data) {
			$('#version').html(data.responseText); 
			console.log(data);
			alert( "AQUI NÃO ENTRA" );
		}).always (function(data) {
			$('#version').html(data.responseText); 
			console.log(data)
		});
	}
	
	
*/	
	function loadVersion() {
		$.ajax({
			type: "GET",
			url: "./api/version",
			contentType: "application/text;charset=UTF-8",
			success: function (data) {
				console.log(data)
				$('#version').html(data);
				console.log("PASSOU")
   		},
			error: function (data) {
   				console.log(data)
				alert("Erro não Funfo.");
            }
		});
	}

	loadVersion()
});
