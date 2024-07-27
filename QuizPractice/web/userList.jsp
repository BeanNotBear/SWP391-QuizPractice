<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>App</title>

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/popup.css">

        <!-- Bootstrap file link  -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <!-- Custom CSS to make the footer fixed -->
        <style>
            html, body {
                height: 100%;
                margin: 0;
                display: flex;
                flex-direction: column;
            }
            .footer {
                width: 100%;
                background-color: var(--white);
                color: var(--main-color);
                padding: 10px;
                text-align: center;
                font-family: Arial, sans-serif;
                border-top: 1px solid;
            }
            .customer {
                display: flex;
            }
            .customer > div {
                margin-left: 20px;
            }
            .popup {
                display: none;
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 9;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
                /*    display: flex;*/
                align-items: center;
                justify-content: center;
            }
            .form-popup {
                margin-top: 60px;
            }
        </style>
    </head>

    <body>
        <%@include file="/layout/header.jsp"%>
        <%@include file="/layout/sidebar.jsp" %>

        <section class="practiceList">
            <h1 class="heading text-center">User Management</h1>

            <div id="topTable" class="container">
                <div class="row">
                    <div class="col-md-3">
                        <a onclick="openPopUpUser()" style="width: 100px;" class="btn btn-primary"><i class="fa-solid fa-plus"></i> Add User</a>
                    </div>
                    <div class="col-md-3">

                    </div>
                    <form class="col-md-2">
                        <input placeholder="Search" class="search-input" style="margin-left: 350px; margin-bottom: 10px; width: 200px;" type="text" name="search">
                        <input type="hidden" name="page" value="${currentPage}">
                        <input type="submit" hidden>
                    </form>
                </div>
            </div>

            <div class="container">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Profile Image</th>
                            <th>Email</th>
                            <th>Phone Number</th>
                            <th>Gender</th>
                            <th>Role</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="userContent">
                        <c:forEach var="i" items="${requestScope.users}">
                            <tr class="table-row">
                                <td>${i.id}</td>
                                <td>${i.fullName}</td>
                                <td><img src="${i.profileImg}" alt="alt"/></td>
                                <td>${i.email}</td>
                                <td>${i.phoneNumber}</td>
                                <td>${i.gender}</td>
                                <td>${i.role}</td>
                                <td>${i.status}</td>
                                <td>
                                    <a class="btn btn-primary"><i style="font-size: 20px; color: var(--orange)" class="fa-solid fa-lock"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
        <br/>
        <div id="modelPop" class="popup" role="dialog">
            <div class="form-container">
                <div>
                    <div>
                        <form class="form-popup">
                            <button onclick="closePopUpUser()" type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4>Add User</h4>
                            <input id="userId" type="hidden">
                            <div class="form-group">
                                <label for="fullName">Full Name: <span class="danger_msg">(require)</span></label>
                                <input onkeyup="" type="text" id="fullName" name="fullName" class="form-control">
                                <div id="fullNameMsg" class="msg danger_msg"></div>
                            </div>
                            <div class="form-group">
                                <label for="email">Email: <span class="danger_msg">(require)</span></label>
                                <input onkeyup="" type="email" id="email" name="email" class="form-control">
                                <div id="emailMsg" class="msg danger_msg"></div>
                            </div>
                            <div class="form-group">
                                <label for="phoneNumber">Phone Number: <span class="danger_msg">(not required)</span></label>
                                <input onkeyup="" type="number" id="phoneNumber" name="phoneNumber" class="form-control">
                                <div id="phoneNumberMsg" class="msg danger_msg"></div>
                            </div>
                            <div class="form-group">
                                <label for="gender">Gender: <span class="danger_msg">(require)</span></label>
                                <div>
                                    <input value="1" type="radio" name="gender" checked> Male
                                    <input value="2" type="radio" name="gender"> Female
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="role">Role: <span class="danger_msg">(require)</span></label>
                                <div>
                                    <select id="role" name="role" class="form-control">
                                        <option selected value="1">Customer</option>
                                        <option value="2">Admin</option>
                                        <option value="5">Marketing</option>
                                        <option value="3">Expert</option>
                                        <option value="4">Sale</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password">Password: <span class="danger_msg">(require)</span></label>
                                <input readonly type="password" id="password" name="password" class="form-control" placeholder="Generated Password">
                            </div>
                            <button onclick="addNewUser()" type="button" class="btn btn-primary" id="addUser">Add New</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/layout/footer.jsp" %>
        <script src="js/logout.js"></script>

        <!--jquery-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <!--swal-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- side bar có thể thu nhỏ khi màn hình nhỏ  -->
        <script src="js/script.js"></script>
        <script src="js/lazyLoad.js"></script>
        <script>
        onunload = function () {
            <%
                   session.setAttribute("pageUser", null);
            %>
        };
        </script>

    </body>
</html>