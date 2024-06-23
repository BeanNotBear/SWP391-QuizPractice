function getPage(page, index) {
    console.log(index);
    $.ajax({
        type: 'POST',
        url: "subjectManager",
        data: {
            page: index
        }, success: function (data) {
            $('#content').html(data);
            const categories2 = $("#selectedCategory").val().toString().split(',');
            document.querySelector('#mutipleSelect').setValue(categories2);
            const status2 = $("#selectedStatus").val().toString().split(',');
            document.querySelector('#status-search').setValue(status2);
            console.log(status2);
        }
    });
}