$(document).ready(function () {
    window.onscroll = load;
});

function load() {
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


function openPopUpUser() {
    $("#modelPop").show();
}

function closePopUpUser() {
    $("#modelPop").hide();
}

function generateRandomPassword(length) {
    var charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+<>?";
    var password = "";
    for (var i = 0; i < length; i++) {
        var randomIndex = Math.floor(Math.random() * charset.length);
        password += charset[randomIndex];
    }
    return password;
}

$(document).ready(function () {
    var randomPassword = generateRandomPassword(12); // Generate a 12-character random password
    $("#password").val(randomPassword);
});