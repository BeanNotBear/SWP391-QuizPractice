<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Subject Detail</title>

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">

        <link rel="stylesheet" href="css/popup.css"/>

        <!-- Bootstrap file link  -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>



    </head>
    <body>
        <%@include file="/layout/header.jsp"%>

        <%@include file="/layout/sidebar.jsp"%>

        <section class="playlist-details">
            <c:set var="subject" value="${requestScope.subject}"/>
            <h1 class="heading">Subject details</h1>

            <div class="row">

                <div class="column">
                    <form action="" method="post" class="save-playlist">
                        <button type="submit"><i class="far fa-bookmark"></i> <span>save subject</span></button>
                    </form>

                    <div class="thumb">
                        <img src="<%=contextPath%>/${subject.img}" alt="">
                        <span>${subject.numberOfLesson} lesson</span>
                    </div>
                    <div>

                    </div>
                </div>
                <div class="column">
                    <div class="tutor">
                        <img src="<%=contextPath%>/${subject.creator.profileImg}" alt="">
                        <div>
                            <h3>${subject.creator.firstName} ${subject.creator.lastName}</h3>
                            <span>${subject.create_at}</span>
                        </div>
                    </div>

                    <div class="details">
                        <h3>${subject.name}</h3>
                        <p>${subject.description}</p>
                        <p>
                            ${subject.name} has ${subject.pricePackages.size()} package price:
                            <br/>
                            <c:forEach var="p" items="${subject.pricePackages}">
                                ${p.name}: ${p.salePrice} VND<br/> 
                            </c:forEach>
                        </p>
                        <a href="<%=contextPath%>/teacher?id=${subject.creator.userId}" class="inline-btn">view profile</a>
                        <a href="<%=contextPath%>/subject/register?id=${subject.id}" class="inline-btn">Register Now</a>
                    </div>
                </div>
            </div>

        </section>
        <%@include file="/layout/footer.jsp" %>

        <!--pop js-->
        <script src="js/popup.js"></script>

        <!--jquery-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <!--swal-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>
    </body>
</html>