package controller;

import dal.QuizDAO;
import dto.QuizDoneDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "QuizDoneListController", urlPatterns = {"/quizDoneList"})
public class QuizDoneListController extends HttpServlet {

    private final QuizDAO quizDAO = QuizDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
         if (user == null) {
            response.sendRedirect("/QuizPractice");
            return;
        }
        int userId = user.getUserId();

        String subjectName = request.getParameter("subjectName");
        String quizName = request.getParameter("quizName");
        int page = 1;
        int recordsPerPage = 4;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
            List<QuizDoneDTO> doneQuizzes = quizDAO.getDoneQuizzes(userId, subjectName, quizName, page, recordsPerPage);
            int totalRecords = quizDAO.getTotalDoneQuizzes(userId, subjectName, quizName);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            request.setAttribute("doneQuizzes", doneQuizzes);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("subjectName", subjectName);
            request.setAttribute("quizName", quizName);

            String ajax = request.getParameter("ajax");
            if ("true".equals(ajax)) {
                request.getRequestDispatcher("/quizDoneTable.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/quizDoneList.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}