package controller;

// Import necessary classes
import dal.SubjectDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Subject;

@WebServlet(name = "SubjectController", urlPatterns = {"/subject"})
public class SubjectController extends HttpServlet {

    // Handles GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the home.jsp page
        String pageParam = request.getParameter("page");
        String searchParam = request.getParameter("search_box");
        String sort = request.getParameter("sort");
        int page = 1; // Default to the first page
        int pageSize = 6; // Set the desired page size
        if (pageParam != null && !pageParam.isEmpty()) {
            // Parse the page parameter to an integer
            page = Integer.parseInt(pageParam);
        }
        
        SubjectDAO subjectDAO = SubjectDAO.getInstance();

        List<Subject> listSubject = subjectDAO.allSubjectsWithConditions(searchParam, sort);
        System.out.println(listSubject);
        List<Subject> pagingSubject = subjectDAO.Paging(listSubject, page, pageSize);
        request.setAttribute("listC", pagingSubject);
        request.setAttribute("totalPages", listSubject.size() % pageSize == 0 ? (listSubject.size() / pageSize) : (listSubject.size() / pageSize + 1));
        request.setAttribute("currentPage", page);
        request.setAttribute("searchParam", searchParam);
        request.getRequestDispatcher("subject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
