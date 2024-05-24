<%-- 
    Document   : blogs
    Created on : May 16, 2024, 10:24:47 AM
    Author     : DELL
--%>

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
    </head>
    <body>
        <header class="header">

            <section class="flex">

                <a href="home.html" class="logo">Educa.</a>


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
                        <a href="login.html" class="option-btn">login</a>
                        <a href="/QuizPractice/register" class="option-btn">register</a>
                    </div>
                </div>

            </section>

        </header> 
        <%@include file="layout/sidebar.jsp" %>
        <%@include file="layout/sidebarright.jsp" %>

        <section class="reviews">

            <h1 class="heading">Pots detail </h1>

            <div class="box-container">
                <c:forEach items="${detail}" var="li">
                    <div class="box">
                        <img src="${li.getThumbnail()}" id="ima"/>
                        <div class="student">
                            <div>
                                <h3>${li.getTitle()}</h3>
                                <p>${li.getBrieinfo()}</p>
                                <div class="stars">
                                    <p>Category: ${li.category.getCategory_Name()}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>

        </section>




        <%@include file="layout/footer.jsp" %>
        <script src="js/script.js"></script>

    </body>
</html>


