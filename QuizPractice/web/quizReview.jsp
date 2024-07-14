
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Quiz Review</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/popup.css">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


        <style>
            body {
                padding: 0;
                margin: 0;
            }
            .footer {
                background: #f8f9fa;
                text-align: center;
                /*           // position: fixed;*/
                bottom: 0;
                width: 100%;
                height: 15%;
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
            .alert {
                margin-bottom: 10px;
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
            #all{
                margin-bottom: 20px;
            }
        </style>
    </head>
<body>
    <%@ include file="/layout/header.jsp" %>

    <div class="container" id="all">
        <br/>
        <h2 class="text-center pt-3">Quiz Review - ${subjectName}</h2>
        <div class="question-info row">
            <h3 class="col-md-3">Lesson: ${lessonName}</h3>
            <div class="col-md-6"></div>
            <h4 class="col-md-3 ">Question ${currentQuestion} of ${totalQuestions}</h4>
            <h4 class="col-md-2 text-primary"> ${result} point</h4>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">Question Detail</div>
            <div class="panel-body">
                <p>${questionDetail}</p>
                <c:forEach var="answer" items="${questions[currentQuestion-1].answers}">
                    <div class="alert ${answer.correct ? 'alert-success' : 'alert-default'}">
                        <label>
                            <input type="radio" name="answer" value="${answer.id}" disabled ${answer.id == questions[currentQuestion-1].yourAnswer ? 'checked' : ''}>
                            ${answer.answerDetail}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="question-nav row">
            <c:if test="${currentQuestion > 1}">
                <a href="quizReview?practiceId=${practiceId}&questionNumber=${currentQuestion - 1}&quizId=${stqId}" class="btn btn-primary col-md-2">Previous Question</a>
            </c:if>
            <div class="col-md-1"></div>
            <c:if test="${currentQuestion < totalQuestions}">
                <a href="quizReview?practiceId=${practiceId}&questionNumber=${currentQuestion + 1}&quizId=${stqId}" class="btn btn-primary col-md-2">Next Question</a>
            </c:if>
            <div class="col-md-1"></div>
            <div class="col-md-2">
                <button class="btn btn-info btn-block" onclick="peekAnswer(${practiceId}, ${currentQuestion}, ${stqId})">Explain</button>
            </div>
            <div class="col-md-1"></div>
            <div class="col-md-2">
                <button class="btn btn-primary btn-danger" onclick="viewProgress()">Review Progress</button>
            </div>
        </div>
        <br/>
    </div>

    <form id="quizForm" method="post" action="QuizReview">
        <input type="hidden" name="practiceId" id="practiceId" value="${param.practiceId}">
        <input type="hidden" name="questionId" id="questionId" value="${questionId}">
        <input type="hidden" name="questionNumber" id="questionNumber" value="${currentQuestion}">
        <input type="hidden" name="totalQuestions" id="totalQuestions" value="${totalQuestions}">
        <input type="hidden" name="action" id="action" value="">
    </form>

    <%@ include file="/layout/footer.jsp" %>

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
                            <button type="button" class="btn btn-secondary" onclick="filterQuestions('all')">All Questions</button>
                            <button type="button" class="btn btn-secondary" onclick="filterQuestions('answered')">Answered</button>
                            <button type="button" class="btn btn-secondary" onclick="filterQuestions('unanswered')">Unanswered</button>
                            <button type="button" class="btn btn-secondary" onclick="filterQuestions('marked')">Marked</button>
                        </div>
                    </div>
                    <hr>
                    <div id="questionsList"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="peekAnswerModal" tabindex="-1" role="dialog" aria-labelledby="peekAnswerModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="peekAnswerModalLabel">Explain</h4>
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

    <script src="js/script.js"></script>
    <script>
        function navigateQuestion(practiceId, questionNumber, action) {
            const url = new URL(window.location.href);
            url.searchParams.set('practiceId', practiceId);
            url.searchParams.set('currentQuestion', questionNumber);
            url.searchParams.set('action', action);
            window.location.href = url.toString();
        }

        function filterQuestions(type) {
            fetch(`FilterQuestions?practiceId=${practiceId}&stqId=${stqId}&type=` + type)
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

                const link = document.createElement('a');
                link.href = `quizReview?practiceId=${practiceId}&questionNumber=` + question.questionNumber+"&quizId=${stqId}";
                link.className = 'btn btn-primary col-md-12';
                link.textContent = question.questionNumber;

                questionItem.appendChild(link);
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
                type: "GET",
                url: "ReviewProgress",
                data: {
                    practiceId: ${practiceId},
                    stqId: ${stqId}
                },
                success: function (response) {
                    displayQuestions(response);
                    $('#reviewProgressModal').modal('show');
                },
                error: function () {
                    console.log("Error saving progress.");
                }
            });
        }

        function peekAnswer(practiceId, questionNumber, stqId) {
            $.ajax({
                type: "GET",
                url: "PeekAnswer",
                data: {
                    practiceId: practiceId,
                    questionNumber: questionNumber,
                    stqId: stqId
                },
                success: function (response) {
                    $('#peekAnswer').text(response.answerDetail);
                    $('#peekSuggestion').text(response.suggestion);
                    $('#peekAnswerModal').modal('show');
                },
                error: function () {
                    console.log("Error peeking answer.");
                }
            });
        }

        $(document).ready(function () {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.get('finished') === 'true') {
                alert('Quiz has finished');
            }
        });
    </script>
    <script src="js/logout.js"></script>
</body>
</html>
