
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Map" %> <!-- Import Map class -->
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Practice Quiz</title>

        <!-- font awesome cdn link -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/popup.css">

        <!-- Bootstrap file link -->
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
            .quizHandle {
                margin-bottom: 10%;
            }
            nav {
                margin-top: -25px;
            }
            .header-section {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
            .lesson-name {
                font-size: 1.5em;
                font-weight: bold;
            }
            .question-info {
                display: flex;
                align-items: center;
            }
            .question-info span {
                margin-right: 20px;
                font-size: 1.2em;
            }
            .timer {
                display: flex;
                align-items: center;
            }
            .timer i {
                margin-right: 5px;
            }
            .timer p {
                font-size: 1.2em;
                margin: 0;
            }
            .left-section {
                border-right: 2px solid #ddd;
                padding-right: 20px;
                margin-right: 20px;
            }
            .btn-link {
                background: none;
                border: none;
                color: #337ab7;
                text-decoration: underline;
                cursor: pointer;
            }
            .btn-link:hover, .btn-link:focus {
                color: #23527c;
                text-decoration: underline;
                background-color: transparent;
            }
            .text-center button {
                display: inline;
                margin-right: 10px;
            }
            .modal-body .btn-group {
                display: flex;
                justify-content: space-between;
                width: 100%;
            }
            .modal-body .btn-group .btn {
                flex: 1;
                margin-right: 10px;
            }
            .modal-body .btn-group .btn:last-child {
                margin-right: 0;
            }
            .question-row {
                display: flex;
                flex-wrap: wrap;
                margin-bottom: 10px;
            }
            .question-btn {
                flex: 1 1 20%;
                margin: 5px;
                padding: 10px;
                border: 2px solid #337ab7;
                text-align: center;
                cursor: pointer;
            }

            .btn-white-border {
                background-color: #fff !important;
                border: 1px solid #ccc !important;
                color: black !important;
            }

            .icon-container {
                display: inline-block;
                width: 16px;
                height: 20px;
                margin-right: 5px;
            }

            .icon-answered {
                border: 1px solid #ccc;
                background-color: #ddd;
            }

            .icon-unanswered {
                border: 1px solid #ccc; /* border for visibility */
            }

            .icon-marked {
                color: red; /* red color for the flag icon */
            }
        </style>
    </head>
    <body>
        <%@include file="/layout/header.jsp"%>

        <section class="quizHandle">
            <h1 class="text-center">Practice Question - <c:out value="${subjectName}" /></h1>
            <div class="container">
                <div class="header-section">
                    <div class="lesson-name">
                        Lesson: <c:out value="${lessonName}" />
                    </div>
                    <div class="question-info">
                        <span>Question <c:out value="${currentQuestion}" /> / <c:out value="${totalQuestions}" /></span>
                        <div class="timer">
                            <i class="fa fa-clock-o"></i>
                            <p id="timer">00:00:00</p>
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-md-2 left-section">
                        <h3>Question <c:out value="${currentQuestion}" /></h3>
                        <button class="btn btn-warning btn-block" onclick="markForReview(${param.practiceId}, ${questionId})">Mark for Review</button>
                        <button class="btn btn-info btn-block" onclick="peekAnswer(${param.practiceId}, ${currentQuestion})">Peek an Answer</button>
                        <button class="btn btn-primary btn-block" onclick="viewProgress()">Review Progress</button>
                    </div>

                    <div class="col-md-8">
                        <div>
                            <p><c:out value="${questionDetail}" /></p>
                        </div>
                        <div>
                            <h4>Select one:</h4>
                            <form id="quizForm" method="post" action="QuizHandle">
                                <c:forEach var="answer" items="${answers}">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="answer" value="${answer.id}" <c:if test="${answer.id == selectedAnswer}">checked</c:if> onchange="saveAnswer(${answer.id})">
                                            <c:out value="${answer.answerDetail}" />
                                        </label>
                                    </div>
                                </c:forEach>
                                <input type="hidden" name="practiceId" id="practiceId" value="${param.practiceId}">
                                <input type="hidden" name="questionId" id="questionId" value="${questionId}">
                                <input type="hidden" name="questionNumber" id="questionNumber" value="${currentQuestion}">
                                <input type="hidden" name="totalQuestions" id="totalQuestions" value="${totalQuestions}">
                                <input type="hidden" name="action" id="action" value="">
                            </form>
                        </div>

                        <div class="text-center">
                            <c:choose>
                                <c:when test="${currentQuestion > 1}">
                                    <button class="btn-link" onclick="navigateQuestion(${param.practiceId}, ${currentQuestion - 1}, 'prev')">Previous Question</button>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${currentQuestion < totalQuestions}">
                                    <button class="btn-link" onclick="navigateQuestion(${param.practiceId}, ${currentQuestion + 1}, 'next')">Next Question</button>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <hr/>
            </div>
        </section>
        <br/>

        <%@include file="/layout/footer.jsp"%>

        <!-- Peek Answer Modal -->
        <div class="modal fade" id="peekAnswerModal" tabindex="-1" role="dialog" aria-labelledby="peekAnswerModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="peekAnswerModalLabel">Peek an Answer</h4>
                    </div>
                    <div class="modal-body">
                        <h5>Answer:</h5>
                        <p id="peekAnswer"></p>
                        <h5>Suggestion:</h5>
                        <p id="peekSuggestion"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Review Progress Modal -->
        <div class="modal fade" id="reviewProgressModal" tabindex="-1" role="dialog" aria-labelledby="reviewProgressModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="reviewProgressModalLabel">Review Progress</h4>
                    </div>
                    <div class="modal-body">
                        <div class="btn-group" role="group">
                            <div style="display: flex; width: 100%;">
                                <% 
     int totalQuestions = (int) request.getAttribute("totalQuestions"); 
     Map<Integer, Integer> userAnswers = (Map<Integer, Integer>) session.getAttribute("userAnswers");
     int answeredCount = userAnswers != null ? userAnswers.size() : 1;
     int markedCount = session.getAttribute("markedQuestions") != null ? ((Map<Integer, Boolean>) session.getAttribute("markedQuestions")).size() : 1;
     int unansweredCount = totalQuestions - answeredCount; 
                                %>
                                <button type="button" class="btn btn-secondary btn-white-border" onclick="filterQuestions('all')">All Questions</button>
                                <button type="button" class="btn btn-secondary btn-white-border" onclick="filterQuestions('answered')">
                                    <span class="icon-container icon-answered"></span>Answered(<%= answeredCount %>)
                                </button>
                                <button type="button" class="btn btn-secondary btn-white-border" onclick="filterQuestions('unanswered')">
                                    <span class="icon-container icon-unanswered"></span>Unanswered(<%= unansweredCount %>)
                                </button>
                                <button type="button" class="btn btn-secondary btn-white-border" onclick="filterQuestions('marked')">
                                    <i class="fa fa-flag icon-container icon-marked"></i>Marked
                                </button>
                                <button class="btn btn-success" style="flex: 1;" onclick="submitQuiz()">Submit</button>
                            </div>
                        </div>

                        <hr>
                        <div id="questionsList">

                        </div>
                    </div>
                </div>
            </div>
        </div>




        <!-- Submit Confirmation Modal -->
        <div class="modal fade" id="submitConfirmModal" tabindex="-1" role="dialog" aria-labelledby="submitConfirmModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="submitConfirmModalLabel">Submit Confirmation</h4>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to exit the exam?</p>
                        <p id="questionStatus"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="confirmSubmit()">Confirm</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- side bar có th? thu nh? khi màn hình nh? -->
        <script src="js/script.js"></script>
        <script>
                            let totalTime = ${remainingTime}; // Remaining time in seconds from the controller
                            const timerElement = document.getElementById('timer');
                            console.log("fdsafsdafsd"+totalTime);
                            function startTimer(duration, display) {
                                let timer = duration, hours, minutes, seconds;
                                setInterval(function () {
                                    hours = parseInt(timer / 3600, 10);
                                    minutes = parseInt((timer % 3600) / 60, 10);
                                    seconds = parseInt(timer % 60, 10);

                                    hours = hours < 10 ? "0" + hours : hours;
                                    minutes = minutes < 10 ? "0" + minutes : minutes;
                                    seconds = seconds < 10 ? "0" + seconds : seconds;

                                    display.textContent = hours + ":" + minutes + ":" + seconds;

                                    if (--timer < 0) {
                                        timer = 0;
                                        submitQuiz(); // Automatically submit the quiz when time is up
                                    }
                                }, 1000);
                            }

                            window.onload = function () {
                                startTimer(totalTime, timerElement);
                            };


                            function navigateQuestion(practiceId, questionNumber, action) {
                                const form = document.getElementById('quizForm');
                                const actionInput = document.getElementById('action');

                                // Set giá tr? cho action
                                actionInput.value = action;

                                // T?o URL m?i v?i các tham s?
                                const url = new URL(window.location.href);
                                url.searchParams.set('practiceId', practiceId);
                                url.searchParams.set('questionNumber', questionNumber);
                                url.searchParams.set('action', action);

                                // ?i?u h??ng ??n URL m?i
                                window.location.href = url.toString();
                            }

                            function markForReview(practiceId, questionId) {
                                $.ajax({
                                    type: "POST",
                                    url: "MarkForReview",
                                    data: {
                                        practiceId: practiceId,
                                        questionId: questionId
                                    },
                                    success: function () {
                                        console.log("Question marked for review.");
                                    },
                                    error: function () {
                                        console.log("Error marking question for review.");
                                    }
                                });
                            }

                            function peekAnswer(practiceId, questionNumber) {
                                $.ajax({
                                    type: "GET",
                                    url: "PeekAnswer",
                                    data: {
                                        practiceId: practiceId,
                                        questionNumber: questionNumber
                                    },
                                    success: function (response) {
                                        $('#peekAnswer').text(response.answerDetail);
                                        $('#peekSuggestion').text(response.suggestion);
                                        $('#peekAnswerModal').modal('show');
                                    },
                                    error: function (error) {
                                        console.log("Error peeking answer.");
                                    }
                                });
                            }

                            function filterQuestions(type) {
                                fetch(`FilterQuestions?practiceId=${practiceId}&type=` + type)
                                        .then(response => response.json())
                                        .then(data => displayQuestions(data));
                            }

                            function displayQuestions(questions) {
                                const questionsList = document.getElementById('questionsList');
                                questionsList.innerHTML = '';
                                let row = document.createElement('div');
                                row.classList.add('question-row');
                                questions.forEach((question, index) => {
                                    const questionItem = document.createElement('div');
                                    questionItem.classList.add('question-btn');
                                    questionItem.textContent = question.questionNumber;
                                    questionItem.onclick = () => navigateQuestion(${param.practiceId}, question.questionNumber);
                                    row.appendChild(questionItem);
                                    if ((index + 1) % 5 === 0) {
                                        questionsList.appendChild(row);
                                        row = document.createElement('div');
                                        row.classList.add('question-row');
                                    }
                                });
                                if (row.children.length > 0) {
                                    questionsList.appendChild(row);
                                }
                            }

                            function viewProgress() {
                                $.ajax({
                                    type: "POST",
                                    url: "QuizHandle",
                                    data: {
                                        practiceId: $('#practiceId').val(),
                                        questionNumber: $('#questionNumber').val(),
                                        action: 'viewProgress'
                                    },
                                    success: function () {
                                        $('#reviewProgressModal').modal('show');
                                    },
                                    error: function () {
                                        console.log("Error saving progress.");
                                    }
                                });
                            }

                            function submitQuiz() {
                                // Count answered questions
                                const answeredQuestions = [...document.querySelectorAll('input[name="answer"]:checked')].length;
                                const totalQuestions = parseInt(document.getElementById('totalQuestions').value);

                                if (answeredQuestions < totalQuestions) {
                                    // Show confirmation modal if not all questions are answered
                                    document.getElementById('questionStatus').textContent = answeredQuestions + `/ ${totalQuestions} questions answered`;
                                    $('#submitConfirmModal').modal('show');
                                } else {
                                    // Submit quiz directly if all questions are answered
                                    document.getElementById('action').value = 'submit';
                                    document.getElementById('quizForm').submit();
                                }
                            }

                            function confirmSubmit() {
                                document.getElementById('action').value = 'submit';
                                document.getElementById('quizForm').submit();
                            }

                            function saveAnswer(answerId) {
                                const practiceId = $('#practiceId').val();
                                const questionNumber = $('#questionNumber').val();

                                $.ajax({
                                    type: "POST",
                                    url: "SaveAnswer",
                                    data: {
                                        practiceId: practiceId,
                                        questionNumber: questionNumber,
                                        answerId: answerId
                                    },
                                    success: function () {
                                        console.log("Answer saved successfully.");
                                    },
                                    error: function () {
                                        console.log("Error saving answer.");
                                    }
                                });
                            }
        </script>
        <script src="js/logout.js"></script>
    </body>
</html>