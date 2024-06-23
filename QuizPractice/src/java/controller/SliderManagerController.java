package controller;

import dal.SliderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Slider;
import model.User;

@WebServlet("/sliderManager")
public class SliderManagerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Slider> lst = new ArrayList<>();
        SliderDAO sliderDAO = SliderDAO.getInstance();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRoleId() != 5) {
            String message = "Permission granted! Login with role marketing to access page.";
            response.sendRedirect("/QuizPractice?error=true&message=" + message);
            return;
        }

        // Lấy trang hiện tại từ request
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // lay ra data search
        String title = "";
        if (request.getParameter("searchTitle") != null) {
            title = request.getParameter("searchTitle");
        }

        int status = -1;
        if (request.getParameter("searchStatus") != null && !request.getParameter("searchStatus").isEmpty()) {
            status = Integer.parseInt(request.getParameter("searchStatus"));
        }

        lst = sliderDAO.getPaginationSliderAll(page, recordsPerPage, status, title);
        int totalRecords = sliderDAO.getTotalRecordSliderAll(status, title);
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

        request.setAttribute("listSlider", lst);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.setAttribute("searchTitle", title);
        request.setAttribute("searchStatus", status);

        // Get messages from session and set them as request attributes
        String successMessage = (String) session.getAttribute("successMessage");
        String errorMessage = (String) session.getAttribute("errorMessage");

        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            session.removeAttribute("errorMessage");
        }

        request.getRequestDispatcher("/sliderManager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}