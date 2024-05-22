<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="side-bar">

    <div id="close-btn">
        <i class="fas fa-times"></i>
    </div>

    <c:if test="${sessionScope.user != null}">
        <div class="profile">
            <img src="images/pic-1.jpg" class="image" alt="">
            <h3 class="name">shaikh anas</h3>
            <p class="role">studen</p>
            <a href="profile.html" class="btn">view profile</a>
        </div>
    </c:if>

    <nav class="navbar">
        <a href="home"><i class="fas fa-home"></i><span>home</span></a>
        <a href="about"><i class="fas fa-question"></i><span>about</span></a>
        <a href="courses"><i class="fas fa-graduation-cap"></i><span>courses</span></a>
        <a href="teachers"><i class="fas fa-chalkboard-user"></i><span>teachers</span></a>
        <a href="contact"><i class="fas fa-headset"></i><span>contact us</span></a>
    </nav>

</div>
