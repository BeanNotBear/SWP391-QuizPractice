function logout() {
    $.ajax({
        type: 'GET',
        url: "logout",
        success: function () {
            Swal.fire({
                title: "Log Out Successfully!",
                icon: "success"
            }).then((result) => {
                window.location.href = "home";
            });
        }
    });
}