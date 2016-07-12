(function(window, undefined) {
    var showModal = function(selecter) {
        $(selecter).css('display', 'block');
        $(".modal-fog").css({
            visibility:"visible",
            opacity:0.3
        });
    }
    function showBookModalById(id) {
        showLoading();
        $.ajax({
            url: "/getTimeTable",
            type: 'GET',
            dataType: 'html',
            data: {
                movieId: movieId,
                theaterId:id
            }
        })
        .done(function(data) {
            $(".modal-body").empty().append(data);
            showModal("#book");
            hideLoading();
        });
    }
    jQuery(document).ready(function($) {
        (function bookButtonEvent() {
            $("#cinema-list").delegate('.cinema-book', 'click', function(event) {
                var id = $(event.target).closest('.cinema-item').data("id");
                showBookModalById(id);
            });
        })();
        (function timeSelectClickEvent() {
            $(".modal-body").delegate('#time-select span', 'click', function(event) {
                var id = $(event.target).data("id");
                $("#time-select span").removeClass('time-active');
                $(event.target).addClass('time-active')
                $(".book-table").addClass('hidden');
                $("#table-" + id).removeClass('hidden');
            });
        })();
        (function bookButtionClickEvent() {
            $(".modal-body").delegate('.book-button', 'click', function(event) {
                // price
                var target = $(event.target);
                var data = {
                    showingPrice: target.data('showingprice'),
                    roomId: target.data('roomid'),
                    movieId: target.data('movieid'),
                    showingTime: target.data('showingtime'),
                    movieName: $("#movieName").data('moviename'),
                    showingId: target.data("showingid")
                };
                var formDom = $("#chooseSeatForm");
                for (item in data) {
                    formDom.append('<input type="text" name="'+item+'" value="'+data[item]+'">')
                }
                showLoading();
                formDom.submit();
                //window.location.href = '/chooseSeat?showingId=' + $(event.target).data("id");
            });
        })();
    });
})(window);