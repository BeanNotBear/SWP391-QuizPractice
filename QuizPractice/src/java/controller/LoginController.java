package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.security.Security;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        password = Security.encryptToSHA512(password);

        HttpSession session = request.getSession();

        UserDAO userDAO = UserDAO.getInstance();

        User user = userDAO.findUserByEmailAndPassword(email, password);
        System.out.println(user);

        if (user == null) {
            request.setAttribute("login_fail_err", "Email or password is wrong");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            session.setAttribute("user", user);
            response.sendRedirect("home");
        }
    }

    @Override

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
