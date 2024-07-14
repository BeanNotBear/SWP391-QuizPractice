package controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.QuestionStatus;
import dal.PracticeDAO;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ReviewProgress")
public class ReviewProgressController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int practiceId = -1;  // Set default value
        int stqId = -1;
         String practiceIdParam = request.getParameter("practiceId");
        if (practiceIdParam != null && !practiceIdParam.isEmpty()) {
            practiceId = Integer.parseInt(practiceIdParam);
        }

        String stqIdParam = request.getParameter("stqId");
        if (stqIdParam != null && !stqIdParam.isEmpty()) {
            stqId = Integer.parseInt(stqIdParam);
        }
        
        PracticeDAO practiceDAO = PracticeDAO.getInstance();
        try {
            List<QuestionStatus> allQuestions = practiceDAO.getAllQuestionsStatus(practiceId,stqId);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(allQuestions));
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }
    }
}
