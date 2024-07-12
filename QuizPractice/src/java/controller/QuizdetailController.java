package controller;

import com.google.gson.Gson;
import dal.QuizDAO;
import dal.SubjectDAO;
import dto.QuizDTO;
import dto.SubjectLoadDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name = "QuizdetailController", urlPatterns = {"/quizdetail"})
public class QuizdetailController extends HttpServlet {

    private int previous_id = -1;
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        int id = -1;

        try {
            if (idParam != null && !idParam.isEmpty()) {
                id = Integer.parseInt(idParam);
                previous_id = id;
            }
        } catch (NumberFormatException e) {
            // Log lỗi nếu cần thiết
        }

        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("/QuizPractice");
                return;
            }

            String subjectName = request.getParameter("subjectName");
            String quizName = request.getParameter("quizName");

            int currentPage = 1;
            int recordsPerPage = 5;
            if (request.getParameter("page") != null) {
                currentPage = Integer.parseInt(request.getParameter("page"));
            }

            QuizDAO quizDAO = QuizDAO.getInstance();
            List<QuizDTO> quizzes = quizDAO.searchQuizzes(subjectName, quizName, currentPage, recordsPerPage);
            int totalRecords = quizDAO.getTotalRecords(subjectName, quizName);
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            QuizDTO quizDTOId = quizDAO.getQuizById(previous_id);
            SubjectDAO subjectDAO = SubjectDAO.getInstance();
            List<SubjectLoadDTO> subjects = subjectDAO.getAllSubjects();

            request.setAttribute("quizzes", quizzes);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("subjects", subjects);
            request.setAttribute("quizID", quizDTOId);
            request.setAttribute("subjectName", subjectName);
            request.setAttribute("quizName", quizName);

            request.getRequestDispatcher("/QuizDetail.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(QuizdetailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Retrieve form data
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("quizName");
            String level = request.getParameter("level");
            int numberQuestion = Integer.parseInt(request.getParameter("numberQuestion"));
            int duration = Integer.parseInt(request.getParameter("duration"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            String type = request.getParameter("type");

            // Create QuizDTO object
            QuizDTO quiz = new QuizDTO(id, name, null, level, numberQuestion, duration, type);
            quiz.setSubjectId(subjectId);

            // Update quiz in database
            QuizDAO quizDAO = QuizDAO.getInstance();
            boolean updated = quizDAO.updateQuiz(quiz);

            // Prepare JSON response
            JSONObject jsonObject = new JSONObject();
            if (updated) {
                jsonObject.put("status", "success");
                jsonObject.put("message", "Quiz updated successfully");
            } else {
                jsonObject.put("status", "error");
                jsonObject.put("message", "Failed to update quiz");
            }

            // Send JSON response
            response.setContentType("application/json");
            response.getWriter().write(jsonObject.toString());
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (SQLException ex) {
            Logger.getLogger(QuizdetailController.class.getName()).log(Level.SEVERE, null, ex);

            // Handle database access error
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Database access error");
        } catch (JSONException ex) {
            Logger.getLogger(QuizdetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
