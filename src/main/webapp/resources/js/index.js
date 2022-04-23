$(function() {

	$(".btn-login").click(function() {

		if ($("#j_username").val() != "" && $("#j_password").val() != "") {
			$.LoadingOverlay("show");
		}

	});

});