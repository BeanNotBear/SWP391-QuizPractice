<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <!-- header -->
        <%@include file="layout/header.jsp"%>  

        <!-- sidebar -->
        <%@include file="layout/sidebar.jsp"%>
        <section class="form-container">

            <form action="register" method="post">
                <h3>register now</h3>
                <p>Enter your first name <span>*</span></p>
                <input type="text" name="first-name" placeholder="First name" required maxlength="50" class="box">
                <span class="danger_msg">${requestScope.firstName_err}</span>

                <p>Enter your last name <span>*</span></p>
                <input type="text" name="last-name" placeholder="Last name" required maxlength="50" class="box">
                <span class="danger_msg">${requestScope.lastName_err}</span>

                <p>Enter your email <span>*</span></p>
                <input type="email" name="email" placeholder="Email" required maxlength="50" class="box">
                <span class="danger_msg">${requestScope.email_err}</span>

                <p>Enter your phone number <span>*</span></p>
                <input type="text" name="phone" placeholder="Phone number" required maxlength="50" class="box">
                <span class="danger_msg">${requestScope.phone_err}</span>

                <p>Enter your date of birth <span>*</span></p>
                <input type="date" name="dob" placeholder="Date of birth" required maxlength="50" class="box">
                <span class="danger_msg">${requestScope.dob_err}</span>

                <p>Gender <span>*</span></p>
                <select class="box" name="gender">
                    <option value="true">Male</option>
                    <option value="false">Female</option>
                </select>
                <span class="danger_msg">${requestScope.gender_err}</span>

                <p>Enter your username <span>*</span></p>
                <input type="text" name="username" placeholder="Username" required maxlength="20" class="box">
                <span class="danger_msg">${requestScope.username_err}</span>

                <div class="group-pass">
                    <p>Enter your password <span>*</span></p>
                    <input id="password" type="password" name="password" placeholder="Password" required maxlength="20" class="box password">
                    <span id="show-pass" class="show-password">Show password</span>
                    <div class="danger_msg">${requestScope.passowrd_err}</div>
                </div>

                <div class="group-cf-pass">
                    <p>Enter your password again<span>*</span></p>
                    <input id="cfPasssword" type="password" name="cf-password" placeholder="Confirm password" required maxlength="20" class="box cf-password">
                    <span id="show-cf-pass" class="show-password">Show password</span>
                    <div class="danger_msg">${requestScope.cfPassowrd_err}</div>
                </div>
                <input type="submit" value="register new" name="submit" class="btn">
            </form>

        </section>

        <!-- footer -->
        <%@include file="layout/footer.jsp"%>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>


    </body>
</html>