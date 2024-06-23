package controller;

import dal.SubjectDAO;
import dto.DimensionDTO;
import dto.MyRegisterDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.User;

@WebServlet("/myRegister")
public class MyRegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<MyRegisterDTO> lst = new ArrayList<>();
        SubjectDAO subjectDAO = SubjectDAO.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/QuizPractice/"); // Thay đổi đường dẫn này tùy theo trang đăng nhập của bạn
            return;
        }

        // Lấy trang hiện tại từ request
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
           // lay ra data search
        String name = "";
        if (request.getParameter("searchName") != null) {
            name = request.getParameter("searchName");
        }
        String content = "";
        if (request.getParameter("searchContent") != null) {
            content = request.getParameter("searchContent");
        }
        int dimensionId = -1;
        if (request.getParameter("searchDimensionId") != null && !request.getParameter("searchDimensionId").isEmpty()) {
            dimensionId = Integer.parseInt(request.getParameter("searchDimensionId"));
        }

        lst = subjectDAO.getPaginationRegisterSubjectAll(user.getUserId(), page, recordsPerPage, dimensionId, name, content);
        int totalRecords = subjectDAO.getTotalRecords(user.getUserId(), dimensionId, name, content);
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

        request.setAttribute("listRegister", lst);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
                
        request.setAttribute("searchName", name);
        request.setAttribute("searchContent", content);
        request.setAttribute("searchDimensionId", dimensionId);
                
        List<DimensionDTO> listDimension = subjectDAO.getListDimensionDTO();
        request.setAttribute("listDimension", listDimension);
        

        request.getRequestDispatcher("/myRegister.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
}