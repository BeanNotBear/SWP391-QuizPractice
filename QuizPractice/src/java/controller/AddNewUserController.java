package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import org.json.JSONObject;
import util.mail.Mail;
import util.security.CodeVerify;
import util.security.Security;

@WebServlet(name = "AddNewUserController", urlPatterns = {"/AddNewUser"})
public class AddNewUserController extends HttpServlet {

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
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        String role = request.getParameter("role");
        String password = request.getParameter("password");

        JSONObject jsonResponse = new JSONObject();
        try {
            boolean isValidInformation = true;
            if (!UserDAO.getInstance().checkExistByEmail(email)) {
                isValidInformation = false;
                jsonResponse.put("status", "fail");
                jsonResponse.put("message", "Email has been existed");
            }
            if (isValidInformation) {
                jsonResponse.put("status", "success");
                // Create a new user object and set its properties
                User user = new User();
                user.setFullName(fullName);
                user.setEmail(email);
                user.setPhoneNumber(phoneNumber);
                user.setGender(Boolean.parseBoolean(gender));
                
                String saltPassword = password;
                password = Security.encryptToSHA512(password); // Encrypt the password
                user.setPassword(password);
                String token = CodeVerify.generateVerificationCode(); // Generate a verification token
                user.setToken(token);
                UserDAO.getInstance().insert(user); // Insert the user into the database
                // Create an activation link
                String activeLink = request.getScheme() + "://"
                        + request.getServerName()
                        + ":"
                        + request.getServerPort()
                        + "/QuizPractice/active";

                // Send a verification email
                Mail.sendMailNewUser(email, token, activeLink, saltPassword);
                jsonResponse.put("message", "Create new user successful!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
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
