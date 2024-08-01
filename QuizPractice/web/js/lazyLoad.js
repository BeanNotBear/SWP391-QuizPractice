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

function normalizeSpaces(str) {
    return str.trim().replace(/\s+/g, ' ');
}

function searchUser(search) {
    console.log(normalizeSpaces($(search).val()));
    const data = normalizeSpaces($(search).val());
    $.ajax({
        url: "SearchUser",
        type: 'GET',
        data: {
            search: data
        },
        success: function (data) {
            $("#userContent").html(data);
        }
    });
}

function openPopUpUser() {
    $("#modelPop").show();
}

function closePopUpUser() {
    $("#modelPop").hide();
}

function makeButton() {
    let fullName = $("#fullName").val();
    let email = $("#email").val();
    let phoneNumber = $("#phoneNumber").val();
    let gender = $('input[name="gender"]:checked').val();
    let role = $("#role").val();
    let password = $("#password").val();
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const phonePattern = /^(\+?\d{1,4}?[-.\s]?)?(\(?\d{3}\)?[-.\s]?)?\d{3}[-.\s]?\d{4}$/;
    console.log(fullName + " " + email + " " + gender + " " + role + " " + password);
    if (fullName.trim() === "" || !emailPattern.test(email)
            || (phoneNumber.trim() !== "" && !phonePattern.test(phoneNumber))) {
        document.getElementById("addUser").disabled = true;
    } else {
        document.getElementById("addUser").disabled = false;
    }
}

function addNewUser() {
    let fullName = $("#fullName").val();
    let email = $("#email").val();
    let phoneNumber = $("#phoneNumber").val();
    let gender = $('input[name="gender"]:checked').val();
    let role = $("#role").val();
    let password = $("#password").val();
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const phonePattern = /^(\+?\d{1,4}?[-.\s]?)?(\(?\d{3}\)?[-.\s]?)?\d{3}[-.\s]?\d{4}$/;
    console.log(fullName + " " + email + " " + gender + " " + role + " " + password);
    if (fullName.trim() === "" || !emailPattern.test(email.value)
            || (phoneNumber.value.trim() !== "" && !phonePattern.test(phoneNumber.value))) {
        $("#buttonId").prop("disabled", true);
    }
    $.ajax({
        url: "AddNewUser",
        type: 'POST',
        data: {
            fullName: fullName,
            email: email,
            phoneNumber: phoneNumber,
            gender: gender,
            role: role,
            password: password
        },
        beforeSend: function () {
            $("#loading").css('display', 'flex');
        },
        complete: function () {
            $("#loading").css('display', 'none');
        },
        success: function (data) {
            if (data.status === "success") {
                Swal.fire({
                    title: data.message,
                    icon: "success"
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.reload();
                    }
                });
            } else {
                Swal.fire({
                    title: data.message,
                    icon: "error"
                });
            }
        }
    });
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


function validateFullName1(fName) {
    let fullName = document.querySelector("#fullNameMsg");
    if (fName.value.trim() === "") {
        fullName.innerHTML = "You must fill full name";
    } else {
        fullName.innerHTML = "";
    }
}

function validateEmail1(email) {
    let uEmail = document.querySelector("#emailMsg");
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(email.value)) {
        uEmail.innerHTML = "Email is wrong format";
    } else {
        uEmail.innerHTML = "";
    }
}

function validatePhoneNumber1(phone) {
    let phoneNumber = document.querySelector("#phoneNumberMsg");
    const phonePattern = /^(\+?\d{1,4}?[-.\s]?)?(\(?\d{3}\)?[-.\s]?)?\d{3}[-.\s]?\d{4}$/;
    if (phone.value.trim() !== "" && !phonePattern.test(phone.value)) {
        phoneNumber.innerHTML = "Phone is wrong format";
    } else {
        phoneNumber.innerHTML = "";
    }
}

function lockAccount(id) {
    $.ajax({
        url: "LockAccount",
        type: 'POST',
        data: {
            id: id
        },
        success: function (data) {
            Swal.fire({
                title: "Lock account success!",
                icon: "success"
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.reload();
                }
            });
        }
    });
}

function unlockAccount(id) {
    $.ajax({
        url: "LockAccount",
        type: 'GET',
        data: {
            id: id
        },
        success: function (data) {
            Swal.fire({
                title: "Unlock account success!",
                icon: "success"
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.reload();
                }
            });
        }
    });
}