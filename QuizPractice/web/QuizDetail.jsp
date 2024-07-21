<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quiz Detail</title>

        <!-- Include CSS libraries -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/popup.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">

        <!-- Include jQuery and Bootstrap JavaScript libraries -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <!-- Include toastr JavaScript library -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

        <!-- Custom CSS -->
        <style>
            body {
                padding: 0;
                margin: 0;
            }
            .postDetail {
                margin-bottom: 100px;
            }
            nav {
                margin-top: -25px;
            }
            img {
                width: 100%;
            }
            button {
                width: 20%;
            }
            .switch {
                position: relative;
                display: inline-block;
                width: 60px;
                height: 34px;
            }
            .switch input {
                opacity: 0;
                width: 0;
                height: 0;
            }
            .slider {
                position: absolute;
                cursor: pointer;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                -webkit-transition: .4s;
                transition: .4s;
            }
            .slider:before {
                position: absolute;
                content: "";
                height: 26px;
                width: 26px;
                left: 4px;
                bottom: 4px;
                background-color: white;
                -webkit-transition: .4s;
                transition: .4s;
            }
            input:checked + .slider {
                background-color: #2196F3;
            }
            input:focus + .slider {
                box-shadow: 0 0 1px #2196F3;
            }
            input:checked + .slider:before {
                -webkit-transform: translateX(26px);
                -ms-transform: translateX(26px);
                transform: translateX(26px);
            }
            .slider.round {
                border-radius: 34px;
            }
            .slider.round:before {
                border-radius: 50%;
            }
            .swal2-styled {
                padding: 6.250px 25px 6.250px 11px !important;
            }
        </style>

        <!-- Custom JavaScript -->
        <script>
            //        $(document).ready(function() {
            //            // X? lý khi form submit
            //            $('form').submit(function(event) {
            //                event.preventDefault(); // Ng?n ch?n hành ??ng m?c ??nh c?a form
            //                
            //                // L?y giá tr? c?a các tr??ng input
            //                var quizId = $('#id').val();
            //                var quizName = $('#quizName').val();
            //                var subjectId = $('#subjectId').val();
            //                var level = $('#level').val();
            //                var numberQuestion = $('#numberQuestion').val();
            //                var duration = $('#duration').val();
            //                var type = $('#type').val();
            //                
            //                // Th?c hi?n các x? lý l?u d? li?u ? ?ây (ví d?: g?i AJAX request)
            //
            //                // Hi?n th? thông báo thành công b?ng toastr
            //                toastr.success('Update Succsesfully !!', 'Thông báo');
            //
            //                // N?u mu?n chuy?n h??ng sau khi l?u thành công, s? d?ng window.location.href = 'url_chuyen_huong';
            //            });
            //        });

            $(document).ready(function () {
                $('form').submit(function (event) {
                    event.preventDefault(); // Ng?n ch?n hành ??ng m?c ??nh c?a form

                    // L?y giá tr? c?a các tr??ng nh?p li?u
                    var quizId = $('#id').val();
                    var quizName = $('#quizName').val();
                    var subjectId = $('#subjectId').val();
                    var level = $('#level').val();
                    var numberQuestion = $('#numberQuestion').val();
                    var duration = $('#duration').val();
                    var type = $('#type').val();
                    var passRate = $('#passRate').val();

                    // Ki?m tra các tr??ng không ???c ?? tr?ng
                    if (!quizName || !subjectId || !level || !numberQuestion || !duration || !type || !passRate) {
                        toastr.error('All fields are required!', 'Error');
                        return;
                    }

                    // Ki?m tra các tr??ng nh?p s? ph?i l?n h?n 0
                    if (numberQuestion <= 0 || duration <= 0 || passRate <= 0) {
                        toastr.error('Number of Questions, Duration, and Pass Rate must be greater than 0!', 'Error');
                        return;
                    }

                    // T?o object JSON ?? g?i lên server
                    var formData = {
                        id: quizId,
                        quizName: quizName,
                        subjectId: subjectId,
                        level: level,
                        numberQuestion: numberQuestion,
                        duration: duration,
                        type: type,
                        passRate: passRate
                    };

                    // G?i AJAX request ?? l?u d? li?u vào c? s? d? li?u
                    $.ajax({
                        type: 'POST', // Ph??ng th?c g?i d? li?u là POST
                        url: 'quizdetail', // ???ng d?n x? lý d? li?u
                        data: formData, // D? li?u c?n g?i ?i
                        success: function (response) {
                            toastr.success('Update Successfully!', 'Success');
                        },
                        error: function (error) {
                            toastr.error('Failed to update!', 'Error');
                        }
                    });
                });
            });

        </script>
    </head>

    <body>
        <%@include file="/layout/header.jsp"%>

        <section class="postDetail">
            <h1 class="heading text-center">Quiz Detail</h1>
            <div class=" text-right">
                <a href="AddQuiz">Add New Quiz</a>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <form method="post" action="quizdetail">
                        <div class="col-md-3">
                            <div class="form-group">
                                <input type="hidden" id="id" name="id" value="<c:out value='${quizID.id}'/>">
                                <label for="quizName">Quiz Name</label>
                                <input type="text" class="form-control" id="quizName" name="quizName" value="<c:out value='${quizID.name}'/>" required>
                            </div>

                            <div class="form-group">
                                <label for="subjectId">Subject</label>
                                <select class="form-control" id="subjectId" name="subjectId" required>
                                    <c:forEach var="subject" items="${subjects}">
                                        <option value="${subject.id}" <c:if test="${quizID.subjectId == subject.id}">selected</c:if>>${subject.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="level">Level</label>
                                <select class="form-control" id="level" name="level" <c:if test="${not empty param.quizId}">disabled</c:if>>
                                    <option value="Easy" <c:if test="${quizID.level == 'Easy'}">selected</c:if>>Easy</option>
                                    <option value="Medium" <c:if test="${quizID.level == 'Medium'}">selected</c:if>>Medium</option>
                                    <option value="Hard" <c:if test="${quizID.level == 'Hard'}">selected</c:if>>Hard</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-2"></div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="numberQuestion">Number of Questions</label>
                                    <input type="number" class="form-control" id="numberQuestion" value="<c:out value='${quizID.numberQuestion}'/>" name="numberQuestion" required>
                            </div>
                            <div class="form-group">
                                <label for="duration">Duration (minutes)</label>
                                <input type="number" class="form-control" id="duration" name="duration" required value="<c:out value='${quizID.duration}'/>">
                            </div>
                            <div class="form-group">
                                <label for="Type">Type</label>
                                <select class="form-control" id="type" name="type" <c:if test="${not empty param.quizId}">disabled</c:if>>
                                    <option value="Muti-choice" <c:if test="${quizID.type == 'Muti-choice'}">selected</c:if>>Muti-choice</option>
                                    <option value="Essay" <c:if test="${quizID.type == 'Essay'}">selected</c:if>>Essay</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="passrate">Passrate (%)</label>
                                    <input type="number" class="form-control" id="passRate" name="passRate" value="50" required>
                                </div>
                                <button type="submit" class="btn btn-primary btn-sms">Save</button>
                            </div>
                        </form>
                    </div>
                </div>
            </section>

            <br/>

        <%@include file="/layout/footer.jsp" %>
        <script src="js/logout.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <!-- Include other scripts as needed -->
        <script src="js/ChangeStatusOfSubject.js"></script>
    </body>
</html>