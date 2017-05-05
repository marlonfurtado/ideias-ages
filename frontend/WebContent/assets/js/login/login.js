$(function() {
    var $loadingWrapper = $("#loadingWrapper");
    var $cpf = $("#cpf");

    var user = store.get("user");

    if (user !== undefined && user !== null && user.cpf !== null)
        document.location = "./";
    else
        $loadingWrapper.remove();

    $("#formLogin").submit(function (event) {
        event.preventDefault();

        var user = {};
        user.cpf = removeDotsAndDashes($cpf.val());
        user.password = $("#password").val();

        $.ajax({
            type: "POST",
            url: "./api/auth/login",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(user),
            success: function (data) {
                if (data.success) {
                    $.get("./api/auth/me", function(user) {
                        store.set("user", user);

                        window.location.href = "./";
                    });
                } else {
                    alert(data.message);
                }
            }
        });
    });

    function removeDotsAndDashes(str) {
        return str.toString().replace(/([.-])/g, '');
    }

    $cpf.mask('999.999.999-99');
});