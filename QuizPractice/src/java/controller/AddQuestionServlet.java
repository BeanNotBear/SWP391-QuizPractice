package controller;

import dal.QuizDAO;
import dto.AnswerDTO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        int numberQuestion = Integer.parseInt(request.getParameter("numberQuestion"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int subjectId = Integer.parseInt(request.getParameter("subject"));
        int lessonId = Integer.parseInt(request.getParameter("lesson"));
        String detail = request.getParameter("detail");
        String suggestion = request.getParameter("suggestion");

        try {
            QuizDAO quizDAO = QuizDAO.getInstance();
            List<AnswerDTO> answers = quizDAO.getAnswersByQuestionId(questionId); // Method to fetch answers for the question
            request.setAttribute("answers", answers);
            request.setAttribute("quizId", quizId);
            request.setAttribute("questionId", questionId);
            request.setAttribute("name", name);
            request.setAttribute("level", level);
            request.setAttribute("numberQuestion", numberQuestion);
            request.setAttribute("duration", duration);
            request.setAttribute("subject", subjectId);
            request.setAttribute("lesson", lessonId);
            request.setAttribute("detail", detail);
            request.setAttribute("suggestion", suggestion);
            request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String detail = request.getParameter("detail");
        String suggestion = request.getParameter("suggestion");
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        int numberQuestion = Integer.parseInt(request.getParameter("numberQuestion"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int subjectId = Integer.parseInt(request.getParameter("subject"));
        int lessonId = Integer.parseInt(request.getParameter("lesson"));

        try {
            QuizDAO quizDAO = QuizDAO.getInstance();
            int newQuestionId = quizDAO.addNewQuestion(detail, suggestion);
            quizDAO.addQuestionToQuiz(quizId, newQuestionId);
            List<AnswerDTO> answers = quizDAO.getAnswersByQuestionId(newQuestionId); // Method to fetch answers for the question
            request.setAttribute("answers", answers);
            request.setAttribute("quizId", quizId);
            request.setAttribute("questionId", newQuestionId);
            request.setAttribute("name", name);
            request.setAttribute("level", level);
            request.setAttribute("numberQuestion", numberQuestion);
            request.setAttribute("duration", duration);
            request.setAttribute("subject", subjectId);
            request.setAttribute("lesson", lessonId);
            request.setAttribute("detail", detail);
            request.setAttribute("suggestion", suggestion);
            request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}