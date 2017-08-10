

function deleteWish(id, wishName, pageNumb) {
    $.ajax(
        {
            type: 'post',
            url: '/deletewish',
            data: {
                deleteWishId: id
            },
            success: function(data){
                // if($('.tr').size() < 10 && $("a[id != 1]")){
                //     $("a[id != 1]").closest("li").remove();
                // }
                $("button[id = " + id + "]").closest(".tr").remove();
            }
        });
}