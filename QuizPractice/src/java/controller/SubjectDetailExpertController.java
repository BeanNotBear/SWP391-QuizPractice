package controller;

import dal.LessonDAO;
import dal.PricePackageDAO;
import dal.SubjectDAO;
import dal.UserDAO;
import dto.DimensionDTO;
import dto.LessonSubjectDTO;
import dto.OwnerDTO;
import dto.SubjectPackagePriceDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Dimension;
import model.Subject;
import model.User;

// Subject Detail
@WebServlet("/subjectDetailExpert")
public class SubjectDetailExpertController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tempId = request.getParameter("id");
        HttpSession session = request.getSession();
        int id = 0;
        if (tempId != null) {
            id = Integer.parseInt(tempId);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        session.setAttribute("subjectId", id);
        SubjectDAO subjectDAO = SubjectDAO.getInstance();
        List<DimensionDTO> dimensionDTOs = new ArrayList<>();
        dimensionDTOs = subjectDAO.getListDimensionDTO();
        request.setAttribute("dimensions", dimensionDTOs);

        List<Dimension> dimensionList = new ArrayList<>();
        dimensionList = subjectDAO.getListDimension();
        request.setAttribute("dimensionList", dimensionList);

        List<LessonSubjectDTO> lessons = new ArrayList<>();
        lessons = LessonDAO.getInstance().getLessonsBySubjectId(id, 1, 10);
        request.setAttribute("lessons", lessons);

        Subject subject = subjectDAO.getSubjectById(id);
        request.setAttribute("subject", subject);

        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO = UserDAO.getInstance().getOnwerBySubjectId(id);
        request.setAttribute("owner", ownerDTO);
        
        session.setAttribute("ownerId", ownerDTO.getId());
        
        List<OwnerDTO> experts = UserDAO.getInstance().getExpertPagination(1, 5, ownerDTO.getId(), "");
        request.setAttribute("experts", experts);
        
        List<SubjectPackagePriceDTO> packageList = new ArrayList<>();
        packageList = subjectDAO.getListSubjectPackagePriceDTO(id);
        request.setAttribute("packageList", packageList);

        int numberOfPackagePrice = 0;
        numberOfPackagePrice = PricePackageDAO.getInstance().getNumberOfPricePackagesBySubjectId(id);
        request.setAttribute("noOfPackage", numberOfPackagePrice);

        int numberOfLesson = 0;
        numberOfLesson = LessonDAO.getInstance().getNumberOfLessonsBySubjectId(id);
        request.setAttribute("noOfLessons", numberOfLesson);

        int numberOfStudent = 0;
        numberOfStudent = UserDAO.getInstance().getNumberOfStudentsBySubjectId(id);
        request.setAttribute("noOfStudents", numberOfStudent);

        request.getRequestDispatcher("subjectDetailExpert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO subjectDAO = SubjectDAO.getInstance();
        String tempId = request.getParameter("id");
        int id = 0;
        if (tempId != null) {
            id = Integer.parseInt(tempId);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        System.out.println(id);
        String img = request.getParameter("img");
        System.out.println(img);
        String name = request.getParameter("name");
        System.out.println(name);
        int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
        System.out.println(dimensionId);
        String description = request.getParameter("description");
        System.out.println(description);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/QuizPractice/"); // Thay đổi đường dẫn này tùy theo trang đăng nhập của bạn
            return;
        } else {
            boolean row = subjectDAO.updateSubject(id, name, img, dimensionId, description);
            if (row) {
                response.sendRedirect("subjectDetailExpert?id=" + id); // Thay đổi đường dẫn này tùy theo trang đăng nhập của bạn
                return;
            } else {
                response.sendRedirect("/QuizPractice/error.jsp");
            }
        }
    }

}
