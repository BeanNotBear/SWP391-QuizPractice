package controller;

import dal.QuizDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(urlPatterns = "/AddAnswerServlet")
public class AddAnswerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/QuizPractice"); // Redirect to home page if user is null
            return;
        }

        try {
            String detail = request.getParameter("detail");
            int isCorrect = Integer.parseInt(request.getParameter("isCorrect"));
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            int quizId = Integer.parseInt(request.getParameter("quizId"));
            String name = request.getParameter("name");
            String level = request.getParameter("level");
            int numberQuestion = Integer.parseInt(request.getParameter("numberQuestion"));
            int duration = Integer.parseInt(request.getParameter("duration"));
            int subjectId = Integer.parseInt(request.getParameter("subject"));
            int lessonId = Integer.parseInt(request.getParameter("lesson"));
            String detailQuestion = request.getParameter("detail");
            String suggestion = request.getParameter("suggestion");
            int creatorId = user.getUserId();

            QuizDAO quizDAO = QuizDAO.getInstance();
            int newAnswerId = quizDAO.addNewAnswer(detail, creatorId, isCorrect);
            quizDAO.addAnswerToQuestion(questionId, newAnswerId);

            // Redirect back to addQuestion.jsp with all parameters
            String redirectUrl = String.format(
                "AddQuestionServlet?quizId=%d&questionId=%d&name=%s&level=%s&numberQuestion=%d&duration=%d&subject=%d&lesson=%d&detail=%s&suggestion=%s",
                quizId, questionId, name, level, numberQuestion, duration, subjectId, lessonId, detailQuestion, suggestion
            );
            response.sendRedirect(redirectUrl);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}