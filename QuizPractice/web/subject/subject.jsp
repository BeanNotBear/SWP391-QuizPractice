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
    </head>
    <body>

        <%@include file="../layout/header.jsp" %> 
        <%@include file="../layout/sidebar.jsp" %>

        <section class="courses">
            <h1 class="heading">our subjects</h1>

            <div class="box-container">
                <c:forEach items="${listC}" var="c">
                    <div class="box">
                        <div class="tutor">
                            <img src="${c.creator.profileImg}" alt="">
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
                        <a href="subject/details?id=${c.id}" class="inline-btn">Details</a>
                        <a href="subject/register?id=${c.id}" class="inline-btn">Register Now</a>
                    </div>
                </c:forEach>
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
        <%@include file="../layout/footer.jsp" %>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>

    </body>
</html>
