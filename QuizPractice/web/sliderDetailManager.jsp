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
                z-index: 10; /* Giá trị z-index cao để đè lên sidebar */
                padding-right: 20%;
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
                padding-top: 6%;
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
            
            .container{
                margin-bottom: 10%;
                
            }
            
        </style>
    </head>

    <body>
        <%@include file="/layout/header.jsp"%>

        <section class="sliderList">
            <h1 class="heading text-center">Slider Detail</h1>
            <div class="container">
                <form id="sliderForm" action="editSlider" method="POST">
                    <input type="hidden" class="form-control" id="id" name="id" value="${slider.ID}">
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" class="form-control" id="title" name="title" value="${slider.title}">
                </div>
                <div class="form-group">
                    <label for="subTitle">Sub Title:</label>
                    <input type="text" class="form-control" id="subTitle" name="subTitle" value="${slider.subTitle}">
                </div>
                <div class="form-group">
                    <label for="content">Content:</label>
                    <textarea class="form-control" id="content" name="content" rows="5">${slider.content}</textarea>
                </div>
                <div class="form-group">
                    <label for="image">Image:</label>
                    <input type="text" class="form-control" id="image" name="image" value="${slider.image}">
                </div>
                <div class="form-group">
                    <label for="linkUrl">Link URL:</label>
                    <input type="text" class="form-control" id="linkUrl" name="linkUrl" value="${slider.linkUrl}">
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select class="form-control" id="status" name="status">
                        <option value="0" ${slider.status == 0 ? 'selected' : ''}>Inactive</option>
                        <option value="1" ${slider.status == 1 ? 'selected' : ''}>Active</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary" >Save</button>
            </form>
            </div>
        </section>



        <%@include file="/layout/footer.jsp"%>

        <!-- side bar có thể thu nhỏ khi màn hình nhỏ  -->
        <script src="js/script.js"></script>

    </body>
</html>