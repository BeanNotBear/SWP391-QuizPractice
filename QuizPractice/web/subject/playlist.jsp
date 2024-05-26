<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>video playlist</title>

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="../css/style.css">

    </head>
    <body>
        <%@include file="../layout/header.jsp"%>
        
        <%@include file="../layout/sidebar.jsp"%>

        <section class="playlist-details">
            <c:set var="subject" value="${requestScope.subject}"/>
            <h1 class="heading">playlist details</h1>

            <div class="row">

                <div class="column">
                    <form action="" method="post" class="save-playlist">
                        <button type="submit"><i class="far fa-bookmark"></i> <span>save playlist</span></button>
                    </form>

                    <div class="thumb">
                        <img src="${subject.img}" alt="">
                        <span>${subject.numberOfLesson} lesson</span>
                    </div>
                </div>
                <div class="column">
                    <div class="tutor">
                        <img src="../${subject.creator.profileImg}" alt="">
                        <div>
                            <h3>${subject.creator.firstName} ${subject.creator.lastName}</h3>
                            <span>${subject.create_at}</span>
                        </div>
                    </div>

                    <div class="details">
                        <h3>${subject.name}</h3>
                        <p>${subject.description}</p>
                        <a href="<%=request.getContextPath()%>/teacher?id=${subject.creator.userId}" class="inline-btn">view profile</a>
                        <a href="<%=request.getContextPath()%>/subject/register?id=${subject.id}" class="inline-btn">Register Now</a>
                    </div>
                </div>
            </div>

        </section>

        <section class="playlist-videos">

            <h1 class="heading">playlist videos</h1>

            <div class="box-container">

                <a class="box" href="watch-video.html">
                    <i class="fas fa-play"></i>
                    <img src="images/post-1-1.png" alt="">
                    <h3>complete HTML tutorial (part 01)</h3>
                </a>

                <a class="box" href="watch-video.html">
                    <i class="fas fa-play"></i>
                    <img src="images/post-1-2.png" alt="">
                    <h3>complete HTML tutorial (part 02)</h3>
                </a>

                <a class="box" href="watch-video.html">
                    <i class="fas fa-play"></i>
                    <img src="images/post-1-3.png" alt="">
                    <h3>complete HTML tutorial (part 03)</h3>
                </a>

                <a class="box" href="watch-video.html">
                    <i class="fas fa-play"></i>
                    <img src="images/post-1-4.png" alt="">
                    <h3>complete HTML tutorial (part 04)</h3>
                </a>

                <a class="box" href="watch-video.html">
                    <i class="fas fa-play"></i>
                    <img src="images/post-1-5.png" alt="">
                    <h3>complete HTML tutorial (part 05)</h3>
                </a>

                <a class="box" href="watch-video.html">
                    <i class="fas fa-play"></i>
                    <img src="images/post-1-6.png" alt="">
                    <h3>complete HTML tutorial (part 06)</h3>
                </a>

            </div>

        </section>
        <%@include file="../layout/footer.jsp" %>

        <!-- custom js file link  -->
        <script src="../js/script.js"></script>


    </body>
</html>