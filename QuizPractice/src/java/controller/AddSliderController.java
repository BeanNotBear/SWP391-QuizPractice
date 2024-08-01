package controller;

import com.google.gson.Gson;
import dal.SliderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/addSlider")
public class AddSliderController extends HttpServlet {

    private final Gson gson = new Gson();
    private final SliderDAO sliderDAO = SliderDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title").trim();
        String subTitle = request.getParameter("subTitle").trim();
        String content = request.getParameter("content").trim();
        String image = request.getParameter("image");
        String linkUrl = request.getParameter("linkUrl").trim();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.getWriter().write(gson.toJson(new ResponseMessage("User not logged in!")));
            return;
        }

        if (title.isEmpty() || subTitle.isEmpty() || content.isEmpty() || image.isEmpty() || linkUrl.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson(new ResponseMessage("All fields must be filled and cannot be empty spaces.")));
            return;
        }

        boolean success = sliderDAO.addSlider(title, subTitle, content, image, linkUrl, user.getUserId());

        if (success) {
            response.getWriter().write(gson.toJson(new ResponseMessage("Slider added successfully")));
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson(new ResponseMessage("Failed to add slider")));
        }
    }

    private static class ResponseMessage {
        private final String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}