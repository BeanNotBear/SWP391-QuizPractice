package controller;

import dal.QuizDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DeleteQuestionFromQuizServlet")
public class DeleteQuestionFromQuizServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        int numberQuestion = Integer.parseInt(request.getParameter("numberQuestion"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int subjectId = Integer.parseInt(request.getParameter("subject"));
        int lessonId = Integer.parseInt(request.getParameter("lesson"));
        
        QuizDAO quizDAO = QuizDAO.getInstance();
        try {
            quizDAO.deleteQuestionFromQuiz(quizId, questionId);
            response.sendRedirect("AddQuiz?quizId=" + quizId + "&name=" + name + "&level=" + level + "&numberQuestion=" + numberQuestion + "&duration=" + duration + "&subject=" + subjectId + "&lesson=" + lessonId);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}