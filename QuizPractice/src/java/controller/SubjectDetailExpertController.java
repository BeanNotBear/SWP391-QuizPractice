package controller;

import dal.LessonDAO;
import dal.PricePackageDAO;
import dal.SubjectDAO;
import dal.UserDAO;
import dto.DimensionDTO;
import dto.LearnerDTO;
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
import org.apache.http.HttpResponse;

@WebServlet("/subjectDetailExpert")
public class SubjectDetailExpertController extends HttpServlet {
    int id = 0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tempId = request.getParameter("id");
        HttpSession session = request.getSession();
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
        lessons = LessonDAO.getInstance().getLessonsBySubjectId(id, 1, 5, "");
        request.setAttribute("lessons", lessons);

        Subject subject = subjectDAO.getSubjectById(id);
        request.setAttribute("subject", subject);

        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO = UserDAO.getInstance().getOnwerBySubjectId(id);
        request.setAttribute("owner", ownerDTO);
        
        session.setAttribute("ownerId", ownerDTO.getId());
        
        List<LearnerDTO> learners = UserDAO.getInstance().getLearnersBySubjectId(id, 1, 5, "");
        request.setAttribute("learners", learners);
        
        List<OwnerDTO> experts = UserDAO.getInstance().getExpertPagination(1, 5, ownerDTO.getId(), "");
        request.setAttribute("experts", experts);
        
        List<SubjectPackagePriceDTO> packageList = new ArrayList<>();
        packageList = subjectDAO.getListSubjectPackagePriceDTO(id, 1, 5, "");
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
        String name = request.getParameter("name");
        String txtStatus = request.getParameter("status");
        String txtCategory = request.getParameter("category");
        String description = request.getParameter("description");
        String img = request.getParameter("img");
        boolean feature = true;
        
        try {
            int status = Integer.parseInt(txtStatus);
            System.out.println("status: " + status);
            int category = Integer.parseInt(txtCategory);
            SubjectDAO.getInstance().updateSubject(id, name, status, img, category, description);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
