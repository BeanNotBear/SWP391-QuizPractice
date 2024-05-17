package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import jakarta.servlet.http.HttpServlet;

/**
 *
 * @author nghin
 */
public class ActiveUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String tokenFromServer = (String) session.getAttribute("token");
        String token = request.getParameter("token");

        UserDAO userDAO = UserDAO.getInstance();

        if (tokenFromServer.equals(token)) {
            session.removeAttribute("token");
            User user = userDAO.findUserByEmail(email);
            userDAO.UpdateStatusByEmail(email);
            session.setAttribute("user", user);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("active.jsp").forward(request, response);
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
