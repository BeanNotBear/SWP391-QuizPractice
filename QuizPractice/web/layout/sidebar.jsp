<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String contextPath = request.getContextPath();%>
<div class="side-bar">
    <div id="close-btn">
        <i class="fas fa-times"></i>
    </div>
    <c:if test="${sessionScope.user != null}">
        <div class="profile">
            <img src="<%=contextPath%>/${sessionScope.user.profileImg}" class="image" alt="">
            <h3 class="name">${sessionScope.user.fullName}</h3>
            <p class="role">student</p>
            <a href="<%=contextPath%>/profile" class="option-btn">view profile</a>
        </div>
    </c:if>

    <nav class="navbar">
        <a href="<%=contextPath%>/home"><i class="fas fa-home"></i><span>Home</span></a>
        <a href="<%=contextPath%>/subject"><i class="fas fa-graduation-cap"></i><span>Subjects</span></a>
        <a href="<%=contextPath%>/blog"><i class="fas fa-newspaper"></i><span>Posts</span></a>
    </nav>


</div>