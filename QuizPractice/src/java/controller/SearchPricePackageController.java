/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SubjectDAO;
import dto.SubjectPackagePriceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.User;

/**
 *
 * @author nghin
 */
@WebServlet(name = "SearchPricePackageController", urlPatterns = {"/searchpackageprice"})
public class SearchPricePackageController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            session.setAttribute("pagePackage", null);
            String search = "";
            int subjectId = (int) session.getAttribute("subjectId");
            if (request.getParameter("searchPackage") != null) {
                search = request.getParameter("searchPackage");
            }
            int page;
            int recordsPerPage = 5;
            if (session.getAttribute("pagePackage") == null) {
                page = 1;
            } else {
                page = (int) session.getAttribute("pagePackage");
            }
            session.setAttribute("searchPackage", search);
            List<SubjectPackagePriceDTO> packagePriceDTOs = SubjectDAO.getInstance().getListSubjectPackagePriceDTO(subjectId, page, recordsPerPage, search);

            User user = (User) session.getAttribute("user");
            int role = user.getRoleId();
            for (SubjectPackagePriceDTO packagePrice : packagePriceDTOs) {
                out.println("<tr>");
                out.println("<td>" + packagePrice.getId() + "</td>");
                out.println("<td>" + packagePrice.getPackageName() + "</td>");
                out.println("<td>" + packagePrice.getDuration() + "</td>");
                out.println("<td>" + packagePrice.getPrice() + "</td>");
                out.println("<td>" + packagePrice.getSalePrice() + "</td>");
                out.println("<td>" + packagePrice.getOriginalPrice() + "</td>");
                out.println("<td>" + packagePrice.getStatus() + "</td>");

                if (role == 2) {
                    out.println("<td>");
                    out.println("<c:choose>");
                    if ("active".equals(packagePrice.getStatus())) {
                        out.println("<a href=\"#\" class=\"btn btn-danger btn-sm\" onclick=\"changeStatus(" + packagePrice.getId() + ", 'inactive', " + subjectId + ")\">Inactive</a>");
                    } else {
                        out.println("<a href=\"#\" class=\"btn btn-success btn-sm\" onclick=\"changeStatus(" + packagePrice.getId() + ", 'active', " + subjectId + ")\">Active</a>");
                    }
                    out.println("</c:choose>");
                    out.println("<a onclick=\"showPopUpEdit(event, 'Edit')\" class=\"btn btn-warning btn-sm edit-btn\">Edit</a>");
                    out.println("</td>");
                }

                out.println("</tr>");
            }
        }
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
