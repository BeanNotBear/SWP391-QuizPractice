package controller;

import dal.UserDAO;
import dto.LearnerDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.apache.catalina.Session;

@WebServlet(name = "LoadMoreLearnersController", urlPatterns = {"/loadmoreexpert"})
public class LoadMoreLearnersController extends HttpServlet {

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
            int page;
            int recordsPerPage = 5;
            HttpSession session = request.getSession();
            if (session.getAttribute("leanerPage") == null) {
                page = 2;
            } else {
                page = (int) session.getAttribute("leanerPage");
            }

            String search = (String) session.getAttribute("searchLearner");
            int subjectId = (int) session.getAttribute("subjectId");
            session.setAttribute("leanerPage", page + 1);

            List<LearnerDTO> learners = UserDAO.getInstance().getLearnersBySubjectId(subjectId, page, recordsPerPage, search);
            for (LearnerDTO learner : learners) {
                String gender = learner.gender() == 1 ? "Male" : "Female";
                out.println("<tr>\n"
                        + "                                        <td>" + learner.id() + "</td>\n"
                        + "                                        <td>" + learner.fullName() + "</td>\n"
                        + "                                        <td><img src=\"" + learner.profileImg() + "\" alt=\"avatar\"/></td>   \n"
                        + "                                        <td>" + learner.email() + "</td>\n"
                        + "                                        <td>" + gender + "</td>\n"
                        + "                                        <td>" + learner.phoneNumber() + "</td>\n"
                        + "                                    </tr>");
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
