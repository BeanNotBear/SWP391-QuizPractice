<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Multiple Choice Quiz App</title>
        <link rel="stylesheet" href="css/style.css">
        <style>
            .container {
                margin-top: 100px;
                width: 90%;
                background-color: white;
                border-radius: 10px;
            }
            .header-quiz {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
            .progress-bar {
                width: 100%;
                background-color: #2e2d4d;
                height: 10px;
                border-radius: 5px;
                margin-bottom: 20px;
            }
            .progress {
                width: 0%;
                height: 100%;
                background-color: #4caf50;
                border-radius: 5px;
                transition: width 0.5s ease-in-out;
            }
            .question-card {
                border-radius: 10px;
                padding: 20px;
                margin-bottom: 20px;
            }
            .question {
                font-size: 18px;
                margin-bottom: 20px;
            }
            .options {
                display: grid;
                grid-template-columns: 1fr 1fr;
                gap: 10px;
            }
            .option {
                border: 1px solid;
                border-radius: 10px;
                color: var(--main-color);
                padding: 10px;
                text-align: left;
                text-decoration: none;
                font-size: 16px;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            .option:hover {
                background-color: #3e3d5d;
                color: white;
            }
            .dont-know {
                text-align: center;
                margin-top: 10px;
                color: #6c6b8a;
                cursor: pointer;
                font-size: 20px;
            }
            .feedback {
                margin-top: 20px;
                padding: 10px;
                border-radius: 5px;
                font-size: 20px;
                text-align: center;
                font-weight: bold;
            }
            .correct {
                background-color: #4caf50;
            }
            .incorrect {
                background-color: #f44336;
            }
            .results {
                text-align: center;
                font-size: 18px;
                margin-top: 20px;
            }
            .try-again {
                background-color: #4caf50;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin-top: 20px;
                cursor: pointer;
                border-radius: 5px;
            }
        </style>
    </head>
    <body >
        <c:import url="layout/header.jsp" />
        <c:import url="layout/sidebar.jsp" />
        <section class="container">
            <div>
                <div class="header-quiz">
                    <h1>Practice</h1>
                    <h2>Practice Mode</h2>
                </div>
                <div class="progress-bar">
                    <div style="width: 0%" class="progress" id="progress"></div>
                </div>
                <div class="question-card">
                    <div class="question" id="question"></div>
                    <div class="options" id="options"></div>
                    <div class="dont-know" id="dontKnow">Don't know?</div>
                    <div class="feedback" id="feedback"></div>
                </div>
                <div class="results" id="results"></div>
            </div>

        </section>

        <audio style="display: none;" id="correctSound" src="sound/Correct.mp3"></audio>
        <audio style="display: none;" id="incorrectSound" src="sound/Wrong.mp3"></audio>
        <script>
            var quizData = ${requestScope.quizData};
        </script>
        <script src="js/quizHandle.js">
        </script>
        <script src="js/script.js"></script>
    </body>
</html>