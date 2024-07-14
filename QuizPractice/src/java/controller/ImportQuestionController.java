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

        if (filePart == null || filePart.getSize() == 0) {
            response.getWriter().write("Fail to add, question file is empty");
            return;
        }

        List<QuestionImport> questions = new ArrayList<>();
        try (InputStream fileContent = filePart.getInputStream();
             Workbook workbook = new XSSFWorkbook(fileContent)) {
            
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getPhysicalNumberOfRows() <= 1) {
                response.getWriter().write("Fail to add, question file is empty");
                return;
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                if (row.getPhysicalNumberOfCells() < 3) {
                    response.getWriter().write("Wrong format question");
                    return;
                }

                String detail = getStringCellValue(row.getCell(0));
                String suggestion = getStringCellValue(row.getCell(1));
                if (detail.isEmpty() || suggestion.isEmpty()) {
                    response.getWriter().write("Not except field empty");
                    return;
                }

                int status;
                try {
                    status = (int) row.getCell(2).getNumericCellValue();
                } catch (Exception e) {
                    response.getWriter().write("Wrong format question");
                    return;
                }
                String media = row.getCell(3) != null ? getStringCellValue(row.getCell(3)) : null;
                questions.add(new QuestionImport(detail, suggestion, status, media));
            }
        } catch (Exception e) {
            response.getWriter().write("Error reading Excel file");
            return;
        }

        QuestionDAO questionDAO = QuestionDAO.getInstance();
        try {
            for (QuestionImport question : questions) {
                questionDAO.addQuestion(question, subjectId, lessonId);
            }
            response.getWriter().write("Questions imported successfully");
        } catch (SQLException e) {
            response.getWriter().write("Database access error");
        }
    }

    private String getStringCellValue(Cell cell) {
        return cell == null ? "" : cell.toString().trim();
    }
}
