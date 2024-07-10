package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/AddQuestionToQuiz")
public class AddQuestionToQuizController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("quizId", request.getParameter("quizId"));
        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("level", request.getParameter("level"));
        request.setAttribute("numberQuestion", request.getParameter("numberQuestion"));
        request.setAttribute("duration", request.getParameter("duration"));
        request.setAttribute("subject", request.getParameter("subject"));
        request.setAttribute("lesson", request.getParameter("lesson"));

        request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
    }
}