// Author Artem Batutin //

$('a[href^="#"]').on('click', function(event) {
	//Smooth scroll for all a tags with href.
    var target = $(this.getAttribute('href'));
    if( target.length ) {
        event.preventDefault();
		//Collapsing menu if open on mobile.
		$(".navbar-collapse").collapse('hide');
        $('html, body').stop().animate({
            scrollTop: target.offset().top
        }, 1000);
    }

});
