<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!--<meta http-equiv="X-UA-Compatible="IE=edge">-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quiz Done List</title>

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

        <script>
            $(document).ready(function () {
                $('#searchForm').submit(function (event) {
                    event.preventDefault();
                    var subjectName = $('#subjectName').val();
                    var quizName = $('#quizName').val();
                    $.ajax({
                        url: 'quizDoneList',
                        type: 'POST',
                        data: {subjectName: subjectName, quizName: quizName, ajax: 'true'},
                        success: function (response) {
                            $('#results').html(response);
                        }
                    });
                });
            });
        </script>
    </head>

    <body>
        <%@include file="/layout/header.jsp"%>

        <section class="practiceList">
            <h1 class="heading text-center">Quiz Done List</h1>

            <div id="topTable" class="container">
                <div class="row">
                    <!--<div class="col-md-3">-->
                    <form id="searchForm" method="post" action="quizDoneList">
                        <div class="form-group col-md-3">
                            <input type="text" id="subjectName" name="subjectName" class="form-control" placeholder="Search by Subject Name" value="${subjectName}">
                        </div>
                        <div class="form-group col-md-3">
                            <input type="text" id="quizName" name="quizName" class="form-control" placeholder="Search by Quiz Name" value="${quizName}">
                        </div>
                        <button style="display:none" type="submit" class="btn btn-primary">Search</button>
                    </form>
                    <!--</div>-->
                </div>
            </div>

            <div class="container">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Quiz Id</th>
                            <th>Subject</th>
                            <th>Quiz Name</th>
                            <th>Level</th>
                            <th>#Question</th>
                            <th>Duration</th>
                            <th>Score</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="results">
                        <c:forEach var="quiz" items="${doneQuizzes}">
                            <tr>
                                <td>${quiz.id}</td>
                                <td>${quiz.subjectName}</td>
                                <td>${quiz.quizName}</td>
                                <td>${quiz.level}</td>
                                <td>${quiz.numberQuestion}</td>
                                <td>${quiz.duration}</td>
                                <td><fmt:formatNumber value="${quiz.score}" pattern="0.##"/>%</td>
<!--                                <td><a href="quizReview?practiceId=${quiz.id}" class="btn btn-info btn-sm">View</a></td>-->
                                <!--                                <td><a href="quizReview?practiceId=1002" class="btn btn-info btn-sm">View</a></td>-->
                                <td><a href="quizReview?quizId=${quiz.stqId}" class="btn btn-info btn-sm">View</a>

                                   <a href="startNewQuiz?stqId=${quiz.stqId}" class="btn btn-success btn-sm">Start</a>
<!--                                     <a href="quizHandle?practiceId=1002" class="btn btn-success btn-sm">Start</a>-->
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Pagination -->
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:choose>
                            <c:when test="${currentPage > 1}">
                                <li>
                                    <a href="quizDoneList?page=${currentPage - 1}" aria-label="Previous">
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
                                    <li><a href="quizDoneList?page=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        <c:choose>
                            <c:when test="${currentPage < totalPages}">
                                <li>
                                    <a href="quizDoneList?page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
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
        <script src="js/logout.js"></script>

        <!-- side bar có thể thu nhỏ khi màn hình nhỏ  -->
        <script src="js/script.js"></script>
    </body>
</html>