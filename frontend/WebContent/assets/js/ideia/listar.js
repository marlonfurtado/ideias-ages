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
        	str = ideas.data[i].creationDate;
        	var year = str.substring(0,4);
        	var month = str.substring(5,7);
            var day = str.substring(8,10);
            str = day + "/" + month + "/" + year;
            console.log(str);
            ideas.data[i].creationDate = str;
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