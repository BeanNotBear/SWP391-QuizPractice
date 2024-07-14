package controller;

import dal.LessonDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Lesson;
import model.User;

@WebServlet("/lessonManager")
public class LessonManagerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LessonDAO lessonDAO = LessonDAO.getInstance();
        List<Lesson> lst = new ArrayList<>();

        int subjectId = 0;
        String subjectIdTmp = request.getParameter("subjectId");
        if (subjectIdTmp == null || subjectIdTmp.trim().isEmpty()) {
            response.sendRedirect("/QuizPractice/");
            return;
        }
        subjectId = Integer.parseInt(subjectIdTmp);

        int expertId = 0;
        expertId = lessonDAO.getUserIdBySubjectId(subjectId);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("subjectId " + subjectId);
//         System.out.println("userId "+user.getUserId());
        System.out.println("expertId " + expertId);
        if (user == null || expertId == 0 || user.getUserId() != expertId) {
            response.sendRedirect("/QuizPractice/"); // Thay đổi đường dẫn này tùy theo trang đăng nhập của bạn
            return;
        }

        // Lấy trang hiện tại từ request
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // lay ra data search
        String name = "";
        if (request.getParameter("searchName") != null) {
            name = request.getParameter("searchName");
        }

        String type = "";
        if (request.getParameter("searchType") != null) {
            type = request.getParameter("searchType");
        }

        int status = -1;
        if (request.getParameter("searchStatus") != null && !request.getParameter("searchStatus").isEmpty()) {
            status = Integer.parseInt(request.getParameter("searchStatus"));
        }

        lst = lessonDAO.getLessonsBySubjectWithPaging(subjectId, page, recordsPerPage, name, status == -1 ? null : status, type);
        int totalRecords = lessonDAO.getCount(subjectId, name, status == -1 ? null : status, type);

        int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);
        System.out.println("totalPage: " + totalPages);

        request.setAttribute("listLesson", lst);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.setAttribute("subjectId", subjectId);

        request.setAttribute("searchName", name);
        request.setAttribute("searchType", type);
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

//        request.getRequestDispatcher("/lessonManager.jsp").forward(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lessonManager.jsp");
        dispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String formType = request.getParameter("formType");
        if ("addForm".equals(formType)) {
            // Handle the form submission for adding a new lesson
            String name = request.getParameter("name");
            String content = request.getParameter("content");
            String media = request.getParameter("media");
            int lessonIndex = Integer.parseInt(request.getParameter("lessonIndex"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            String type = request.getParameter("type");

            // Print out the values for debugging
            System.out.println("Add Lesson Form Data:");
            System.out.println("Name: " + name);
            System.out.println("Content: " + content);
            System.out.println("Media: " + media);
            System.out.println("Lesson Index: " + lessonIndex);
            System.out.println("Subject ID: " + subjectId);
            System.out.println("Type: " + type);

            LessonDAO lessonDAO = LessonDAO.getInstance();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            
            lessonDAO.insertLesson(name, content, media, lessonIndex, type, user.getUserId());
            int lessonId = lessonDAO.getIdAddCurrent();
            System.out.println(lessonId + "-------------------");
            boolean s = lessonDAO.insertSubjectLesson(subjectId, lessonId);

            request.setAttribute("subjectId", subjectId);

            if (s) {
                session.setAttribute("successMessage", "Lesson add successfully.");
            } else {
                session.setAttribute("errorMessage", "Failed to add the lesson.");
            }

            String path = "lessonManager?subjectId=" + subjectId;
            response.sendRedirect(path);
//        request.getRequestDispatcher("/lessonManager.jsp").forward(request, response);

            // request.getRequestDispatcher(path).forward(request, response);
            // Logic to add the new lesson
            // Redirect or forward after adding the lesson
        } else if ("editForm".equals(formType)) {
            // Handle the form submission for adding a new lesson
            String name = request.getParameter("name");
            String content = request.getParameter("content");
            String media = request.getParameter("media");
            int lessonIndex = Integer.parseInt(request.getParameter("lessonIndex"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            String type = request.getParameter("type");
            int id = Integer.parseInt(request.getParameter("id"));

            LessonDAO lessonDAO = LessonDAO.getInstance();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            boolean s = lessonDAO.updateLesson(name, content, media, lessonIndex, type, id);

            request.setAttribute("subjectId", subjectId);

            if (s) {
                session.setAttribute("successMessage", "Lesson edited successfully.");
            } else {
                session.setAttribute("errorMessage", "Failed to edit the Lesson.");
            }

            String path = "lessonManager?subjectId=" + subjectId;
            response.sendRedirect(path);

        } else if ("toggleStatus".equals(formType)) {
            int lessonId = Integer.parseInt(request.getParameter("id"));
            int status = Integer.parseInt(request.getParameter("status"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));

            LessonDAO lessonDAO = LessonDAO.getInstance();
            boolean s = lessonDAO.updateStatus(status, lessonId);
            request.setAttribute("subjectId", subjectId);
            HttpSession session = request.getSession();
            if (s) {
                session.setAttribute("successMessage", "Lesson edited successfully.");
            } else {
                session.setAttribute("errorMessage", "Failed to edit the Lesson.");
            }

            String path = "lessonManager?subjectId=" + subjectId;
            response.sendRedirect(path);
        }
    }

}
