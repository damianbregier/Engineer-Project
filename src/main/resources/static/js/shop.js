$(window).on("load", function () {

    });
  
    $("#navigation li a").click(function (e) {
      e.preventDefault();
  
      var targetElement = $(this).attr("href");
      var targetPosition = $(targetElement).offset().top;
      $("html, body").animate(
        {
          scrollTop: targetPosition - 50,
        },
        "slow"
      );
    });
  
  //Read this function when document (page) is ready
  $(document).ready(function () {
    //Activates superslide
    $("#slides").superslides({
      animation: "fade",
      play: 7000,
      pagination: false,
    });
  });
  
    function changePage(event) {
      if($(event.target).hasClass('external')) {
          window.location.href = $(event.target).attr('href');
          return;
      }
  }
  $(function () {
      $('.navbar-nav li').click( changePage );
  });
  
  
  