<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="side-barright">
    <div class="box-container">            
        <h3>Hot new Post</h3> <!-- Hiển thị blogs mới nhất theo thời gian -->
        <div class="box">
            <button> <a href="blogdetail?pid=${nee.blog_id}"> <img src="${nee.getThumbnail()}" id="ima"/></a></button>
            <div class="student">                        
                <h3>    ${nee.getTitle()}</h3>                           
            </div>
            <p>Date: ${nee.createdDate}</p>    
        </div>
    </div>
    <c:forEach items="${newblog}" var="li" >                 
        <div class="box">
            <img src="${li.thumbnail}" id="ima"/>
            <div class="student">
                <div>
                    <h3>${li.title}</h3>
                    <p> ${li.brieinfo}</p>                              
                </div>
            </div>
        </div>
    </c:forEach> 

    <br><!-- Tìm kiếm blogs theo tiêu đề. -->
    <div class="icons">
        <div id="search-btn" class="fas fa-search"> </div>
    </div>
    <h1>Search</h1>           
    <form action="blog" method="post" class="search-formm" >
        <input id="sea" type="text" name="search"  placeholder=" Search blogs...." maxlength="100" value="${param.search}" >
        <br><!-- comment -->
        <button  id="seb" type="submit" class="fas fa-search" ></button>
    </form>

    <br><!-- Hiển thị tất cả danh mục (category)-->          
    <h2>Category Posts</h2> 
    <ul>
        <c:forEach items="${listcatego}" var="l">
            <li class="catepost ${tag == l.id? "choose":""}"><a href="category?cid=${l.id}">${l.getCategory_Name()}</a></li>                
        </c:forEach>
    </ul>
</div>