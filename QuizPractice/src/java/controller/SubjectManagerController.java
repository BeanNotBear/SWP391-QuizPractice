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

    private String searchCondition = "";
    private int _status = -1;
    private int dimension = -1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        searchCondition = "";
        _status = -1;
        dimension = -1;
        List<SubjectManagerDTO> lst = new ArrayList<>();
        SubjectDAO subjectDAO = SubjectDAO.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/QuizPractice/"); // Thay đổi đường dẫn này tùy theo trang đăng nhập của bạn
            return;
        }
        if (user.getRoleId() != 2 && user.getRoleId() != 3) {
            response.sendError(404);
            return;
        }
        int page = 1;
        int recordsPerPage = 10;
        int totalRecords = 0;
        int totalPages = 0;

        if (user.getRoleId() == 3) {
            // Lấy trang hiện tại từ request

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            lst = subjectDAO.getPaginationExpertManagerSubject(user.getUserId(), page, recordsPerPage);
            totalRecords = subjectDAO.getTotalRecordsExpertManagerSubject(user.getUserId());
            totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            List<DimensionDTO> dimensionDTOs = new ArrayList<>();
            dimensionDTOs = subjectDAO.getListDimensionDTO();
            request.setAttribute("dimensions", dimensionDTOs);

            request.setAttribute("listSubject", lst);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
        } else if (user.getRoleId() == 2) {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            lst = subjectDAO.getPaginationAdmin(page, recordsPerPage);
            totalRecords = subjectDAO.getTotalRecordSubject();
            totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            List<DimensionDTO> dimensionDTOs = new ArrayList<>();
            dimensionDTOs = subjectDAO.getListDimensionDTO();
            request.setAttribute("dimensions", dimensionDTOs);

            request.setAttribute("listSubject", lst);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
        }
        request.getRequestDispatcher("/subjectManager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        var user = (User) session.getAttribute("user");
        // User is an expert
        if (user.getRoleId() == 3) {
            filterByExpert(request, response);
        } else if (user.getRoleId() == 2) { // user is an admin
            filterByAdmin(request, response);
        }
    }

    private void filterByExpert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("typeSubmit");
        System.out.println(type);
        if (type.equalsIgnoreCase("submitName")) {
            String subjectName = request.getParameter("subjectName");
            searchCondition = subjectName;
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

            lst = subjectDAO.getPaginationExpertManagerSubjectSearchBySubjectName(user.getUserId(), page, recordsPerPage, subjectName, dimension, _status);
            int totalRecords = subjectDAO.getTotalRecordsExpertManagerSubjectSearchBySubjectName(user.getUserId(), subjectName);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            List<DimensionDTO> dimensionDTOs = new ArrayList<>();
            dimensionDTOs = subjectDAO.getListDimensionDTO();
            request.setAttribute("dimensions", dimensionDTOs);

            request.setAttribute("listSubject", lst);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.setAttribute("searchCondition", searchCondition);
            request.setAttribute("dimension", dimension);
            request.setAttribute("status", _status);

            request.getRequestDispatcher("/subjectManager.jsp").forward(request, response);
        } else if (type.equalsIgnoreCase("dimensionId")) {
            int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
            dimension = dimensionId;
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

            lst = subjectDAO.getPaginationExpertManagerSubjectSearchByDimensionId(user.getUserId(), page, recordsPerPage, dimensionId, searchCondition, _status);
            int totalRecords = subjectDAO.getTotalRecordsExpertManagerSubjectSearchByDimensionId(user.getUserId(), dimensionId);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            List<DimensionDTO> dimensionDTOs = new ArrayList<>();
            dimensionDTOs = subjectDAO.getListDimensionDTO();
            request.setAttribute("dimensions", dimensionDTOs);

            request.setAttribute("listSubject", lst);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.setAttribute("searchCondition", searchCondition);
            request.setAttribute("dimension", dimension);
            request.setAttribute("status", _status);

            request.getRequestDispatcher("/subjectManager.jsp").forward(request, response);
        } else if (type.equalsIgnoreCase("status")) {
            int status = request.getParameter("status").equalsIgnoreCase("true") ? 1 : 0;
            _status = status;
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

            lst = subjectDAO.getPaginationExpertManagerSubjectSearchByStatus(user.getUserId(), page, recordsPerPage, status, searchCondition, dimension);
            int totalRecords = subjectDAO.getTotalRecordsExpertManagerSubjectSearchByStatus(user.getUserId(), status);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            List<DimensionDTO> dimensionDTOs = new ArrayList<>();
            dimensionDTOs = subjectDAO.getListDimensionDTO();
            request.setAttribute("dimensions", dimensionDTOs);

            request.setAttribute("listSubject", lst);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.setAttribute("searchCondition", searchCondition);
            request.setAttribute("dimension", dimension);
            request.setAttribute("status", _status);

            request.getRequestDispatcher("/subjectManager.jsp").forward(request, response);
        }
    }

    private void filterByAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("typeSubmit");
        System.out.println(type);
        if (type.equalsIgnoreCase("submitName")) {
            String subjectName = request.getParameter("subjectName");
            searchCondition = subjectName;
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

            lst = subjectDAO.getPaginationAdminManagerSubjectSearchBySubjectName(page, recordsPerPage, subjectName, dimension, _status);
            int totalRecords = subjectDAO.getTotalRecordsAdminManagerSubjectSearchBySubjectName(subjectName);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            List<DimensionDTO> dimensionDTOs = new ArrayList<>();
            dimensionDTOs = subjectDAO.getListDimensionDTO();
            request.setAttribute("dimensions", dimensionDTOs);

            request.setAttribute("listSubject", lst);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.setAttribute("searchCondition", searchCondition);
            request.setAttribute("dimension", dimension);
            request.setAttribute("status", _status);

            request.getRequestDispatcher("/subjectManager.jsp").forward(request, response);
        } else if (type.equalsIgnoreCase("dimensionId")) {
            int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
            dimension = dimensionId;
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

            lst = subjectDAO.getPaginationAdminManagerSubjectSearchByDimensionId(page, recordsPerPage, dimensionId, searchCondition, _status);
            int totalRecords = subjectDAO.getTotalRecordsExpertManagerSubjectSearchByDimensionId(user.getUserId(), dimensionId);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            List<DimensionDTO> dimensionDTOs = new ArrayList<>();
            dimensionDTOs = subjectDAO.getListDimensionDTO();
            request.setAttribute("dimensions", dimensionDTOs);

            request.setAttribute("listSubject", lst);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.setAttribute("searchCondition", searchCondition);
            request.setAttribute("dimension", dimension);
            request.setAttribute("status", _status);

            request.getRequestDispatcher("/subjectManager.jsp").forward(request, response);
        } else if (type.equalsIgnoreCase("status")) {
            int status = request.getParameter("status").equalsIgnoreCase("true") ? 1 : 2;
            _status = status;
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

            lst = subjectDAO.getPaginationAdminManagerSubjectSearchByStatus(page, recordsPerPage, status, searchCondition, dimension);
            int totalRecords = subjectDAO.getTotalRecordsExpertManagerSubjectSearchByStatus(user.getUserId(), status);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            List<DimensionDTO> dimensionDTOs = new ArrayList<>();
            dimensionDTOs = subjectDAO.getListDimensionDTO();
            request.setAttribute("dimensions", dimensionDTOs);

            request.setAttribute("listSubject", lst);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.setAttribute("searchCondition", searchCondition);
            request.setAttribute("dimension", dimension);
            request.setAttribute("status", _status);

            request.getRequestDispatcher("/subjectManager.jsp").forward(request, response);
        }
    }
}
