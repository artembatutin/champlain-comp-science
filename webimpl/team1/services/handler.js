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
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            document.getElementById("order").innerHTML = xhttp.responseText;
          }
        };
        xhttp.open("GET", "./services/" + service.id + ".html", true);
        xhttp.send();
      });
    });
  },
});
