function addLink(wishID) {
    var getInput = $("input[id = " + wishID +"]")[0].files[0];

    var fd = new FormData();
    fd.append('file', getInput);
    fd.append('wishID', wishID);

    $.ajax({
        type: 'POST',
        url: '/addLocalLink',
        data: fd,
        contentType: false,
        cache: false,
        processData:false,
        success: function(data) {
            $("img[id = " + wishID +"]").attr('src', "data:image/jpg;base64," + data)
        },
        error: function(data) {
        }
    });
}
