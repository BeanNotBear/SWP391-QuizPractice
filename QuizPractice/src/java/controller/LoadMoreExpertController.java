package controller;

import dal.UserDAO;
import dto.OwnerDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(urlPatterns = {"/loadMoreExperts"})
public class LoadMoreExpertController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        int page;
        int recordsPerPage = 5;
        if (session.getAttribute("page") == null) {
            page = 2;
        } else {
            page = (int) session.getAttribute("page");
        }
        session.setAttribute("page", page + 1);
        String search = (String) session.getAttribute("search");
        int ownerId = (int) session.getAttribute("ownerId");
        List<OwnerDTO> experts = UserDAO.getInstance().getExpertPagination(page, recordsPerPage, ownerId, search);
        for (OwnerDTO expert : experts) {
            String gender = expert.getGender() == 1 ? "Male" : "Female";
            out.print("<tr>\n"
                    + "                                        <td>"+ expert.getId() +"</td>\n"
                    + "                                        <td><img class=\"epxertImg\" src=\"" + expert.getImg() + "\" alt=\"alt\"/></td>\n"
                    + "                                        <td>" + expert.getName() + "</td>   \n"
                    + "                                        <td>" + expert.getEmail() + "</td>\n"
                    + "                                        <td>" + expert.getPhoneNumber() + "</td>\n"
                    + "                                        <td>" + gender + "</td>\n"
                    + "                                        <td>" + expert.getDate() + "</td>\n"
                    + "                                        <td>\n"
                    + "                                            <a onclick=\"assignExpert("+ expert.getId() +")\" class=\"btn btn-success btn-sm\">Assign</a>\n"
                    + "                                        </td>\n"
                    + "                                    </tr>");
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
