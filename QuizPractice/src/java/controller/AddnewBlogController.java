package controller;

import dal.BlogDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.User;
import org.json.JSONObject;

@WebServlet(name = "AddnewBlogController", urlPatterns = {"/addnewblog"})
public class AddnewBlogController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/QuizPractice/");
            return;
        }
        if (user.getRoleId() != 5) {
            response.sendError(404);
            return;
        }
        BlogDAO blogDao = BlogDAO.getInstance();
        List<Category> listCategory = blogDao.getAllCategories();

        request.setAttribute("listCategory", listCategory);
        request.getRequestDispatcher("AddNewBlog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BlogDAO blogDao = BlogDAO.getInstance();
        String title = request.getParameter("title");
        String briefinfo = request.getParameter("briefinfo");
        String categoryName = request.getParameter("categoryName");
        String content = request.getParameter("content");
        String thumbnail = request.getParameter("thumbnail");
        boolean status = request.getParameter("status") != null;

        JSONObject jsonResponse = new JSONObject();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/QuizPractice/");
            return;
        } else {
            boolean flag = true;
            int categoryId = blogDao.getCategoryIdByName(categoryName);
            if (categoryId == -1) {
                flag = false;
                try {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Invalid category name.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            boolean row = false;
            if (flag) {
                int authorId = user.getUserId(); // Giả sử bạn có phương thức getId() để lấy ID của người dùng
                row = blogDao.addBlog(title, briefinfo, content, status, thumbnail, categoryId, authorId);
            }
            try {
                if (row) {
                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Add a new blog successfully!");
                } else if (flag && !row) {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Add a new blog fail!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
