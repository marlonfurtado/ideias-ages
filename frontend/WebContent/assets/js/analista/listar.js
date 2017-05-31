$(function() {
	var $analystListBody = $("#analystListBody");

	//templates
    var analystListTemplate = $("#analystListTemplate").html();
    var analystListEmptyTemplate = $("#analystListEmptyTemplate").html();

	var analyst = {
		data: []
	};

    $.get("/api/accounts/analyst/list", {}, function (json) {
		analyst.data = json;
	}, "json").then(function () {
        //render the template
        var htmlContent;

        //in case the list of users are empty
        if (analyst.data.length == 0)
            htmlContent = Mustache.render(analystListEmptyTemplate);

        //otherwise, render the table
        else
            htmlContent = Mustache.render(analystListTemplate, analyst);

        //update the DOM by replacing the HTML content
        $analystListBody.html(htmlContent);

        $("#analystListTable").DataTable({
            "language": {
                "url": "/assets/json/Portuguese-Brasil.json"
            },
            "columnDefs": [{
                "targets": 'no-sort',
                "orderable": false,
            }]
        });
    });

    $("body").on("click", ".delete", function() {
    	var $obj = $(this);
    	var id = $obj.data("id");

    	if (confirm("Você tem certeza que deseja excluir o registro " + id + "?")) {
    		alert("Needs to be implemented.");
		}
	});
});
