$(function() {
	var $idealizerListBody = $("#idealizerListBody");

	//templates
    var idealizerListTemplate = $("#idealizerListTemplate").html();
    var idealizerListEmptyTemplate = $("#idealizerListEmptyTemplate").html();

	var idealizers = {
		data: []
	};

    $("body").on("click", ".delete", function() {
    	var $obj = $(this);
    	var id = $obj.data("id");

    	if (confirm("Você tem certeza que deseja excluir o registro " + id + "?")) {
    		alert("Needs to be implemented.");
		}
	});

	$.get(
		"/api/accounts/idealizer/list", {},
		function (json) {
			idealizers.data = json;
        }, "json"
	).then(function () {
        //render the template
        var htmlContent;

        //in case the list of users are empty
        if (idealizers.data.length == 0)
            htmlContent = Mustache.render(idealizerListEmptyTemplate);

        //otherwise, render the table
        else
            htmlContent = Mustache.render(idealizerListTemplate, idealizers);

        //update the DOM by replacing the HTML content
        $idealizerListBody.html(htmlContent);

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