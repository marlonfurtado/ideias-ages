$(function() {
	
	var $newPassword = $("#new-password");
	var $confirmPassword = $("#confirm-password");
	var $modal = $('#change-password-modal');
	var $modalBody = $('#change-password-modal-body');
	
	var url = new URL(window.location);
	var token = url.searchParams.get("token");
	
	$.ajax({
        type: "POST",
        url: "./api/password/validateRecoverPasswordToken",
        contentType: "application/text;charset=UTF-8",
        data: token,
        success: function (data) {
        	
            if (data.success) {
            	$('#new-password').prop('disabled', false);
            	$('#confirm-password').prop('disabled', false);
            	$('#btn-create-new-password').prop('disabled', false);
            } else {
            	$modalBody.html("<p>" + data.message + "<p/>");
        		$modal.modal({
        			  keyboard: false,
        			  backdrop: 'static'
        			});
        		
        		$('#modal-close').click(function() {
        			window.location.href="./login.jsp";
        		});
            }
        }
    });
	
	$("#btn-create-new-password").click(function (event) {
		event.preventDefault();
		 
		var passwordChangeWrapper = {};
		 
		if ($.trim($newPassword.val()) != "") {
			//check if the passwords are the same
			if ($newPassword.val() != $confirmPassword.val()) {
				$confirmPassword.val("").trigger("focus");
				displayModal("As senhas informadas não conferem. Por favor, certifique-se que ambas senhas estão iguais.")
				return false;
			}
		
			passwordChangeWrapper.password = $newPassword.val();
			passwordChangeWrapper.token = token;
			
			$.ajax({
				type: "POST",
				url: "./api/password/changePassword",
				contentType: "application/json;charset=UTF-8",
				data: JSON.stringify(passwordChangeWrapper),
				success: function (data) {
					if (data.success) {
						displayModal(data.message)
					} else {
						displayModal(data.message)
					}
				}
			});
		}
		
	});
	
	function displayModal(message) {
		$modalBody.html("<p>" + message + "<p/>");
		$modal.modal();
	}
});