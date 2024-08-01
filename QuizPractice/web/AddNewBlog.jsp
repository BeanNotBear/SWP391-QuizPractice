<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Blog</title>

    <!-- Font Awesome CDN link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

    <!-- Custom CSS file link -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/popup.css">

    <!-- Bootstrap file link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <!-- Custom CSS to make the footer fixed -->
    <style>
        body {
            padding: 0;
            margin: 0;
        }

        .postDetail {
            margin-bottom: 100px;
        }
        nav {
            margin-top: -25px;
        }
        img {
            width: 100%;
        }
        button {
            width: 20%;
        }
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
        .slider.round {
            border-radius: 34px;
        }
        .slider.round:before {
            border-radius: 50%;
        }
        .swal2-styled {
            padding: 6.250px 25px 6.250px 11px !important;
        }
    </style>
</head>

<body>
    <%@include file="/layout/header.jsp"%>
    <section class="postDetail">
        <h1 class="heading text-center">New Blog</h1>
        <div class="container">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-3">
                    <img alt="Image Blog" class="img-responsive" id="avatarImage">
                    <br>
                    <button type="button" class="btn btn-primary btn-sm" id="uploadButton">
                        <i class="fa fa-upload"></i> Upload image
                    </button>
                    <input accept="image/png, image/jpeg" type="file" id="fileInput" style="display: none;" required>
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-6">
                    <form action="addnewblog" method="post" id="postUpdateForm">
                        <input type="hidden" id="id" name="id">
                        <input type="hidden" id="thumbnail" name="thumbnail">
                        <div class="form-group">
                            <label for="title">Title:</label>
                            <input type="text" name="title" id="title" required class="form-control" placeholder="Input Title....">
                        </div>
                        <div class="form-group">
                            <label for="brief">Brief Info:</label>
                            <textarea id="brief" name="briefinfo" required class="form-control" rows="3" placeholder="Input information..."></textarea>
                        </div>
                        <div class="form-group">
                            <label for="category">Category:</label>
                            <select id="category" name="categoryName" class="form-control" required>
                                <option disabled selected>Choose category</option>
                                <c:forEach var="category" items="${listCategory}">
                                    <option value="${category.category_Name}">${category.category_Name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="content">Content:</label>
                            <textarea id="content" name="content" required class="form-control" rows="5" placeholder="Input content.........."></textarea>
                        </div>
                        <div class="form-group">
                            <label for="status">Status:</label>
                            <br/>
                            <label class="switch">
                                <input type="checkbox" id="status" name="status">
                                <span class="slider round"></span>
                            </label>
                        </div>
                        <button type="submit" class="btn btn-primary btn-sm">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <%@include file="/layout/footer.jsp" %>
    <!-- Scripts -->
    <script>
        document.getElementById('uploadButton').addEventListener('click', function () {
            document.getElementById('fileInput').click();
        });

        document.getElementById('fileInput').addEventListener('change', function () {
            var file = this.files[0];
            var formData = new FormData();

            // Ki?m tra ??nh d?ng t?p
            var allowedTypes = ['image/jpeg', 'image/png'];
            if (allowedTypes.indexOf(file.type) === -1) {
                Swal.fire({
                    title: "Only allowed to upload image formats: JPEG, PNG",
                    icon: "error"
                }).then(() => {
                    // Reset giá tr? c?a input file ?? ng??i dùng có th? ch?n t?p khác
                    document.getElementById('fileInput').value = "";
                });
                return;
            }

            formData.append('file', file);

            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'upload', true);
            xhr.onload = function () {
                if (xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    var fileName = response.fileName;
                    var avatarImage = document.getElementById('avatarImage');
                    avatarImage.src = 'images/' + fileName; // Thay ??i src c?a hình ?nh avatarImage
                    console.log(fileName);

                    // C?p nh?t giá tr? c?a hidden input ?? l?u vào database
                    document.getElementById('thumbnail').value = 'images/' + fileName;

                    // Thông báo c?p nh?t thành công
                    Swal.fire({
                        title: "Image uploaded successfully",
                        icon: "success"
                    });
                } else {
                    // Thông báo l?i khi upload th?t b?i
                    Swal.fire({
                        title: "Image upload failed",
                        icon: "error"
                    });
                }
            };
            xhr.send(formData);
        });

        $(document).ready(function() {
            $('#postUpdateForm').submit(function(e) {
                e.preventDefault();
                
                // Ki?m tra n?u thumbnail ?ã ???c c?p nh?t
                var thumbnail = $('#thumbnail').val();
                if (!thumbnail) {
                    Swal.fire({
                        title: 'Please upload an image before saving.',
                        icon: 'error'
                    });
                    return; // Ng?ng g?i form
                }
                
                var formData = {
                    id: $('#id').val(),
                    thumbnail: $('#thumbnail').val(),
                    title: $('#title').val(),
                    briefinfo: $('#brief').val(),
                    categoryName: $('#category').val(),
                    content: $('#content').val(),
                    status: $('#status').prop('checked') ? 'on' : 'off'  // Check the status of the checkbox
                };

                $.ajax({
                    type: 'POST',
                    url: 'addnewblog',
                    data: formData,
                    success: function(response) {
                        if (response.status === 'success') {
                            Swal.fire({
                                title: response.message,
                                icon: 'success'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    window.location.href = 'blog'; // Chuy?n h??ng ??n danh sách blog ho?c trang c?n thi?t
                                }
                            });
                        } else {
                            Swal.fire({
                                title: response.message,
                                icon: 'error'
                            });
                        }
                    },
                    error: function() {
                        Swal.fire({
                            title: 'An error occurred. Please try again.',
                            icon: 'error'
                        });
                    }
                });
            });
        });
    </script>
    <%@include file="layout/script.jsp" %>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js" integrity="sha512-+k1pnlgt4F1H8L7t3z95o3/KO+o78INEcXTbnoJQ/F2VqDVhWoaiVml/OEHv9HsVgxUaVW+IbiZPUJQfF/YxZw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>
