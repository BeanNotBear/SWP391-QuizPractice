<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <a href="<%=request.getContextPath()%>/home"><i class="fas fa-home"></i><span>home</span></a>
        <a href="<%=request.getContextPath()%>/about"><i class="fas fa-question"></i><span>about</span></a>
        <a href="<%=request.getContextPath()%>/subject"><i class="fas fa-graduation-cap"></i><span>subject</span></a>
        <a href="<%=request.getContextPath()%>/teachers"><i class="fas fa-chalkboard-user"></i><span>teachers</span></a>
        <a href="<%=request.getContextPath()%>/contact"><i class="fas fa-headset"></i><span>contact us</span></a>
    </nav>

</div>
