package controller;

import dal.QuizDAO;
import dal.SubjectDAO;
import dto.QuizDTO;
import dto.SubjectLoadDTO;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import model.Subject;

@WebServlet(name = "QuizManagerController", urlPatterns = {"/quizManager"})
public class QuizManagerController extends HttpServlet {

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

    String subjectName = request.getParameter("subjectName");
    String quizName = request.getParameter("quizName");
    String subjectId = request.getParameter("subjectId");
    int currentPage = 1;
    int recordsPerPage = 5;
    if (request.getParameter("page") != null) {
        currentPage = Integer.parseInt(request.getParameter("page"));
    }

    try {
        QuizDAO quizDAO = QuizDAO.getInstance();
        List<QuizDTO> quizzes = quizDAO.searchQuizzes(subjectId, subjectName, quizName, currentPage, recordsPerPage); // Add subjectId as a parameter
        int totalRecords = quizDAO.getTotalRecords(subjectId, subjectName, quizName); // Add subjectId as a parameter
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
       
        SubjectDAO subjectDAO = SubjectDAO.getInstance();
        List<SubjectLoadDTO> subjects = subjectDAO.getAllSubjects();
        
        request.setAttribute("quizzes", quizzes);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("subjects", subjects);
        request.setAttribute("subjectId", subjectId);
        request.setAttribute("subjectName", subjectName);
        request.setAttribute("quizName", quizName);

        request.getRequestDispatcher("/QuizManager.jsp").forward(request, response);
    } catch (Exception e) {
        throw new ServletException("Database access error.", e);
    }
}
}