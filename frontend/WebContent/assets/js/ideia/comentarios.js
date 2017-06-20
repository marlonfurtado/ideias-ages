$(function() {
    var $addComment = $("#addComment");
    var $formAddComment = $("#formAddComment");
    var $addCommentText = $("#addCommentText");
    var $openAddComment = $("#openAddComment");

    var userRole = Cookies.get("userRole");

    $openAddComment.hide();
    $formAddComment.hide();

    $formAddComment.unbind("submit").bind("submit", function() {
        var ideaId = $("#ideaId").val();

        if ($addCommentText.val() == "") {
            alert("Por favor, informe uma mensagem válida.");
            $addCommentText.trigger("focus");
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
                alert(json.message);

                if (json.success) {
                    //close the 'add box'
                    $openAddComment.trigger("click");

                    //reload the list of comments
                    loadListOfComments();
                }
            },
            error: function() {
                alert("Erro não esperado ao cadastrar a sua mensagem. Por favor, tente novamente.");
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
            var htmlContent = Mustache.render(commentsListTemplate, {
                data: json
            });

            $commentsListBody.html(htmlContent);
        });
    }

    //by default, do load the list of comments
    window.setTimeout(function() {
        loadListOfComments();
    }, 500);
});