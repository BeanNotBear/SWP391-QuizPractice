package controller;

import dto.RegisterUserDto;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.validation.Validation;

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
        String phone = request.getParameter("phone");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cfPassword = request.getParameter("cf-password");
        boolean isValidInformation = true;
        Validation validation = Validation.getInstance();
        if (firstName == null || firstName.isEmpty()) {
            request.setAttribute("firstName_err",
                    "You must fill first name!");
            isValidInformation = false;
        }
        if (lastName == null || lastName.isEmpty()) {
            request.setAttribute("lastName_err",
                    "You must fill last name!");
            isValidInformation = false;
        }
        if (!validation.CheckFormatEmail(email)) {
            request.setAttribute("email_err",
                    "Email is incorrect format!");
            isValidInformation = false;
        }
        if (!validation.CheckFormatPhone(phone)) {
            request.setAttribute("phone_err",
                    "Phone number is incorrect format!");
            isValidInformation = false;
        }
        if (dob == null || dob.isEmpty()) {
            request.setAttribute("dob_err",
                    "You must fill date of birth!");
            isValidInformation = false;
        }
        if (gender == null || gender.isEmpty()) {
            request.setAttribute("gender_err",
                    "You must select gender!");
            isValidInformation = false;
        }
        if (username == null || username.isEmpty()) {
            request.setAttribute("username_err",
                    "You must fill username!");
            isValidInformation = false;
        }
        if (!validation.CheckFormatPassword(password)) {
            request.setAttribute("passowrd_err",
                    "Password is incorrect format!");
            isValidInformation = false;
        }
        if (!password.equals(cfPassword)) {
            request.setAttribute("cfPassowrd_err",
                    "Passowrd and confirm password do not match!");
            isValidInformation = false;
        }
        if (!isValidInformation) {
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("dob", dob);
            request.setAttribute("gender", gender);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("cfPassword", cfPassword);
            request.getRequestDispatcher("register.jsp")
                    .forward(request, response);
        } else {
            RegisterUserDto registerUserDto = new RegisterUserDto();
            try {
                registerUserDto.setFirstName(firstName);
                registerUserDto.setLastName(lastName);
                registerUserDto.setEmail(email);
                registerUserDto.setPhoneNumber(phone);
                registerUserDto.setGender(Boolean.getBoolean(gender));
                SimpleDateFormat formatter = new SimpleDateFormat(
                        "dd-MMM-yyyy");
                Date date = formatter.parse(dob);
                registerUserDto.setDob(date);
                registerUserDto.setUsername(username);
                registerUserDto.setPassword(password);
            } catch (Exception e) {
            }
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
