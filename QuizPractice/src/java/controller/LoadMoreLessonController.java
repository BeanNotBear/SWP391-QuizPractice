package controller;

import dal.LessonDAO;
import dto.LessonSubjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "LoadMoreLessonController", urlPatterns = {"/loadmorelesson"})
public class LoadMoreLessonController extends HttpServlet {

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
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int subjectId = (int) session.getAttribute("subjectId");
        int page;
        int recordsPerPage = 5;
        if (session.getAttribute("pageLesson") == null) {
            page = 2;
        } else {
            page = (int) session.getAttribute("pageLesson");
        }
        String search = (String)session.getAttribute("searchLesson");
        session.setAttribute("pageLesson", page + 1);
        List<LessonSubjectDTO> lessons = LessonDAO.getInstance().getLessonsBySubjectId(subjectId, page, recordsPerPage, search);
        if (lessons.size() != 0) {
            for (LessonSubjectDTO lesson : lessons) {
                out.println("<div class=\"faq\">\n"
                        + "                                <button class=\"accordion\">\n"
                        + "                                    Lesson " + lesson.getLessonIndex() + " : " + lesson.getName() + "\n"
                        + "                                    <i class=\"fa-solid fa-chevron-down\"></i>\n"
                        + "                                </button>\n"
                        + "                                <div class=\"pannel\">\n"
                        + "                                    <div>Type: <b><i>" + lesson.getType() + "</i></b></div>\n"
                        + "                                    <p>\n"
                        + "                                        Content: " + lesson.getContent() + "\n"
                        + "                                    </p>\n"
                        + "                                </div>\n"
                        + "                            </div>");
            }
        }
        out.close();
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
