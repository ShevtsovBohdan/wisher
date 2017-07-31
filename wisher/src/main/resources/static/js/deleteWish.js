

function deleteWish(id, wishName, pageNumb) {
    $.ajax(
        {
            type: 'post',
            url: '/deletewish',
            data: {
                deleteWishId: id
            },
            success: function(data){
                // location.href = "/view?page=" + pageNumb;
                $("button[id = " + id + "]").closest(".tr").remove();
            }
        });
}