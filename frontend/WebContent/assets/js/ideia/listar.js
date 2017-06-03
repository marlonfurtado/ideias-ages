$(function() {
	var $ideasListBody = $("#ideasListBody");

	//templates
    var ideasListTemplate = $("#ideasListTemplate").html();
    var ideasListEmptyTemplate = $("#ideasListEmptyTemplate").html();

	var ideas = {
		data: []
	};


	$.get(
		"./api/ideas/list", {},
		function (json) {
			ideas.data = json;
        }, "json"
	).then(function () {
        //render the template
        var htmlContent;

        //in case the list of users are empty
        if (ideas.data.length == 0){
        	alert("ideas = 0");
        	htmlContent = Mustache.render(ideasListEmptyTemplate);
        }

        //otherwise, render the table
        else{
        	alert("ideas > 0");
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