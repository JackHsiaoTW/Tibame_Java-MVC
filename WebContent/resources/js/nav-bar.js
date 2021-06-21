$(document).ready(function () {
    
    
    $(".hamburger").off("click").click(function(){
        $(".second-nav-bar").slideToggle();
        $(".hamburger").toggleClass("is-active");
        
    });


});