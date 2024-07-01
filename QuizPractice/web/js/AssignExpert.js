function assignExpert(expertId) {
    Swal.fire({
        title: "Do you want to assign this expert?",
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: "Assign",
        denyButtonText: `Don't assign`
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "assignExpert", // Replace with your actual endpoint URL
                type: 'GET',
                data: {
                    expertId: expertId
                },
                beforeSend: function () {
                    $("#loading").css('display', 'flex');
                },
                complete: function () {
                    $("#loading").css('display', 'none');
                },
                success: function (data) {
                    Swal.fire("Assigned!", "", "success").then((result) => {
                        window.location.reload();
                    });
                },
                error: function (xhr, status, error) {
                    // Handle errors here
                    Swal.fire("Error occurred", error, "error");
                }
            });
        } else if (result.isDenied) {
            Swal.fire("Changes are not saved", "", "info");
        }
    });
}
