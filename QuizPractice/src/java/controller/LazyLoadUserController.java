package controller;

import dal.UserDAO;
import dto.UserManagement;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "LazyLoadUserController", urlPatterns = {"/lazyLoadUser"})
public class LazyLoadUserController extends HttpServlet {

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
            int total = (int) Math.ceil(UserDAO.getInstance().getTotalUser(""));
            HttpSession session = request.getSession();
            if (session.getAttribute("pageUser") == null) {
                page = 6;
            } else {
                page = (int) session.getAttribute("pageUser");
                if (page >= total) {
                    return;
                }
            }
            String search = "";
            if (session.getAttribute("search") != null) {
                search = (String) session.getAttribute("search");
            }
            session.setAttribute("pageUser", page + 5);
            List<UserManagement> users = UserDAO.getInstance().getUsers(page, 5, search);
            for (UserManagement user : users) {
                String statusButton = user.getStatus().equals("Block")
                        ? "<a onclick=\"unlockAccount('" + user.getId() + "')\" class=\"btn btn-primary\">"
                        + "<i style=\"font-size: 20px; color: var(--orange)\" class=\"fa-solid fa-unlock\"></i></a>"
                        : "<a onclick=\"lockAccount('" + user.getId() + "')\" class=\"btn btn-primary\">"
                        + "<i style=\"font-size: 20px; color: var(--orange)\" class=\"fa-solid fa-lock\"></i></a>";

                out.println("<tr class=\"table-row\">\n"
                        + "    <td>" + user.getId() + "</td>\n"
                        + "    <td>" + user.getFullName() + "</td>\n"
                        + "    <td><img src=\"" + user.getProfileImg() + "\" alt=\"Profile Image\"/></td>\n"
                        + "    <td>" + user.getEmail() + "</td>\n"
                        + "    <td>" + user.getPhoneNumber() + "</td>\n"
                        + "    <td>" + user.getGender() + "</td>\n"
                        + "    <td>" + user.getRole() + "</td>\n"
                        + "    <td>" + user.getStatus() + "</td>\n"
                        + "    <td>\n"
                        + "        " + statusButton + "\n"
                        + "    </td>\n"
                        + "</tr>");
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
