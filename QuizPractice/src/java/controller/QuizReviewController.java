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

        String practiceIdStr = request.getParameter("practiceId");
        if (practiceIdStr == null || practiceIdStr.isEmpty()) {
            response.sendRedirect("/QuizPractice");
            return;
        }

        int practiceId = Integer.parseInt(practiceIdStr);

        int currentQuestionNumber = 1; // Default to first question
        if (request.getParameter("questionNumber") != null && !request.getParameter("questionNumber").isEmpty()) {
            currentQuestionNumber = Integer.parseInt(request.getParameter("questionNumber"));
        }
        try {
            PracticeDAO practiceDAO = PracticeDAO.getInstance();
            QuestionsDAO questionDAO = QuestionsDAO.getInstance();

            Practice practice = practiceDAO.getPracticeById(practiceId);
            if (practice == null) {
                response.sendRedirect("/QuizPractice");
                return;
            }

            List<QuestionReviewDTO> questions = questionDAO.getQuestionsForReview(practiceId);

            QuestionReviewDTO currentQuestion = questions.get(currentQuestionNumber - 1);

            request.setAttribute("subjectName", practiceDAO.getSubjectNameById(practice.getSubjectId()));
            request.setAttribute("lessonName", practice.getLessonName());
            request.setAttribute("currentQuestion", currentQuestionNumber);
            request.setAttribute("totalQuestions", questions.size());
            request.setAttribute("questionDetail", currentQuestion.getDetail());
            request.setAttribute("practiceId", practiceId);
            request.setAttribute("questionId", currentQuestion.getId());
            request.setAttribute("questions", questions);
            request.setAttribute("result", (int) (practice.getNumberCorrect() * 100.0 / practice.getNumberQuestion()));
            String finished = request.getParameter("finished");
            if ("true".equals(finished)) {
                request.setAttribute("finished", true);
            }

            request.getRequestDispatcher("/quizReview.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }
    }
}

//    package controller;
//
//import dal.PracticeDAO;
//import dal.QuestionsDAO;
//import dal.StudentTakeQuizDAO;
//import dto.QuestionReviewDTO;
//import model.Practice;
//import model.Question;
//import model.User;
//import model.StudentTakeQuiz;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//@WebServlet(name = "QuizReviewController", urlPatterns = {"/quizReview"})
//public class QuizReviewController extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            response.sendRedirect("/QuizPractice");
//            return;
//        }
//
//        String practiceIdStr = request.getParameter("practiceId");
//        String studentTakeQuizIdStr = request.getParameter("studentTakeQuizId");
//
//        if ((practiceIdStr == null || practiceIdStr.isEmpty()) && (studentTakeQuizIdStr == null || studentTakeQuizIdStr.isEmpty())) {
//            response.sendRedirect("/QuizPractice");
//            return;
//        }
//
//        int practiceId = 0;
//        int studentTakeQuizId = 0;
//        Practice practice = null;
//        StudentTakeQuiz studentTakeQuiz = null;
//
//        try {
//            PracticeDAO practiceDAO = PracticeDAO.getInstance();
//            QuestionsDAO questionDAO = QuestionsDAO.getInstance();
//            StudentTakeQuizDAO studentTakeQuizDAO = StudentTakeQuizDAO.getInstance();
//            
//            if (practiceIdStr != null && !practiceIdStr.isEmpty()) {
//                practiceId = Integer.parseInt(practiceIdStr);
//                practice = practiceDAO.getPracticeById(practiceId);
//            } else if (studentTakeQuizIdStr != null && !studentTakeQuizIdStr.isEmpty()) {
//                studentTakeQuizId = Integer.parseInt(studentTakeQuizIdStr);
//                studentTakeQuiz = studentTakeQuizDAO.getStudentTakeQuizById(studentTakeQuizId);
//                practice = practiceDAO.getPracticeById(studentTakeQuiz.getQuizId()); // Assume practiceId is the same as quizId
//            }
////
////            if (practice == null) {
////                response.sendRedirect("/QuizPractice");
////                return;
////            }
//
//            int currentQuestionNumber = 1; // Default to first question
//            if (request.getParameter("questionNumber") != null && !request.getParameter("questionNumber").isEmpty()) {
//                currentQuestionNumber = Integer.parseInt(request.getParameter("questionNumber"));
//            }
//
//            List<QuestionReviewDTO> questions = questionDAO.getQuestionsForReview(practiceId);
//
//            QuestionReviewDTO currentQuestion = questions.get(currentQuestionNumber - 1);
//
//            request.setAttribute("subjectName", practiceDAO.getSubjectNameById(practice.getSubjectId()));
//            request.setAttribute("lessonName", practice.getLessonName());
//            request.setAttribute("currentQuestion", currentQuestionNumber);
//            request.setAttribute("totalQuestions", questions.size());
//            request.setAttribute("questionDetail", currentQuestion.getDetail());
//            request.setAttribute("practiceId", practiceId);
//            request.setAttribute("studentTakeQuizId", studentTakeQuizId);
//            request.setAttribute("questionId", currentQuestion.getId());
//            request.setAttribute("questions", questions);
//            request.setAttribute("result", (int) (practice.getNumberCorrect() * 100.0 / practice.getNumberQuestion()));
//            String finished = request.getParameter("finished");
//            if ("true".equals(finished)) {
//                request.setAttribute("finished", true);
//            }
//
//            request.getRequestDispatcher("/quizReview.jsp").forward(request, response);
//        } catch (SQLException e) {
//            throw new ServletException("Database access error.", e);
//        }
//    }
//}
//

