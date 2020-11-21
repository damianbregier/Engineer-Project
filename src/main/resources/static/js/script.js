$(window).on("load", function () {
  
    /* Loader control */
    $(".loader .loader-inner").fadeOut(3000, function () {
      $(".loader").fadeOut(1000);
    });
  
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

  /* Sticky navigation */
  const nav = $("#navigation");
  const navTop = nav.offset().top;
  $(window).on("scroll", stickyNavigation);
  function stickyNavigation() {
    var body = $("body");

    if ($(window).scrollTop() >= navTop) {
      body.css("padding-top", nav.outerHeight() + "px");
      body.addClass("fixedNav");
    } else {
      body.css("padding-top", 0);
      body.removeClass("fixedNav");
    }
  }

//Read this function when document (page) is ready
$(document).ready(function () {
  //Activates superslide
  $("#slides").superslides({
    animation: "fade",
    play: 7000,
    pagination: false,
  });

  //Activate typing of title elements
  var typed = new Typed(".typed", {
    strings: [
      "Kursy internetowe",
      "Usługi dla biznesu",
      "Teraz także w abonamencie",
    ],
    typeSpeed: 70,
    loop: true,
    startDelay: 1000,
    showCursor: false,
  });
});
