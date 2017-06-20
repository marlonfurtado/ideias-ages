$(function() {
    var $addComment = $("#addComment");
    var $formAddComment = $("#formAddComment");
    var $addCommentText = $("#addCommentText");
    var $openAddComment = $("#openAddComment");

    var isUserAbleToPostComment = false;

    $formAddComment.unbind("submit").bind("submit", function() {
        var ideaId = $("#ideaId").val();

        if ($addCommentText.val() == "") {
            modal.show("Comentário", "Por favor, informe uma mensagem válida.");
			$('#myModal').on('hide.bs.modal', function () {
	            $addCommentText.trigger("focus");
			})
            return false;
        }

        $.ajax({
            url: "./api/ideas/" + ideaId + "/comments",
            type: "POST",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify({
                id: 0,
                comment: $addCommentText.val()
            }),
            dataType: "json",
            success: function(json) {
                modal.show("Comentário", json.message);
                
                if (json.success) {
                    //close the 'add box'
                    $openAddComment.trigger("click");

                    //reload the list of comments
                    loadListOfComments();
                }
            },
            error: function() {
                modal.show("Comentário", "Erro não esperado ao cadastrar a sua mensagem. Por favor, tente novamente.");
            }
        });

        return false;
    });

    $openAddComment.unbind("click").bind("click", function() {
        //reset this fields
        $addCommentText.val("");

        //toggle visualization
        if ($addComment.is(":visible")) {
            $addComment.addClass("hide");
            $openAddComment.html("Adicionar comentário")
                .removeClass("btn-danger")
                .addClass("btn-success");
        }
        else {
            $addComment.removeClass("hide");
            $openAddComment.html("Fechar inclusão de novo comentário")
                .removeClass("btn-success")
                .addClass("btn-danger");
        }
    });

    var commentsListTemplate = $("#commentsListTemplate").html();
    var $commentsListBody = $("#commentsListBody");

    function loadListOfComments() {
        var ideaId = $("#ideaId").val();

        $commentsListBody.html("Carregando...")

        $.get("./api/ideas/" + ideaId + "/comments", function(json) {
            //modify user's ability to comment
            handleUserAbilityToComment(json);

            var htmlContent = Mustache.render(commentsListTemplate, {
                data: json
            });

            $commentsListBody.html(htmlContent);
        });
    }

    function handleUserAbilityToComment(json) {
        //disable if the user has already posted more than 3
        isUserAbleToPostComment = (json.length < 4);

        if (isUserAbleToPostComment)
            $openAddComment.removeClass("hide");
        else
            $openAddComment.addClass("hide");
    }

    //by default, do load the list of comments
    window.setTimeout(function() {
        loadListOfComments();
    }, 500);
});