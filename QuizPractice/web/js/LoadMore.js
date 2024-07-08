function loadMoreExpert() {
    $.ajax({
        url: "loadMoreExperts",
        type: 'GET',
        beforeSend: function () {
            $("#loading").css('display', 'flex');
        },
        complete: function () {
            $("#loading").css('display', 'none');
        },
        success: function (data) {
            let row = document.getElementById("expertTab");
            row.innerHTML += data;
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

function loadMoreLesson() {
    $.ajax({
        url: "loadmorelesson",
        type: "GET",
        beforeSend: function () {
            $("#loading").css('display', 'flex');
        },
        complete: function () {
            $("#loading").css('display', 'none');
        },
        success: function (data) {
            let row = document.getElementById("lessonContent");
            row.innerHTML += data;
            attachAccordionListeners(); // Re-attach the event listeners
        }
    });
}

// Call this function initially to attach listeners to the existing elements
attachAccordionListeners();

function loadMoreLearner() {
    $.ajax({
        url: "loadmoreexpert",
        type: 'GET',
        beforeSend: function () {
            $("#loading").css('display', 'flex');
        },
        complete: function () {
            $("#loading").css('display', 'none');
        },
        success: function (data) {
            let row = document.getElementById("learnerContent");
            row.innerHTML += data;
        }
    });
}