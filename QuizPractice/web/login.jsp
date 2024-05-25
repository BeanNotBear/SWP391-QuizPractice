<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>login</title>

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">

    </head>
    <body> 
        <%@include file="layout/header.jsp" %>
        <%@include file="layout/sidebar.jsp" %>
        <section class="form-container">

            <form action="login" method="post">
                <h3>login now</h3>
                <p>your email <span>*</span></p>
                <input type="email" name="email" placeholder="enter your email" required maxlength="50" class="box">
                <p>your password <span>*</span></p>
                <input type="password" name="password" placeholder="enter your password" required maxlength="50" class="box">
                <span class="danger_msg">${requestScope.login_fail_err}</span>
                <input type="submit" value="login new" name="submit" class="btn">
            </form>
        </section>
       
        <%@include file="layout/footer.jsp" %>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>
    </body>
</html>