package controller;

import com.google.gson.Gson;
import dal.PracticeDAO;
import dto.QuestionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Question;

public class FilterQuestionsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int practiceId = Integer.parseInt(request.getParameter("practiceId"));
        String type = request.getParameter("type");

       try {
            PracticeDAO practiceDAO = PracticeDAO.getInstance();
            List<Question> allQuestions = practiceDAO.getQuestionsByPracticeId(practiceId);
            List<QuestionDTO> filteredQuestions = practiceDAO.getFilteredQuestions(practiceId, type);

            // Gán questionNumber cho từng QuestionDTO
            for (QuestionDTO questionDTO : filteredQuestions) {
                for (int i = 0; i < allQuestions.size(); i++) {
                    if (questionDTO.getId() == allQuestions.get(i).getId()) {
                        questionDTO.setQuestionNumber(i + 1);
                        break;
                    }
                }
            }

            String json = new Gson().toJson(filteredQuestions);
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}