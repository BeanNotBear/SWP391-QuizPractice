<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Answer</title>

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
            <h1 class="heading text-center">Add New Answer</h1>
            <div class="container">
                <form id="addAnswerForm" action="AddAnswerServlet" method="post">
                    <input type="hidden" name="quizId" value="${param.quizId}">
                    <input type="hidden" name="questionId" value="${param.questionId}">
                    <input type="hidden" name="name" value="${param.name}">
                    <input type="hidden" name="level" value="${param.level}">
                    <input type="hidden" name="numberQuestion" value="${param.numberQuestion}">
                    <input type="hidden" name="duration" value="${param.duration}">
                    <input type="hidden" name="subject" value="${param.subject}">
                    <input type="hidden" name="lesson" value="${param.lesson}">
                    <input type="hidden" name="detail" value="${param.detail}">
                    <input type="hidden" name="suggestion" value="${param.suggestion}">

                    <div class="form-group">
                        <label for="detail">Answer Detail:</label>
                        <input type="text" class="form-control" id="detail" name="detail" required>
                    </div>
                    <div class="form-group">
                        <label for="isCorrect">Is Correct:</label>
                        <select class="form-control" id="isCorrect" name="isCorrect">
                            <option value="0">No</option>
                            <option value="1">Yes</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Answer</button>
                </form>
            </div>
        </section>
        <br/>

        <%@include file="/layout/footer.jsp" %>

        <!-- side bar có thể thu nhỏ khi màn hình nhỏ  -->
        <script src="js/script.js"></script>
        <script src="js/logout.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </body>
</html>