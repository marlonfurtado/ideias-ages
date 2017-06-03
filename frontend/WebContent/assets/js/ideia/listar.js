$(function() {
	var $ideasListBody = $("#idealizerListBody");

	//templates
    var ideasListTemplate = $("#ideasListTemplate").html();
    var ideas3ListEmptyTemplate = $("#idealizerListEmptyTemplate").html();

	var idealizers = {
		data: []
	};


	$.get(
		"./api/accounts/ideas/list", {},
		function (json) {
			ideas.data = json;
        }, "json"
	).then(function () {
        //render the template
        var htmlContent;

        //in case the list of users are empty
        if (ideas.data.length == 0)
            htmlContent = Mustache.render(ideasListEmptyTemplate);

        //otherwise, render the table
        else
            htmlContent = Mustache.render(ideasListTemplate, idealizers);

        //update the DOM by replacing the HTML content
        $ideasListBody.html(htmlContent);

        $("#idealizer-table").DataTable({
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