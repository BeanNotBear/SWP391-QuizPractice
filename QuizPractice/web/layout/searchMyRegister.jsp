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
            <a onclick="openPopUp2()" class="option-btn">view profile</a>
        </div>
    </c:if>
    <br/>

    <form action="<%=request.getContextPath()%>/myRegister" method="get">
        <input type="text" name="searchName" value="${searchName}" placeholder="Subject" maxlength="100" id="searchNameInput" class="styled-input">

        <select id="searchDimensionId" name="searchDimensionId" class="styled-combobox">
            <option value="" disabled selected>Search by category</option>
            <c:forEach var="dimension" items="${listDimension}">
                <option value="${dimension.id}" <c:if test="${dimension.id == searchDimensionId}">selected</c:if>>${dimension.name}</option>
            </c:forEach>
        </select>
        
        <input type="text" name="searchContent" value="${searchContent}" placeholder="Static content" maxlength="100" id="searchContentInput" class="styled-input">
    </form>
   
</div>

<style>
    .styled-input, .styled-combobox {
        width: 90%;
        padding: 10px;
        margin: 10px 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 16px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        outline: none;
    }

    .styled-input:focus, .styled-combobox:focus {
        border-color: #66afe9;
        box-shadow: 0 0 8px rgba(102, 175, 233, 0.6);
    }

    .styled-combobox {
        appearance: none;
        -webkit-appearance: none;
        -moz-appearance: none;
        background: url('data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyNCAyNCIgd2lkdGg9IjE2IiBoZWlnaHQ9IjE2Ij48cGF0aCBkPSJNMTIgMTUuNDFsLTUuNy01Ljc0YTEuMDAxIDEuMDAxIDAgMCAxIDEuNDEtMS40MWw0LjI5IDQuMjkgNC4yOS00LjI5YTEuMDAxIDEuMDAxIDAgMSAxIDEuNDEgMS40MWwtNS43IDUuNzRjLS4zOS4zOS0xLjAyLjM5LTEuNDEgMHoiLz48L3N2Zz4=') no-repeat right 10px center;
        background-size: 12px;
    }
</style>

<script>
    document.getElementById('searchNameInput').addEventListener('keypress', function (event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            this.form.submit();
        }
    });

    document.getElementById('searchDimensionId').addEventListener('change', function () {
        this.form.submit();
    });

    document.getElementById('searchContentInput').addEventListener('keypress', function (event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            this.form.submit();
        }
    });
</script>