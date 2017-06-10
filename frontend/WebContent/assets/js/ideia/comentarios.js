$(function() {
    var $addComment = $("#addComment");
    var $addCommentText = $("#addCommentText");

    $addComment.unbind("submit").bind("submit", function() {
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
                    //TODO: should close the add section
                    //TODO: should refresh the list
                }
            },
            error: function() {
                alert("Erro não esperado ao cadastrar a sua mensagem. Por favor, tente novamente.");
            }
        });

        return false;
    });
});