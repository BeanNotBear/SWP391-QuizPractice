/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import dto.RegisterUserDto;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mapper.UserMapper;
import model.User;
import util.mail.Mail;
import util.security.CodeVerify;
import util.security.Security;
import util.validation.Validation;

/**
 *
 * @author nghin
 */
public class RegisterController extends HttpServlet {

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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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

        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String dob = request.getParameter("dob");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cfPassword = request.getParameter("cf-password");

        boolean isValidInformation = true;

        UserDAO userDAO = UserDAO.getInstance();

        HttpSession session = request.getSession();

        Validation validation = Validation.getInstance();

        if (firstName == null || firstName.isEmpty()) {
            isValidInformation = false;
            request.setAttribute("firstName_err",
                    "You must fill first name");
        }

        if (lastName == null || lastName.isEmpty()) {
            isValidInformation = false;
            request.setAttribute("lastName_err",
                    "You must fill last name");
        }

        if (!validation.CheckFormatEmail(email)) {
            isValidInformation = false;
            request.setAttribute("email_err",
                    "Email is wrong format");
        }

        if (gender == null || gender.isEmpty()
                || (!gender.equals("true")
                && !gender.equals("false"))) {
            isValidInformation = false;
            request.setAttribute("gender_err",
                    "Gender must be male or female");
        }

        if (!validation.CheckFormatPhone(phone)) {
            isValidInformation = false;
            request.setAttribute("phone_err",
                    "Phone is wrong format");
        }

        if (username == null || username.isEmpty()) {
            isValidInformation = false;
            request.setAttribute("username_err",
                    "You must fill username");
        }

        if (!userDAO.checkUserExistedByUsernameAndEmail(username, email)) {
            isValidInformation = false;
            request.setAttribute("usernam_err",
                    "Usernam has been existed");
            request.setAttribute("email_err", "Email has been existed");
        }

        if (!validation.CheckFormatPassword(password)) {
            isValidInformation = false;
            request.setAttribute("password_err",
                    "Your input must contain at least one uppercase letter,"
                    + " one lowercase letter, one digit, "
                    + "one special character, "
                    + "and be at least 8 characters long.");
        }

        if (!password.equals(cfPassword)) {
            isValidInformation = false;
            request.setAttribute("cfPassword_err",
                    "Password and Confirm password do not match");
        }

        if (isValidInformation) {
            try {
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPhoneNumber(phone);
                user.setDob(java.sql.Date.valueOf(dob));
                user.setGender(Boolean.parseBoolean(gender));
                user.setUsername(username);
                password = Security.encryptToSHA512(password);
                user.setPassword(password);
                userDAO.insert(user);
                String token = CodeVerify.generateVerificationCode();
                session.setAttribute("token",
                        token);
                session.setAttribute("email", email);
                String activeLink = request.getScheme() + "://"
                        + // "http" + "://
                        request.getServerName()
                        + // "myhost"
                        ":"
                        + // ":"
                        request.getServerPort()
                        + // "9999"
                        "/QuizPractice/active";
                request.getQueryString();
                Mail.sendMailVerify(email, token, activeLink);
                request.getRequestDispatcher("active.jsp").forward(request, response);

            } catch (Exception e) {
            }
        } else {
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("cfPassowrd", cfPassword);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
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
