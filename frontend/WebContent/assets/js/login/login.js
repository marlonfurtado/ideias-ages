$(function() {
    var $loadingWrapper = $("#loadingWrapper");
    var $cpf = $("#cpf");

    var user = store.get("user");

    if (user !== undefined && user !== null)
        document.location = "/";
    else
        $loadingWrapper.remove();

    $("#formLogin").submit(function () {
        var user = {};
        user.cpf = $("#cpf").val();
        user.password = $("#password").val();

        $.ajax({
            type: "POST",
            url: "/api/auth/login",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(user),
            success: function (data) {
                if (data.success) {
                    $.get("/api/auth/me", function(user) {
                        store.set("user", user);

                        window.location.href = "/";
                    });
                } else {
                    alert(data.message);
                }
            }
        });

        return false;
    });
});