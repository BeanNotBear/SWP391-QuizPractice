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
    </head>
    <body>
        <%@include file="layout/header.jsp" %>
        <%@include file="layout/sidebar.jsp" %>
        <%@include file="layout/sidebarright.jsp" %>


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
        <script src="js/rightsidebar.js"></script>
        <!--pop js-->
        <script src="js/popup.js"></script>

        <!--jquery-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <!--swal-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    </body>
</html>
