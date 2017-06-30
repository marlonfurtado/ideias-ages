$(function() {
    var $formAnswer = $("#form-resposta-duvida");
    var $text = $("#idea-question-answer");
    var $answersContainer = $("#answersContainer");

    var userRole = Cookies.get("userRole");

    $formAnswer.unbind("submit").bind("submit", function() {
        var ideaId = $("#ideaId").val();

        if ($text.val() == "") {
            modal.show("Comentário", "Por favor, informe uma mensagem válida.");
			$('#myModal').on('hide.bs.modal', function () {
	            $text.trigger("focus");
			})
            return false;
        }

        $.ajax({
            url: "./api/ideas/" + ideaId + "/question",
            type: "PUT",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify({
                answer: $text.val()
            }),
            dataType: "json",
            success: function(json) {
                //remove the modal
                $(".modal-backdrop.fade.in").remove();

                modal.show("Resposta", json.message);

                if (json.success) {
                    //reload the list of comments
                    loadListOfAnswers();
                }
            },
            error: function() {
                modal.show("Resposta", "Erro não esperado ao responder a dúvida. Por favor, tente novamente.");
            }
        });

        return false;
    });

    var answersListTemplate = $("#answersListTemplate").html();
    var $answersListBody = $("#answersListBody");

    function loadListOfAnswers() {
        var ideaId = $("#ideaId").val();

        $answersListBody.html("Carregando...")

        $.get("./api/ideas/" + ideaId + "/question", function(json) {
            if (json != null && json.length > 0) {
                //render the template
                var htmlContent = Mustache.render(answersListTemplate, {data: json});
                $answersListBody.html(htmlContent);

                //show the container
                $answersContainer.removeClass("hide");
            }
        });
    }

    //by default, do load the list of comments
    window.setTimeout(function() {
        loadListOfAnswers();
    }, 500);
});