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
            html, body {
                height: 100%;
                margin: 0;
                display: flex;
                flex-direction: column;
            }
            .footer {
                width: 100%;
                background-color: var(--white);
                color: var(--main-color);
                padding: 10px;
                text-align: center;
                font-family: Arial, sans-serif;
                border-top: 1px solid;
            }
            .customer {
                display: flex;
            }
            .customer > div {
                margin-left: 20px;
            }
        </style>
    </head>

    <body>
        <%@include file="/layout/header.jsp"%>
        <%@include file="/layout/sidebar.jsp" %>

        <section class="practiceList">
            <h1 class="heading text-center">Register List</h1>

            <div id="topTable" class="container">
                <div class="row">
                    <div class="col-md-3">

                    </div>
                    <div class="col-md-3">

                    </div>
                    <div class="col-md-5"></div>
                    <form class="col-md-2">
                        <input placeholder="Search" class="search-input" style="margin-left: 939px; margin-bottom: 10px; width: 200px;" type="text" name="search">
                        <input type="hidden" name="page" value="${currentPage}">
                        <input type="submit" hidden>
                    </form>
                </div>
            </div>

            <div class="container">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Customer</th>
                            <th>Subject</th>
                            <th>Price Package</th>
                            <th>Duration</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="registrationContent">
                        <!-- Assume that 'practices' is a list of practice objects passed from the back-end -->
                        <c:forEach var="i" items="${listOfRegistartion}">
                            <tr class="table-row">
                                <td style="width: 350px;">
                                    <div class="customer">
                                        <img src="${i.img}" alt="alt"/>
                                        <div>
                                            <input class="user-id" type="hidden" value="${i.userId}">
                                            <div>Full Name: ${i.fullName}</div>
                                            <div>Gender: ${i.gender}</div>
                                            <div>Email: ${i.email}</div>
                                            <div>Phone Number: ${i.phoneNumber}</div>
                                        </div>
                                    </div>
                                </td>
                                <td style="width: 180px">
                                    <div>
                                        <div>
                                            <input class="subject-id" type="hidden" value="${i.subjectId}">
                                            <div>${i.subjectName}</div>
                                        </div>
                                    </div>
                                </td>
                                <td style="width: 180px">
                                    <div>
                                        <div>
                                            <input type="hidden" value="${i.packageId}">
                                            <div>Name: ${i.packageName}</div>
                                            <div>Price: ${i.salePrice}VND</div>
                                        </div>
                                    </div>
                                </td>
                                <td class="duration">${i.duration}</td>
                                <td>${i.status}</td>
                                <td><c:if test="${i.status == 'pending'}">
                                        <a class="btn btn-primary">Active</a>
                                    </c:if>
                                </td>
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
                                    <a onclick="paging(${currentPage-1}, '${search}')">
                                        <i class="fa-solid fa-chevron-left"></i>
                                    </a>
                                </li>
                            </c:when>
                        </c:choose>
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li ${i == currentPage ? "class=\"active\"" : ""}><a onclick="paging('${i}', '${search}')">${i}</a></li>
                            </c:forEach>

                        <c:choose>
                            <c:when test="${currentPage < totalPages}">
                                <li>
                                    <a onclick="paging(${currentPage+1}, '${search}')">
                                        <i class="fa-solid fa-chevron-right"></i>
                                    </a>
                                </li>
                            </c:when>
                        </c:choose>
                    </ul>
                </nav>
            </div>
        </section>
        <br/>

        <%@include file="/layout/footer.jsp" %>
        <script src="js/logout.js"></script>

        <!--jquery-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <!--swal-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="js/PaginationSale.js"></script>
        <!-- side bar có thể thu nhỏ khi màn hình nhỏ  -->
        <script src="js/script.js"></script>
        <script>
                                        onunload = function () {
            <%
                    session.setAttribute("totalPages", null);
                    session.setAttribute("currentPage", null);
                    session.setAttribute("search", null);
            %>
                                        };
        </script>
    </body>
</html>