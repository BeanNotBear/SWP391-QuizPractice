<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>App</title>

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
            /*            body {
                            padding: 0;
                            margin: 0;
                        }*/
            .subjectList {
                margin-bottom: 50px;
            }
            #searchByDimension, #searchByStatus {
                margin-left: 5%;
                padding: 5% 10%;
            }
            .heading {
                text-align: center;
            }
            .side-bar {
                padding-top: 6%;
                z-index: 1; /* Giá trị z-index thấp hơn để bị đè */
            }
            nav{
                margin-top: -25px;
            }

            #newSubject{
                text-decoration: underline;
                margin-bottom: 20px;
            }


        </style>
    </head>

    <body>
        <%@include file="/layout/header.jsp"%>
        <%@include file="/layout/searchSubjectList.jsp"%>

        <section class="subjectList">
            <h1 class="heading text-center">Subject list</h1>
            <div class="container">
                <c:if test="${sessionScope.user.roleId == 2}">
                    <a href="newSubject" id="newSubject">New Subject</a>
                </c:if>
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Thumbnail</th>
                            <th>Owner(Expert)</th>
                            <th>Category</th>
                            <th>Number of Lessons</th>                         
                            <th>Status</th>
                            <th>
                                <!--expert-->
                                <c:if test="${sessionScope.user.roleId == 3}">
                                    Detail 
                                </c:if>
                                <!--admin-->
                                <c:if test="${sessionScope.user.roleId == 2}">
                                    Action 
                                </c:if>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <!--admin-->
                        <c:if test="${sessionScope.user.roleId == 2}">
                            <c:forEach var="item" items="${listSubject}">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td style="width: 20%"><img width="100%" src="${item.thumbnail}" alt="alt"/></td>
                                    <td>${item.owner}</td>
                                    <td>${item.dimensionName}</td>
                                    <td>${item.numberOfLesson}</td>
                                    <td>
                                        <a onclick="changeStatus(this, ${item.id})" 
                                           <c:if test="${item.status == 1}">class="btn btn-info btn-sm"</c:if>
                                           <c:if test="${item.status == 2}">class="btn btn-danger btn-sm"</c:if>
                                           ><c:if test="${item.status == 1}">Published</c:if><c:if test="${item.status == 2}">Unpublished</c:if></a>
                                        </td>

                                            <td><a href="subjectDetailExpert?id=${item.id}" class="btn btn-info btn-sm">Detail</a></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <!--expert-->
                        <c:if test="${sessionScope.user.roleId == 3}">
                            <c:forEach var="item" items="${listSubject}">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td style="width: 20%"><img width="100%" src="${item.thumbnail}" alt="alt"/></td>
                                    <td>${item.owner}</td>
                                    <td>${item.dimensionName}</td>
                                    <td>${item.numberOfLesson}</td>
                                    <td>
                                        <a
                                            <c:if test="${item.status == 1}">class="btn btn-info btn-sm"</c:if>
                                            <c:if test="${item.status == 2}">class="btn btn-danger btn-sm"</c:if>
                                            ><c:if test="${item.status == 1}">Published</c:if><c:if test="${item.status == 2}">Unpublished</c:if></a>
                                        </td>

                                            <td><a href="subjectDetailExpert?id=${item.id}" class="btn btn-info btn-sm">Detail</a></td>
                                </tr>
                            </c:forEach>
                        </c:if>

                    </tbody>
                </table>
                <!-- Pagination -->
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:choose>
                            <c:when test="${currentPage > 1}">
                                <li>
                                    <a href="subjectManager?page=${currentPage - 1}" aria-label="Previous">
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
                                    <li><a href="subjectManager?page=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        <c:choose>
                            <c:when test="${currentPage < totalPages}">
                                <li>
                                    <a href="subjectManager?page=${currentPage + 1}" aria-label="Next">
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

        <%@include file="/layout/script.jsp" %>

        <!--change status of subject-->
        <script src="js/ChangeStatusOfSubject.js"></script>
    </body>
</html>