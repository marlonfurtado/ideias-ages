$(function() {
    var $loadingWrapper = $("#loadingWrapper");
    var $cpf = $("#cpf");

    $('#cpf').mask('999.999.999-99');

    var user = store.get("user");

    if (user !== undefined && user !== null && user.cpf !== null)
        document.location = "/ideiasWeb/";
    else
        $loadingWrapper.remove();

    $("#formLogin").submit(function (event) {
        event.preventDefault();

        var user = {};
        user.cpf = $("#cpf").unmask().val();
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

                        window.location.href = "/ideiasWeb";
                    });
                } else {
                    alert(data.message);
                }
            }
        });
    });
});