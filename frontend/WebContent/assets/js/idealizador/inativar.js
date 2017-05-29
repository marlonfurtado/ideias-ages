$(document).ready(function() {

    $("body").on("click", ".inativar", function() {
    	var $obj = $(this);
    	var cpf = $obj.data("id");

    	if (confirm("Você tem certeza que deseja inativar o registro " + cpf + "?")) {
    	    		
    		$.ajax({
    			type: "PUT",
    			url: "./api/accounts/idealizer/inactive",
    			contentType: "application/json;charset=UTF-8",
    			data: JSON.stringify(cpf),
    			success: function (data) {
    				if (data.success) {    					
                        alert("Perfil inativado.");
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