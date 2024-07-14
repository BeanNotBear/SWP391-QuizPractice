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

        int stqId = Integer.parseInt(request.getParameter("stqId"));
        try {
            QuizDAO quizDAO = QuizDAO.getInstance();
            StudentTakeQuiz originalQuiz = quizDAO.getStudentTakeQuizById(stqId);
            if (originalQuiz == null) {
                response.sendRedirect("quizDoneList");
                return;
            }

            // Tạo bản sao mới của Student_Take_Quiz
            StudentTakeQuiz newQuiz = new StudentTakeQuiz();
            newQuiz.setUserId(user.getUserId());
            newQuiz.setQuizId(originalQuiz.getQuizId());
           // newQuiz.setNumberCorrect(-1); // Để null cho NumberCorrect
            int newQuizId = quizDAO.createStudentTakeQuiz(newQuiz);

            // Tạo các bản ghi mới cho Student_Quiz_Question
            List<StudentQuizQuestion> originalQuestions = quizDAO.getStudentQuizQuestionsByQuizId(stqId);
            for (StudentQuizQuestion originalQuestion : originalQuestions) {
                StudentQuizQuestion newQuestion = new StudentQuizQuestion();
                newQuestion.setStudentQuizId(newQuizId);
                newQuestion.setQuestionId(originalQuestion.getQuestionId());
                newQuestion.setYourAnswer(null); // Để null cho YourAnswer
                newQuestion.setIsMarked(null); // Để null cho IsMarked
                quizDAO.createStudentQuizQuestion(newQuestion);
            }

            response.sendRedirect("quizHandle?stqId=" + newQuizId);
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }
    }
}
