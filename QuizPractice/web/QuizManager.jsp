<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quiz Manager</title>

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/popup.css">

        <!-- Bootstrap file link  -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <!-- Toastr CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">

        <!-- Toastr JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

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
            .quizList {
                margin-bottom: 10%;
            }
            nav {
                margin-top: -25px;
            }
        </style>

        <script>
            $(document).ready(function () {
                $('#searchForm').submit(function (event) {
                    event.preventDefault();
                    $.ajax({
                        url: 'quizManager',
                        type: 'POST',
                        data: $(this).serialize(),
                        success: function (response) {
                            $('#results').html($(response).find('#results').html());
                            $('#pagination').html($(response).find('#pagination').html());
                        }
                    });
                });

                $(document).on('click', '.edit-quiz', function () {
                    var quizId = $(this).data('id');
                    $.ajax({
                        url: 'getQuizById',
                        type: 'GET',
                        data: {id: quizId},
                        success: function (response) {
                            var quiz = JSON.parse(response);
                            $('#editQuizForm #quizId').val(quiz.id);
                            $('#editQuizForm #quizName').val(quiz.name);
                            $('#editQuizForm #subjectId').val(quiz.subjectId);
                            $('#editQuizForm #level').val(quiz.level);
                            $('#editQuizForm #numberQuestion').val(quiz.numberQuestion);
                            $('#editQuizForm #duration').val(quiz.duration);
                            $('#editQuizModal').modal('show');
                        }
                    });
                });

                $(document).on('click', '.delete-quiz', function () {
                    var quizId = $(this).data('id');
                    $.ajax({
                        url: 'deleteQuiz',
                        type: 'POST',
                        data: {id: quizId},
                        success: function (response) {
                            toastr.success("Successfully deleted quiz");
                            $('#searchForm').submit();
                        }
                    });
                });

                $('#saveQuiz').click(function () {
                    $.ajax({
                        url: 'editQuiz',
                        type: 'POST',
                        data: $('#editQuizForm').serialize(),
                        success: function (response) {
                            toastr.success("Edit successfully!");
                            $('#editQuizModal').modal('hide');
                            $('#searchForm').submit();
                        }
                    });
                });
            });
        </script>

    </head>

    <body>
        <%@include file="/layout/header.jsp"%>

        <section class="quizList">
            <h1 class="heading text-center">Quiz Manager</h1>

            <div id="topTable" class="container">
                <div class="row">
                    
                    <form id="searchForm" method="post">
                       <div class="col-md-3">
                            <select id="subjectId" name="subjectId" class="form-control">
                                <option value="">Select Subject</option>
                                <c:forEach var="subject" items="${subjects}">
                                    <option value="${subject.id}" ${subject.id == param.subjectId ? 'selected' : ''}>${subject.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                       
                        
                        <div class="col-md-2">
                            <input type="text" id="subjectName" name="subjectName" class="form-control" placeholder="Search by Subject Name" value="${param.subjectName}">
                        </div>
                        
                        <div class="col-md-2">
                            <input type="text" id="quizName" name="quizName" class="form-control" placeholder="Search by Quiz Name" value="${param.quizName}">
                        </div>
                        
                        <div class="col-md-2">
<!--                            <button type="submit" style="margin-bottom: 10px" class="btn btn-primary">Search</button>-->
                        <button type="submit" style="margin-bottom: 10px;padding-top: 0px;margin-top: 0px;border-top-width: 4px;" class="btn btn-primary">Search</button>
                        </div> 
                        <div class="col-md-2">
                            <button style="display: none" type="submit" class="btn btn-primary">Search</button>
                        </div>
                    </form>
                        
                    <div class="col-md-2 text-right">
                        <a href="AddQuiz" >Add New Quiz</a>
                    </div>
                </div>
            </div>

            <div class="container">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Subject</th>
                            <th>Level</th>
                            <th>#Questions</th>
                            <th>Duration</th>
                            <th>Type</th>>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="results">
                        <c:forEach var="quiz" items="${quizzes}">
                            <tr>
                                <td>${quiz.id}</td>
                                <td>${quiz.name}</td>
                                <td>${quiz.subjectName}</td>
                                <td>${quiz.level}</td>
                                <td>${quiz.numberQuestion}</td>
                                <td>${quiz.duration}</td>
                                <td>${quiz.type}</td>

                                <td class="row">
                                    <button class="btn btn-info  col-md-3" data-id="${quiz.id}"><a href="quizdetail?id=${quiz.id}">Edit</a></button>

                                    <span class="col-md-1"></span>
                                    <button class="btn btn-danger delete-quiz col-md-3" data-id="${quiz.id}">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Pagination -->
                <nav aria-label="Page navigation" id="pagination">
                    <ul class="pagination">
                        <c:choose>
                            <c:when test="${currentPage > 1}">
                                <li>
                                    <a href="quizManager?page=${currentPage - 1}&subjectName=${param.subjectName}&quizName=${param.quizName}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:when>
                        </c:choose>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <c:choose>
                                <c:when test="${i == currentPage}">
                                    <li class="active"><a href="#">${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li><a href="quizManager?page=${i}&subjectName=${param.subjectName}&quizName=${param.quizName}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        <c:choose>
                            <c:when test="${currentPage < totalPages}">
                                <li>
                                    <a href="quizManager?page=${currentPage + 1}&subjectName=${param.subjectName}&quizName=${param.quizName}" aria-label="Next">
                                        <span
                                            aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:when>
                        </c:choose>
                    </ul>
                </nav>
            </div>
        </section>
        <br/>

        <%@include file="/layout/footer.jsp"%>



        <!-- Add Quiz Modal -->
        <div class="modal fade" id="addQuizModal" tabindex="-1" role="dialog" aria-labelledby="addQuizModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="addQuizModalLabel">Add Quiz</h4>
                    </div>
                    <div class="modal-body">
                        <form id="addQuizForm" method="post" action="addQuiz">
                            <div class="form-group">
                                <label for="quizName">Quiz Name</label>
                                <input type="text" class="form-control" id="quizName" name="quizName" required>
                            </div>
                            <div class="form-group">
                                <label for="subjectId">Subject</label>
                                <select class="form-control" id="subjectId" name="subjectId" required>
                                    <c:forEach var="subject" items="${subjects}">
                                        <option value="${subject.id}">${subject.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="level">Level</label>
                                <input type="text" class="form-control" id="level" name="level" required>
                            </div>
                            <div class="form-group">
                                <label for="numberQuestion">Number of Questions</label>
                                <input type="number" class="form-control" id="numberQuestion" name="numberQuestion" required>
                            </div>
                            <div class="form-group">
                                <label for="duration">Duration (minutes)</label>
                                <input type="number" class="form-control" id="duration" name="duration" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Quiz</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- side bar có thể thu nhỏ khi màn hình nhỏ  -->
        <script src="js/script.js"></script>
    </body>
</html>