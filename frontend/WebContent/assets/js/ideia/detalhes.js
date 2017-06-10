$(function() {
    var $title = $("#title");
    var $goal = $("#goal");
    var $tags = $("#tags");
    var $description = $("#description");

    var $pageTitle = $("#h2PageTitle");
    var $ideaId = $("#ideaId");

    var $fields = $("#title, #goal, #tags, #description");

    //get ID from query string
    var id = getIdFromUrl();

    //in case the value is valid
    if (id != 0) {
        $.get("./api/ideas/" + id, function (json) {
        	if (json != null) {
        		//append the idea ID in the title
                $pageTitle.html($pageTitle.html() + " #" + json.id);
                $ideaId.val(json.id);

        		//replace values in fields
                $title.val(json.title);
                $goal.val(json.goal);
                $tags.val(json.tags);
                $description.val(json.description);


                //check if the fields must be disabled
                if (json.status !== "DRAFT")
                    $fields.attr("disabled", true);
            }
            else {
        		ideaDoesNotExist();
			}
        });
    }
    else {
    	ideaDoesNotExist();
	}

    function getIdFromUrl() {
        var param = getQueryStringValue("id");

        if (param !== "")
        	return param;
		else
        	return 0;
	}

	function ideaDoesNotExist() {
        alert("A ideia informada não existe. Você irá ser redirecionado para a página inicial.");
        document.location = "./";
	}
});