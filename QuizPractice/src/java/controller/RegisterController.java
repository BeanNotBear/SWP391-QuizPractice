package controller;

// Import necessary classes
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import util.mail.Mail;
import util.security.CodeVerify;
import util.security.Security;
import util.validation.Validation;

// Servlet class for handling user registration
@WebServlet("/register")
public class RegisterController extends HttpServlet {

    // Handles GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the register.jsp page
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    // Handles POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve registration parameters from the request
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String dob = request.getParameter("dob");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cfPassword = request.getParameter("cf-password");

        boolean isValidInformation = true; // Flag to check if the information is valid

        // Get an instance of UserDAO
        UserDAO userDAO = UserDAO.getInstance();

        // Get an instance of Validation
        Validation validation = Validation.getInstance();

        // Validate first name
        if (firstName == null || firstName.isEmpty()) {
            isValidInformation = false;
            request.setAttribute("firstName_err", "You must fill first name");
        }

        // Validate last name
        if (lastName == null || lastName.isEmpty()) {
            isValidInformation = false;
            request.setAttribute("lastName_err", "You must fill last name");
        }

        // Validate email format
        if (!validation.CheckFormatEmail(email)) {
            isValidInformation = false;
            request.setAttribute("email_err", "Email is wrong format");
        }

        // Validate gender
        if (gender == null || gender.isEmpty()
                || (!gender.equals("true") && !gender.equals("false"))) {
            isValidInformation = false;
            request.setAttribute("gender_err", "Gender must be male or female");
        }

        // Validate phone number format
        if (!validation.CheckFormatPhone(phone)) {
            isValidInformation = false;
            request.setAttribute("phone_err", "Phone is wrong format");
        }

        // Validate username
        if (username == null || username.isEmpty()) {
            isValidInformation = false;
            request.setAttribute("username_err", "You must fill username");
        }

        // Check if the username already exists
        if (!userDAO.checkUserExistedByUsername(username)) {
            isValidInformation = false;
            request.setAttribute("username_err", "Username has been existed");
        }
        
        if(!userDAO.checkExistByEmail(email)) {
            isValidInformation = false;
            request.setAttribute("email_err", "Email has been existed");
        }

        // Validate password format
        if (!validation.CheckFormatPassword(password)) {
            isValidInformation = false;
            request.setAttribute("password_err",
                    "Your input must contain at least one uppercase letter,"
                    + " one lowercase letter, one digit, "
                    + "one special character, "
                    + "and be at least 8 characters long.");
        }

        // Validate if passwords match
        if (!password.equals(cfPassword)) {
            isValidInformation = false;
            request.setAttribute("cfPassword_err", "Password and Confirm password do not match");
        }

        // If all information is valid
        if (isValidInformation) {
            try {
                // Create a new user object and set its properties
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPhoneNumber(phone);
                user.setDob(java.sql.Date.valueOf(dob));
                user.setGender(Boolean.parseBoolean(gender));
                user.setUsername(username);
                password = Security.encryptToSHA512(password); // Encrypt the password
                user.setPassword(password);
                String token = CodeVerify.generateVerificationCode(); // Generate a verification token
                user.setToken(token);
                userDAO.insert(user); // Insert the user into the database
                
                // Create an activation link
                String activeLink = request.getScheme() + "://"
                        + request.getServerName()
                        + ":"
                        + request.getServerPort()
                        + "/QuizPractice/active";
                
                // Send a verification email
                Mail.sendMailVerify(email, token, activeLink);
                
                // Forward the request to the active.jsp page
                request.getRequestDispatcher("active.jsp").forward(request, response);

            } catch (Exception e) {
                // Handle exceptions
                e.printStackTrace();
            }
        } else {
            // If the information is not valid, set request attributes and forward back to register.jsp
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("phone", phone);
            request.setAttribute("dob", dob);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("cfPassword", cfPassword);
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
        return "RegisterController handles user registration and validation.";
    }

}
