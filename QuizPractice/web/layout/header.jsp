<%-- 
    Document   : header
    Created on : May 14, 2024, 12:10:50 PM
    Author     : nghin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
