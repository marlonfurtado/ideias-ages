//$(function() {
//	var $form = $("#form-cadastro-ideia");
//	
//	var data = new Object();
//	data.title = $("#title").val();
//	data.goal = $("#goal").val();
//	data.tags = $("#tags").val();
//	data.description = $("#description").val();
//	data.status = $("#status").val();
//
//
//	console.log(JSON.stringify(data));
//
//	
////    $form.bind("submit", function (event) {
////		event.preventDefault();
////
////		$.ajax({
////			type: "POST",
////            //TODO: define new url
////			//url: "./api/accounts/analyst/register",
////			contentType: "application/json;charset=UTF-8",
////			data: JSON.stringify(data),
////			success: function (data) {
////				if (data.success) {
////                    alert("Parabéns! Sua ideia foi cadastrada com sucesso. Em breve, nossos analistas retornarão após análise.");
////                    document.location = "./";
////				}
////				else
////					alert(data.message);
////			}
////		});
////		
////	});
//    
//    
//    
//    
//});




function cadastrar(){
	
	var data = new Object();
	data.title = $("#title").val();
	data.goal = $("#goal").val();
	data.tags = $("#tags").val();
	data.description = $("#description").val();
	data.status = "draft";
	$.ajax({
	type: "POST",
        url: "./api/ideas",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(data),
		success: function (data) {
			if (data.success) {
                alert("Parabéns! Sua ideia foi cadastrada com sucesso. Em breve, nossos analistas retornarão após análise.");
                document.location = "./";
			}
			else
				alert(data.message);
		}
	});
	
}

function atualizarStatus(){

	var data = new Object();
	data.title = $("#title").val();
	data.goal = $("#goal").val();
	data.tags = $("#tags").val();
	data.description = $("#description").val();
	data.status = "open";


	$.ajax({
	type: "PUT",
        url: "./api/ideas",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(data),
		success: function (data) {
			if (data.success) {
                alert("Parabéns! Sua ideia foi cadastrada com sucesso. Em breve, nossos analistas retornarão após análise.");
                document.location = "./";
			}
			else
				alert(data.message);
		}
	});
	
}

