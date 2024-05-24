<%-- 
    Document   : sidebarright
    Created on : May 19, 2024, 3:41:01 PM
    Author     : DELL
--%>
<%-- 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
    </head>
    <body>

        <div class="side-barright">

            <form action="logindetail" method="POST">            
                <a href="blogdetail.jsp" > <input name="newblog" id="hot"  type="submit" value="Hot news post" /></a>
            </form>
            
           
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
            
                
             <br><!-- comment -->
            <div class="icons">

                <div id="search-btn" class="fas fa-search"> </div>

            </div>
            <h1>Search</h1>
            
            <form action="blogslist" method="post" class="search-formm" >
                <input id="sea" type="text" name="search"  placeholder=" Search blogs...." maxlength="100" value="${param.search}" >
                <br><!-- comment -->
                <button  id="seb" type="submit" class="fas fa-search" ></button>
            </form>


                <br>
               
                
                <br><!-- comment -->
                
           <h2>Category Posts</h2>
          
            <ul>
                <c:forEach items="${listcatego}" var="l">
                   <li><a href="#">${l.getCategory_Name()}</a></li>
                   
                </c:forEach>
            </ul>
           

        </div>

    </body>
</html> --%>