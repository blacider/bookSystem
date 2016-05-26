(function(window, undefined) {
    jQuery(document).ready(function($) {
        (function bannerInit() {
            var container = $(".banner ul");
            var size = container.find('li').length;
            container.width(size*169);
            var current = 1;
            var moveTo = function(dest) {
                dest = dest > size-7 ? size-6 : dest;
                container.animate({
                    left: -(dest-1)*169
                });
                current = dest;
            }
            $(".prev").click(function(event) {
                if (current > 1) {
                    moveTo(current-1);
                    console.log(current);
                }
            });
            $(".next").click(function(event) {
                moveTo(current+1);
                console.log(current);
            });
        })();
        (function bendEvent() {
            $("#login-button").click(function(event) {
                $("#login").css('display', 'block');
            });
            $("#login .close").click(function(event) {
                $("#login").css('display', 'none');
            });
        })();
    });
})(window);