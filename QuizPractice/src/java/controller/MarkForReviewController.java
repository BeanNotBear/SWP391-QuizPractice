package controller;

import dal.PracticeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class MarkForReviewController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int practiceId = Integer.parseInt(request.getParameter("practiceId"));
        int questionId = Integer.parseInt(request.getParameter("questionId"));

        try {
            PracticeDAO practiceDAO = PracticeDAO.getInstance();
            practiceDAO.markQuestionForReview(practiceId, questionId);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new ServletException("Database access error.", e);
        }
    }
}