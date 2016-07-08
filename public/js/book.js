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
    });
})(window);