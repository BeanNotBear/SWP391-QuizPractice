package controller;

import dal.QuestionDAO;
import model.QuestionImport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/importQuestions"})
@MultipartConfig
public class ImportQuestionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String subjectId = request.getParameter("subjectId");
        String lessonId = request.getParameter("lessonId");

        System.out.println("Go importtttt");
        List<QuestionImport> questions = new ArrayList<>();
        try (InputStream fileContent = filePart.getInputStream();
             Workbook workbook = new XSSFWorkbook(fileContent)) {
            
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                String detail = row.getCell(0).getStringCellValue();
                String suggestion = row.getCell(1).getStringCellValue();
                int status = (int) row.getCell(2).getNumericCellValue();
                String media = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null;
                questions.add(new QuestionImport(detail, suggestion, status, media));
                
                System.out.println(detail+suggestion+ status+ media);
            }
        } catch (Exception e) {
            throw new ServletException("Error reading Excel file", e);
        }

        QuestionDAO questionDAO = QuestionDAO.getInstance();
        try {
            for (QuestionImport question : questions) {
                questionDAO.addQuestion(question, subjectId, lessonId);
            }
            response.sendRedirect("questionList"); // Redirect to the question list page
        } catch (SQLException e) {
            throw new ServletException("Database access error", e);
        }
    }
}