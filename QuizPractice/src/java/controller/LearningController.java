package controller;

import dal.LessonDAO;
import dto.LessonLearning;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.User;

@WebServlet(name = "LearningController", urlPatterns = {"/learning"})
public class LearningController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtSubject = request.getParameter("subjectId");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        try {
            int subjectId = Integer.parseInt(txtSubject);
            int userId = user.getUserId();
            List<LessonLearning> lessonLearnings = LessonDAO.getInstance().getLessonLearning(subjectId, userId);
            request.setAttribute("lessons", lessonLearnings);
            
            int currentIndex = LessonDAO.getInstance().getCurrentLessonIndex(subjectId, userId);
            request.setAttribute("currentIndex", currentIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("learning.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtUserId = request.getParameter("userId");
        String txtLessonId = request.getParameter("lessonId");
        
        try {
            int userId = Integer.parseInt(txtUserId);
            int lessonId = Integer.parseInt(txtLessonId);
            LessonDAO.getInstance().markLessonDone(userId, lessonId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
