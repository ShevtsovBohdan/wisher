function addLink(wishID) {
    var getInput = $("input[id = " + wishID +"]")[0].files[0];

    var fd = new FormData();
    fd.append('file', getInput);

    $.ajax({
        type: 'POST',
        url: '/addLocalLink',
        data: fd,
        contentType: false,
        async: false,
        cache: false,
        processData:false,
        success: function (data) {
            $("img[id = " + wishID +"]").src = "http://localhost/" + data;
        },
        error: function (data) {
            alert("error");
        }
    });
}
