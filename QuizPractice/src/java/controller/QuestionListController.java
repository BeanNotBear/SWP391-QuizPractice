package controller;

import dal.QuizQuestionDAO;
import dal.SubjectDAO;
import dto.SubjectLoadDTO;
import dto.QuestionListDTO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuestionListController", urlPatterns = {"/questionList"})
public class QuestionListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/QuizPractice");
            return;
        }

        String subjectId = request.getParameter("subjectId");
        String lessonId = request.getParameter("lessonId");
        String status = request.getParameter("status");
        String detail = request.getParameter("detail");

        int currentPage = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        try {
            QuizQuestionDAO questionDAO = QuizQuestionDAO.getInstance();
            SubjectDAO subjectDAO = SubjectDAO.getInstance();

            List<SubjectLoadDTO> subjects = subjectDAO.getAllSubjects();
            List<QuestionListDTO> questions = questionDAO.searchQuestions(subjectId, lessonId, status, detail, currentPage, recordsPerPage);
            int totalRecords = questionDAO.getTotalRecords(subjectId, lessonId, status, detail);
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            request.setAttribute("subjects", subjects);
            request.setAttribute("questions", questions);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);

            request.setAttribute("subjectId", subjectId);
            request.setAttribute("lessonId", lessonId);
            request.setAttribute("status", status);
            request.setAttribute("detail", detail);

            request.getRequestDispatcher("/QuestionList.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Database access error.", e);
        }
    }
}
