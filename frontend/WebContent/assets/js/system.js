$(function() {
    var $logoutAction = $("#logoutAction");

    $logoutAction.unbind("click").bind("click", function() {
        $.get("./api/auth/logout", function() {
            Cookies.remove("userName");
            Cookies.remove("userRole");
            Cookies.remove("userCpf");

            document.location = document.location;
        });
    });
});