<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dash Board</title>

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/popup.css">
        <!-- Bootstrap file link  -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/virtual-select.min.css"/>
        <style>
            .chart-area{
                width: 30%;
                box-shadow: 10px 10px 5px lightblue;
                border: 1px solid;
                margin: 20px;
            }

            #line-chart {
                width: 66.66%; /* 2/3 của hàng */
                box-shadow: 10px 10px 5px lightblue;
                border: 1px solid;
                margin: 20px auto;
            }

            .bar-chart {
                width: 48%;
                box-shadow: 10px 10px 5px lightblue;
                border: 1px solid;
                margin: 20px auto;
            }

            .chart-name {
                text-align: center;
                font-weight: bold;
            }

            .flex {
                display: flex;
            }

            .format-number {
                font-size: 20px;
                font-weight: bold;
            }

            .tag {
                box-sizing:  border-box;
                border: 1px solid;
                border-radius: 10px;
                margin: 10px 50px;
                padding: 10px 50px;
                width: 30%;
                box-shadow: 10px 10px 5px lightblue;
                background-color: var(--light-bg);
                color: var(--black);
            }

            .area {
                margin: 20px;
            }

            .most-subject {
                box-sizing: border-box;
                width: 100%;
                padding: 10px;

            }
        </style>
    </head>

    <body id="content">
        <%@include file="/layout/header.jsp"%>
        <div class="side-bar">
            <div id="close-btn">
                <i class="fas fa-times"></i>
            </div>

            <div class="profile">
                <img src="${sessionScope.user.profileImg}" class="image" alt="">
                <br>
                <h3 class="name">${sessionScope.user.fullName}</h3>
                <br>
                <c:if test="${sessionScope.user.roleId == 1}">
                    <p class="role">Customer</p>
                </c:if>
                <c:if test="${sessionScope.user.roleId == 2}">
                    <p class="role">Admin</p>
                </c:if>
                <c:if test="${sessionScope.user.roleId == 3}">
                    <p class="role">Expert</p>
                </c:if>
                <c:if test="${sessionScope.user.roleId == 4}">
                    <p class="role">Sale</p>
                </c:if>
                <c:if test="${sessionScope.user.roleId == 4}">
                    <p class="role">Marketing</p>
                </c:if>
                <br>
                <a onclick="openPopUp2()" class="option-btn">view profile</a>
            </div>

            <!--            <nav class="navbar">
                            <a href=""><i class="fas fa-home"></i><span>Home</span></a>
                            <a href=""><i class="fas fa-graduation-cap"></i><span>Subjects</span></a>
                            <a href=""><i class="fas fa-newspaper"></i><span>Posts</span></a>
                        </nav>-->
        </div>
        <section>
            <div class="flex">
                <div class="tag">
                    <label>Total Revenue</label>
                    <div class="format-number">${requestScope.totalRevenue} VND</div>
                </div>

                <div class="tag">
                    <label>Number of Enrollments</label>
                    <div class="format-number">${requestScope.numberOfEnrollments} users</div>
                </div>

                <div class="tag">
                    <label>Total Profit</label>
                    <div class="format-number">${requestScope.totalProfit} VND</div>
                </div>

            </div>
            <div class="flex area">
                <div class="chart-area">
                    <div class="chart-name">Number of subject registrations</div>
                    <div class="chart">
                        <canvas id="myChart"></canvas>
                    </div>
                </div>

                <div id="line-chart" class="chart-area">
                    <div class="chart-name">Enrollments</div>
                    <div class="chart">
                        <canvas id="myChart2"></canvas>
                    </div>
                </div>


            </div>
            <div class="flex area">
                <div class="chart-area bar-chart">
                    <div class="chart-name">Profit</div>
                    <div class="chart">
                        <canvas id="myChart3"></canvas>
                    </div>
                </div>

                <div class="chart-area bar-chart">
                    <div class="chart-name">Revenue</div>
                    <div class="chart">
                        <canvas id="myChart4"></canvas>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="/layout/script.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
                    const ctx = document.getElementById('myChart');
                    let ecdtosJson = ${requestScope.ecdtosJson};
                    let lables = ecdtosJson.map(e => e.name);
                    let data = ecdtosJson.map(e => e.numberOfEnrollments);
                    new Chart(ctx, {
                        type: 'pie',
                        data: {
                            labels: lables,
                            datasets: [{
                                    label: 'Number of Enrollments',
                                    data: data,
                                    borderWidth: 1
                                }]
                        },
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        }
                    });

                    const ctx2 = document.getElementById('myChart2');

                    new Chart(ctx2, {
                        type: 'line',
                        data: {
                            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                            datasets: [{
                                    label: 'Number of Enrollments',
                                    data: [0, 0, 0, 5, 0, 3, 0, 0, 3, 4, 0, 3],
                                    borderWidth: 1
                                }]
                        },
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        }
                    });
                    
                    const ctx3 = document.getElementById('myChart3');

                    new Chart(ctx3, {
                        type: 'bar',
                        data: {
                            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                            datasets: [{
                                    label: '# of Votes',
                                    data: [1100000, 1911345, 1000000, 1000000, 1000000, 1000000, 1000000, 2000000, 2000000, 3000000, 2000000, 3000000],
                                    borderWidth: 1
                                }]
                        },
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        }
                    });

                    const ctx4 = document.getElementById('myChart4');

                    new Chart(ctx4, {
                        type: 'bar',
                        data: {
                            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                            datasets: [{
                                    label: '# of Votes',
                                    data: [1200000, 1912345, 3000000, 5000000, 2000000, 3000000, 2000000, 3000000, 2000000, 3000000, 2000000, 3000000],
                                    borderWidth: 1
                                }]
                        },
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        }
                    });

                    function formatNumber(num) {
                        // Convert number to a string and ensure it has a decimal point
                        let numStr = Number(num).toFixed(1);

                        // Split the number into integer and decimal parts
                        let [integerPart, decimalPart] = numStr.split('.');

                        // Add thousands separators
                        integerPart = integerPart.replace(/\B(?=(\d{3})+(?!\d))/g, ".");

                        // Format the number with a comma as the decimal separator
                        if(decimalPart != 0) {
                            return integerPart + ',' + decimalPart;
                        }
                        return integerPart;
                    }

                    let numElements = document.getElementsByClassName('format-number');
                    for (let i = 0; i < numElements.length; i++) {
                        let num = numElements[i].innerText;
                        numElements[i].innerText = formatNumber(num.split(' ')[0]) + ' ' + num.split(' ')[1];
                    }
        </script>
    </body>
</html>
