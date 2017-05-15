$.ajax({
    type: "GET",
    url: "./services/types.json",
    dataType: "JSON",
    success: function(data) {
		var container = $('#desc');
		var original = container.text();
        $.each(data.services, function(index, service) {
            $('#' + service.id).hover(
                function() {
                    container.fadeOut(500, function() {
						container.text(service.desc);
						container.fadeIn(500);
					});
                },
                function() {
                    container.fadeOut(500, function() {
						container.text(original);
						container.fadeIn(500);
					});
                }
            );
        });
    },
});
