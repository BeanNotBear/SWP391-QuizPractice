function saveChangeSubject(e) {
    Swal.fire({
        title: "Do you want to save the changes?",
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: "Save",
        denyButtonText: `Don't Save`
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire("Changes are saved", "", "success").then(rs => {
                if (rs.isConfirmed) {
                    window.location.reload();
                }
            });
        } else if (result.isDenied) {
            Swal.fire("Changes are not saved", "", "info");
        }
    });
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

document.querySelector('#desContent').addEventListener("keyup", function () {
    const errorMessage = $("#desMsg");
    const content = $("#desContent").val();
    if (content.trim() === "") {
        $(errorMessage).css('display', 'block');
    } else {
        $(errorMessage).css('display', 'none');
    }
});

tinymce.init({
    selector: '#desContent',
    setup: function (editor) {
        editor.on('keyup', function (e) {
            const errorMessage = $("#desMsg");
            const content = $(e).val();
            if (content.trim() === "") {
                $(errorMessage).css('display', 'block');
            } else {
                $(errorMessage).css('display', 'none');
            }
        });
    }
});
