<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Quiz</title>

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/popup.css">

        <!-- Bootstrap file link  -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <!-- Custom CSS to make the footer fixed -->
        <style>
            body {
                padding: 0;
                margin: 0;
            }
            .footer {
                background: #f8f9fa;
                text-align: center;
                position: fixed;
                bottom: 0;
                width: 100%;
                height: 15%;
            }
            #topTable {
                margin-bottom: 0.5%;
            }
            .practiceList {
                margin-bottom: 10%;
            }
            nav {
                margin-top: -25px;
            }
        </style>
    </head>

    <body>
        <%@include file="/layout/header.jsp"%>

        <section class="practiceList">
            <h1 class="heading text-center">Add New Quiz</h1>
            <div class="container">
                <form id="addQuizForm" action="AddQuiz" method="post">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="subject">Subject:</label>
                                <select class="form-control" required id="subject" name="subject" <c:if test="${not empty param.quizId}">disabled</c:if>>
                                        <option value="" >Select Subject</option>
                                    <c:forEach var="subject" items="${subjects}">
                                        <option value="${subject.id}" <c:if test="${subject.id == param.subject}">selected</c:if>>${subject.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="lesson">Lesson:</label>
                                <select class="form-control" required id="lesson" name="lesson" <c:if test="${param.subject != null}">enabled</c:if> <c:if test="${not empty param.quizId}">disabled</c:if>>
                                        <option value="">Select Lesson</option >
                                    <c:if test="${param.lesson != null}">
                                        <c:forEach var="lesson" items="${lessons}">
                                            <option value="${lesson.id}" <c:if test="${lesson.id == param.lesson}">selected</c:if>>${lesson.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select >
                            </div>
                            <div class="form-group">
                                <label for="Type">Type</label>
                                <select class="form-control" id="type" name="type" <c:if test="${not empty param.quizId}">disabled</c:if>>
                                    <option value="Muti-choice" <c:if test="${param.level == 'Muti-choice'}">selected</c:if>>Muti-choice</option>
                                    <option value="Essay" <c:if test="${param.level == 'Essay'}">selected</c:if>>Essay</option>

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="name">Quiz Name:</label>
                                    <input type="text" class="form-control" id="name" name="name" value="${param.name}" <c:if test="${not empty param.quizId}">disabled</c:if> required>
                                </div>
                                <div class="form-group">
                                    <label for="level">Level:</label>
                                    <select class="form-control" id="level" name="level" <c:if test="${not empty param.quizId}">disabled</c:if>>
                                    <option value="Easy" <c:if test="${param.level == 'Easy'}">selected</c:if>>Easy</option>
                                    <option value="Medium" <c:if test="${param.level == 'Medium'}">selected</c:if>>Medium</option>
                                    <option value="Hard" <c:if test="${param.level == 'Hard'}">selected</c:if>>Hard</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="duration">Duration (minutes):</label>
                                    <input type="number" class="form-control" id="duration" name="duration" 
                                           value="${param.duration}" 
                                    <c:if test="${not empty param.quizId}">disabled</c:if> 
                                        required oninput="validateDuration()">
                                    <small id="durationError" class="form-text text-danger" style="display:none;">Please enter a positive integer.</small>
                                </div>



                                <div class="form-group">
                                    <label for="numberQuestion">Number of Questions:</label>
                                    <input type="number" class="form-control" id="numberQuestion" name="numberQuestion" 
                                           value="${param.numberQuestion}" 
                                    <c:if test="${not empty param.quizId}">disabled</c:if> 
                                        required oninput="validateNumberOfQuestions()">
                                    <small id="numberQuestionError" class="form-text text-danger" style="display:none;">Please enter a positive integer.</small>
                                </div>
                                <button type="submit" class="btn btn-primary " <c:if test="${not empty param.quizId}">style="display:none"</c:if>>Add Quiz</button>
                            </div>
                        </div>
                    </form>
                <c:if test="${not empty param.quizId}">
                    <div class="list-questions">
                        <h2>List Questions in Quiz</h2>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Detail</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="question" items="${questions}">
                                    <tr>
                                        <td>${question.id}</td>
                                        <td>${question.detail}</td>
                                        <td>
                                            <form action="DeleteQuestionFromQuizServlet" method="post" style="display:inline;">
                                                <input type="hidden" name="quizId" value="${param.quizId}">
                                                <input type="hidden" name="questionId" value="${question.id}">
                                                <input type="hidden" name="name" value="${param.name}">
                                                <input type="hidden" name="level" value="${param.level}">
                                                <input type="hidden" name="numberQuestion" value="${param.numberQuestion}">
                                                <input type="hidden" name="duration" value="${param.duration}">
                                                <input type="hidden" name="subject" value="${param.subject}">
                                                <input type="hidden" name="lesson" value="${param.lesson}">
                                                <input type="hidden" name="type" value="${param.type}">

                                                <button type="submit" class="btn btn-danger">Delete</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <c:if test="${fn:length(questions) < param.numberQuestion}">
                            <form action="AddQuestionToQuiz" method="get">
                                <input type="hidden" name="quizId" value="${param.quizId}">
                                <input type="hidden" name="name" value="${param.name}">
                                <input type="hidden" name="level" value="${param.level}">
                                <input type="hidden" name="numberQuestion" value="${param.numberQuestion}">
                                <input type="hidden" name="duration" value="${param.duration}">
                                <input type="hidden" name="subject" value="${param.subject}">
                                <input type="hidden" name="lesson" value="${param.lesson}">
                                <input type="hidden" name="type" value="${param.type}">

                                <button type="submit" class="btn btn-success">Add More</button>
                            </form>
                        </c:if>
                    </div>
                </c:if>
            </div>
        </section>
        <br/>

        <%@include file="/layout/footer.jsp" %>

        <!-- side bar có thể thu nhỏ khi màn hình nhỏ  -->
        <script src="js/script.js"></script>
        <script>
            $(document).ready(function () {
                $('#subject').change(function () {
                    var subjectId = $(this).val();
                    if (subjectId) {
                        $.ajax({
                            type: 'POST',
                            url: 'GetLessonsServlet',
                            data: {subjectId: subjectId},
                            success: function (response) {
                                $('#lesson').html(response).prop('disabled', false);
                            }
                        });
                    } else {
                        $('#lesson').html('<option value="">Select Lesson</option>').prop('disabled', true);
                    }
                });
            });


        </script>
        <script src="js/logout.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <script>
            function validateDuration() {
                var durationInput = document.getElementById('duration');
                var durationError = document.getElementById('durationError');
                var value = durationInput.value;

                // Check if the value is a positive integer
                if (!Number.isInteger(Number(value)) || value <= 0) {
                    durationError.style.display = 'block';
                    durationInput.setCustomValidity('Invalid field.');
                } else {
                    durationError.style.display = 'none';
                    durationInput.setCustomValidity('');
                }
            }
            function validateNumberOfQuestions() {
                var numberQuestionInput = document.getElementById('numberQuestion');
                var numberQuestionError = document.getElementById('numberQuestionError');
                var value = numberQuestionInput.value;

                // Check if the value is a positive integer
                if (!Number.isInteger(Number(value)) || value <= 0) {
                    numberQuestionError.style.display = 'block';
                    numberQuestionInput.setCustomValidity('Invalid field.');
                } else {
                    numberQuestionError.style.display = 'none';
                    numberQuestionInput.setCustomValidity('');
                }
            }
        </script>
    </body>
</html>