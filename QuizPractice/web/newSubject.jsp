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
            body {
                padding: 0;
                margin: 0;
            }
            .footer {
                background: #f8f9fa;
                text-align: center;
                position: fixed;
                bottom: 0;
                width: 100%;
                height: 15%;
            }
            .postDetail {
                margin-bottom: 100px;

            }
            nav {
                margin-top: -25px;
            }

            img{
                width: 100%;
            }
            button{
                width: 20%;
            }


            /* */
            .switch {
                position: relative;
                display: inline-block;
                width: 60px;
                height: 34px;
            }

            .switch input {
                opacity: 0;
                width: 0;
                height: 0;
            }

            .slider {
                position: absolute;
                cursor: pointer;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                -webkit-transition: .4s;
                transition: .4s;
            }

            .slider:before {
                position: absolute;
                content: "";
                height: 26px;
                width: 26px;
                left: 4px;
                bottom: 4px;
                background-color: white;
                -webkit-transition: .4s;
                transition: .4s;
            }

            input:checked + .slider {
                background-color: #2196F3;
            }

            input:focus + .slider {
                box-shadow: 0 0 1px #2196F3;
            }

            input:checked + .slider:before {
                -webkit-transform: translateX(26px);
                -ms-transform: translateX(26px);
                transform: translateX(26px);
            }

            /* Rounded sliders */
            .slider.round {
                border-radius: 34px;
            }

            .slider.round:before {
                border-radius: 50%;
            }






        </style>
    </head>

    <body>
        <%@include file="/layout/header.jsp"%>

        <section class="postDetail">
            <h1 class="heading text-center">New Subject</h1>

            <div class="container">
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <img src="./images/anhgt_1.jpg" alt="Ảnh Subject" class="img-responsive" id="avatarImage">
                        <br>
                        <button type="button" class="btn btn-primary btn-sm" id="uploadButton">
                            <i class="fa fa-upload"></i> Upload Image
                        </button>
                        <!-- Input để chọn file ảnh, không hiển thị cho người dùng -->
                        <input type="file" id="fileInput" style="display: none;">
                    </div>
                    <div class="col-md-1"></div>

                    <div class="col-md-6">
                        <form action="newSubject" method="POST">
                            <input type="hidden" id="img" name="img" value="./images/anhgt_1.jpg">
                            <div class="form-group">
                                <label for="name">Name:</label>
                                <input type="text" id="name" name="name" class="form-control" placeholder="Enter subject name">
                            </div>

                            <div class="form-group">
                                <label for="category">Dimension:</label>
                                <select id="dimension" name="dimensionId" class="form-control">
                                    <option value="" disabled selected>Chọn dimension</option>
                                    <c:forEach var="dimension" items="${dimensions}">
                                        <option value="${dimension.id}">${dimension.name}</option>
                                    </c:forEach>
                                </select>
                            </div>


                            <div class="form-group">
                                <label for="description">Description: </label>
                                <textarea id="description" name="description" class="form-control" rows="5" placeholder="Enter description"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary btn-sm col-md-2">Save</button>
                            <div class="col-md-1"></div>
                            <a href="/QuizPractice/subjectManager" class="btn btn-primary btn-sm col-md-2">Back to list</a>
                        </form>
                    </div>


                </div>
            </div>
        </section>
        <br/>

        <%@include file="/layout/footer.jsp" %>

        <%@include file="/layout/script.jsp" %>
        
         <!-- Script để xử lý upload ảnh -->
    <script>
        document.getElementById('uploadButton').addEventListener('click', function () {
            document.getElementById('fileInput').click();
        });

        document.getElementById('fileInput').addEventListener('change', function () {
            var formData = new FormData();
            formData.append('file', this.files[0]);

            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'upload', true);
            xhr.onload = function () {
                if (xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    var fileName = response.fileName;
                    var avatarImage = document.getElementById('avatarImage');
                    avatarImage.src = 'images/' + fileName; // Thay đổi src của hình ảnh avatarImage
                    console.log(fileName);

                    // Cập nhật giá trị của hidden input để lưu vào database
                    document.getElementById('img').value = 'images/'+fileName;
                }
            };
            xhr.send(formData);
        });
    </script>
        
        
        <!-- Bootstrap Toggle JS and CSS (optional) -->
        <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
        <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    </body>
</html>