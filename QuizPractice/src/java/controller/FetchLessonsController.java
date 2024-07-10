package controller;

import dal.LessonDAO;
import dto.LessonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "FetchLessonsController", urlPatterns = {"/fetchLessons"})
public class FetchLessonsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subjectId = request.getParameter("subjectId");
        LessonDAO lessonDAO = LessonDAO.getInstance();

        try {
            List<LessonDTO> lessons = lessonDAO.getLessonsBySubjectId(subjectId);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            for (LessonDTO lesson : lessons) {
                out.println("<option value=\"" + lesson.getId() + "\">" + lesson.getName() + "</option>");
            }

        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }
    }
}
