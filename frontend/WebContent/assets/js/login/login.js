$(function() {
    var $loadingWrapper = $("#loadingWrapper");

    $("#formLogin").submit(function () {
        var user = {};
        user.cpf = $("#cpf").val();
        user.password = $("#password").val();

        $.ajax({
            type: "POST",
            url: "/api/login",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(user),
            success: function (data) {
                if (data.success) {
                    window.location.href = "/";
                } else {
                    alert(data.message);
                }
            }
        });

        return false;
    });

    $(document).ready(function() {
       $.get("/api/me", function(data) {
           //it means user is already logged
           if (data.cpf != null)
               document.location = "/";
           else
               $loadingWrapper.remove();
       });
    });
});