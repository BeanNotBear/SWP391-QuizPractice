$(document).ready(function () {
    window.onscroll = load;
});

function load () {
    if ($(window).scrollTop() >= $(document).height() - $(window).height()) {
        console.log("ok");
        $.ajax({
            url: "lazyLoadUser",
            type: 'GET',
            success: function (data) {
                $("#userContent").append(data);
            }
        });
    }
}