<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>home</title>

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>

        <!-- header -->
        <%@include file="layout/header.jsp" %>

        <!-- sidebar -->
        <%@include file="layout/sidebar.jsp" %>

        <section class="home-grid">

            <h1 class="heading">quick options</h1>

            <div class="box-container">

                <div class="box">
                    <h3 class="title">likes and comments</h3>
                    <p class="likes">total likes : <span>25</span></p>
                    <a href="#" class="inline-btn">view likes</a>
                    <p class="likes">total comments : <span>12</span></p>
                    <a href="#" class="inline-btn">view comments</a>
                    <p class="likes">saved playlists : <span>4</span></p>
                    <a href="#" class="inline-btn">view playlists</a>
                </div>

                <div class="box">
                    <h3 class="title">top categories</h3>
                    <div class="flex">
                        <a href="#"><i class="fas fa-code"></i><span>development</span></a>
                        <a href="#"><i class="fas fa-chart-simple"></i><span>business</span></a>
                        <a href="#"><i class="fas fa-pen"></i><span>design</span></a>
                        <a href="#"><i class="fas fa-chart-line"></i><span>marketing</span></a>
                        <a href="#"><i class="fas fa-music"></i><span>music</span></a>
                        <a href="#"><i class="fas fa-camera"></i><span>photography</span></a>
                        <a href="#"><i class="fas fa-cog"></i><span>software</span></a>
                        <a href="#"><i class="fas fa-vial"></i><span>science</span></a>
                    </div>
                </div>

                <div class="box">
                    <h3 class="title">popular topics</h3>
                    <div class="flex">
                        <a href="#"><i class="fab fa-html5"></i><span>HTML</span></a>
                        <a href="#"><i class="fab fa-css3"></i><span>CSS</span></a>
                        <a href="#"><i class="fab fa-js"></i><span>javascript</span></a>
                        <a href="#"><i class="fab fa-react"></i><span>react</span></a>
                        <a href="#"><i class="fab fa-php"></i><span>PHP</span></a>
                        <a href="#"><i class="fab fa-bootstrap"></i><span>bootstrap</span></a>
                    </div>
                </div>

                <div class="box">
                    <h3 class="title">become a tutor</h3>
                    <p class="tutor">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Perspiciatis, nam?</p>
                    <a href="teachers.html" class="inline-btn">get started</a>
                </div>

            </div>

        </section>



        <section class="courses">

            <h1 class="heading">our courses</h1>

            <div class="box-container">

                <div class="box">
                    <div class="tutor">
                        <img src="images/pic-2.jpg" alt="">
                        <div class="info">
                            <h3>john deo</h3>
                            <span>21-10-2022</span>
                        </div>
                    </div>
                    <div class="thumb">
                        <img src="images/thumb-1.png" alt="">
                        <span>10 videos</span>
                    </div>
                    <h3 class="title">complete HTML tutorial</h3>
                    <a href="playlist.html" class="inline-btn">view playlist</a>
                </div>

                <div class="box">
                    <div class="tutor">
                        <img src="images/pic-3.jpg" alt="">
                        <div class="info">
                            <h3>john deo</h3>
                            <span>21-10-2022</span>
                        </div>
                    </div>
                    <div class="thumb">
                        <img src="images/thumb-2.png" alt="">
                        <span>10 videos</span>
                    </div>
                    <h3 class="title">complete CSS tutorial</h3>
                    <a href="playlist.html" class="inline-btn">view playlist</a>
                </div>

                <div class="box">
                    <div class="tutor">
                        <img src="images/pic-4.jpg" alt="">
                        <div class="info">
                            <h3>john deo</h3>
                            <span>21-10-2022</span>
                        </div>
                    </div>
                    <div class="thumb">
                        <img src="images/thumb-3.png" alt="">
                        <span>10 videos</span>
                    </div>
                    <h3 class="title">complete JS tutorial</h3>
                    <a href="playlist.html" class="inline-btn">view playlist</a>
                </div>

                <div class="box">
                    <div class="tutor">
                        <img src="images/pic-5.jpg" alt="">
                        <div class="info">
                            <h3>john deo</h3>
                            <span>21-10-2022</span>
                        </div>
                    </div>
                    <div class="thumb">
                        <img src="images/thumb-4.png" alt="">
                        <span>10 videos</span>
                    </div>
                    <h3 class="title">complete Boostrap tutorial</h3>
                    <a href="playlist.html" class="inline-btn">view playlist</a>
                </div>

                <div class="box">
                    <div class="tutor">
                        <img src="images/pic-6.jpg" alt="">
                        <div class="info">
                            <h3>john deo</h3>
                            <span>21-10-2022</span>
                        </div>
                    </div>
                    <div class="thumb">
                        <img src="images/thumb-5.png" alt="">
                        <span>10 videos</span>
                    </div>
                    <h3 class="title">complete JQuery tutorial</h3>
                    <a href="playlist.html" class="inline-btn">view playlist</a>
                </div>

                <div class="box">
                    <div class="tutor">
                        <img src="images/pic-7.jpg" alt="">
                        <div class="info">
                            <h3>john deo</h3>
                            <span>21-10-2022</span>
                        </div>
                    </div>
                    <div class="thumb">
                        <img src="images/thumb-6.png" alt="">
                        <span>10 videos</span>
                    </div>
                    <h3 class="title">complete SASS tutorial</h3>
                    <a href="playlist.html" class="inline-btn">view playlist</a>
                </div>

            </div>

            <div class="more-btn">
                <a href="courses.html" class="inline-option-btn">view all courses</a>
            </div>

        </section>

        <!-- footer -->
        <%@include file="layout/footer.jsp" %>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>


    </body>
</html>