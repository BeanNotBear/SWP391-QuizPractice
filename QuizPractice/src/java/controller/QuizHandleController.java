package controller;

import dal.PracticeDAO;
import dal.QuizDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Answer;
import model.Practice;
import model.Question;
import model.User;

@WebServlet(name = "QuizHandleController", urlPatterns = {"/quizHandle"})
public class QuizHandleController extends HttpServlet {

    private static final String LOGIN_PAGE = "/QuizPractice";
    private static final String QUIZ_REVIEW_PAGE = "quizReview";
    private static final String PRACTICE_LIST_PAGE = "practiceList";
    private static final String REVIEW_PROGRESS_PAGE = "ReviewProgress";
    private static final String START_TIME_ATTR = "startTime";
    private static final String USER_ATTR = "user";
    private static final String USER_ANSWERS_ATTR = "userAnswers";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTR);

        if (user == null) {
            response.sendRedirect(LOGIN_PAGE);
            return;
        }

        try {
            int practiceId = parseId(request.getParameter("practiceId"));
            int stqId = parseId(request.getParameter("stqId"));
            request.setAttribute("stqId", stqId);
            int quizId = parseId(request.getParameter("quizId"));

            PracticeDAO practiceDAO = PracticeDAO.getInstance();
            Practice practice = practiceDAO.getPracticeById(practiceId, stqId);
            
            if (practice == null) {
                response.sendRedirect(LOGIN_PAGE);
                return;
            }

            List<Question> questions = practiceDAO.getQuestionsByPracticeId(practiceId, stqId);
            if (questions.isEmpty()) {
                response.sendRedirect(LOGIN_PAGE);
                return;
            }

            String quizJson = QuizDAO.getInstance().getQuizQuestionJson(stqId);
            request.setAttribute("quizData", quizJson);
            request.getRequestDispatcher("/quizHandle.jsp").forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Database access error.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    private int parseId(String idStr) {
        if (idStr != null && !idStr.isEmpty()) {
            return Integer.parseInt(idStr);
        }
        return -1;
    }

    private int parseIdWithDefault(String idStr, int defaultValue) {
        if (idStr != null && !idStr.isEmpty()) {
            return Integer.parseInt(idStr);
        }
        return defaultValue;
    }
}
