<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String contextPath = request.getContextPath();%>
<div class="side-bar">

<!--    <div id="close-btn">
        <i class="fas fa-times"></i>
    </div>-->
<br/>
<br/>

    <c:if test="${sessionScope.user != null}">
        <div class="profile">
            <img src="<%=contextPath%>/${sessionScope.user.profileImg}" class="image" alt="">
            <h3 class="name">${sessionScope.user.fullName}</h3>
            <p class="role">student</p>
            <a onclick="openPopUp2()" class="option-btn">view profile</a>
        </div>
    </c:if>
    <br/>

    <form action="<%=request.getContextPath()%>/subjectManager" method="post" class="search-form">
        <input type="text" name="subjectName" required placeholder="Subject" maxlength="100">
        <input type="hidden" name="typeSubmit" value="submitName">
        <button type="submit" class="fas fa-search"></button>
    </form>
  
    <form action="<%=request.getContextPath()%>/subjectManager" method="post" class="form-inline" id="searchByDimension">
        <div class="form-group">
            <select id="dimension" name="dimensionId" class="form-control">
                <option value="" disabled selected>Search by category</option>
                <c:forEach var="dimension" items="${dimensions}">
                    <option value="${dimension.id}">${dimension.name}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="typeSubmit" value="dimensionId">
        </div>
    </form>
    
    <form action="<%=request.getContextPath()%>/subjectManager" method="post" class="form-inline" id="searchByStatus">
        <div class="form-group">
            <select id="status" name="status" class="form-control">
                <option value="" disabled selected>Search by status</option>
                <option value="true">Active</option>
                <option value="false">Inactive</option>
            </select>
            <input type="hidden" name="typeSubmit" value="status">
        </div>
    </form>
    <br/>
</div>

<script>
    document.getElementById('dimension').addEventListener('change', function() {
        document.getElementById('searchByDimension').submit();
    });

    document.getElementById('status').addEventListener('change', function() {
        document.getElementById('searchByStatus').submit();
    });
</script>