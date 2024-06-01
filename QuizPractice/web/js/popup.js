function closePopUp() {
    const loginPopUp = document.querySelector(".login-popup");
    if (loginPopUp.style.display !== "none") {
        loginPopUp.style.display = "none";
    }
}

function openPopUp() {
    const loginPopUp = document.querySelector(".login-popup");
    if (loginPopUp.style.display === "none" || loginPopUp.style.display === "") {
        loginPopUp.style.display = "flex";
    }
}

function closePopUp1() {
    const loginPopUp = document.querySelector(".register-popup");
    if (loginPopUp.style.display !== "none") {
        loginPopUp.style.display = "none";
    }
}

function openPopUp1() {
    const loginPopUp = document.querySelector(".register-popup");
    if (loginPopUp.style.display === "none" || loginPopUp.style.display === "") {
        loginPopUp.style.display = "block";
    }
}

$("#login-form").submit(function (e) {
    e.preventDefault();
    const  email = $("#email").val();
    const  password = $(":password").val();
    const  formData = {
        email: email,
        password: password
    };
    const actionUrl = "login";
    $.ajax({
        type: "POST",
        url: actionUrl,
        data: formData,
        beforeSend: function () {
            $("#loading").css('display', 'flex');
        },
        complete: function () {
            $("#loading").css('display', 'none');
        },
        success: function (data) {
            console.log(data.status);
            if (data.status === "success") {
                Swal.fire({
                    title: data.message,
                    icon: "success"
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.reload();
                    }
                });
            } else if(data.status === "unactive") {
                Swal.fire({
                    title: "Please go to your email and verify your account",
                    icon: "error"
                });
            } else {
                Swal.fire({
                    title: data.message,
                    icon: "error"
                });
            }
        }
    });
});

$("#register-form").submit(function (e) {
    e.preventDefault();
    const fullName = $("#full-Name").val();
    const email = $("#email-rg").val();
    const phoneNumber = $("#phone").val();
    const gender = $("#gender").val();
    const password = $("#password-rg").val();
    const cfPassword = $("#cfPasssword-rg").val();
    const formData = {
        fullName: fullName,
        email: email,
        phone: phoneNumber,
        gender: gender,
        password: password,
        cfPassword: cfPassword
    };

    const actionUrl = "register";

    $.ajax({
        type: 'POST',
        url: actionUrl,
        data: formData,
        beforeSend: function () {
            $("#loading").css('display', 'flex');
        },
        complete: function () {
            $("#loading").css('display', 'none');
        },
        success: function (data) {
            console.log("ok");
            if (data.status === "success") {
                Swal.fire({
                    title: "Sign Up successfully!",
                    text: "Please go to your email to verify your account",
                    icon: "success"
                });
            } else {
                Swal.fire({
                    title: "Sign Up fail!",
                    text: data.err,
                    icon: "error"
                });
            }
        }
    });
});

function logout() {
    $.ajax({
        type: 'GET',
        url: "logout",
        success: function () {
            Swal.fire({
                title: "Log Out Successfully!",
                icon: "success"
            }).then((result) => {
                window.location.reload();
            });
        }
    });
}