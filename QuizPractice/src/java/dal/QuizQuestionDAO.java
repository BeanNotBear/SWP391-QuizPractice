package dal;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import context.DBContext;
import dto.QuestionListDTO;

public class QuizQuestionDAO extends DBContext {

    private static QuizQuestionDAO instance;
    private static Object lockPad = new Object();

    private QuizQuestionDAO() {
    }

    public static QuizQuestionDAO getInstance() {
        if (instance == null) {
            synchronized (lockPad) {
                if (instance == null) {
                    instance = new QuizQuestionDAO();
                }
            }
        }
        return instance;
    }

    public List<QuestionListDTO> searchQuestions(String subjectId, String lessonId, String status, String detail, int page, int recordsPerPage) throws SQLException {
        List<QuestionListDTO> questions = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT q.id, q.detail, q.suggestion, s.name AS subject_name, l.name AS lesson_name, q.status ");
        query.append("FROM questions q ");
        query.append("LEFT JOIN Lesson_Has_Question lhq ON q.id = lhq.QuestionId ");
        query.append("LEFT JOIN lessons l ON lhq.LessonId = l.id ");
        query.append("LEFT JOIN subject_has_lesson shl ON l.id = shl.lesson_id ");
        query.append("LEFT JOIN subjects s ON shl.subject_id = s.id ");
        query.append("WHERE 1=1 ");

        if (subjectId != null && !subjectId.isEmpty()) {
            query.append("AND s.id = ? ");
        }
        if (lessonId != null && !lessonId.isEmpty()) {
            query.append("AND l.id = ? ");
        }
        if (status != null && !status.isEmpty()) {
            query.append("AND q.status = ? ");
        }
        if (detail != null && !detail.isEmpty()) {
            query.append("AND q.detail LIKE ? ");
        }
         query.append("ORDER BY q.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        PreparedStatement ps = connection.prepareStatement(query.toString());
        int index = 1;

        if (subjectId != null && !subjectId.isEmpty()) {
            ps.setString(index++, subjectId);
        }
        if (lessonId != null && !lessonId.isEmpty()) {
            ps.setString(index++, lessonId);
        }
        if (status != null && !status.isEmpty()) {
            ps.setString(index++, status);
        }
        if (detail != null && !detail.isEmpty()) {
            ps.setString(index++, "%" + detail + "%");
        }
         ps.setInt(index++, (page - 1) * recordsPerPage);
        ps.setInt(index, recordsPerPage);
         

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            questions.add(new QuestionListDTO(
                rs.getInt("id"),
                rs.getString("detail"),
                rs.getString("suggestion"),
                rs.getString("subject_name"),
                rs.getString("lesson_name"),
                rs.getInt("status")
            ));
        }
        return questions;
    }

    public int getTotalRecords(String subjectId, String lessonId, String status, String detail) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM questions q ");
        query.append("LEFT JOIN Lesson_Has_Question lhq ON q.id = lhq.QuestionId ");
        query.append("LEFT JOIN lessons l ON lhq.LessonId = l.id ");
        query.append("LEFT JOIN subject_has_lesson shl ON l.id = shl.lesson_id ");
        query.append("LEFT JOIN subjects s ON shl.subject_id = s.id ");
        query.append("WHERE 1=1 ");

        if (subjectId != null && !subjectId.isEmpty()) {
            query.append("AND s.id = ? ");
        }
        if (lessonId != null && !lessonId.isEmpty()) {
            query.append("AND l.id = ? ");
        }
        if (status != null && !status.isEmpty()) {
            query.append("AND q.status = ? ");
        }
        if (detail != null && !detail.isEmpty()) {
            query.append("AND q.detail LIKE ? ");
        }

        PreparedStatement ps = connection.prepareStatement(query.toString());
        int index = 1;

        if (subjectId != null && !subjectId.isEmpty()) {
            ps.setString(index++, subjectId);
        }
        if (lessonId != null && !lessonId.isEmpty()) {
            ps.setString(index++, lessonId);
        }
        if (status != null && !status.isEmpty()) {
            ps.setString(index++, status);
        }
        if (detail != null && !detail.isEmpty()) {
            ps.setString(index++, "%" + detail + "%");
        }

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }

    public boolean updateQuestionStatus(int questionId, int newStatus) throws SQLException {
        String query = "UPDATE questions SET status = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, newStatus);
        ps.setInt(2, questionId);
        return ps.executeUpdate() > 0;
    }

    public QuestionListDTO getQuestionById(int questionId) throws SQLException {
        String query = "SELECT * FROM questions WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, questionId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new QuestionListDTO(
                rs.getInt("id"),
                rs.getString("detail"),
                rs.getString("suggestion"),
                rs.getInt("status"),
                rs.getString("media")
            );
        }
        return null;
    }

    public boolean updateQuestion(QuestionListDTO question) throws SQLException {
        String query = "UPDATE questions SET detail = ?, suggestion = ?, status = ?, media = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, question.getDetail());
        ps.setString(2, question.getSuggestion());
        ps.setInt(3, question.getStatus());
        ps.setString(4, question.getMedia());
        ps.setInt(5, question.getId());
        return ps.executeUpdate() > 0;
    }
}