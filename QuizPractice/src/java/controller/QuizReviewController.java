package controller;

import dal.PracticeDAO;
import dal.QuestionsDAO;
import dto.QuestionReviewDTO;
import model.Practice;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "QuizReviewController", urlPatterns = {"/quizReview"})
public class QuizReviewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/QuizPractice");
            return;
        }

        try {

            String practiceIdStr = request.getParameter("practiceId");
            String quizIdStr = request.getParameter("quizId");
            if ((practiceIdStr == null || practiceIdStr.isEmpty()) && (quizIdStr == null || quizIdStr.isEmpty())) {
                response.sendRedirect("/QuizPractice");
                return;
            }

            int practiceId = -1;
            if (practiceIdStr != null && !practiceIdStr.isEmpty()) {
                practiceId = Integer.parseInt(practiceIdStr);
            }

            int quizId = -1;
            if (quizIdStr != null && !quizIdStr.isEmpty()) {
                quizId = Integer.parseInt(quizIdStr);
            }

            int currentQuestionNumber = 1; // Default to first question
            if (request.getParameter("questionNumber") != null && !request.getParameter("questionNumber").isEmpty()) {
                currentQuestionNumber = Integer.parseInt(request.getParameter("questionNumber"));
            }

            PracticeDAO practiceDAO = PracticeDAO.getInstance();
            QuestionsDAO questionDAO = QuestionsDAO.getInstance();

            Practice practice = practiceDAO.getPracticeById(practiceId, quizId);
            if (practice == null) {
                response.sendRedirect("/QuizPractice");
                return;
            }

            List<QuestionReviewDTO> questions = questionDAO.getQuestionsForReview(practiceId, quizId);

            QuestionReviewDTO currentQuestion = questions.get(currentQuestionNumber - 1);

            request.setAttribute("subjectName", practiceDAO.getSubjectNameById(practice.getSubjectId()));
            request.setAttribute("lessonName", practice.getLessonName());
            request.setAttribute("currentQuestion", currentQuestionNumber);
            request.setAttribute("totalQuestions", questions.size());
            request.setAttribute("questionDetail", currentQuestion.getDetail());
            request.setAttribute("practiceId", practiceId);
            request.setAttribute("stqId", quizId);
            request.setAttribute("questionId", currentQuestion.getId());
            request.setAttribute("questions", questions);
            request.setAttribute("result", (int) (practice.getNumberCorrect() * 100.0 / practice.getNumberQuestion()));
            String finished = request.getParameter("finished");
            if ("true".equals(finished)) {
                request.setAttribute("finished", true);
            }

            request.getRequestDispatcher("/quizReview.jsp").forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("quizReview");
            return;
        }
    }
}
