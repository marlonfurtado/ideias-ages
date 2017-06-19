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
                alert(data.message);
                document.location = "./detalhes_ideia.jsp?id=" + data.idea.id;
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
	type: "POST",
        url: "./api/ideas",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(data),
		success: function (data) {
			if (data.success) {
                alert(data.message);
                document.location = "./detalhes_ideia.jsp?id=" + data.idea.id;
			}
			else
				alert(data.message);
		}
	});
	
}

//Função que cria os 'hints', conforme o elemento da tela
function getToolTip(element){
	var $element = $("#" + element.id);
	!$element.hasClass("custom-tooltip") ? $element.addClass("custom-tooltip") : null;

	switch(element.id.toLowerCase()){
	case 'title':
		$element.tooltip({
			title:'Aqui vai o título para a sua idéia de projeto!',
			trigger:'focus',
			placement:'bottom'
		});
		break;
	case 'goal':
		$element.tooltip({
			title:'O objetivo do projeto é o que nos ajudará a compreender qual a real intenção da idéia.',
			trigger:'focus',
			placement:'bottom'
		});
		break;
	case 'tags':
		$element.tooltip({
			title:'As palavras-chave nos auxiliarão a realizar as pesquisas de suas idéias de uma forma mais eficiente.',
			trigger:'focus',
			placement:'bottom'
		});
		break;
	default:
		$element.tooltip({
			title:'Uma descrição bem detalhada sobre o seu projeto é fundamental para que possamos avaliar da melhor forma possível a sua idéia.',
			trigger:'focus',
			placement:'top'
		});
		break;
	}
}
