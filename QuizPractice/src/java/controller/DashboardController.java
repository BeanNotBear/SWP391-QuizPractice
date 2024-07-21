package controller;

import dal.StatisticDAO;
import dto.EnrollmentCountDTO;
import java.io.IOException;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name="DashboardController", urlPatterns={"/dashboard"})
public class DashboardController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        double totalRevenue = StatisticDAO.getInstance().getTotalRevenue();
        request.setAttribute("totalRevenue", totalRevenue);
        
        int numberOfEnrollments = StatisticDAO.getInstance().getNumberOfEnrollments();
        request.setAttribute("numberOfEnrollments", numberOfEnrollments);
        
        double totalProfit = StatisticDAO.getInstance().getTotalProfit();
        request.setAttribute("totalProfit", totalProfit);
        
        Gson gson = new Gson();
         
        List<EnrollmentCountDTO> ecdtos = StatisticDAO.getInstance().getEnrollments();
        String ecdtosJson = gson.toJson(ecdtos);
        request.setAttribute("ecdtosJson", ecdtosJson);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
