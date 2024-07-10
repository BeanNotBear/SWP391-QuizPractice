package controller;

import dal.PracticeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Question;
import org.json.JSONException;
import org.json.JSONObject;

public class PeekAnswerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int practiceId = Integer.parseInt(request.getParameter("practiceId"));
        int questionNumber = Integer.parseInt(request.getParameter("questionNumber"));

        try {
            PracticeDAO practiceDAO = PracticeDAO.getInstance();
            List<Question> questions = practiceDAO.getQuestionsByPracticeId(practiceId);
            Question currentQuestion = questions.get(questionNumber - 1);
            List<Answer> answers = practiceDAO.getAnswersByQuestionId(currentQuestion.getId());

            Answer correctAnswer = null;
            for (Answer answer : answers) {
                if (answer.isCorrect()) {
                    correctAnswer = answer;
                    break;
                }
            }

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("answerDetail", correctAnswer != null ? correctAnswer.getAnswerDetail() : "No correct answer available");
            jsonResponse.put("suggestion", currentQuestion.getSuggestion());

            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        } catch (JSONException ex) {
            Logger.getLogger(PeekAnswerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}