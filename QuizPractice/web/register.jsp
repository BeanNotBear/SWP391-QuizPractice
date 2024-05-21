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
                <input onkeyup="validateFirstName(this)" type="text" name="first-name" placeholder="First name" required maxlength="50" class="box" value="${requestScope.firstName}">
                <span id="firstName" class="danger_msg">${requestScope.firstName_err}</span>

                <p>Enter your last name <span>*</span></p>
                <input onkeyup="validateLastName(this)" type="text" name="last-name" placeholder="Last name" required maxlength="50" class="box" value="${requestScope.lastName}">
                <span id="lastName" class="danger_msg">${requestScope.lastName_err}</span>

                <p>Enter your email <span>*</span></p>
                <input onkeyup="validateEmail(this)" type="email" name="email" placeholder="Email" required maxlength="50" class="box" value="${requestScope.email}">
                <span id="uEmail" class="danger_msg">${requestScope.email_err}</span>
                
                
                <p>Enter your phone number <span>*</span></p>
                <input onkeyup="validatePhoneNumber(this)" type="text" name="phone" placeholder="Phone number" required maxlength="50" class="box" value="${requestScope.phone}">
                <span id="phoneNumber" class="danger_msg">${requestScope.phone_err}</span>

                <p>Enter your gender <span>*</span></p>
                <select id="gender" class="box" name="gender">
                    <option value="true">Male</option>
                    <option value="false">Female</option>
                </select>
                <span class="danger_msg">${requestScope.gender_err}</span>
                
                <p>Enter your dob <span>*</span></p>
                <input oninput="validateDob(this)" type="date" name="dob" placeholder="DOB" required maxlength="50" class="box" value="${requestScope.dob}">
                <span id="dobMsg" class="danger_msg">${requestScope.dbo_err}</span>
                
                
                <p>Enter your username <span>*</span></p>
                <input onkeyup="validateUsername(this)" type="text" name="username" placeholder="Username" required maxlength="20" class="box" value="${requestScope.username}">
                <span id="usernameMsg" class="danger_msg">${requestScope.username_err}</span>

                <div class="group-pass">
                    <p>Enter your password <span>*</span></p>
                    <input onkeyup="validatePassword(this)" id="password" type="password" name="password" placeholder="Password" required maxlength="20" class="box password" value="${requestScope.password}">
                    <span id="show-pass" class="show-password">Show password</span>
                    <div id="passwordMsg" class="danger_msg">${requestScope.password_err}</div>
                </div>

                <div class="group-cf-pass">
                    <p>Enter your password again<span>*</span></p>
                    <input onkeyup="checkPasswordAndCfPassword(this)" id="cfPasssword" type="password" name="cf-password" placeholder="Confirm password" required maxlength="20" class="box cf-password" value="${requestScope.cfPassowrd}">
                    <span id="show-cf-pass" class="show-password">Show password</span>
                    <div id="cfPassowrdMsg" class="danger_msg">${requestScope.cfPassword_err}</div>
                </div>
                <input onmouseover="isAllowSendData(this)" type="submit" value="register new" name="submit" class="btn">
            </form>

        </section>
        <!-- footer -->
        <%@include file="layout/footer.jsp"%>
        <!-- custom js file link  -->
        <script src="js/script.js"></script>
        <script src="js/validate.js"></script>
    </body>
</html>