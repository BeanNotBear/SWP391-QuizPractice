package controller;

import dal.QuizDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.StudentTakeQuiz;
import model.StudentQuizQuestion;
import model.User;

@WebServlet(name = "StartNewQuizController", urlPatterns = {"/startNewQuiz"})
public class StartNewQuizController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/QuizPractice");
            return;
        }

        int quizId = Integer.parseInt(request.getParameter("quizId"));
        try {
            QuizDAO quizDAO = QuizDAO.getInstance();

            int newTakeQuizId = createNewQuiz(user, quizId, quizDAO);
            createNewQuizQuestions(quizId, newTakeQuizId, quizDAO);

            response.sendRedirect("quizHandle?stqId=" + newTakeQuizId + "&quizId=" + quizId);
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }
    }

    private int createNewQuiz(User user, int quizId, QuizDAO quizDAO) throws SQLException {
        StudentTakeQuiz newQuiz = new StudentTakeQuiz();
        newQuiz.setUserId(user.getUserId());
        newQuiz.setQuizId(quizId);
        return quizDAO.createStudentTakeQuiz(newQuiz);
    }

    private void createNewQuizQuestions(int quizId, int newTakeQuizId, QuizDAO quizDAO) throws SQLException {
        List<Integer> originalQuestions = quizDAO.getStudentQuizQuestionsByQuizId(quizId);
        for (Integer questionId : originalQuestions) {
            StudentQuizQuestion newQuestion = new StudentQuizQuestion();
            newQuestion.setStudentQuizId(newTakeQuizId);
            newQuestion.setQuestionId(questionId);
            quizDAO.createStudentQuizQuestion(newQuestion);
        }
    }
}