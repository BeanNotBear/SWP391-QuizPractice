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
       <div class="side-barright">
            <div class="box-container">            
                <h3>Hot new Post</h3> <!-- Hiển thị blogs mới nhất theo thời gian -->
                <div class="box">
                    <img src="${nee.getThumbnail()}" id="ima"/>
                    <div class="student">                        
                        <h3>    ${nee.getTitle()}</h3>                           
                    </div>
                    <p>Date: ${nee.createdDate}</p>    
                </div>
            </div>
            <c:forEach items="${newblog}" var="li" >                 
                <div class="box">
                    <img src="${li.thumbnail}" id="ima"/>
                    <div class="student">
                        <div>
                            <h3>${li.title}</h3>
                            <p> ${li.brieinfo}</p>                              
                        </div>
                    </div>
                </div>
            </c:forEach> 

            <br><!-- Tìm kiếm blogs theo tiêu đề. -->
            <div class="icons">
                <div id="search-btn" class="fas fa-search"> </div>
            </div>
            <h1>Search</h1>           

            <form action="blog" method="post" class="search-formm" >
                <input id="sea" type="text" name="search"  placeholder=" Search blogs...." maxlength="100" value="${param.search}" >
                <br><!-- comment -->
                <button  id="seb" type="submit" class="fas fa-search" ></button>
            </form>

                
                
                
            <br><!-- Hiển thị tất cả danh mục (category)-->          
            <h2>Category Posts</h2> 
            <ul>
                <c:forEach items="${listcatego}" var="l">
                    <li class="catepost ${tag == l.id? "choose":""}"><a href="category?cid=${l.id}">${l.getCategory_Name()}</a></li>                
                    </c:forEach>
            </ul>
        </div>


        <section class="reviews">

            <h1 class="heading">Pots detail </h1>

            <div class="box-container">
               
                    <div class="box">
                        <img src="${detail.getThumbnail()}" id="detailImg"/>
                        <div class="student">
                            <div>
                                <h3>${detail.getTitle()}</h3>
                             
                                <div class="stars">
                                    <p>Author: ${detail.blog_firstName}</p>
                                    <p>Create date: ${detail.createdDate}</p>
                                    <p>Category: ${detail.category.getCategory_Name()}</p>                                  
                                </div>                        
                                  <p>${detail.content}</p>
                            </div>
                        </div>
                    </div>
               

            </div>

        </section>
        <%@include file="layout/footer.jsp" %>
        <script src="js/script.js"></script>

    </body>
</html>
