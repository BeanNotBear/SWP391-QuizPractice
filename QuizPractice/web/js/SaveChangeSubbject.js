function saveChangeSubject(e) {
    const regex = /^[A-Za-z0-9\s!@#\$%\^\&*\)\(+=._-]{3,50}$/;
    let msg = "Do you want to save the changes?";
    if(numberOfStudent > 0) {
        msg = "This course has "+ numberOfStudent +" students are you sure you want to change it?"
    }
    if (regex.test($("#name").val().trim()) && $("#categorySelect").val() !== "" &&  tinymce.get('description').getContent() != "") {
        Swal.fire({
            title: msg,
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: "Save",
            denyButtonText: `Don't Save`
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: "subjectDetailExpert",
                    type: 'POST',
                    data: {
                        name: $("#name").val(),
                        status: $('input[name="status"]:checked').val(),
                        category: $("#categorySelect").val(),
                        description: tinymce.get('description').getContent(),
                        img: $("#img").val()
                    },
                    success: function () {
                        Swal.fire("Changes are saved", "", "success").then((rs) => {
                            if (rs.isConfirmed) {
                                window.location.reload();
                            }
                        });
                    },
                    error: function (xhr, status, error) {
                        Swal.fire("An error occurred", "", "error");
                        console.error("Error details:", status, error);
                    }
                });
            } else if (result.isDenied) {
                Swal.fire("Changes are not saved", "", "info");
            }
        });
    }
}


document.querySelector('#categorySelect').addEventListener('change', function () {
    if (this.value === "") {
        $("#cateMsg").css('display', 'block');
    } else {
        $("#cateMsg").css('display', 'none');
    }
});

function validateName(e) {
    const errorMessage = $("#name-err");
    const subjectName = $(e).val();

    // Regular expression to allow only letters and spaces (minimum 3 characters, maximum 50 characters)
    const regex = /^[A-Za-z0-9\s!@#\$%\^\&*\)\(+=._-]{3,50}$/;

    if (regex.test(subjectName.trim())) {
        $(errorMessage).css('display', 'none');
        // Here you can proceed with form submission or further actions
    } else {
        $(errorMessage).css('display', 'block');
    }
}
