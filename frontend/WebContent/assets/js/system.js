$(function() {
    var $loadingWrapper = $("#loadingWrapper");
    var $userNameContainer = $("#userNameContainer");
    var $logoutAction = $("#logoutAction");

    var user = getSession();

    if (user === undefined || user === null || user.cpf === null)
        document.location = "/ideiasWeb/login.jsp";
    else {
        $loadingWrapper.remove();
        $userNameContainer.html(user.name);
    }

    $logoutAction.unbind("click").bind("click", function() {
        $.get("/api/auth/logout", function() {
            store.remove("user");
            document.location = document.location;
        });
    });

    function getSession() {
        $.get("/api/auth/me", function(user) {
            store.set("user", user);
        });

        return store.get("user");
    }
});