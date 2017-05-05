$(function() {
    var $loadingWrapper = $("#loadingWrapper");
    var $cpf = $("#cpf");

    $('#cpf').mask('999.999.999-99');

    var user = store.get("user");

    if (user !== undefined && user !== null && user.cpf !== null)
        document.location = "/projetos/ideias/";
    else
        $loadingWrapper.remove();

    $("#formLogin").submit(function (event) {
        event.preventDefault();

        var user = {};
        user.cpf = $("#cpf").unmask().val();
        user.password = $("#password").val();

        $.ajax({
            type: "POST",
            url: "/projetos/ideias/api/auth/login",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(user),
            success: function (data) {
                if (data.success) {
                    $.get("/projetos/ideias/api/auth/me", function(user) {
                        store.set("user", user);

                        window.location.href = "/projetos/ideias/";
                    });
                } else {
                    alert(data.message);
                }
            }
        });
    });
});