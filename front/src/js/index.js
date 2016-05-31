(function(window, undefined) {
    jQuery(document).ready(function($) {
        (function bannerInit() {
            var container = $(".banner-container");
            container.delegate('a', 'click', function(event) {
                var id = $(event.target).closest('.banner-item').data("id");
                //window.location.href = "/app/book";
                //TODO
                window.location.href = "/app/book?id=" + id;
                event.preventDefault();
            });
            var list1 = container.find("ul");
            //不够7个时复制
            var aDoms = list1.find('.banner-item');
            while (list1.find(".banner-item").length < 6) {
                list1.append(aDoms.clone(true, true));
            }
            //改变 container 宽度至可以容纳两倍的列表
            var size = container.find('li').length;
            container.width(size*166.5*2);
            //复制列表
            var list2 = list1.clone(true);
            list1.after(list2);
            var current = 1;
            var moveTo = function(dest) {
                if (dest == 0) {
                    container.css('left', -size*166.5);
                    dest = size;
                }
                container.animate({
                    left: -(dest-1)*166.5
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
    });
})(window);