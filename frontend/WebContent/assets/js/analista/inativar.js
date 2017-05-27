$(document).ready(function() {	
	$("#inativar").click(function (event) {
     
		cpf = $.urlParam('cpf');
        
		
		$.ajax({
			type: "PUT",
			url: "./api/accounts/analyst/inactive",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(cpf),
			success: function (data) {
				if (data.success) {
					console.log(cpf);

                    alert("Perfil inativado com sucesso.");
				} else {
					console.log(cpf);

					alert(data.message);
				}
			},
			error: function () {
				alert("Erro ao enviar informações para o servidor.");
			}
		});

		return false;
	});
	
//    function loadData() {
//    	$.get("./api/accounts/analyst/list", function(data) {	
//    		$.map(data, function(user) {
//    			if(user.cpf == $.urlParam('cpf')){
//    				$name.val(user.name);
//    				$email.val(user.email);
//    				$phone.val(user.phone);
//    			}
//    		});
//    		
//    		//load the mask
//            $phone.mask("(99) 99999-9999");
//		});
//	}

    $.urlParam = function(name){
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        if (results==null){
           return null;
        }
        else{
           return results[1] || 0;
        }
    }
 
//	loadData();
});
