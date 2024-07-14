/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import context.DBContext;
import dto.PracticeListDTO;
import model.Question;
import dto.QuestionDTO;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Practice;
import model.QuestionStatus;

/**
 *
 * @author Admin
 */
public class PracticeDAO extends DBContext {

    private static PracticeDAO instance;
    // Lock object for thread-safe singleton instantiation
    private static Object lockPad = new Object();

    // Private constructor to prevent instantiation
    private PracticeDAO() {
    }

    // Returns the singleton instance of SliderDAO
    public static PracticeDAO getInstance() {
        if (instance == null) {
            synchronized (lockPad) {
                if (instance == null) {
                    instance = new PracticeDAO();
                }
            }
        }
        return instance;
    }

    public int getTotalRecordsSearch(int userId, String searchName) {
        String query = "SELECT COUNT(*) FROM Practices WHERE UserId = ? AND SubjectId = (select id from subjects where name =?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, searchName);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                return id;
            }

        } catch (SQLException e) {
            // Log the exception (if a logging framework is available)
            e.printStackTrace(); // Replace with logger in real application
        }
        return 0;
    }

    public List<PracticeListDTO> getPaginationPracticeListSearch(int userId, int page, int recordsPerPage, String searchName) {
        List<PracticeListDTO> lst = new ArrayList<>();
        int start = (page - 1) * recordsPerPage + 1;
        int end = start + recordsPerPage - 1;

        try {
            String query = "WITH PagedResults AS (\n"
                    + "    SELECT p.id,s.name AS subject_name, p.CreatedAt, p.NumberQuestion, p.NumberCorrect, p.Duration,\n"
                    + "           ROW_NUMBER() OVER (ORDER BY p.CreatedAt) AS row_num\n"
                    + "    FROM Practices p\n"
                    + "    LEFT JOIN subjects s ON p.SubjectId = s.id \n"
                    + "    WHERE p.UserId = ? AND s.name = ?\n"
                    + ")\n"
                    + "SELECT * \n"
                    + "FROM PagedResults\n"
                    + "WHERE row_num BETWEEN ? AND ?\n"
                    + "ORDER BY row_num";

            ps = connection.prepareStatement(query);
            ps.setInt(1, userId); // Thay đổi UserId tương ứng
            ps.setString(2, searchName);
            ps.setInt(3, start);
            ps.setInt(4, end);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String subjectName = rs.getString(2);
                Date createdAt = rs.getDate(3);
                int numberQuestion = rs.getInt(4);
                int numberCorrect = rs.getInt(5);
                int duration = rs.getInt(6);

                PracticeListDTO p = new PracticeListDTO(id, subjectName, createdAt, numberQuestion, numberCorrect, duration);
                lst.add(p);
            }
        } catch (SQLException ex) {
        }
        return lst;
    }

    public List<String> getListDimensionName() {
        List<String> lst = new ArrayList<>();

        try {
            String query = "select DimensionName from Dimension";

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                lst.add(name);
            }
        } catch (SQLException ex) {
        }
        return lst;
    }

    public List<String> getListSubjectName() {
        List<String> lst = new ArrayList<>();

        try {
            String query = "Select name from subjects";

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                lst.add(name);
            }
        } catch (SQLException ex) {
        }
        return lst;
    }

    public List<String> getSubjectByDimension(String dimension) {
        List<String> lst = new ArrayList<>();

        try {
            String query = "Select name from subjects where dimensionId = \n"
                    + "(select DimensionId from Dimension where DimensionName = ?)";

            ps = connection.prepareStatement(query);
            ps.setString(1, dimension);
            rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                lst.add(name);
            }
        } catch (SQLException ex) {
        }
        return lst;
    }

    public List<String> getLessonBySubject(String subject) {
        List<String> lst = new ArrayList<>();

        try {
            String query = "select l.name from subject_has_lesson sl \n"
                    + "left join lessons l on sl.lesson_id = l.id\n"
                    + "where sl.subject_id =\n"
                    + "(select id from subjects where name = ?)";

            ps = connection.prepareStatement(query);
            ps.setString(1, subject);
            rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                lst.add(name);
            }
        } catch (SQLException ex) {
        }
        return lst;
    }

    public List<PracticeListDTO> getPaginationPracticeList(int userId, int page, int recordsPerPage) {
        List<PracticeListDTO> lst = new ArrayList<>();
        int start = (page - 1) * recordsPerPage + 1;
        int end = start + recordsPerPage - 1;

        try {
            String query = "WITH PagedResults AS (\n"
                    + "    SELECT p.id,s.name AS subject_name, p.CreatedAt, p.NumberQuestion, p.NumberCorrect, p.Duration,\n"
                    + "           ROW_NUMBER() OVER (ORDER BY p.CreatedAt) AS row_num\n"
                    + "    FROM Practices p\n"
                    + "    LEFT JOIN subjects s ON p.SubjectId = s.id \n"
                    + "    WHERE p.UserId = ?\n"
                    + ")\n"
                    + "SELECT * \n"
                    + "FROM PagedResults\n"
                    + "WHERE row_num BETWEEN ? AND ?\n"
                    + "ORDER BY row_num";

            ps = connection.prepareStatement(query);
            ps.setInt(1, userId); // Thay đổi UserId tương ứng
            ps.setInt(2, start);
            ps.setInt(3, end);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String subjectName = rs.getString(2);
                Date createdAt = rs.getDate(3);
                int numberQuestion = rs.getInt(4);
                int numberCorrect = rs.getInt(5);
                int duration = rs.getInt(6);

                PracticeListDTO p = new PracticeListDTO(id, subjectName, createdAt, numberQuestion, numberCorrect, duration);
                lst.add(p);
            }
        } catch (SQLException ex) {
        }
        return lst;
    }

    public int getTotalRecords(int userId) {
        String query = "SELECT COUNT(*) FROM Practices WHERE UserId = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                return id;
            }

        } catch (SQLException e) {
            // Log the exception (if a logging framework is available)
            e.printStackTrace(); // Replace with logger in real application
        }
        return 0;
    }

//    public List<Question> getQuestionsByPracticeId(int practiceId) throws SQLException {
//        String query = "SELECT q.* FROM questions q JOIN Practice_Question pq ON q.id = pq.QuestionId WHERE pq.PracticeId = ?";
//        ps = connection.prepareStatement(query);
//        ps.setInt(1, practiceId);
//        rs = ps.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            Question question = new Question(
//                    rs.getInt("id"),
//                    rs.getString("detail"),
//                    rs.getString("Suggestion"),
//                    rs.getString("Status"),
//                    rs.getString("Media")
//            );
//            questions.add(question);
//        }
//        return questions;
//    }
    public List<Question> getQuestionsByPracticeId(Integer practiceId, Integer stqId) throws SQLException {
        String query;

        if (practiceId == null || practiceId < 0) {
            query = "SELECT q.* FROM questions q "
                    + "JOIN Student_Quiz_Question sq ON q.id = sq.QuestionId "
                    + "JOIN Student_Take_Quiz stq ON sq.StudentQuizId = stq.id "
                    + "WHERE stq.id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, stqId);
        } else {
            query = "SELECT q.* FROM questions q "
                    + "JOIN Practice_Question pq ON q.id = pq.QuestionId "
                    + "WHERE pq.PracticeId = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, practiceId);
        }

        rs = ps.executeQuery();
        List<Question> questions = new ArrayList<>();

        while (rs.next()) {
            Question question = new Question(
                    rs.getInt("id"),
                    rs.getString("detail"),
                    rs.getString("Suggestion"),
                    rs.getString("Status"),
                    rs.getString("Media")
            );
            questions.add(question);
        }

        return questions;
    }

//    public List<QuestionDTO> getFilteredQuestions(int practiceId, String type) throws SQLException {
//        String query = "SELECT q.* FROM questions q "
//                + "JOIN Practice_Question pq ON q.id = pq.QuestionId "
//                + "WHERE pq.PracticeId = ?";
//
//        switch (type) {
//            case "answered":
//                query += " AND pq.YourAnswer IS NOT NULL";
//                break;
//            case "unanswered":
//                query += " AND pq.YourAnswer IS NULL";
//                break;
//            case "marked":
//                query += " AND pq.IsMarked = 1";
//                break;
//            case "all":
//            default:
//                break;
//        }
//
//        ps = connection.prepareStatement(query);
//        ps.setInt(1, practiceId);
//        rs = ps.executeQuery();
//
//        List<QuestionDTO> questions = new ArrayList<>();
//        int questionNumber = 1;
//        while (rs.next()) {
//            QuestionDTO question = new QuestionDTO(
//                    rs.getInt("id"),
//                    rs.getString("detail"),
//                    rs.getString("suggestion"),
//                    rs.getString("status"),
//                    rs.getString("media"),
//                    questionNumber++
//            );
//            questions.add(question);
//        }
//        return questions;
//    }
    public List<QuestionDTO> getFilteredQuestions(Integer practiceId, Integer stqId, String type) throws SQLException {
        String query;
        if (stqId != null && stqId > 0) {
            query = "SELECT q.* FROM questions q "
                    + "JOIN Student_Quiz_Question sq ON q.id = sq.QuestionId "
                    + "JOIN Student_Take_Quiz stq ON sq.StudentQuizId = stq.id "
                    + "WHERE stq.id = ?";

            switch (type) {
                case "answered":
                    query += " AND sq.YourAnswer IS NOT NULL";
                    break;
                case "unanswered":
                    query += " AND sq.YourAnswer IS NULL";
                    break;
                case "marked":
                    query += " AND sq.IsMarked = 1";
                    break;
                case "all":
                default:
                    break;
            }
        } else {
            query = "SELECT q.* FROM questions q "
                    + "JOIN Practice_Question pq ON q.id = pq.QuestionId "
                    + "WHERE pq.PracticeId = ?";

            switch (type) {
                case "answered":
                    query += " AND pq.YourAnswer IS NOT NULL";
                    break;
                case "unanswered":
                    query += " AND pq.YourAnswer IS NULL";
                    break;
                case "marked":
                    query += " AND pq.IsMarked = 1";
                    break;
                case "all":
                default:
                    break;
            }
        }

        ps = connection.prepareStatement(query);
        if (stqId != null && stqId > 0) {
            ps.setInt(1, stqId);
        } else {
            ps.setInt(1, practiceId);
        }

        rs = ps.executeQuery();

        List<QuestionDTO> questions = new ArrayList<>();
        int questionNumber = 1;
        while (rs.next()) {
            QuestionDTO question = new QuestionDTO(
                    rs.getInt("id"),
                    rs.getString("detail"),
                    rs.getString("suggestion"),
                    rs.getString("status"),
                    rs.getString("media"),
                    questionNumber++
            );
            questions.add(question);
        }
        return questions;
    }

//    public List<QuestionStatus> getAllQuestionsStatus(int practiceId) throws SQLException {
//        String query = "SELECT pq.QuestionId, q.detail, pq.YourAnswer "
//                + "FROM Practice_Question pq "
//                + "JOIN questions q ON pq.QuestionId = q.id "
//                + "WHERE pq.PracticeId = ?";
//        ps = connection.prepareStatement(query);
//        ps.setInt(1, practiceId);
//        rs = ps.executeQuery();
//
//        List<QuestionStatus> questionStatuses = new ArrayList<>();
//        while (rs.next()) {
//            int questionId = rs.getInt("QuestionId");
//            String detail = rs.getString("detail");
//            int yourAnswer = rs.getInt("YourAnswer");
//            QuestionStatus status = new QuestionStatus(questionId, detail, yourAnswer);
//            questionStatuses.add(status);
//        }
//        return questionStatuses;
//    }
    public List<QuestionStatus> getAllQuestionsStatus(Integer practiceId, Integer stqId) throws SQLException {
        String query;
        if (stqId != null && stqId > 0) {
            query = "SELECT q.id as QuestionId, q.detail, sq.YourAnswer "
                    + "FROM questions q "
                    + "JOIN Student_Quiz_Question sq ON q.id = sq.QuestionId "
                    + "JOIN Student_Take_Quiz stq ON sq.StudentQuizId = stq.id "
                    + "WHERE stq.id = ?";
        } else {
            query = "SELECT pq.QuestionId, q.detail, pq.YourAnswer "
                    + "FROM Practice_Question pq "
                    + "JOIN questions q ON pq.QuestionId = q.id "
                    + "WHERE pq.PracticeId = ?";
        }

        ps = connection.prepareStatement(query);
        if (stqId != null && stqId > 0) {
            ps.setInt(1, stqId);
        } else {
            ps.setInt(1, practiceId);
        }

        rs = ps.executeQuery();

        List<QuestionStatus> questionStatuses = new ArrayList<>();
        while (rs.next()) {
            int questionId = rs.getInt("QuestionId");
            String detail = rs.getString("detail");
            int yourAnswer = rs.getInt("YourAnswer");
            QuestionStatus status = new QuestionStatus(questionId, detail, yourAnswer);
            questionStatuses.add(status);
        }
        return questionStatuses;
    }

//    
//    public Practice getPracticeById(int practiceId) throws SQLException {
//        String query = "SELECT * FROM Practices WHERE id = ?";
//        ps = connection.prepareStatement(query);
//        ps.setInt(1, practiceId);
//        rs = ps.executeQuery();
//
//        if (rs.next()) {
//            Practice practice = new Practice(
//                    rs.getInt("id"),
//                    rs.getInt("UserId"),
//                    rs.getInt("SubjectId"),
//                    rs.getString("LessonName"),
//                    rs.getInt("NumberQuestion"),
//                    rs.getTimestamp("CreatedAt"),
//                    rs.getInt("NumberCorrect"),
//                    rs.getInt("Duration")
//            );
//            return practice;
//        }
//        return null;
//    }
//    
    public Practice getPracticeById(int practiceId, Integer quizId) throws SQLException {
        String query;
        if (quizId != null && quizId > 0) {
            query = "SELECT "
                    + "stq.id AS id, "
                    + "stq.UserId AS userId, "
                    + "q.SubjectId AS subjectId, "
                    + "l.name AS lessonName, "
                    + "q.NumberQuestion AS numberQuestion, "
                    + "stq.CreatedAt, "
                    + "stq.NumberCorrect AS numberCorrect, "
                    + "q.Duration AS duration "
                    + "FROM "
                    + "Student_Take_Quiz stq "
                    + "JOIN Quizs q ON stq.QuizId = q.Id "
                    + "JOIN lessons l ON q.LessonId = l.id "
                    + "JOIN Subjects s ON q.SubjectId = s.id "
                    + "WHERE stq.id = ?";
        } else {
            query = "SELECT * FROM Practices WHERE id = ?";
        }

        ps = connection.prepareStatement(query);
        if (quizId != null && quizId > 0) {
            ps.setInt(1, quizId);
        } else {
            ps.setInt(1, practiceId);
        }
        rs = ps.executeQuery();

        if (rs.next()) {
            Practice practice = new Practice(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getInt("subjectId"),
                    rs.getString("lessonName"),
                    rs.getInt("numberQuestion"),
                    rs.getTimestamp("createdAt"),
                    rs.getInt("numberCorrect"),
                    rs.getInt("duration")
            );
            return practice;
        }
        return null;
    }

    public String getSubjectNameById(int subjectId) throws SQLException {
        String query = "SELECT name FROM subjects WHERE id = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, subjectId);
        rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString("name");
        }
        return null;
    }

    public List<Answer> getAnswersByQuestionId(int questionId) throws SQLException {
        String query = "SELECT a.* FROM answers a JOIN question_has_answer qha ON a.id = qha.answer_id WHERE qha.question_id = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, questionId);
        rs = ps.executeQuery();

        List<Answer> answers = new ArrayList<>();
        while (rs.next()) {
            Answer answer = new Answer(
                    rs.getInt("id"),
                    rs.getString("answer_detail"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("update_at"),
                    rs.getInt("creator_id"),
                    rs.getBoolean("is_correct")
            );
            answers.add(answer);
        }
        return answers;
    }

    public void markQuestionForReview(int practiceId, int questionId) throws SQLException {
        String query = "UPDATE Practice_Question SET IsMarked = 1 WHERE PracticeId = ? AND QuestionId = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, practiceId);
        ps.setInt(2, questionId);
        ps.executeUpdate();

    }

    public List<Question> getQuestionsByPracticeId(int practiceId, int stqId) throws SQLException {
        String query;
        if (stqId > 0) {
            query = "SELECT q.* FROM questions q "
                    + "JOIN Student_Quiz_Question sq ON q.id = sq.QuestionId "
                    + "JOIN Student_Take_Quiz stq ON sq.StudentQuizId = stq.id "
                    + "WHERE stq.id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, stqId);
        } else {
            query = "SELECT q.* FROM questions q "
                    + "JOIN Practice_Question pq ON q.id = pq.QuestionId "
                    + "WHERE pq.PracticeId = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, practiceId);
        }

        rs = ps.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question question = new Question(
                    rs.getInt("id"),
                    rs.getString("detail"),
                    rs.getString("Suggestion"),
                    rs.getString("Status"),
                    rs.getString("Media")
            );
            questions.add(question);
        }
        return questions;
    }

    public void saveAnswers(int practiceId, int stqId, Map<Integer, Integer> answers) throws SQLException {
        String query;
        if (stqId > 0) {
            query = "UPDATE Student_Quiz_Question SET YourAnswer = ? WHERE StudentQuizId = ? AND QuestionId = ?";
            ps = connection.prepareStatement(query);
            for (Map.Entry<Integer, Integer> entry : answers.entrySet()) {
                ps.setInt(1, entry.getValue());
                ps.setInt(2, stqId);
                ps.setInt(3, entry.getKey());
                ps.addBatch();
            }
        } else {
            query = "UPDATE Practice_Question SET YourAnswer = ? WHERE PracticeId = ? AND QuestionId = ?";
            ps = connection.prepareStatement(query);
            for (Map.Entry<Integer, Integer> entry : answers.entrySet()) {
                ps.setInt(1, entry.getValue());
                ps.setInt(2, practiceId);
                ps.setInt(3, entry.getKey());
                ps.addBatch();
            }
        }
        ps.executeBatch();
    }

    public void saveUserAnswer(int practiceId, int stqId, int questionId, int answerId) throws SQLException {
        String query;
        if (stqId > 0) {
            query = "UPDATE Student_Quiz_Question SET YourAnswer = ? WHERE StudentQuizId = ? AND QuestionId = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, answerId);
            ps.setInt(2, stqId);
            ps.setInt(3, questionId);
        } else {
            query = "UPDATE Practice_Question SET YourAnswer = ? WHERE PracticeId = ? AND QuestionId = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, answerId);
            ps.setInt(2, practiceId);
            ps.setInt(3, questionId);
        }
        ps.executeUpdate();
    }

    // Kiểm tra câu trả lời đúng
    public boolean isCorrectAnswer(int questionId, int answerId) throws SQLException {
        String query = "SELECT is_correct FROM answers WHERE id = ?";
        ps = connection.prepareStatement(query);

        ps.setInt(1, answerId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getBoolean("is_correct");
        }

        return false;
    }

    public void updateNumberCorrect(int practiceId, int stqId, int numberCorrect) throws SQLException {
        String query;
        if (stqId > 0) {
            query = "UPDATE Student_Take_Quiz SET NumberCorrect = ? WHERE Id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, numberCorrect);
            ps.setInt(2, stqId);
        } else {
            query = "UPDATE Practices SET NumberCorrect = ? WHERE Id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, numberCorrect);
            ps.setInt(2, practiceId);
        }
        ps.executeUpdate();
    }

    public static void main(String[] args) {
        PracticeDAO p = new PracticeDAO();
        try {
            //        System.out.println(p.getPaginationPracticeList(27, 1, 5));
//        System.out.println(p.getTotalRecords(27));
//        System.out.println(p.getListSubjectName());
//        System.out.println(p.getListDimensionName());
            System.out.println(p.getPracticeById(-1, 1));
        } catch (SQLException ex) {
            Logger.getLogger(PracticeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
