(function(window, undefined) {
    jQuery(document).ready(function($) {
        (function bannerInit() {
            var container = $(".banner-container");
            var size = container.find('li').length;
            container.width(size*169*2);
            var list1 = container.find("ul");
            var list2 = list1.clone(true);
            list1.after(list2);
            var current = 1;
            var moveTo = function(dest) {
                if (dest == 0) {
                    container.css('left', -size*169);
                    dest = 8;
                }
                container.animate({
                    left: -(dest-1)*169
                },function() {
                    current = dest;
                    if (dest > size) {
                        container.css('left', 0);
                        current = 1;
                    }
                    console.log(current);
                });
            }
            var intervalId = setInterval(function() {
                moveTo(current+1)
            }, 2000);
            $(".prev").click(function(event) {
                moveTo(current-1);
                clearInterval(intervalId);
                intervalId = setInterval(function() {
                    moveTo(current+1)
                }, 2000);
            });
            $(".next").click(function(event) {
                moveTo(current+1);
                clearInterval(intervalId);
                intervalId = setInterval(function() {
                    moveTo(current+1)
                }, 2000);
            });
            container.mouseleave(function(event) {
                intervalId = setInterval(function() {
                    moveTo(current+1)
                }, 2000);
            }).mouseenter(function(event) {
                clearInterval(intervalId);
            });
        })();
        (function bendEvent() {
            $("#login-button").click(function(event) {
                $("#login").css('display', 'block');
                $(".modal-fog").css({
                    visibility:"visible",
                    opacity:0.9
                });
            });
            $("#login .close").click(function(event) {
                $("#login").css('display', 'none');
                $(".modal-fog").css({
                    visibility:"hidden",
                    opacity:0
                });
            });
        })();
    });
})(window);