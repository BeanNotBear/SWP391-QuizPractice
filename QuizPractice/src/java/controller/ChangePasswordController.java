/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.json.JSONException;
import org.json.JSONObject;
import util.security.Security;
import util.validation.Validation;

@WebServlet("/change-password")
public class ChangePasswordController extends HttpServlet {

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
        doPost(request, response);
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
        HttpSession session = request.getSession();

        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        String confirmPassword = request.getParameter("confirm-password");

        UserDAO userDAO = UserDAO.getInstance();
        User user = (User) session.getAttribute("user");
        Validation validation = Validation.getInstance();

        JSONObject jsonResponse = new JSONObject();

        try {

            if (validation.CheckFormatPassword(newPassword) && newPassword.equals(confirmPassword) && Security.encryptToSHA512(oldPassword).equals(user.getPassword())) {
                jsonResponse.put("status", "success");
                userDAO.updatePassword(newPassword, user.getEmail());
            } else if (!validation.CheckFormatPassword(newPassword)) {
                jsonResponse.put("status", "error");
                jsonResponse.put("error", "Your input must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long.");
            } else if (!newPassword.equals(confirmPassword)) {
                jsonResponse.put("status", "error");
                jsonResponse.put("error", "Password and Confirm password do not match");
            } else {
                jsonResponse.put("status", "error");
                jsonResponse.put("error", "Old password is incorrect or other validation errors occurred");
            }
        } catch (JSONException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
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