package controller;

import dal.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Blog;
import model.Category;
import model.User;

@WebServlet(name="BlogListServlet", urlPatterns={"/blog"})
public class BlogListController extends HttpServlet {

         protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        BlogDAO dao = BlogDAO.getInstance();

          HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String search = request.getParameter("search");
        if (search == null) {
            search = "";
        } else {
            search = search.trim();
        }

        String category = request.getParameter("category");
        if (category == null) {
            category = "";
        }

        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);

        int count;
        if (user != null && user.getRoleId() == 5) {
            count = dao.countAllBlogsByTitleAndCategory(search, category);
        } else {
            count = dao.countBlogsByTitleAndCategory(search, category);
        }

        int endPage = count / 6;
        if (count % 6 != 0) {
            endPage++;
        }

        List<Blog> list;
        if (user != null && user.getRoleId() == 5) {
            list = dao.searchPagingAllBlogs(search, category, index);
        } else {
            list = dao.searchPagingBlogs(search, category, index);
        }

        List<Category> listcate = dao.getAllCategories();
        Blog blog = dao.findNewPost();

        request.setAttribute("listcatego", listcate);
        request.setAttribute("bloglist", list);
        request.setAttribute("endPage", endPage);
        request.setAttribute("searchQuery", search);
        request.setAttribute("selectedCategory", category);
        request.setAttribute("currentPage", index);
        request.setAttribute("nee", blog);

        request.getRequestDispatcher("blogslist.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
