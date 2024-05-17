package controller;

// Import necessary classes
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.mail.Mail;
import util.security.CodeVerify;
import util.security.Security;

// Servlet class for handling login requests
public class LoginController extends HttpServlet {

    // Handles GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the login.jsp page
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    // Handles POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve email and password parameters from the request
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Encrypt the password using SHA-512
        password = Security.encryptToSHA512(password);

        // Retrieve the current session
        HttpSession session = request.getSession();

        // Get an instance of UserDAO
        UserDAO userDAO = UserDAO.getInstance();

        // Find the user by email and encrypted password
        User user = userDAO.findUserByEmailAndPassword(email, password);

        // Check if the user exists
        if (user == null) {
            // If user is not found, set an error message and forward to login.jsp
            request.setAttribute("login_fail_err", "Email or password is wrong");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // If user is found, set the user object in the session
            session.setAttribute("user", user);
            
            // Check the user's status
            if (user.getStatusID() == 1) {
                // If user status is 1 (inactive), generate a verification token
                String token = CodeVerify.generateVerificationCode();
                
                // Update the token for the user in the database
                userDAO.UpdateTokenByEmail(token, email);
                
                // Create an activation link
                String activeLink = request.getScheme() + "://"
                        + request.getServerName()
                        + ":"
                        + request.getServerPort()
                        + "/QuizPractice/active";
                
                // Send a verification email to the user
                Mail.sendMailVerify(email, token, activeLink);
                
                // Forward to the active.jsp page
                request.getRequestDispatcher("active.jsp").forward(request, response);
            } else {
                // If user status is active, redirect to the home page
                response.sendRedirect("home");
            }
        }
    }

    // Provides a short description of the servlet
    @Override
    public String getServletInfo() {
        return "LoginController handles user login requests and redirects appropriately based on user status.";
    }
}
