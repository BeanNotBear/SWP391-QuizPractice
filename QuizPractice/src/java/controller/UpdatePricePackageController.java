package controller;

import dal.PricePackageDAO;
import dto.SubjectPackagePriceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

@WebServlet(name = "UpdatePricePackageController", urlPatterns = {"/EditPricePackage"})
public class UpdatePricePackageController extends HttpServlet {

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
            JSONObject jsonResponse = new JSONObject();
            HttpSession session = request.getSession();

            String txtId = request.getParameter("id");
            String name = request.getParameter("name");
            String txtDuration = request.getParameter("duration");
            String txtPrice = request.getParameter("price");
            String txtSalePrice = request.getParameter("salePrice");
            String txtOriginalPrice = request.getParameter("originalPrice");

            int id = Integer.parseInt(txtId);
            int duration = Integer.parseInt(txtDuration);
            double price = Double.parseDouble(txtPrice);
            double salePrice = Double.parseDouble(txtSalePrice);
            double originalPrice = Double.parseDouble(txtOriginalPrice);

            SubjectPackagePriceDTO packagePriceDTO = new SubjectPackagePriceDTO(id,
                    name,
                    duration,
                    salePrice,
                    price,
                    originalPrice,
                    null);

            PricePackageDAO.getInstance().UpdatePricePackageById(packagePriceDTO);
        } catch (Exception e) {
            e.printStackTrace();
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
