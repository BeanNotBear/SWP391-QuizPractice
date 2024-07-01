function searchExpert(search) {
    $.ajax({
        url: "searchExpert",
        data: {
            search: $(search).val()
        },
        type: 'GET',
        beforeSend: function () {
            $("#loading").css('display', 'flex');
        },
        complete: function () {
            $("#loading").css('display', 'none');
        },
        success: function (data) {
            let row = document.getElementById("expertTab");
            row.innerHTML = data;
        }
    });
}