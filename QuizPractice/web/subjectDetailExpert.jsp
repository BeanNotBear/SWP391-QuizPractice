<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.Dimension" %> <!-- Import model Dimension -->
<%@ page import="dto.SubjectPackagePriceDTO" %> <!-- Import model Dimension -->
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
            div.mce-fullscreen {
                position: fixed;
                top: 0;
                left: 0;
                margin-top: 70.96px;
            }
            .custom-btn {
                width: 83.64px !important;
            }
            .faq {
                width: 1123.94px;
            }
            .accordion {
                background-color: white;
                color: rgba(0, 0, 0, 0.8);
                cursor: pointer;
                width: 100%;
                padding: 2rem 2.5rem;
                border: none;
                outline: none;
                transition: 0.4s;
                display: flex;
                justify-content: space-between;
                align-items: center;
                font-weight: bold;
            }

            .accordion i {
                font-size: 1.6rem;
            }

            .active1,
            .accordion:hover {
                background-color: #f1f7f5;
            }
            .pannel {
                padding: 0 2rem 2.5rem 2rem;
                background-color: white;
                overflow: hidden;
                background-color: #f1f7f5;
                display: none;
                box-sizing: border-box;
            }
            .pannel p {
                color: rgba(0, 0, 0, 0.7);
                line-height: 1.4;
            }

            .faq {
                border: 1px solid rgba(0, 0, 0, 0.2);
                margin: 10px 0;
            }
            .faq.active1 {
                border: none;
            }
            .search-input {
                width: 300px;
            }
            #searchExpert, #searchLearner {
                margin-left: 830px;
            }
            #searchLesson {
                margin-left: 820px;
            }
            .epxertImg {
                width: 100px;
                height: 100px;
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
                border-radius: 30px;
                background-color: #ccc;
                -webkit-transition: .4s;
                transition: .4s;
            }

            .slider:before {
                border-radius: 30px;
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
            #statusForm {
                text-align: center;
                height: 34px;
                width: 200px;
            }
            .stt {
                font-weight: normal;
            }
            .stt-ex {
                width: 100px;
            }
            /*            #cateMsg {
                            display: none;
                        }*/
            .msg {
                display: none;
            }
            .popup {
                display: none;
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
                /*    display: flex;*/
                align-items: center;
                justify-content: center;
            }

            .form-popup {
                margin-top: 100px;
            }
        </style>
        <link rel="stylesheet" href="css/virtual-select.min.css"/>
    </head>

    <body>
        <%@include file="/layout/header.jsp"%>
        <%@include file="layout/sidebar.jsp" %>

        <section class="subjectDetailExpert">
            <h1 class="heading col-md-3">Subject Details              
            </h1>
            <div class="col-md-6"></div>
            <a class="col-md-3" id="lessonManager" href="lessonManager?subjectId=${subject.id}">Lesson Manager</a>

            <div class="container col-md-12" id="tabBar">

                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#home">Overview</a></li>
                    <li><a data-toggle="tab" href="#menu1">Lesson (${requestScope.noOfLessons})</a></li>
                    <li><a data-toggle="tab" href="#menu2">Price Package (${requestScope.noOfPackage})</a></li>
                        <c:if test="${sessionScope.user.roleId == 2}">
                        <li><a data-toggle="tab" href="#menu3">Assign Expert</a></li>
                        </c:if>
                    <li><a data-toggle="tab" href="#menu4">Learners (${requestScope.noOfStudents})</a></li>
                </ul>

                <div class="tab-content">
                    <div id="home" class="tab-pane fade in active">
                        <h3>Overview</h3>
                        <div class="container">
                            <div class="row">
                                <!--                                <div class="col-md-2"></div>-->
                                <div class="col-md-3">

                                    <img src="${subject.img}" alt="Ảnh Subject" class="img-responsive" id="avatarImage">
                                    <br>
                                    <button type="button" class="btn btn-primary btn-sm" id="uploadButton">
                                        <i class="fa fa-upload"></i> Upload Image
                                    </button>
                                    <input type="file" id="fileInput" style="display: none;">
                                </div>
                                <div class="col-md-1"></div>

                                <div class="col-md-6">
                                    <form id="subjectForm" action="subjectDetailExpert" method="POST">
                                        <input type="hidden" id="img" name="img" value="${subject.img}">
                                        <input type="hidden" id="id" name="id" value="${subject.id}">
                                        <div class="form-group">
                                            <label for="name">Name:</label>
                                            <input onkeyup="validateName(this)" type="text" id="name" name="name" class="form-control" placeholder="Enter subject name" value="${subject.name}">
                                            <div id="name-err" class="danger_msg msg">Name cannot be empty</div>
                                        </div>

                                        <div class="form-group">
                                            <label for="subjectStatus">Status:</label>
                                            <input <c:if test="${sessionScope.user.roleId != 2}">disabled=""</c:if> <c:if test="${subject.status == 1}">checked</c:if> type="radio" name="status" value="1">
                                                <label class="stt">Publish</label>
                                                    <input <c:if test="${sessionScope.user.roleId != 2}">disabled=""</c:if> <c:if test="${subject.status == 2}">checked</c:if> type="radio" name="status" value="2">
                                                <label class="stt">Unpublish</label>
                                            </div>

                                            <div class="form-group">
                                                <label for="category">Category:</label>
                                                <select required id="categorySelect" name="dimensionId" placeholder="Select Category" data-search="true" data-silent-initial-value-set="true">
                                                <c:forEach var="dimension" items="${dimensions}">
                                                    <option value="${dimension.id}" <c:if test="${dimension.id == subject.dimensionId}">selected</c:if>>${dimension.name}</option>
                                                </c:forEach>
                                            </select>
                                            <div id="cateMsg" class="danger_msg msg">Must select category</div>
                                        </div>

                                        <div class="form-group">
                                            <label>Owner(Expert):</label>
                                            <br>
                                            <div id="expertProfile" class="form-group row">
                                                <img class="col-md-3" src="${requestScope.owner.img}" alt="alt"/>
                                                <div class="col-md-6">
                                                    <div>Full Name: ${requestScope.owner.name}</div>
                                                    <div>Gender: ${requestScope.owner.gender == 1 ? "Male" : "Female"}</div>
                                                    <div>Email: ${requestScope.owner.email}</div>
                                                    <div>Phone Number: ${requestScope.owner.phoneNumber}</div>
                                                    <div>Joined Date: ${requestScope.owner.date}</div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="description">Description: </label>
                                            <textarea required id="description" name="description" class="form-control textEditor" rows="5" placeholder="Enter description">${subject.description}</textarea>
                                            <div id="desMsg" class="danger_msg msg">Description can not be empty!</div>
                                        </div>

                                        <input onclick="saveChangeSubject(this)" class="btn btn-primary btn-sm col-md-2" type="button" value="Save"/>
                                        <div class="col-md-1"></div>
                                        <a href="/QuizPractice/subjectManager" class="btn btn-primary btn-sm col-md-2">Back to list</a>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="menu1" class="tab-pane fade">
                        <h3>Lesson</h3>
                        <!--<a class="col-md-3" id="addLessonbtn">Add Lesson</a>-->
                        <input id="searchLesson" onkeyup="searchLesson(this)" class="search-input" type="text" name="search" placeholder="Search Lesson">
                        <div id="lessonContent">
                            <c:forEach var="i" items="${lessons}">
                                <div class="faq">
                                    <button class="accordion">
                                        Lesson ${i.lessonIndex} : ${i.name}
                                        <i class="fa-solid fa-chevron-down"></i>
                                    </button>
                                    <div class="pannel">
                                        <div>Type: <b><i>${i.type}</i></b></div>
                                        <p>
                                            Content: ${i.content}
                                        </p>
                                    </div>
                                </div> 
                            </c:forEach>
                        </div>
                        <c:set var="pageLess" value="${sessionScope.pageLesson != null ? sessionScope.pageLesson : 1}"/>
                        <a onclick="loadMoreLesson()" >Load More <i class="fa-solid fa-chevron-down"></i></a>
                    </div>

                    <div id="menu2" class="tab-pane fade">
                        <h3 class="col-md-3">Package Price</h3>
                        <c:if test="${sessionScope.user.roleId == 2}">
                            <a style="margin-left: 1000px;" onclick="showPopUp('Add New')">Add Package Price</a>
                        </c:if>
                        <div style="padding-left: 0; margin-bottom: 10px" class="col-md-6">
                            <input <c:if test="${sessionScope.user.roleId == 3}"> style="margin-left: 550px; margin-top: 10px;" </c:if> onkeyup="searchPricePackage(this)" class="search-input" type="text" placeholder="Search Price Package">
                        </div>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>PackageName</th>
                                    <th>Duration (Month)</th>
                                    <th>Price (VND)</th>
                                    <th>Sale Price (VND)</th>
                                    <th>Original Price (VND)</th>
                                    <th>Status</th>
                                        <c:if test="${sessionScope.user.roleId == 2}">
                                        <th>Action</th>
                                        </c:if>
                                </tr>
                            </thead>
                            <tbody id="pricePackageContent">
                                <c:forEach var="mypackage" items="${packageList}">
                                    <tr>
                                        <td>${mypackage.getId()}</td>
                                        <td>${mypackage.getPackageName()}</td>
                                        <td>${mypackage.getDuration()}</td>   
                                        <td>${mypackage.getPrice()}</td>
                                        <td>${mypackage.getSalePrice()}</td>
                                        <td>${mypackage.getOriginalPrice()}</td>
                                        <td>${mypackage.getStatus()}</td>
                                        <c:if test="${sessionScope.user.roleId == 2}">
                                            <td>
                                                <c:choose>
                                                    <c:when test="${mypackage.getStatus() eq 'active'}">
                                                        <a href="#" class="btn btn-danger btn-sm" onclick="changeStatus(${mypackage.getId()}, 'inactive',${subject.id})">Inactive</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="#" class="btn btn-success btn-sm" onclick="changeStatus(${mypackage.getId()}, 'active',${subject.id})">Active</a>
                                                    </c:otherwise>
                                                </c:choose>
                                                <a onclick="showPopUpEdit(event, 'Edit')" class="btn btn-warning btn-sm edit-btn">Edit</a>
                                            </td>
                                        </c:if>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <a onclick="loaadMorePackagePrice()" >Load More <i class="fa-solid fa-chevron-down"></i></a>
                    </div>

                    <div id="menu3" class="tab-pane fade">
                        <h3 class="col-md-3">Expert</h3>
                        <div>
                            <input onkeyup="searchExpert(this)" id="searchExpert" class="search-input" type="text" name="name" placeholder="Seach expert">
                        </div>
                        <div class="col-md-6"></div>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Phone Number</th>
                                    <th>Gender</th>
                                    <th>Joined Date</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody id="expertTab">
                                <c:forEach var="e" items="${experts}">
                                    <tr>
                                        <td>${e.id}</td>
                                        <td><img class="epxertImg" src="${e.img}" alt="alt"/></td>
                                        <td>${e.name}</td>   
                                        <td>${e.email}</td>
                                        <td>${e.phoneNumber}</td>
                                        <td>${e.gender == 1 ? "Male" : "Female"}</td>
                                        <td>${e.date}</td>
                                        <td>
                                            <a onclick="assignExpert(${e.id})" class="btn btn-success btn-sm">Assign</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <a onclick="loadMoreExpert()" >Load More <i class="fa-solid fa-chevron-down"></i></a>
                    </div>

                    <div id="menu4" class="tab-pane fade">
                        <h3 class="col-md-3">Learners</h3>
                        <input onkeyup="searchLearner(this)" placeholder="Search learner" id="searchLearner" class="search-input" type="text">
                        <div class="col-md-6"></div>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Full Name</th>
                                    <th>Avatar</th>
                                    <th>Email</th>
                                    <th>Gender</th>
                                    <th>Phone Number</th>
                                </tr>
                            </thead>
                            <tbody id="learnerContent">
                                <c:forEach var="learner" items="${requestScope.learners}">
                                    <tr>
                                        <td>${learner.id()}</td>
                                        <td>${learner.fullName()}</td>
                                        <td><img src="${learner.profileImg()}" alt="avatar"/></td>   
                                        <td>${learner.email()}</td>
                                        <td>${learner.gender() == 1 ? "Male" : "Female"}</td>
                                        <td>${learner.phoneNumber()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <a onclick="loadMoreLearner()">Load More <i class="fa-solid fa-chevron-down"></i></a>
                    </div>
                </div>
            </div>
        </section>
        <br/>
        <%@include file="/layout/footer.jsp" %>
        <div id="addLesson" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Add Lesson</h4>
                    </div>
                    <div class="modal-body">
                        <form id="addLesson">
                            <div class="form-group">
                                <label for="name">Name:</label>
                                <input type="text" id="name" name="name" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="editDimensionName">Dimension Name:</label>
                                <input type="text" id="editDimensionName" name="dimensionName" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="editDescription">Description:</label>
                                <input type="text" id="editDescription" name="description" class="form-control">
                            </div>
                            <button type="button" class="btn btn-primary" id="saveDimensionChanges">Save Changes</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!--add package price pop up-->
        <div id="modalPackage" class="popup" role="dialog">
            <div class="form-container">
                <div>
                    <div>
                        <form class="form-popup">
                            <button onclick="hidePopUp()" type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4>Package Price</h4>
                            <input id="packageId" type="hidden">
                            <div class="form-group">
                                <label for="packageName">Name: <span class="danger_msg">(require)</span></label>
                                <input onkeyup="validatePackageName(this)" type="text" id="packageName" name="packageName" class="form-control">
                                <div id="namePkMsg" class="msg danger_msg">Name cannot be empty</div>
                            </div>
                            <div class="form-group">
                                <label for=packageDuration>Duration (month): <span class="danger_msg">(require)</span></label>
                                <input onkeyup="validateDuration(this)" type=number id="packageDuration" name="packageDuration" class="form-control">
                                <div id="durationMsg" class="msg danger_msg"></div>
                            </div>
                            <div class="form-group">
                                <label for="salePrice">Sale Price (vnd): <span class="danger_msg">(require)</span></label>
                                <input onkeyup="validateSalePrice(this)" type="number" id="salePrice" name="salePrice" class="form-control">
                                <div id="salePriceMsg" class="msg danger_msg"></div>
                            </div>
                            <div class="form-group">
                                <label for="price">Price (vnd): <span class="danger_msg">(require)</span></label>
                                <input onkeyup="validatePrice(this)" type="number" id="price" name="price" class="form-control">
                                <div id="priceMsg" class="msg danger_msg"></div>
                            </div>
                            <div class="form-group">
                                <label for="originalPrice">Original Price (vnd): <span class="danger_msg">(require)</span></label>
                                <input onkeyup="validateOriginalPrice(this)" type="number" id="originalPrice" name="originalPrice" class="form-control">
                                <div id="originalPriceMsg" class="msg danger_msg"></div>
                            </div>
                            <!--                            <div class="form-group">
                                                            <label for="statusPackagePrice">Status:</label>
                                                            <select class="form-group" id="statusPackagePrice">
                                                                <option value="active">Active</option>
                                                                <option selected value="inactive">Inactive</option>
                                                            </select>
                                                        </div>-->
                            <button onclick="addNew()" onmouseover="addPackagePrice(this)" type="button" class="btn btn-primary" id="addPackage">Add New</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            var numberOfStudent = '${requestScope.noOfStudents}';
            document.getElementById('uploadButton').addEventListener('click', function () {
                document.getElementById('fileInput').click();
            });

            document.getElementById('fileInput').addEventListener('change', function () {
                var file = this.files[0];
                var formData = new FormData();
                formData.append('file', this.files[0]);
                var allowedTypes = ['image/jpeg', 'image/png'];
                if (allowedTypes.indexOf(file.type) === -1) {
                    Swal.fire({
                        title: "Only allowed to upload image formats: JPEG, PNG",
                        icon: "error"
                    }).then(() => {
                        // Reset giá trị của input file để người dùng có thể chọn tệp khác
                        document.getElementById('fileInput').value = "";
                    });
                    return;
                }
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
                        document.getElementById('img').value = 'images/' + fileName;
                    }
                };
                xhr.send(formData);
            });

            // Function to handle status change
            function changeStatus(packageId, newStatus, subjectId) {
                var confirmation = confirm('Are you sure you want to change the status?');
                if (confirmation) {
                    $.ajax({
                        type: 'GET',
                        url: 'changePackageStatus',
                        data: {id: packageId, status: newStatus, subjectId: subjectId},
                        success: function (response) {
                            if (response.success) {
                                location.reload(); // Reload the page to reflect changes
                            } else {
                                alert('Failed to change status');
                            }
                        },
                        error: function () {
                            alert('Error occurred while changing status');
                        }
                    });
                }
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/@tinymce/tinymce-webcomponent@2/dist/tinymce-webcomponent.min.js"></script>
        <!-- side bar có thể thu nhỏ khi màn hình nhỏ  -->
        <script src="js/script.js"></script>
        <script src="js/virtual-select.min.js"></script>
        <script src="js/tinymce/tinymce.min.js"></script>
        <script src="js/textEditor.js"></script>
        <script>
            VirtualSelect.init({
                ele: '#categorySelect'
            });
        </script> 
        <script src="js/SubjectDetails.js"></script>
        <script src="js/LoadMore.js"></script>
        <script>
            onunload = function () {
            <%
                    session.setAttribute("page", null);
                    session.setAttribute("search", "");
                    session.setAttribute("pageLesson", null);
                    session.setAttribute("searchLesson", "");
                    session.setAttribute("leanerPage", null);
                    session.setAttribute("searchLearner", "");
                    session.setAttribute("pagePackage", null);
                    session.setAttribute("searchPackage", "");
            %>
            };
        </script>
        <script src="js/Searching.js"></script>
        <script src="js/AssignExpert.js"></script>
        <script src="js/SaveChangeSubbject.js"></script>
        <script src="js/PackagePrice.js"></script>
        <script src="js/logout.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js" integrity="sha512-ykZ1QQr0Jy/4ZkvKuqWn4iF3lqPZyij9iRv6sGqLRdTPkY69YX6+7wvVGmsdBbiIfN/8OdsI7HABjvEok6ZopQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </body>
</html>