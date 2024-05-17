package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import jakarta.servlet.http.HttpServlet;
import util.security.CodeVerify;

/**
 *
 * @author nghin
 */
public class ActiveUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String token = request.getParameter("token");

        UserDAO userDAO = UserDAO.getInstance();

        User user = userDAO.findUserByToken(token);
        
        if(user != null) {
            String newToken = CodeVerify.generateVerificationCode();
            session.setAttribute("user", user);
            userDAO.UpdateStatusByToken(token);
            userDAO.UpdateTokenByEmail(newToken, user.getEmail());
            response.sendRedirect("home");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
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
