<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/popup.css">

        <!-- Bootstrap file link  -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
            .pagination-area {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 10px; /* Adjust as needed */
            }

            .pagination {
                display: flex;
                list-style: none;
                padding: 0;
                margin: 0;
            }

            .page-item {
                margin: 0 5px; /* Adjust spacing between pagination items as needed */
            }

            .page-link {
                display: block;
                padding: 8px 12px;
                text-decoration: none;
                border: 1px solid #ddd;
                border-radius: 4px;
                color: #007bff;
            }

            .page-link:hover {
                background-color: #f1f1f1;
                text-decoration: none;
                color: #0056b3;
            }

            .skill-box{
                width: 100%;
                margin: 25px 0;
            }
            .skill-box .title{
                display: block;
                font-size: 14px;
                font-weight: 600;
                color: #333;
            }
            .skill-box .skill-bar{
                height: 8px;
                width: 100%;
                border-radius: 6px;
                margin-top: 6px;
                background: rgba(0,0,0,0.1);
            }
            .skill-bar .skill-per{
                position: relative;
                display: block;
                height: 100%;
                width: 90%;
                border-radius: 6px;
                background: #4070f4;
                animation: progress 0.4s ease-in-out forwards;
                opacity: 0;
            }
            .skill-per.${name} {
                width: ${percent}%;
                animation-delay: 0.3s;
            }
            .skill-per.javascript{
                width: 50%;
                animation-delay: 0.3s;
            }
            .skill-per.nodejs{
                width: 30.25%;
                animation-delay: 0.3s;
            }
            @keyframes progress {
                0%{
                    width: 0;
                    opacity: 1;
                }
                100%{
                    opacity: 1;
                }
            }
            .skill-per .tooltip1{
                position: absolute;
                right: -14px;
                top: -28px;
                font-size: 9px;
                font-weight: 500;
                color: #fff;
                padding: 2px 6px;
                border-radius: 3px;
                background: #4070f4;
                z-index: 1;
            }
            .tooltip1::before{
                content: '';
                position: absolute;
                left: 50%;
                bottom: -2px;
                height: 10px;
                width: 10px;
                z-index: -1;
                background-color: #4070f4;
                transform: translateX(-50%) rotate(45deg);
            }
        </style>
    </head>
    <body>
        <%@include file="layout/header.jsp"%>
        <%@include file="layout/sidebar.jsp"%>
        <section class="subjectDetailExpert">
            <h1 class="heading col-md-3">My Learning</h1>
            <div class="col-md-6"></div>
            <a class="col-md-3" id="lessonManager" href="#">Explore more subject</a>

            <div class="container col-md-12" id="tabBar">

                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#home">In Progress (10)</a></li>
                    <li><a data-toggle="tab" href="#menu1">Complete (10)</a></li>
                </ul>
                <div class="tab-content">
                    <div id="home" class="tab-pane fade in active">
                        <div style="width: 1130px" class="courses">
                            <h3>In progress</h3>
                            <div class="box-container">

                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <div class="skill-box">
                                        <div class="skill-bar">
                                            <span class="skill-per css">
                                                <span class="tooltip1">${percent}%</span>
                                            </span>
                                        </div>
                                        <span>Overall Progress</span>
                                    </div>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <div class="skill-box">
                                        <div class="skill-bar">
                                            <span class="skill-per nodejs">
                                                <span class="tooltip1">30.23%</span>
                                            </span>
                                        </div>
                                        <span>Overall Progress</span>
                                    </div>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                            </div>
                            <nav class="mt-3 pagination-area" aria-label="Page navigation example">
                                <ul class="pagination">
                                    <!-- Disable the "Previous" link if on the first page -->
                                    <li class="page-item">
                                        <a class="page-link"><i class="fa-solid fa-chevron-left"></i></a>
                                    </li>

                                    <!-- Display the page numbers as links -->
                                    <li class="page-item">
                                        <a class="page-link" href="">1</a>
                                    </li>

                                    <!-- Disable the "Next" link if on the last page -->
                                    <li class="page-item">
                                        <a class="page-link" href=""><i class="fa-solid fa-chevron-right"></i></a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <div id="menu1" class="tab-pane fade">
                        <div style="width: 1130px" class="courses">
                            <h3>Completed</h3>
                            <div class="box-container">

                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                                <div class="box">
                                    <div class="thumb">
                                        <img src="images/thumb-1.png" alt="">
                                        <span>10 videos</span>
                                    </div>
                                    <h3 class="title">complete HTML tutorial</h3>
                                    <a href="playlist.html" class="inline-btn">Go to subject</a>
                                </div>
                            </div>
                            <nav class="mt-3 pagination-area" aria-label="Page navigation example">
                                <ul class="pagination">
                                    <!-- Disable the "Previous" link if on the first page -->
                                    <li class="page-item">
                                        <a class="page-link"><i class="fa-solid fa-chevron-left"></i></a>
                                    </li>

                                    <!-- Display the page numbers as links -->
                                    <li class="page-item">
                                        <a class="page-link" href="">1</a>
                                    </li>

                                    <!-- Disable the "Next" link if on the last page -->
                                    <li class="page-item">
                                        <a class="page-link" href=""><i class="fa-solid fa-chevron-right"></i></a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="layout/footer.jsp" %>
        <script src="js/script.js"></script>

    </body>
</html>
