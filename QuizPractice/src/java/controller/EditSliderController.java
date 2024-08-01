package controller;

import dal.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Slider;

@WebServlet("/editSlider")
public class EditSliderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SliderDAO sliderDAO = SliderDAO.getInstance();
        Slider s = sliderDAO.getSliderById(id);

        request.setAttribute("slider", s);

        request.getRequestDispatcher("sliderDetailManager.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title").trim();
        String subTitle = request.getParameter("subTitle").trim();
        String content = request.getParameter("content").trim();
        String image = request.getParameter("image");
        String linkUrl = request.getParameter("linkUrl").trim();
        int status = Integer.parseInt(request.getParameter("status"));
        int id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();

        if (title.isEmpty() || subTitle.isEmpty() || content.isEmpty() || linkUrl.isEmpty() || image.isEmpty()) {
            session.setAttribute("errorMessage", "All fields must be filled and cannot be empty spaces.");
            response.sendRedirect("editSlider?id=" + id);
            return;
        }

        SliderDAO sliderDAO = SliderDAO.getInstance();
        boolean s = sliderDAO.editSlider(title, subTitle, content, image, linkUrl, status, id);

        if (s) {
            session.setAttribute("successMessage", "Slider edited successfully.");
        } else {
            session.setAttribute("errorMessage", "Failed to edit the slider.");
        }
        response.sendRedirect("sliderManager");
    }
}