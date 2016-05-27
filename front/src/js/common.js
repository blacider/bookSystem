(function(window, undefined) {
    jQuery(document).ready(function($) {
        (function bendEvent() {
            var showModal = function(selecter) {
                $(selecter).css('display', 'block');
                $(".modal-fog").css({
                    visibility:"visible",
                    opacity:0.9
                });
            }
            $("header .right").delegate('a', 'click', function(event) {
                var target = $(event.target).data("modal");
                showModal("#" + target);
            });
            $(".close").click(function(event) {
                $(".modal").css('display', 'none');
                $(".modal-fog").css({
                    visibility:"hidden",
                    opacity:0
                });
            });
        })();
    });
})(window);