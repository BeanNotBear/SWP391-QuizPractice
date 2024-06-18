/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SliderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Slider;

@WebServlet("/sliderManager")
public class SliderManagerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Slider> lst = new ArrayList<>();
        SliderDAO sliderDAO = SliderDAO.getInstance();
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            response.sendRedirect("/QuizPractice/"); // Thay đổi đường dẫn này tùy theo trang đăng nhập của bạn
//            return;
//        }

        // Lấy trang hiện tại từ request
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        lst = sliderDAO.getPaginationSlider(page, recordsPerPage);
        int totalRecords = sliderDAO.getTotalRecordSlider();
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

       
        request.setAttribute("listSlider", lst);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/sliderManager.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}