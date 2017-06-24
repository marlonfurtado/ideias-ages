$(function() {
	var $ideasListBody = $("#ideasListBody");

	//templates
    var ideasListTemplate = $("#ideasListTemplate").html();
    var ideasListEmptyTemplate = $("#ideasListEmptyTemplate").html();

	var ideas = {
		data: []
	};


	$.get(
		"./api/ideas/", {},
		function (json) {
			ideas.data = json;
        }, "json"
	).then(function () {
        //render the template
        var htmlContent;
        ideas.data.forEach(function(d){
        var date = new Date(d.creationDate)
        d.creationDate = (date.getDate() + 1) + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
        })
        //in case the list of users are empty
        if (ideas.data.length == 0){
        	htmlContent = Mustache.render(ideasListEmptyTemplate);
        } else{
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