$("#filter").on('click', function () {
    const categories = $('#mutipleSelect').val().toString();
    const status = $('#status-search').val().toString();
    $.ajax({
        type: 'POST',
        url: "subjectManager",
        data: {
            categories: categories,
            status: status
        }, success: function (data) {
            $('#content').html(data);
            const categories2 = $("#selectedCategory").val().toString().split(',');
            document.querySelector('#mutipleSelect').setValue(categories2);
            const status2 = $("#selectedStatus").val().toString().split(',');
            document.querySelector('#status-search').setValue(status2);
            console.log(status2);
        }
    });
});