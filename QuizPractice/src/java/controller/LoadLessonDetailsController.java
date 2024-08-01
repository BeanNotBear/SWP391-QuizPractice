package controller;

import dal.LessonDAO;
import dto.CurrentLesson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "LoadLessonDetailsController", urlPatterns = {"/loadLesson"})
public class LoadLessonDetailsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String txtIndex = request.getParameter("index");
            int index = Integer.parseInt(txtIndex);
            String name = request.getParameter("name"); // lesson name
            CurrentLesson lesson = LessonDAO.getInstance().getCurrentLesson(index, name);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            int userId = user.getUserId();
            // Kiểm tra nếu lesson không null
            if (lesson != null) {
                String media = lesson.getMedia();
                String content = lesson.getContent();
                String video = "";
                int quizId = lesson.getQuizId();
                System.out.println("Media " + media);
                if (media != null && !media.equals("")) {
                    video = "<div style=\"width: 100%; height: 570px; border: 2px solid;\">\n"
                            + "    <iframe style=\"width: 100%; height: 100%;\" src=\"" + media + "\" frameborder=\"0\" allowfullscreen></iframe>\n"
                            + "</div>\n";
                } else {
                    video = "\n";
                }
                String btn = "";
                if (!LessonDAO.getInstance().checkLessonIsDone(userId, lesson.getId())) {
                    btn = "<button onclick=\"markdone(" + "\'" + userId + "\'" + "," + "\'" + lesson.getId() + "\'" + ")\" class=\"inline-btn next\">Mark done & Next Lesson</button>";
                } else {
                    btn = "";
                }
                if (quizId != -1) {
                    btn = "<button onclick=\"takeQuiz(" + quizId + ")\" class=\"inline-btn next\">Take Quiz</button>";
                    out.println(video
                            + "<div>\n"
                            + "    <div class=\"course\">\n"
                            + "        " + content + "\n"
                            + "    </div>\n"
                            + "</div>\n"
                            + "<button onclick=\"markdone(" + "\'" + userId + "\'" + "," + "\'" + lesson.getId() + "\'" + ")\" class=\"inline-btn next\">Mark done & Next Lesson</button>"
                            + "<br>"
                            + btn);
                } else {
                    out.println(video
                            + "<div>\n"
                            + "    <div class=\"course\">\n"
                            + "        " + content + "\n"
                            + "    </div>\n"
                            + "</div>\n"
                            + btn);
                }
            } else {
                out.println("<div>Lesson not found.</div>");
            }
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
