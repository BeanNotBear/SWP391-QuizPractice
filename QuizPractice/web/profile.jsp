<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>
        <!-- header -->
        <%@include file="layout/header.jsp" %>

        <!-- sidebar -->
        <%@include file="layout/sidebar.jsp" %>
        <section>
            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-3">
                        <div class="text-center mb-3">
                            <img src="avatar.png" class="rounded-circle" alt="Avatar" width="100">
                            <div>
                                <button type="button" class="btn btn-primary btn-sm mt-2">Upload new photo</button>
                            </div>
                        </div>
                        <ul class="list-group" id="sidebar">
                            <li class="list-group-item active" data-target="general">General</li>
                            <li class="list-group-item" data-target="change-password">Change password</li>
                            <li class="list-group-item" data-target="info">Info</li>
                        </ul>
                    </div>
                    <div class="col-md-9">
                        <div class="card">
                            <div class="card-body">
                                <div id="general" class="content">
                                    <form action="profile" method="post">
                                        <div class="form-group">
                                            <label for="username">First Name:</label>
                                            <input type="text" class="form-control" id="first-name" value="${user.firstName}">
                                        </div>
                                        <div class="form-group">
                                            <label for="name">Last Name:</label>
                                            <input type="text" class="form-control" id="last-name" value="${user.lastName}">
                                        </div>
                                        <div class="form-group">
                                            <label for="email">Email:</label>
                                            <input type="email" class="form-control" id="email" value="${user.email}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="company">Phone Number:</label>
                                            <input type="text" class="form-control" id="phone-number" value="${user.phoneNumber}">
                                        </div>
                                        <div class="form-group">
                                            <label for="company">Gender:</label>
                                            <input type="text" class="form-control" id="gender" value="${user.gender}">
                                        </div>
                                        <div class="form-group">
                                            <label for="company">Date of birth:</label>
                                            <input type="text" class="form-control" id="dob" value="${user.dob}">
                                        </div>
                                        <div class="form-group">
                                            <label for="company">Username:</label>
                                            <input type="text" class="form-control" id="username" value="${user.username}">
                                        </div>
                                        <div class="form-group">
                                            <label for="company">Created At:</label>
                                            <input type="text" class="form-control" id="created-at" value="${user.createdAt}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="company">Updated At:</label>
                                            <input type="text" class="form-control" id="company" value="${user.updatedAt}" readonly>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Save changes</button>
                                        <button type="reset" class="btn btn-secondary">Cancel</button>
                                    </form>
                                </div>
                                <div id="change-password" class="content d-none">
                                    <form action="change-password" method="post">
                                        <div class="form-group">
                                            <label for="current-password">Current password</label>
                                            <input type="password" class="form-control" id="current-password">
                                        </div>
                                        <div class="form-group">
                                            <label for="new-password">New password</label>
                                            <input type="password" class="form-control" id="new-password">
                                        </div>
                                        <div class="form-group">
                                            <label for="repeat-new-password">Repeat new password</label>
                                            <input type="password" class="form-control" id="repeat-new-password">
                                        </div>
                                        <div class="box-container">
                                            <button type="submit" class="btn btn-primary">Save changes</button>
                                            <button type="reset" class="btn btn-secondary">Cancel</button>
                                        </div>
                                    </form>
                                </div>
                                <!-- Add other content divs similarly, setting them as d-none initially -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="layout/footer.jsp" %>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="js/script.js"></script>
        <script >
            document.addEventListener('DOMContentLoaded', function () {
                var sidebarItems = document.querySelectorAll('#sidebar .list-group-item');
                var contentDivs = document.querySelectorAll('.content');

                sidebarItems.forEach(function (item) {
                    item.addEventListener('click', function () {
                        sidebarItems.forEach(function (i) {
                            i.classList.remove('active');
                        });
                        item.classList.add('active');

                        var target = item.getAttribute('data-target');
                        contentDivs.forEach(function (div) {
                            if (div.id === target) {
                                div.classList.remove('d-none');
                            } else {
                                div.classList.add('d-none');
                            }
                        });
                    });
                });
            });
        </script>
    </body>
</html>