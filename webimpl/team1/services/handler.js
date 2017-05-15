var activated;
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
            $('#' + service.id).click(function() {
				//activating buttons.
				if(activated != null)
					activated.removeClass('active');
				$(this).addClass('active');

				//Same button closes the order panel.
				if(activated != null && activated.attr("id") == $(this).attr("id")) {
					$('#order').animate({
						height: 0
					}, 500, function() {
						//done
					});
					activated.removeClass('active');
					$('#order').children().fadeOut(500);
					activated = null;
					return;
				}

				activated = $(this);

                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        var con = $('<div></div>');
                        con.html(xhttp.responseText);
						$('#order').html(con);
						var oheight = con.height() + 20;
						con.hide();

                        $('#order').animate({
                            height: oheight
                        }, 500, function() {
							con.fadeIn(500);
                        });
                    }
                };
                xhttp.open("GET", "./services/" + service.id + ".html", true);
                xhttp.send();
            });
        });
    },
});
