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

        <header class="header">

            <section class="flex">

                <a href="home.html" class="logo">Educa.</a>

                <form action="search.html" method="post" class="search-form">
                    <input type="text" name="search_box" required placeholder="search courses..." maxlength="100">
                    <button type="submit" class="fas fa-search"></button>
                </form>

                <div class="icons">
                    <div id="menu-btn" class="fas fa-bars"></div>
                    <div id="search-btn" class="fas fa-search"></div>
                    <div id="user-btn" class="fas fa-user"></div>
                    <div id="toggle-btn" class="fas fa-sun"></div>
                </div>

                <div class="profile">
                    <img src="images/pic-1.jpg" class="image" alt="">
                    <h3 class="name">shaikh anas</h3>
                    <p class="role">student</p>
                    <a href="profile.html" class="btn">view profile</a>
                    <div class="flex-btn">
                        <a href="login.html" class="option-btn">login</a>
                        <a href="register.html" class="option-btn">register</a>
                    </div>
                </div>

            </section>

        </header>   

        <div class="side-bar">

            <div id="close-btn">
                <i class="fas fa-times"></i>
            </div>

            <div class="profile">
                <img src="images/pic-1.jpg" class="image" alt="">
                <h3 class="name">shaikh anas</h3>
                <p class="role">studen</p>
                <a href="profile.html" class="btn">view profile</a>
            </div>

            <nav class="navbar">
                <a href="home.html"><i class="fas fa-home"></i><span>home</span></a>
                <a href="about.html"><i class="fas fa-question"></i><span>about</span></a>
                <a href="courses.html"><i class="fas fa-graduation-cap"></i><span>courses</span></a>
                <a href="teachers.html"><i class="fas fa-chalkboard-user"></i><span>teachers</span></a>
                <a href="contact.html"><i class="fas fa-headset"></i><span>contact us</span></a>
            </nav>

        </div>

        <section class="form-container">

            <form action="register" method="post">
                <h3>register now</h3>
                <p>Enter your first name <span>*</span></p>
                <input type="text" name="first-name" placeholder="First name" required maxlength="50" class="box">

                <p>Enter your last name <span>*</span></p>
                <input type="text" name="last-name" placeholder="Last name" required maxlength="50" class="box">

                <p>Enter your email <span>*</span></p>
                <input type="email" name="email" placeholder="Email" required maxlength="50" class="box">

                <p>Enter your phone number <span>*</span></p>
                <input type="text" name="phone" placeholder="Phone number" required maxlength="50" class="box">

                <p>Enter your date of birth <span>*</span></p>
                <input type="date" name="dob" placeholder="Date of birth" required maxlength="50" class="box">

                <p>Gender <span>*</span></p>
                <select class="box" name="gender">
                    <option value="true">Male</option>
                    <option value="false">Female</option>
                </select>

                <p>Enter your username <span>*</span></p>
                <input type="text" name="username" placeholder="Username" required maxlength="20" class="box">

                <div class="group-pass">
                    <p>Enter your password <span>*</span></p>
                    <input id="password" type="password" name="password" placeholder="Password" required maxlength="20" class="box password">
                    <i id="show-pass" class="fa-regular fa-eye eye-p"></i>
                </div>

                <div class="group-cf-pass">
                    <p>Enter your password again<span>*</span></p>
                    <input id="cfPasssword" type="password" name="cf-password" placeholder="Confirm password" required maxlength="20" class="box cf-password">
                    <i id="show-cf-pass" class="fa-regular fa-eye eye-cf show-pass"></i>
                </div>
                <input type="submit" value="register new" name="submit" class="btn">
            </form>

        </section>















        <footer class="footer">

            &copy; copyright @ 2022 by <span>mr. web designer</span> | all rights reserved!

        </footer>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>


    </body>
</html>