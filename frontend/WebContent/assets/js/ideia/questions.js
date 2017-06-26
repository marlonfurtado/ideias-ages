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
		success: function (data) {
			console.log("Ok");
		},
		error:function(data){
			console.log("err");
		}
	});
}