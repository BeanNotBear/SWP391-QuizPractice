function paging(page, search) {
    window.location.href = "PagingRegistration?page=" + page + "&search=" + search;
}

function searching(search) {
    const v = search.value;
    const page = 1;
    window.location.href = "PagingRegistration?page=" + page + "&search=" + v;
}



$('a.btn-primary').on('click', function () {
    const row = $(this).closest("tr");

    // Get the userId and subjectId from the hidden inputs in the row
    const userId = row.find('.user-id').val();
    const subjectId = row.find('.subject-id').val();
    const duration = row.find('.duration').html().split(' ')[0];
    Swal.fire({
        title: "Do you want to change status?",
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: "Change",
        denyButtonText: `Don't change`
    }).then((result) => {
        if (result.isConfirmed) {
            alert('duartion: ' + duration);
            $.ajax({
                url: "ChangeSubjectRegistration",
                type: 'POST',
                data: {
                    userId: userId,
                    subjectId: subjectId,
                    duration: duration
                }, success: function (data) {
                    Swal.fire("Changes are saved", "", "success").then((rs) => {
                        if (rs.isConfirmed) {
                            window.location.reload();
                        }
                    });
                }
            });
        } else {
            Swal.fire("Changes are not saved", "", "info");
        }

    });
});