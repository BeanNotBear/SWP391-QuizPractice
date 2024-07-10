package controller;

import dal.QuestionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ToggleQuestionStatusController", urlPatterns = {"/toggleQuestionStatus"})
public class ToggleQuestionStatusController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int questionId = Integer.parseInt(request.getParameter("id"));
//        String action = request.getParameter("action");
//        int status = action.equals("activate") ? 1 : 0;
        int status = Integer.parseInt(request.getParameter("status"));

        QuestionDAO questionDAO = QuestionDAO.getInstance();
        try {
            boolean success = questionDAO.toggleQuestionStatus(questionId, status);
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }
    }
}
