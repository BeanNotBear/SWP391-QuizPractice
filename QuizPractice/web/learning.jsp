<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Learning</title>
        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/popup.css">

        <style>
            .next {
                position: sticky;
                top: 10px;
            }

            .current {
                background-color: #fcdcd3;
                font-weight: bold;
                color: #000;
            }
            .locked {
                color: gray;
                pointer-events: none; /* Disable clicking */
            }
        </style>

        <!-- Bootstrap file link  -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="layout/header.jsp"%>
        <div class="side-bar">
            <h4 class="feature-subject">The lessons of subject</h4>
            <nav class="navbar">
                <c:forEach var="i" items="${requestScope.lessons}">
                    <a onclick="loadLesson('${i.lessonIndex}')" id="lesson_${i.lessonIndex}"
                       class="${i.lessonIndex > requestScope.currentIndex ? 'locked' : ''}">
                        <span>${i.lessonIndex}. ${i.name} 
                            <c:if test="${i.status == true}">
                                <i class="fa-solid fa-check"></i>
                            </c:if>
                            <c:if test="${i.lessonIndex > requestScope.currentIndex}">
                                <i class="fa-solid fa-lock"></i>
                            </c:if>
                        </span>
                    </a>
                </c:forEach>
            </nav>
        </div>
        <section style="width: 100%; overflow: hidden;" id="lessonContent">
            <!-- Placeholder for lesson content -->
        </section>

        <%@include file="layout/footer.jsp" %>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                // Cuộn đến bài học được chọn nếu có lớp selected
                var selectedDiv = document.querySelector('.selected');
                if (selectedDiv) {
                    selectedDiv.scrollIntoView({behavior: 'smooth'});
                }
                let index = '${requestScope.currentIndex}';
                // Gửi yêu cầu Ajax khi trang được tải
                $.ajax({
                    url: "loadLesson",
                    type: 'POST',
                    data: {
                        index: '${requestScope.currentIndex}'
                    },
                    success: function (data) {
                        let row = document.getElementById("lessonContent");
                        row.innerHTML = data;

                        var lessonLinks = document.querySelectorAll('.navbar a');
                        lessonLinks.forEach(function (link) {
                            link.classList.remove('selected');
                        });

                        var selectedLink = document.getElementById("lesson_" + index);
                        selectedLink.classList.add('selected');
                        selectedLink.classList.add('current');

                        // Cuộn đến bài học được chọn
                        selectedLink.scrollIntoView({behavior: 'smooth'});

                    }
                });
            });

            function loadLesson(index) {
                // Gửi yêu cầu Ajax khi người dùng chọn bài học
                $.ajax({
                    url: "loadLesson",
                    type: 'POST',
                    data: {
                        index: index
                    },
                    success: function (data) {
                        let row = document.getElementById("lessonContent");
                        row.innerHTML = data;

                        var lessonLinks = document.querySelectorAll('.navbar a');
                        lessonLinks.forEach(function (link) {
                            link.classList.remove('selected');
                            link.classList.remove('current');
                        });

                        var selectedLink = document.getElementById("lesson_" + index);
                        selectedLink.classList.add('selected');
                        selectedLink.classList.add('current');

                        // Cuộn đến bài học được chọn
                        selectedLink.scrollIntoView({behavior: 'smooth'});

                    }
                });
            }

            function markdone(userId, lessonId) {
                $.ajax({
                    url: "learning",
                    type: 'POST',
                    data: {
                        userId: userId,
                        lessonId: lessonId
                    }, success: function () {
                        window.location.reload();
                    }
                });
            }
        </script>
        <script src="js/script.js"></script>

    </body>
</html>
