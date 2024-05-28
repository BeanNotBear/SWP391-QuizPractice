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
            <h1 class="heading">Subject details</h1>

            <div class="row">

                <div class="column">
                    <form action="" method="post" class="save-playlist">
                        <button type="submit"><i class="far fa-bookmark"></i> <span>save subject</span></button>
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
        <%@include file="../layout/footer.jsp" %>

        <!-- custom js file link  -->
        <script src="../js/script.js"></script>


    </body>
</html>