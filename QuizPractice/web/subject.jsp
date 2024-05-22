<%-- 
    Document   : subject
    Created on : May 20, 2024, 2:15:14 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>subjects</title>

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <style>
            .pagination {
                justify-content: center; /* Căn giữa các nút phân trang */
                text-align: center;
            }

            .page-item .page-link {
                font-size: 1.25rem; /* Tăng kích thước chữ của các nút phân trang */
                padding: 0.75rem 1.25rem; /* Tăng kích thước padding của các nút phân trang */
            }

            .page-item.active .page-link {
                background-color: #007bff; /* Màu nền cho nút đang hoạt động */
                border-color: #007bff; /* Màu viền cho nút đang hoạt động */
            }
        </style>
    </head>
    <body>

        <header class="header">

            <section class="flex">

                <a href="home" class="logo">Educa.</a>


                <form action="subject" method="get" class="search-form">
                    <input type="text" name="search"  placeholder="search subjects..." maxlength="100" value="${param.search}">
                    <input type="hidden" name="page" value="${currentPage}">
                    <button type="submit" class="fas fa-search"></button>
                </form>

                <div class="icons">
                    <div id="menu-btn" class="fas fa-bars"></div>
                    <div id="search-btn" class="fas fa-search"></div>
                    <div id="user-btn" class="fas fa-user"></div>
                    <div id="toggle-btn" class="fas fa-sun"></div>
                </div>

                <div class="profile">
                    <img src="images/pic-1.jpg" class="image" alt="">
                    <h3 class="name">shaikh anas</h3>
                    <p class="role">student</p>
                    <a href="profile.html" class="btn">view profile</a>
                    <div class="flex-btn">
                        <a href="/QuizPractice/login" class="option-btn">login</a>
                        <a href="/QuizPractice/register" class="option-btn">register</a>
                    </div>
                </div>

            </section>

        </header> 

        <%@include file="layout/sidebar.jsp" %>

        <section class="subjects">

            <h1 class="heading">our subjects</h1>

            <div class="box-container">
                <c:forEach items="${listC}" var="c">
                    <div class="box">
                        <div class="tutor">
                            <img src="images/pic-2.jpg" alt="">
                            <div class="info">
                                <h3>${c.creator.firstName} ${c.creator.lastName}</h3>
                                <span>${c.create_at}</span>
                            </div>
                        </div>
                        <div class="thumb">
                            <img src="${c.img}" alt="">
                            <span>${c.numberOfLesson} lesson</span>
                        </div>
                        <h3 class="title">${c.name}</h3>
                        <a href="playlist.html" class="inline-btn">view playlist</a>
                    </div>
                </c:forEach>



            </div>
        </div>

    </section>
    <nav class="mt-3" style="" aria-label="Page navigation example">
        <ul class="pagination">
            <!-- Disable the "Previous" link if on the first page -->
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;sort=${param.sort}&amp;color=${param.color}&amp;size=${param.size}&amp;page=${currentPage - 1}">Previous</a>
                </li>
            </c:if>

            <!-- Display the page numbers as links -->
            <c:forEach var="pageNum" begin="1" end="${totalPages}">
                <li class="page-item <c:if test='${pageNum == currentPage}'>active</c:if>">
                    <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;sort=${param.sort}&amp;color=${param.color}&amp;size=${param.size}&amp;page=${pageNum}">${pageNum}</a>
                </li>
            </c:forEach>

            <!-- Disable the "Next" link if on the last page -->
            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;sort=${param.sort}&amp;color=${param.color}&amp;size=${param.size}&amp;page=${currentPage + 1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
    <%@include file="layout/footer.jsp" %>

    <!-- custom js file link  -->
    <script src="js/script.js"></script>

</body>
</html>
