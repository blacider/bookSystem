function showLoading(){
	$(".modal-fog").css({
	    visibility:"visible",
	    opacity:0.3
	});
	$(".loadingContainer").css("display","block");
}

function hideLoading(){
	$(".modal-fog").css({
	    visibility:"hidden",
	    opacity:0
	});
	$(".loadingContainer").css("display","none");
}