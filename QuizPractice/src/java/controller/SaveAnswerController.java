package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SaveAnswerController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        int practiceId = Integer.parseInt(request.getParameter("practiceId"));
        int questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
        int answerId = Integer.parseInt(request.getParameter("answerId"));

        Map<Integer, Integer> userAnswers = (Map<Integer, Integer>) session.getAttribute("userAnswers");
        if (userAnswers == null) {
            userAnswers = new HashMap<>();
        }

        userAnswers.put(questionNumber, answerId);
        session.setAttribute("userAnswers", userAnswers);

        response.getWriter().write("Success");
    }
}