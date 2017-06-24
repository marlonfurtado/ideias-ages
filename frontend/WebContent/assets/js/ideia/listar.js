$(function() {
	var $ideasListBody = $("#ideasListBody");

	//templates
    var ideasListTemplate = $("#ideasListTemplate").html();
    var ideasListEmptyTemplate = $("#ideasListEmptyTemplate").html();

	var ideas = {
		data: []
	};

    var status = new Object();
    status["DRAFT"] = "Rascunho";
    status["OPEN"] = "Em aberto";
    status["UNDER_ANALYSIS"] = "Em análise";
    status["APPROVED"] = "Aprovada";
    status["REJECTED"] = "Rejeitada";
	$.get(
		"./api/ideas/", {},
		function (json) {
			ideas.data = json;
        }, "json"
	).then(function () {
        //render the template
        var htmlContent;
        $.each(ideas.data, function(i, val){
        	ideas.data[i].status = status[val.status];
        })
        
     
        //in case the list of users are empty
        if (ideas.data.length == 0) {
        	htmlContent = Mustache.render(ideasListEmptyTemplate);
        } else{
            ideas.btnLabel = "Visualizar";

            if (Cookies.get("userRole") === "idealizer") {
                ideas.btnLabel = "Editar";
            }

        	htmlContent = Mustache.render(ideasListTemplate, ideas);
        }

        //update the DOM by replacing the HTML content
        $ideasListBody.html(htmlContent);

        $("#ideas-table").DataTable({
            "language": {
                "url": "/assets/json/Portuguese-Brasil.json"
            },
            "columnDefs": [{
                "targets": 'no-sort',
                "orderable": false,
            }]
        });
    });
});