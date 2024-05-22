/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Blogs;
import model.Category;

/**
 *
 * @author DELL
 */
public class BlogListController extends HttpServlet {

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

        UserDAO dao = UserDAO.getInstance();

        // Lấy tham số tìm kiếm
        String search = request.getParameter("search");
        if (search == null) {
            search = "";
        }

        // Lấy chỉ số trang
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);

        // Đếm tổng số blog phù hợp với tìm kiếm
        int count = dao.countBlogsByTitle(search);
        int endPage = count / 3;
        if (count % 3 != 0) {
            endPage++;
        }

        // Lấy danh sách blog phù hợp với tìm kiếm và phân trang
        List<Blogs> list = dao.searchPagingBlogs(search, index);      
        List<Category> listcate = dao.getAllCategories();
        Blogs blog = dao.findNewPost();
        
        //tất cả danh mục
        request.setAttribute("listcatego", listcate);
        
        // hiển thị tất cả các blog
        request.setAttribute("bloglist", list);
        
        // hiển thị số trang cuối cùng.
        request.setAttribute("endPage", endPage); 
        
        // Lưu lại thông tin tìm kiếm để hiển thị trên form
        request.setAttribute("searchQuery", search); 
        
        // Đánh dấu trang hiện tại
        request.setAttribute("currentPage", index); 
        
        // Thông tin của blog mới nhất.
        request.setAttribute("nee", blog); 
        request.getRequestDispatcher("blogslist.jsp").
                forward(request, response);

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
