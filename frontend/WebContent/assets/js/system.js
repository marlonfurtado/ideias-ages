$(function() {
    var $loadingWrapper = $("#loadingWrapper");
    var $userNameContainer = $("#userNameContainer");
    var $logoutAction = $("#logoutAction");

    var user = getSession();

    if (user === undefined || user === null || user.cpf === null)
        document.location = "/projetos/ideias/login.jsp";
    else {
        $loadingWrapper.remove();
        $userNameContainer.html(user.name);
    }

    $logoutAction.unbind("click").bind("click", function() {
        $.get("/projetos/ideias/api/auth/logout", function() {
            store.remove("user");
            document.location = document.location;
        });
    });

    function getSession() {
        $.get("/projetos/ideias/api/auth/me", function(user) {
            store.set("user", user);
        });

        return store.get("user");
    }
});