package dal;

import context.DBContext;
import java.sql.SQLException;
import model.QuestionImport;

public class QuestionDAO extends DBContext {

    private static QuestionDAO instance;
    private static final Object lockPad = new Object();

    private QuestionDAO() {
    }

    public static QuestionDAO getInstance() {
        if (instance == null) {
            synchronized (lockPad) {
                if (instance == null) {
                    instance = new QuestionDAO();
                }
            }
        }
        return instance;
    }

    public boolean toggleQuestionStatus(int questionId, int status) throws SQLException {
        String query = "UPDATE questions SET Status = ? WHERE id = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, status);
        ps.setInt(2, questionId);
        return ps.executeUpdate() > 0;
    }

    public int getIdAddCurrent() {
        String sql = "SELECT @@IDENTITY AS LastInsertedId;";

        try {
            ps = connection.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log exception as needed
        }

        return 0; // Default return value if no count retrieved
    }

    public boolean addQuestion(QuestionImport question, String subjectId, String lessonId) throws SQLException {
        String query = "INSERT INTO questions (detail, suggestion, Status, Media) VALUES (?, ?, ?, ?)";
        ps = connection.prepareStatement(query);
        ps.setString(1, question.getDetail());
        ps.setString(2, question.getSuggestion());
        ps.setInt(3, question.getStatus());
        ps.setString(4, question.getMedia());
        ps.executeUpdate();

        int id = getIdAddCurrent();

        if (lessonId != null && !lessonId.equalsIgnoreCase("")) {
            String linkQuery = "INSERT INTO Lesson_Has_Question (LessonId, QuestionId) VALUES (?, ?)";
            ps = connection.prepareStatement(linkQuery);
            ps.setInt(1, Integer.parseInt(lessonId));
            ps.setInt(2, id);
            ps.executeUpdate();
        }

        return true;
    }

}