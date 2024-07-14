package controller;

import dal.PracticeDAO;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // If user is not logged in, redirect to login page
        if (user == null) {
            response.sendRedirect("/QuizPractice");
            return;
        }
        String practiceIdStr = request.getParameter("practiceId");
        String stqIdStr = request.getParameter("stqId");

        int practiceId = -1;
        int stqId = -1;
        if (practiceIdStr != null && !practiceIdStr.isEmpty()) {
            practiceId = Integer.parseInt(practiceIdStr);
        }

        if (stqIdStr != null && !stqIdStr.isEmpty()) {
            stqId = Integer.parseInt(stqIdStr);
        }

        int currentQuestionNumber = 1; // Default to first question
        if (request.getParameter("questionNumber") != null && !request.getParameter("questionNumber").isEmpty()) {
            currentQuestionNumber = Integer.parseInt(request.getParameter("questionNumber"));
        }

        try {
            PracticeDAO practiceDAO = PracticeDAO.getInstance();
            Practice practice = practiceDAO.getPracticeById(practiceId, stqId);
            if (practice == null) {
                response.sendRedirect("/QuizPractice");
                return;
            }

            List<Question> questions = practiceDAO.getQuestionsByPracticeId(practiceId, stqId);
            if (questions.isEmpty()) {
                response.sendRedirect("/QuizPractice");
                return;
            }

            // Check and set startTime if not present in session
            Long startTime = (Long) session.getAttribute("startTime");
            if (startTime == null) {
                startTime = System.currentTimeMillis();
                session.setAttribute("startTime", startTime);
            }

            long currentTime = System.currentTimeMillis();
            long elapsedTime = (currentTime - startTime) / 1000;
            int remainingTime = (int) (practice.getDuration() * 60 - elapsedTime);

            if (remainingTime <= 0) {
                // Submit answers if time is up
                submitAnswers(session, practiceId, stqId);
                response.sendRedirect("quizReview?practiceId=" + practiceId + "&stqId=" + stqId + "&finished=true");
                return;
            }

            // Check if the current time + duration is greater than createdAt + duration
            long endTime = practice.getCreatedAt().getTime() + (practice.getDuration() * 60 * 1000);
            if (currentTime >= endTime) {
                // Redirect to review page with a popup
                response.sendRedirect("quizReview?practiceId=" + practiceId + "&stqId=" + stqId + "&finished=true");
                return;
            }

            // Get current question and selected answer from session
            Map<Integer, Integer> userAnswers = (Map<Integer, Integer>) session.getAttribute("userAnswers");
            if (userAnswers == null) {
                userAnswers = new HashMap<>();
                session.setAttribute("userAnswers", userAnswers);
            }
            Integer selectedAnswer = userAnswers.get(currentQuestionNumber);

            Question currentQuestion = questions.get(currentQuestionNumber - 1);
            List<Answer> answers = practiceDAO.getAnswersByQuestionId(currentQuestion.getId());

            request.setAttribute("subjectName", practiceDAO.getSubjectNameById(practice.getSubjectId())); // Get subject name
            request.setAttribute("lessonName", practice.getLessonName());
            request.setAttribute("currentQuestion", currentQuestionNumber);
            request.setAttribute("totalQuestions", questions.size());
            request.setAttribute("remainingTime", remainingTime); // Remaining time
            request.setAttribute("questionDetail", currentQuestion.getDetail());
            request.setAttribute("answers", answers);
            request.setAttribute("selectedAnswer", selectedAnswer);
            request.setAttribute("practiceId", practiceId);
            request.setAttribute("stqId", stqId);
            request.setAttribute("questionId", currentQuestion.getId());

            request.getRequestDispatcher("/quizHandle.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        try{
        // If user is not logged in, redirect to login page
        if (user == null) {
            response.sendRedirect("/QuizPractice");
            return;
        }
        String practiceIdStr = request.getParameter("practiceId");
        String stqIdStr = request.getParameter("stqId");

        int practiceId = -1;
        int stqId = -1;
        if (practiceIdStr != null && !practiceIdStr.isEmpty()) {
            practiceId = Integer.parseInt(practiceIdStr);
        }

        if (stqIdStr != null && !stqIdStr.isEmpty()) {
            stqId = Integer.parseInt(stqIdStr);
        }
        int currentQuestionNumber = 1; // Default to first question
        if (request.getParameter("questionNumber") != null && !request.getParameter("questionNumber").isEmpty()) {
            currentQuestionNumber = Integer.parseInt(request.getParameter("questionNumber"));
        }

        int questionNumber = currentQuestionNumber;
        String action = request.getParameter("action");

        // Save selected answer to session
        Map<Integer, Integer> userAnswers = (Map<Integer, Integer>) session.getAttribute("userAnswers");
        if (userAnswers == null) {
            userAnswers = new HashMap<>();
            session.setAttribute("userAnswers", userAnswers);
        }
        if (request.getParameter("answer") != null) {
            int selectedAnswer = Integer.parseInt(request.getParameter("answer"));
            userAnswers.put(questionNumber, selectedAnswer);
        }

        // Handle actions
        if ("submit".equals(action)) {
            submitAnswers(session, practiceId, stqId);
            response.sendRedirect("practiceList");
        } else if ("viewProgress".equals(action)) {
            saveAnswers(session, practiceId, stqId);
            response.sendRedirect("ReviewProgress?practiceId=" + practiceId + "&stqId=" + stqId);
        } else {
            // Navigate to next or previous question
            int nextQuestionNumber = "next".equals(action) ? questionNumber + 1 : questionNumber - 1;
            response.sendRedirect("quizHandle?practiceId=" + practiceId + "&questionNumber=" + nextQuestionNumber + "&stqId=" + stqId);
        }
        }catch(NumberFormatException e){
            response.sendRedirect("/QuizPractice");
            return;
        }
    }

    private void submitAnswers(HttpSession session, int practiceId, int stqId) throws ServletException {
        Map<Integer, Integer> userAnswers = (Map<Integer, Integer>) session.getAttribute("userAnswers");
        int numberCorrect = 0; // Biến để đếm số câu trả lời đúng

        if (userAnswers != null) {
            try {
                PracticeDAO practiceDAO = PracticeDAO.getInstance();
                List<Question> questions = practiceDAO.getQuestionsByPracticeId(practiceId, stqId);

                for (Map.Entry<Integer, Integer> entry : userAnswers.entrySet()) {
                    Question question = questions.get(entry.getKey() - 1);
                    int userAnswerId = entry.getValue();
                    practiceDAO.saveUserAnswer(practiceId, stqId, question.getId(), userAnswerId);

                    // Kiểm tra câu trả lời đúng
                    if (practiceDAO.isCorrectAnswer(question.getId(), userAnswerId)) {
                        numberCorrect++;
                    }
                }

                // Cập nhật số câu trả lời đúng vào bảng Practice
                practiceDAO.updateNumberCorrect(practiceId, stqId, numberCorrect);

            } catch (SQLException e) {
                throw new ServletException("Database access error.", e);
            }
        }

        session.removeAttribute("userAnswers");
        session.removeAttribute("startTime");
    }

    private void saveAnswers(HttpSession session, int practiceId, int stqId) throws ServletException {
        Map<Integer, Integer> userAnswers = (Map<Integer, Integer>) session.getAttribute("userAnswers");
        if (userAnswers != null) {
            try {
                PracticeDAO practiceDAO = PracticeDAO.getInstance();
                for (Map.Entry<Integer, Integer> entry : userAnswers.entrySet()) {
                    Question question = practiceDAO.getQuestionsByPracticeId(practiceId, stqId).get(entry.getKey() - 1);
                    practiceDAO.saveUserAnswer(practiceId, stqId, question.getId(), entry.getValue());
                }
            } catch (SQLException e) {
                throw new ServletException("Database access error.", e);
            }
        }
    }
}
