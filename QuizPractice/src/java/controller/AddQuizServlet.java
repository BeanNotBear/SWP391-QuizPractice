package controller;

import dal.QuizDAO;
import dto.QuizDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddQuizServlet", urlPatterns = {"/addQuiz"})
public class AddQuizServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("quizName");
        String level = request.getParameter("level");
        int numberQuestion = Integer.parseInt(request.getParameter("numberQuestion"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        String type = request.getParameter("type");

        QuizDTO quiz = new QuizDTO(0, name, null, level, numberQuestion, duration,type);
        quiz.setSubjectId(subjectId);

        QuizDAO quizDAO = QuizDAO.getInstance();
        try {
            boolean success = quizDAO.addQuiz(quiz);
            if (success) {
                response.sendRedirect("quizManager");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add quiz.");
            }
        } catch (SQLException e) {
            throw new ServletException("Database access error", e);
        }
    }
}