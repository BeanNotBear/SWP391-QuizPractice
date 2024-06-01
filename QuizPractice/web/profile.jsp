<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <%@include file="layout/head.jsp" %>
    <body>

        <!-- header -->
        <%@include file="layout/header.jsp" %>

        <!-- sidebar -->
        <%@include file="layout/sidebar.jsp" %>


        <!-- Edit profile -->
        <section class="user-form-section">
            <div class="container">
                <div class="row">
                    <!-- Form column -->
                    <div class="col-md-6 col-lg-6 mx-auto">
                        <div class="form-header">
                            <h1 class="heading">User Profile</h1>
                        </div>

                        <form action="profile?email=${user.email}" method="POST" class="user-form">
                            <%
                                   if(request.getAttribute("msg")!=null){
                                       out.print("<p class='text-danger ml-1'>"+request.getAttribute("msg")+"</p>");
                                   }
		  
                            %>
                            <div class="form-group">
                                <label for="name">First name:</label>
                                <input type="text" id="name" name="first-name" class="form-control" required value=${user.firstName} >
                            </div>
                            <div class="form-group">
                                <label for="phone">Last name:</label>
                                <input type="text" id="phone" name="last-name" class="form-control" required value=${user.lastName}>
                            </div>
                            <div class="form-group">
                                <label for="address">User name:</label>
                                <input type="text" id="address" name="username" class="form-control" required value="${user.username}">
                            </div>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" id="email" name="email" class="form-control" readonly value="${user.email}" >
                            </div>
                            <div class="form-group">
                                <label for="address">Phone number:</label>
                                <input type="text" id="address" name="phone-number" class="form-control" required value=${user.phoneNumber}>
                            </div>

                            <div class="form-group">
                                <a href="change-password">Change password</a>
                            </div>
                            <div class="form-group buttons-inline">
                                <button type="reset" class="btn btn-primary">Reset</button>
                                <button type="submit" class="btn btn-secondary">Save changes</button>
                            </div>
                        </form>
                    </div>

                    <div class="col-md-6 col-lg-2"></div>
                    <!-- Image column -->
                    <div class="col-md-6 col-lg-3">
                        <div class="image-container">
                            <img src="images/thumb-1.png" alt="User Profile Image" class="img-responsive">
                            <button type="button" class="btn btn-secondary" onclick="location.href = 'home'">  <i class="fas fa-upload"></i> Upload</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- footer -->
        <%@include file="layout/footer.jsp" %>

        <!--pop js-->
        <script src="js/popup.js"></script>

        <!--jquery-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <!--swal-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>
    </body>
</html>