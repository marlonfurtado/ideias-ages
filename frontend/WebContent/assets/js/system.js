$(function() {
    var $loadingWrapper = $("#loadingWrapper");
    var $userNameContainer = $("#userNameContainer");
    var $logoutAction = $("#logoutAction");

    var user = store.get("user");

    if (user === undefined || user === null)
        document.location = "/login.jsp";
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
});