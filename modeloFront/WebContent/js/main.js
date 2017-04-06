$(document).ready(function() {

	$("#btnAdd").click(function() {
		add();
	});
	
	function add() {
		var user = new Object();
		user.name = $("#name").val();
		user.email = $("#email").val();
		user.password = $("#password").val();

		var url = "/modeloRest/api/adduser"

		$.ajax({
			type : "POST",
			url : url,
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(user),
			success : function(data) {

				if (data == "sucesso") {
					$('#msgAviso').html('Aluno Cadastrado com Sucesso');
					window.location.href = "lista.html";
				} else {
					$('#msgAviso').html('erro');
					window.location.href = "erro.html";
				}

			}
		});

	}
});
