$(function() {
	var $idealizerListBody = $("#idealizerListBody");

	//templates
    var idealizerListTemplate = $("#idealizerListTemplate").html();
    var idealizerListEmptyTemplate = $("#idealizerListEmptyTemplate").html();

	var dataMock = {
		data: [{
			id: 1,
			name: "name 1",
			cpf: "cpf 1",
			email: "email 1",
			phone: "phone 1"
		},
		{
            id: 2,
			name: "name 2",
			cpf: "cpf 2",
			email: "email 2",
			phone: "phone 2"
		},
		{
            id: 3,
			name: "name 3",
			cpf: "cpf 3",
			email: "email 3",
			phone: "phone 3"
		}]
	};

	//render the template
	var htmlContent;

	//in case the list of users are empty
	if (dataMock.data.length == 0)
        htmlContent = Mustache.render(idealizerListEmptyTemplate);

	//otherwise, render the table
	else
        htmlContent = Mustache.render(idealizerListTemplate, dataMock);

	//update the DOM by replacing the HTML content
	$idealizerListBody.html(htmlContent);


    $("body").on("click", ".delete", function() {
    	var $obj = $(this);
    	var id = $obj.data("id");

    	if (confirm("Você tem certeza que deseja excluir o registro " + id + "?")) {
    		alert("Needs to be implemented.");
		}
	});

	$("#idealizer-table").DataTable({
		"language": {
			"url": "/assets/json/Portuguese-Brasil.json"
		}
	});
});