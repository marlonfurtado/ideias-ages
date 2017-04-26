$(function() {
    var $loadingWrapper = $("#loadingWrapper");

    $(document).ready(function() {
        $.get("/api/me", function(data) {
            //it means user is already logged
            if (data.cpf == null)
                document.location = "/login.jsp";
            else
                $loadingWrapper.remove();
        });
    });
});