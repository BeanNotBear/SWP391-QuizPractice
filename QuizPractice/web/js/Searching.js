function searchExpert(search) {
    $.ajax({
        url: "searchExpert",
        data: {
            search: $(search).val()
        },
        type: 'GET',
        beforeSend: function () {
            $("#loading").css('display', 'flex');
        },
        complete: function () {
            $("#loading").css('display', 'none');
        },
        success: function (data) {
            let row = document.getElementById("expertTab");
            row.innerHTML = data;
        }
    });
}

function attachAccordionListeners() {
    var acc = document.getElementsByClassName("accordion");
    for (let i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function () {
            this.classList.toggle("active1");
            this.parentElement.classList.toggle("active1");

            var pannel = this.nextElementSibling;

            if (pannel.style.display === "block") {
                pannel.style.display = "none";
            } else {
                pannel.style.display = "block";
            }
        });
    }
}

function searchLesson(search) {
    $.ajax({
        url: "searchLesson",
        data: {
            searchLesson: $(search).val()
        },
        type: 'GET',
        beforeSend: function () {
            $("#loading").css('display', 'flex');
        },
        complete: function () {
            $("#loading").css('display', 'none');
        },
        success: function (data) {
            let row = document.getElementById("lessonContent");
            row.innerHTML = data;
            attachAccordionListeners();
        }
    });
}
attachAccordionListeners();

function searchLearner(search) {
    $.ajax({
        url: "searchlearner",
        data: {
            searchLearner: $(search).val()
        },
        type: 'GET',
        beforeSend: function () {
            $("#loading").css('display', 'flex');
        },
        complete: function () {
            $("#loading").css('display', 'none');
        },
        success: function (data) {
            let row = document.getElementById("learnerContent");
            row.innerHTML = data;
        }
    });
}