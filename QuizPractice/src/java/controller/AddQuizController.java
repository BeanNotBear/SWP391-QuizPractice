package controller;

import dal.QuizDAO;
import dto.QuizSubjectDTO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import model.Question;
import model.User;

@WebServlet(urlPatterns = "/AddQuiz")
public class AddQuizController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/QuizPractice");
            return;
        }

        QuizDAO quizDAO = QuizDAO.getInstance();
        List<QuizSubjectDTO> subjects = null;
        try {
            subjects = quizDAO.getSubjectsOfExpert(user.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("subjects", subjects);

        String quizId = request.getParameter("quizId");
        if (quizId != null) {
            try {
                List<Question> questions = quizDAO.getSearchQuestionByQuizId(Integer.parseInt(quizId));
                request.setAttribute("questions", questions);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("addQuiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        int numberQuestion = Integer.parseInt(request.getParameter("numberQuestion"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int subjectId = Integer.parseInt(request.getParameter("subject"));
        int lessonId = Integer.parseInt(request.getParameter("lesson"));
        String type = request.getParameter("type");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        System.out.println(user);
        QuizDAO quizDAO = QuizDAO.getInstance();
        try {
            int newQuizId = quizDAO.addNewQuiz(name, level, numberQuestion, duration, type,user.getUserId(), subjectId, lessonId);
            quizDAO.updateLessonQuizId(lessonId, newQuizId);
            response.sendRedirect("AddQuiz?quizId=" + newQuizId + "&name=" + name + "&level=" + level + "&numberQuestion=" + numberQuestion + "&duration=" + duration+"&type="+type + "&subject=" + subjectId + "&lesson=" + lessonId);
            
            System.out.println(newQuizId + "    "+lessonId);
        } catch (SQLException e) {
            
            
            response.sendRedirect("error.jsp");
        }
    }

}