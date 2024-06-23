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

        <!--Bootstrap Toggle CSS and JS--> 
        <link rel="stylesheet" href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css">
        <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- Custom CSS to make the footer fixed -->
        <style>
            body {
                padding: 0;
                margin: 0;
            }
            .subjectList {
                margin-bottom: 50px;
            }
            #searchByDimension, #searchByStatus {
                margin-left: 5%;
                padding: 5% 10%;
            }
            .heading {
                text-align: center;
            }
            .side-bar {
                z-index: 1; /* Giá trị z-index thấp hơn để bị đè */
            }
            nav {
                margin-top: -25px;
            }

            #newSubject {
                text-decoration: underline;
                margin-bottom: 20px;
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

            img{
                margin-left: 35%;
            }
        </style>

        <script>
            $(document).ready(function () {
            <c:if test="${not empty successMessage}">
                toastr.success('${successMessage}');
            </c:if>
            <c:if test="${not empty errorMessage}">
                toastr.error('${errorMessage}');
            </c:if>
            });
        </script>
    </head>

    <body>

        <%@include file="/layout/header.jsp"%>

        <section class="sliderList">
            <h1 class="heading text-center">Slider List</h1>
            <div class="container">
                <!-- Search Form -->
                <form class="form-inline" method="GET" action="sliderManager" id="searchForm">
                    <div class="form-group col-sm-2">
                        <select class="form-control" id="searchStatus" name="searchStatus" onchange="document.getElementById('searchForm').submit()">
                            <option value="">All</option>
                            <option value="1" <c:if test="${searchStatus == '1'}">selected</c:if>>Active</option>
                            <option value="0" <c:if test="${searchStatus == '0'}">selected</c:if>>Inactive</option>                           
                            </select>
                        </div>


                        <div class="form-group col-sm-8">
                            <input type="text" class="form-control" id="searchTitle" name="searchTitle" value="${searchTitle}" placeholder="Tiltle or black link">
                    </div>
                    <div class="form-group col-sm-0">
                        <a type="button" data-toggle="modal" data-target="#myModal" id="newSubject" class="">New Slider</a>
                    </div>
            </div>
        </form>
        <br/>




        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Image</th>
                    <th>BackLink</th>                         
                    <th>Status</th>
                    <th>Detail</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${listSlider}">
                    <tr>
                        <td>${item.getID()}</td>
                        <td>${item.getTitle()}</td>
                        <td style="width: 20%"><img style="max-width: 100%; margin-left: 0" src="${item.getImage()}" alt="alt"/></td>
                        <td>${item.getLinkUrl()}</td>
                        <td>
                            <label class="switch">  
                                <input type="checkbox" id="status" name="status" <c:if test="${item.getStatus() == 1}">checked</c:if> onchange="toggleStatus(${item.getID()})"/>
                                    <span class="slider round"></span>
                                </label>
                            </td>


                            <td><a href="editSlider?id=${item.getID()}" class="btn btn-info btn-sm">Detail</a></td>                             
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!-- Pagination -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:choose>
                    <c:when test="${currentPage > 1}">
                        <li>
                            <a href="sliderManager?page=${currentPage - 1}&searchStatus=${param.searchStatus}&searchTitle=${param.searchTitle}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:when>
                </c:choose>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <c:choose>
                        <c:when test="${i == currentPage}">
                            <li class="active"><a href="#">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="sliderManager?page=${i}&searchStatus=${param.searchStatus}&searchTitle=${param.searchTitle}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                <c:choose>
                    <c:when test="${currentPage < totalPages}">
                        <li>
                            <a href="sliderManager?page=${currentPage + 1}&searchStatus=${param.searchStatus}&searchTitle=${param.searchTitle}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
        </nav>
    </div>
</section>
<br/>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add New Slider</h4>
            </div>
            <div class="modal-body">
                <img src="images/slider2.jpg" alt="Slider Image" class="img-responsive" id="avatarImage" width="200px">
                <br>

                <div class="row">
                    <div class="col-sm-5"></div>
                    <button type="button" class="btn btn-primary btn-sm col-sm-3" id="uploadButton">
                        <i class="fa fa-upload"></i> Upload Image
                    </button>
                    <div class="col-sm-5"></div>
                </div>

                <input type="file" id="fileInput" style="display: none;" accept="image/*">

                <form id="addSliderForm">
                    <input type="hidden" id="thumbnail" name="image" />

                    <div class="form-group">
                        <label for="title">Title:</label>
                        <input required type="text" class="form-control" id="title" name="title"  placeholder="Enter title">
                    </div>
                    <div class="form-group">
                        <label for="subTitle">Sub Title:</label>
                        <input required type="text" class="form-control" id="subTitle" name="subTitle"  placeholder="Enter sub title">
                    </div>
                    <div class="form-group">
                        <label for="content">Content:</label>
                        <textarea required class="form-control" id="content" name="content" rows="5" placeholder="Enter content"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="linkUrl">Link URL:</label>
                        <input required type="text" class="form-control" id="linkUrl" name="linkUrl" placeholder="Enter link URL">
                    </div>
                </form>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="addSlider()">Add</button>
            </div>
        </div>

    </div>
</div>


<%@include file="/layout/footer.jsp"%>

<!-- side bar có thể thu nhỏ khi màn hình nhỏ  -->
<script src="js/script.js"></script>

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
                                avatarImage.src = 'images/' + fileName; // Update the src of avatarImage
                                console.log(fileName);

                                // Update the hidden input value to save to database
                                document.getElementById('thumbnail').value = 'images/' + fileName;
                            }
                        };
                        xhr.send(formData);
                    });
</script>
<script>
    function toggleStatus(id) {
        $.ajax({
            url: 'changeSlider',
            type: 'POST',
            data: {id: id},
            success: function (response) {
                toastr.success('Status changed successfully for ID ' + id);
            },
            error: function (error) {
                toastr.error('Failed to change status for ID ' + id);
            }
        });
    }

    function confirmDelete(id) {
        if (confirm('Are you sure you want to delete this slider?')) {
            // Uncomment the next line to actually delete
            // window.location.href = 'deleteSlider?id=' + id;
        }
    }

    $(document).ready(function () {
        $('.status-toggle').bootstrapToggle();
    });


    function addSlider() {

        var title = $('#title').val();
        var subTitle = $('#subTitle').val();
        var content = $('#content').val();
        var image = $('#thumbnail').val();
        var linkUrl = $('#linkUrl').val();

        // Perform AJAX POST request to servlet
        $.ajax({
            url: 'addSlider', // Đổi thành URL của servlet xử lý thêm slider
            type: 'POST',
            data: {

                title: title,
                subTitle: subTitle,
                content: content,
                image: image,
                linkUrl: linkUrl
            },
            success: function (response) {
                toastr.success('Slider added successfully');
                $('#myModal').modal('hide');  // Close the modal after successful addition
                // Reload the slider list or update the UI as needed
            },
            error: function (error) {
                toastr.error('Failed to add slider');
                console.error(error);
            }
        });
    }

</script>
</body>
</html>