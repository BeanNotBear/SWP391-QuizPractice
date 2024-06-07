<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="header">

    <section class="flex">
        <a href="<%=request.getContextPath()%>/home" class="logo">Educa.</a>

        <div class="icons">
            <div id="menu-btn" class="fas fa-bars"></div>
            <div id="search-btn" class="fas fa-search"></div>
            <div id="user-btn" class="fas fa-user"></div>
            <div onclick="openRightSideBar()" id="right-side-btn" class="fas fa-sun"></div>
        </div>
        <div class="profile ">
            <c:if test="${sessionScope.user != null}">
                <img src="<%=request.getContextPath()%>/${sessionScope.user.profileImg}" class="image" alt="">
                <h3 class="name">${user.fullName}</h3>
                <p class="role">student</p>
                <a onclick="openPopUp2()" class="option-btn">view profile</a>
            </c:if>
            <div class="flex-btn">
                <c:if test="${sessionScope.user == null}">
                    <a onclick="openPopUp()" class="option-btn">login</a>
                    <a onclick="openPopUp1()" class="option-btn">register</a>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <a onclick="logout()" class="option-btn">Log Out</a>
                </c:if>
            </div>
        </div>
    </section>
</header>

<!--login pop-up-->
<%@include file="login.jsp" %>
