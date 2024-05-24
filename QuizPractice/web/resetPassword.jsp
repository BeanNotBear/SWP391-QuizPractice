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
    <style>
        .password-container {
            position: relative;
            width: 100%;
        }

        .password-container input {
            width: 100%;
            padding-right: 40px;
        }

        .password-container .fas {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
            color: #666;
            font-size: 18px;
        }

        .password-container .fas:hover {
            color: #333;
        }
    </style>
    <body> 
        <%@include file="layout/header.jsp" %>
        <%@include file="layout/sidebar.jsp" %>
        <section class="form-container">
            <form action="new-password" method="post">
                <h3>Reset Password</h3>
                <p>New password <span>*</span></p>
                <div class="password-container">
                    <input type="password" id="new_password" name="newPassword" placeholder="Enter your new password" required maxlength="50" class="box">
                    <i class="fas fa-eye" id="toggleNewPassword" onclick="togglePassword('new_password', 'toggleNewPassword')"></i>
                </div>
                <p>Confirm new password <span>*</span></p>
                <div class="password-container">
                    <input type="password" id="confirm_password" name="confirmPassword" placeholder="Confirm your new password" required maxlength="50" class="box">
                    <i class="fas fa-eye" id="toggleConfirmPassword" onclick="togglePassword('confirm_password', 'toggleConfirmPassword')"></i>
                </div>
                <%
                                 if(request.getAttribute("status")!=null){
                                     out.print("<p class='text-danger ml-1'>"+request.getAttribute("status")+"</p>");
                                 }
		  
                %>
                <input type="submit" value="Reset Password" name="submit" class="btn">
            </form>
        </section>
        <%@include file="layout/footer.jsp" %>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>

        <script>
                        function togglePassword(inputId, toggleId) {
                            const passwordField = document.getElementById(inputId);
                            const toggleIcon = document.getElementById(toggleId);
                            if (passwordField.type === "password") {
                                passwordField.type = "text";
                                toggleIcon.classList.remove("fa-eye");
                                toggleIcon.classList.add("fa-eye-slash");
                            } else {
                                passwordField.type = "password";
                                toggleIcon.classList.remove("fa-eye-slash");
                                toggleIcon.classList.add("fa-eye");
                            }
                        }
        </script>
    </body>
</html>