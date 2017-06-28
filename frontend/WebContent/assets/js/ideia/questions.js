//Função que cria os 'hints', conforme o elemento da tela
function getToolTip(element){
	var $element = $("#" + element.id);
	!$element.hasClass("custom-tooltip") ? $element.addClass("custom-tooltip") : null;

	switch(element.id.toLowerCase()){
	case 'idea-question-description':
		$element.tooltip({
			title:'Faça um questionamento de forma objetiva, de forma que fique claro para o idealizador e assim seja respondida o mais breve possível a fim de sanar a sua dúvida.',
			trigger:'focus',
			placement:'bottom'
		});
		break;
		default:
			break;
	}
}

function enviar(){
	var data = new Object();
	data.id = $('#ideaId').val();
	data.question = $("#idea-question-description").val();
	$.ajax({
	type: "POST",
        url: "./api/ideas/".concat(data.id).concat("/question"),
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(data),
		success: function (response) {
			$('#question-description-required-simbol').hide();
			$("#idea-question-description").fadeOut(function(){
				if(!response.success){
					$('#response').removeClass("alert-success").addClass("alert-danger");
				}else{
					$('#response').removeClass("alert-danger").addClass("alert-success");
				}
				$('#response').removeClass("hide").html(response.message);
				$('#btn-cancel-modal').html("Fechar");
				$('#btn-send-modal').addClass("hide");
				
			});
		},
		error:function(data){
			console.log("err");
		}
	});
}

function resetModal(){
	$('#response').removeClass("alert-success")
				  .removeClass("alert-danger")
				  .addClass('hide')
				  .html('');
	$("#idea-question-description").show()
								   .val('');
	$('#question-description-required-simbol').show();
	$('#btn-cancel-modal').html("Cancelar");
	$('#btn-send-modal').removeClass("hide");
}

$(function(){
	$('#openQuestionModal').click(resetModal);
});

