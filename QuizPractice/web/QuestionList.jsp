<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!--<meta http-equiv="X-UA-Compatible="IE=edge">-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Question List</title>

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
            .questionList {
                margin-bottom: 10%;
            }
            nav {
                margin-top: -25px;
            }
        </style>

        <script>
            $(document).ready(function () {
                function fetchLessons(subjectId, targetSelect) {
                    $.ajax({
                        url: 'fetchLessons',
                        type: 'GET',
                        data: {subjectId: subjectId},
                        success: function (response) {
                            $(targetSelect).html(response);
                        },
                        error: function (xhr, status, error) {
                            console.error("AJAX Error: ", status, error);  // Debug: Log any AJAX errors
                        }
                    });
                }

                $('#subjectId').change(function () {
                    fetchLessons($(this).val(), '#lessonId');
                });

                $('#popupSubjectId').change(function () {
                    fetchLessons($(this).val(), '#popupLessonId');
                });

                // Fetch lessons on page load if subject is selected
                if ($('#subjectId').val() !== "") {
                    fetchLessons($('#subjectId').val(), '#lessonId');
                }
                if ($('#popupSubjectId').val() !== "") {
                    fetchLessons($('#popupSubjectId').val(), '#popupLessonId');
                }

                $('#searchForm').submit(function (event) {
                    event.preventDefault();
                    $.ajax({
                        url: 'questionList',
                        type: 'POST',
                        data: $(this).serialize(),
                        success: function (response) {
                            $('#results').html($(response).find('#results').html());
                            $('#pagination').html($(response).find('#pagination').html());
                        }
                    });
                });

                $(document).on('click', '.edit-question', function () {
                    var questionId = $(this).data('id');
                    $.ajax({
                        url: 'getQuestionById',
                        type: 'GET',
                        data: {id: questionId},
                        success: function (response) {
                            $('#editQuestionModal .modal-body').html(response);
                            $('#editQuestionModal').modal('show');
                        }
                    });
                });

                $(document).on('click', '.update-status', function () {
                    var questionId = $(this).data('id');
                    var newStatus = $(this).data('status');
                    $.ajax({
                        url: 'toggleQuestionStatus',
                        type: 'POST',
                        data: {id: questionId, status: newStatus},
                        success: function (response) {
                            toastr.success("Successfully change status");
                            $('#searchForm').submit();
                        }
                    });
                });

                $('#saveQuestion').click(function () {
                    $.ajax({
                        url: 'saveQuestion',
                        type: 'POST',
                        data: $('#editQuestionForm').serialize(),
                        success: function (response) {
                            toastr.success(response.message);
                            $('#editQuestionModal').modal('hide');
                            $('#searchForm').submit();
                        }
                    });
                });

                $('#importQuestionForm').submit(function (event) {
                    event.preventDefault();

                    var formData = new FormData(this);

                    $.ajax({
                        url: 'importQuestions',
                        type: 'POST',
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function (response) {
                            toastr.success("Questions imported successfully");
                            $('#addQuestionModal').modal('hide');
                            $('#searchForm').submit();
                        },
                        error: function (xhr, status, error) {
                            toastr.error("Error importing questions: " + xhr.responseText);
                        }
                    });
                });
            });


        </script>
    </head>

    <body>
        <%@include file="/layout/header.jsp"%>

        <section class="questionList">
            <h1 class="heading text-center">Question List</h1>

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
                        <div class="col-md-3">
                            <select id="lessonId" name="lessonId" class="form-control">
                                <option value="">Select Lesson</option>
                                <c:forEach var="lesson" items="${lessons}">
                                    <option value="${lesson.id}" ${lesson.id == param.lessonId ? 'selected' : ''}>${lesson.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <select id="status" name="status" class="form-control">
                                <option value="">Select Status</option>
                                <option value="1" ${param.status == '1' ? 'selected' : ''}>Active</option>
                                <option value="0" ${param.status == '0' ? 'selected' : ''}>Deactive</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <input type="text" id="detail" name="detail" class="form-control" placeholder="Search by Detail" value="${param.detail}">
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-primary">Search</button>
                        </div>
                    </form>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#addQuestionModal">Add Question</a>
                    </div>
                </div>
            </div>

            <div class="container">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Content</th>
                            <th>Suggestion</th>
                            <th>Subject</th>
                            <th>Lesson</th>
                            <th>Status</th>
                            <th>Action</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody id="results">
                        <c:forEach var="question" items="${questions}">
                            <tr>
                                <td>${question.id}</td>
                                <td>${question.detail}</td>
                                <td>${question.suggestion}</td>
                                <td>${question.subjectName}</td>
                                <td>${question.lessonName}</td>
                                <td class="${question.status == 1 ? 'status-active' : 'status-deactive'}">
                                    ${question.status == 1 ? 'Active' : 'Deactive'}
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${question.status == 1}">
                                            <button class="btn btn-danger update-status" data-id="${question.id}" data-status="0">Deactivate</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-success update-status" data-id="${question.id}" data-status="1">Activate</button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <button class="btn btn-info edit-question" data-id="${question.id}">Edit</button>
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
                                    <a href="questionList?page=${currentPage - 1}" aria-label="Previous">
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
                                    <li><a href="questionList?page=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        <c:choose>
                            <c:when test="${currentPage < totalPages}">
                                <li>
                                    <a href="questionList?page=${currentPage + 1}" aria-label="Next">
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

        <!-- Edit Question Modal -->
        <div class="modal fade" id="editQuestionModal" tabindex="-1" role="dialog" aria-labelledby="editQuestionModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="editQuestionModalLabel">Edit Question</h4>
                    </div>
                    <div class="modal-body">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="saveQuestion">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Add Question Modal -->
        <div class="modal fade" id="addQuestionModal" tabindex="-1" role="dialog" aria-labelledby="addQuestionModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="addQuestionModalLabel">Import Questions</h4>
                    </div>
                    <div class="modal-body">
                        <form id="importQuestionForm" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="popupSubjectId">Subject</label>
                                <select class="form-control" id="popupSubjectId" name="subjectId" required>
                                    <c:forEach var="subject" items="${subjects}">
                                        <option value="${subject.id}">${subject.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="popupLessonId">Lesson</label>
                                <select class="form-control" id="popupLessonId" name="lessonId" required>
                                    <option value="">Select Lesson</option>
                                    <!-- Lessons will be dynamically loaded here -->
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="file">Import File</label>
                                <input type="file" class="form-control" id="file" name="file" accept=".xlsx" required>
                            </div>
                            <div class="form-group">
                                <a href="./excel/test.xlsx" class="btn btn-link" download>Download Sample Template</a>
                            </div>
                            <button type="submit" class="btn btn-primary">Import Questions</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>




        <!-- side bar có thể thu nhỏ khi màn hình nhỏ  -->
        <script src="js/script.js"></script>
        <script src="js/logout.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </body>
</html>