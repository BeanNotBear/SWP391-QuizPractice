package controller;

import dal.QuizQuestionDAO;
import dto.QuestionListDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SaveQuestionController", urlPatterns = {"/saveQuestion"})
public class SaveQuestionController extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String detail = request.getParameter("detail");
            String suggestion = request.getParameter("suggestion");
            int status = Integer.parseInt(request.getParameter("status"));
            String media = request.getParameter("media");

            QuestionListDTO question = new QuestionListDTO(id, detail, suggestion, status, media);
            QuizQuestionDAO questionDAO = QuizQuestionDAO.getInstance();

            boolean success = questionDAO.updateQuestion(question);

            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(gson.toJson(new ResponseMessage("Question updated successfully")));
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(gson.toJson(new ResponseMessage("Failed to update question")));
            }
        } catch (NumberFormatException | SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson(new ResponseMessage("Database access error: " + e.getMessage())));
        }
    }

    private static class ResponseMessage {

        private final String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
