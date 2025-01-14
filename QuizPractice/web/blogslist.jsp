<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blogs</title>

        <!-- Font Awesome CDN link -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
        <!-- Custom CSS file link -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/popup.css"/>

        <!-- Bootstrap file link  -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            .side-bar #close-btn {
                text-align: right;
                margin-bottom: 20px;
            }

            .side-bar #close-btn i {
                font-size: 24px;
                color: #888;
                cursor: pointer;
                transition: color 0.3s ease;
            }

            .side-bar #close-btn i:hover {
                color: #333;
            }

            .side-bar .box-container h5 {
                font-size: 20px;
                margin-bottom: 15px;
                color: #333;
                border-bottom: 2px solid #007bff;
                padding-bottom: 5px;
            }

            .side-bar .box {
                display: block;
                margin-bottom: 20px;
                background: #f9f9f9;
                padding: 10px;
                border-radius: 8px;
                transition: box-shadow 0.3s ease;
                text-align: center;
            }

            .side-bar .box:hover {
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .side-bar .box img {
                width: 100%;
                height: auto;
                object-fit: cover;
                margin-bottom: 10px;
                border-radius: 8px;
            }

            .side-bar .box .student h3 {
                font-size: 18px;
                color: #007bff;
                margin-bottom: 5px;
            }

            .side-bar .box .student p {
                font-size: 14px;
                color: #555;
            }

            .side-bar #date-bl {
                font-size: 14px;
                color: #888;
                margin-top: 5px;
            }

            .side-bar .icons {
                margin-bottom: 15px;
                text-align: center;
            }

            .side-bar .icons .fas.fa-search {
                font-size: 24px;
                color: #007bff;
                cursor: pointer;
                transition: color 0.3s ease;
            }

            .side-bar .icons .fas.fa-search:hover {
                color: #0056b3;
            }

            .side-bar h5 {
                margin-top: 20px;
                margin-bottom: 10px;
                font-size: 18px;
                color: #333;
            }

            .search-form {
                display: flex;
                align-items: center;
                margin-bottom: 20px;
            }

            .search-input {
                flex: 1;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
                transition: border-color 0.3s ease, box-shadow 0.3s ease;
            }

            .search-input:focus {
                border-color: #007bff;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
                outline: none;
            }

            .search-button {
                background-color: #007bff;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                color: #fff;
                font-size: 14px;
                cursor: pointer;
                transition: background-color 0.3s ease;
                margin-left: 5px;
            }

            .search-button:hover {
                background-color: #0056b3;
            }

            .search-button .fas {
                font-size: 16px;
            }


            .side-bar select {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
                margin-bottom: 20px;
                transition: border-color 0.3s ease;
            }

            .side-bar select:focus {
                border-color: #007bff;
            }

            .side-bar ul {
                list-style: none;
                padding: 0;
            }

            .side-bar ul li {
                margin-bottom: 10px;
            }

            .side-bar ul li a {
                text-decoration: none;
                color: #007bff;
                font-size: 16px;
                transition: color 0.3s ease;
            }

            .side-bar ul li a:hover {
                color: #0056b3;
            }

            .side-bar .catepost.choose a {
                font-weight: bold;
                color: #0056b3;
            }
            .box-container {
                margin: 20px;
            }

            .post-box {
                display: flex;
                align-items: center;
                background: #f9f9f9;
                border-radius: 8px;
                overflow: hidden;
                transition: box-shadow 0.3s ease;
                margin-bottom: 20px;
                border: 1px solid #ddd;
            }

            .post-box:hover {
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .post-link {
                display: flex;
                text-decoration: none;
                color: inherit;
                width: 100%;
            }

            .post-image {
                width: 120px; /* Giảm kích thước ảnh */
                height: 80px; /* Giảm kích thước ảnh */
                object-fit: cover;
                border-right: 2px solid #ddd;
            }

            .post-content {
                padding: 10px;
                flex: 1;
            }

            .post-content h3 {
                margin: 0;
                font-size: 16px;
                color: #007bff;
            }

            .post-content p {
                margin: 5px 0;
                color: #888;
                font-size: 14px;
            }

        </style>

    </head>
    <body>
        <%@include file="layout/header.jsp" %>
        <%@include file="layout/login.jsp" %>
        <div class="side-bar">
            <div id="close-btn">
                <i class="fas fa-times"></i>
            </div>

            <div class="box-container">
                <h5 style="color: purple; margin: 10px">Hot new Post</h5> <!-- Hiển thị blogs mới nhất theo thời gian -->
                <div class="post-box">
                    <a href="blogdetail?pid=${nee.blog_id}" class="post-link">
                        <img src="${nee.getThumbnail()}" class="post-image"/>
                        <div class="post-content">
                            <h3>${nee.getTitle()}</h3>
                            <p id="date-bl">Date: ${nee.createdDate}</p>
                        </div>
                    </a>
                </div>
            </div>

            <c:forEach items="${newblog}" var="li">
                <div class="box">
                    <img src="${li.thumbnail}" id="ima"/>
                    <div class="student">
                        <div>
                            <h3>${li.title}</h3>
                            <p>${li.brieinfo}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <!-- Tìm kiếm blogs theo tiêu đề. -->
            <div class="icons">
                <div id="search-btn" class="fas fa-search"></div>
            </div>
            <h5 style="color: purple; margin: 12px">Search</h5>
            <form id="search-form" action="blog" method="get" class="search-form">
                <input type="text" name="search" value="${searchQuery}" placeholder="Search blogs..." class="search-input" />
                <input type="hidden" name="category" value="${selectedCategory}" />
                <input type="hidden" name="index" value="${currentPage}" />
                <button type="submit" class="search-button">
                    <i class="fas fa-search"></i>
                </button>
            </form>

            <!-- Hiển thị tất cả danh mục (category)-->
            <h5 style="color: purple; margin: 12px">Category Posts</h5>
            <form id="category-form" action="blog" method="get">
                <input type="hidden" name="search" value="${searchQuery}" />
                <input type="hidden" name="index" value="${currentPage}" />
                <select name="category" onchange="this.form.submit()" style="width: 90%;margin-left: 10px">
                    <option value="">All Categories</option>
                    <c:forEach items="${listcatego}" var="l">
                        <option value="${l.id}" ${selectedCategory == l.id ? "selected" : ""}>${l.getCategory_Name()}</option>
                    </c:forEach>
                </select>
            </form>
        </div>


        <!-- Hiển thị tất cả blogs có trong database -->
        <section class="reviews">
            <div class="heading-container">
                <h1 class="heading">Blogs</h1>
                <c:if test="${sessionScope.user.roleId == 5}">
                    <a href="addnewblog">Add New Blog</a>
                </c:if>
            </div>
            <div class="box-container">
                <c:forEach items="${bloglist}" var="li">
                    <c:choose>
                        <c:when test="${sessionScope.user.roleId == 5}">
                            <!-- Hiển thị tất cả blog cho admin -->
                            <div class="box">
                                <img src="${li.thumbnail}" id="ima"/>
                                <div class="student">
                                    <div>
                                        <h3>${li.getTitle()}</h3>
                                        <p>${li.getBrieinfo()}</p>
                                        <p>Status:  
                                            <c:choose>
                                                <c:when test="${li.status}">
                                                    <span style="color: green;">Published</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="color: red;">Not published</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </p>

                                        <div class="stars">
                                            <p>Category: ${li.category.getCategory_Name()}</p>
                                            <button><a href="blogdetail?pid=${li.blog_id}">READ MORE</a></button>
                                            <c:if test="${sessionScope.user.roleId == 5}">
                                                <button><a href="postdetail?id=${li.blog_id}">Update</a></button>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <!-- Chỉ hiển thị những blog có status = true cho các vai trò khác -->
                            <c:if test="${li.status == true}">
                                <div class="box">
                                    <img src="${li.thumbnail}" id="ima"/>
                                    <div class="student">
                                        <div>
                                            <h3>${li.getTitle()}</h3>
                                            <p>${li.getBrieinfo()}</p>
                                            <div class="stars">
                                                <p>Category: ${li.category.getCategory_Name()}</p>
                                                <button><a href="blogdetail?pid=${li.blog_id}">READ MORE</a></button>
                                                <c:if test="${sessionScope.user.roleId == 5}">
                                                    <button><a href="postdetail?id=${li.blog_id}">Update</a></button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>



            </div>

        </section>

        <div id="pagee">
            <c:forEach begin="1" end="${endPage}" var="i">
                <a class="${currentPage == i ? 'activee' : ''}" 
                   href="blog?search=${searchQuery}&category=${selectedCategory}&index=${i}">
                    ${i}
                </a>
            </c:forEach>
        </div>


        <%@include file="layout/footer.jsp" %>

        <!--jquery-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <!--swal-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="js/script.js"></script>
        <%@include file="/layout/script.jsp" %>
        <!--        <script src="js/rightsidebar.js"></script>-->
    </body>
</html>