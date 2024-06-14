package controller;

import dal.SubjectDAO;
import dto.DimensionDTO;
import dto.SubjectManagerDTO;
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

@WebServlet("/subjectManager")
public class SubjectManagerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<SubjectManagerDTO> lst = new ArrayList<>();
        SubjectDAO subjectDAO = SubjectDAO.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/QuizPractice/"); // Thay đổi đường dẫn này tùy theo trang đăng nhập của bạn
            return;
        }

        // Lấy trang hiện tại từ request
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        lst = subjectDAO.getPaginationExpertManagerSubject(user.getUserId(), page, recordsPerPage);
        int totalRecords = subjectDAO.getTotalRecordsExpertManagerSubject(user.getUserId());
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

        List<DimensionDTO> dimensionDTOs = new ArrayList<>();
        dimensionDTOs = subjectDAO.getListDimensionDTO();
        request.setAttribute("dimensions", dimensionDTOs);

        request.setAttribute("listSubject", lst);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/subjectManager.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("typeSubmit");
        System.out.println(type);
        if (type.equalsIgnoreCase("submitName")) {
            String subjectName = request.getParameter("subjectName");
            System.out.println(subjectName);
            List<SubjectManagerDTO> lst = new ArrayList<>();
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

            lst = subjectDAO.getPaginationExpertManagerSubjectSearchBySubjectName(user.getUserId(), page, recordsPerPage, subjectName);
            int totalRecords = subjectDAO.getTotalRecordsExpertManagerSubjectSearchBySubjectName(user.getUserId(), subjectName);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            List<DimensionDTO> dimensionDTOs = new ArrayList<>();
            dimensionDTOs = subjectDAO.getListDimensionDTO();
            request.setAttribute("dimensions", dimensionDTOs);

            request.setAttribute("listSubject", lst);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("/subjectManager.jsp").forward(request, response);
        }
        else if (type.equalsIgnoreCase("dimensionId")) {
            int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
            System.out.println(dimensionId);
            List<SubjectManagerDTO> lst = new ArrayList<>();
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

            lst = subjectDAO.getPaginationExpertManagerSubjectSearchByDimensionId(user.getUserId(), page, recordsPerPage, dimensionId);
            int totalRecords = subjectDAO.getTotalRecordsExpertManagerSubjectSearchByDimensionId(user.getUserId(), dimensionId);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            List<DimensionDTO> dimensionDTOs = new ArrayList<>();
            dimensionDTOs = subjectDAO.getListDimensionDTO();
            request.setAttribute("dimensions", dimensionDTOs);

            request.setAttribute("listSubject", lst);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("/subjectManager.jsp").forward(request, response);
        }
        else if (type.equalsIgnoreCase("status")) {
            int status = request.getParameter("status").equalsIgnoreCase("true")?1:0;
            System.out.println(status);
            List<SubjectManagerDTO> lst = new ArrayList<>();
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

            lst = subjectDAO.getPaginationExpertManagerSubjectSearchByStatus(user.getUserId(), page, recordsPerPage, status);
            int totalRecords = subjectDAO.getTotalRecordsExpertManagerSubjectSearchByStatus(user.getUserId(), status);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            List<DimensionDTO> dimensionDTOs = new ArrayList<>();
            dimensionDTOs = subjectDAO.getListDimensionDTO();
            request.setAttribute("dimensions", dimensionDTOs);

            request.setAttribute("listSubject", lst);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("/subjectManager.jsp").forward(request, response);
        }
    }

}