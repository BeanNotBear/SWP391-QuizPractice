package dal;

import context.DBContext;
import dto.AnswerDTO;
import dto.QuizDTO;
import dto.QuizDoneDTO;
import dto.QuizLessonDTO;
import dto.QuizSubjectDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Question;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.StudentQuizQuestion;
import model.StudentTakeQuiz;

public class QuizDAO extends DBContext {

    private static QuizDAO instance;
    private static Object lockPad = new Object();

    private QuizDAO() {
    }

    /**
     * Provides a global point of access to the UserDAO instance. Implements
     * double-checked locking to ensure thread safety.
     *
     * @return the singleton instance of UserDAO
     */
    public static QuizDAO getInstance() {
        if (instance == null) {
            synchronized (lockPad) {
                if (instance == null) {
                    instance = new QuizDAO();
                }
            }
        }
        return instance;
    }

    public List<QuizDTO> searchQuizzes(String subjectName, String quizName, int page, int recordsPerPage) throws SQLException {
        List<QuizDTO> quizzes = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT q.id, q.name, s.name AS subject_name, q.level, q.numberQuestion, q.duration,q.type ");
        query.append("FROM Quizs q ");
        query.append("JOIN subjects s ON q.subjectId = s.id ");
        query.append("WHERE q.DeleteFlag = 1 ");

        if (subjectName != null && !subjectName.isEmpty()) {
            query.append("AND s.name LIKE ? ");
        }
        if (quizName != null && !quizName.isEmpty()) {
            query.append("AND q.name LIKE ? ");
        }
        query.append("ORDER BY q.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        ps = connection.prepareStatement(query.toString());
        int index = 1;

        if (subjectName != null && !subjectName.isEmpty()) {
            ps.setString(index++, "%" + subjectName + "%");
        }
        if (quizName != null && !quizName.isEmpty()) {
            ps.setString(index++, "%" + quizName + "%");
        }
        ps.setInt(index++, (page - 1) * recordsPerPage);
        ps.setInt(index, recordsPerPage);

        rs = ps.executeQuery();
        while (rs.next()) {
            quizzes.add(new QuizDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("subject_name"),
                    rs.getString("level"),
                    rs.getInt("numberQuestion"),
                    rs.getInt("duration"),
                    rs.getString("type")
            ));
        }
        return quizzes;
    }

    public List<QuizDTO> searchQuizzes(String subjectName, String quizName, String quizType, int page, int recordsPerPage) throws SQLException {
        List<QuizDTO> quizzes = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT q.id, q.name, s.name AS subject_name, q.level, q.numberQuestion, q.duration, q.type ");
        query.append("FROM Quizs q ");
        query.append("JOIN subjects s ON q.subjectId = s.id ");
        query.append("WHERE q.DeleteFlag = 1 ");

        if (subjectName != null && !subjectName.isEmpty()) {
            query.append("AND s.name LIKE ? ");
        }
        if (quizName != null && !quizName.isEmpty()) {
            query.append("AND q.name LIKE ? ");
        }
        if (quizType != null && !quizType.isEmpty()) {
            query.append("AND q.type = ? ");
        }
        query.append("ORDER BY q.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int index = 1;

            if (subjectName != null && !subjectName.isEmpty()) {
                ps.setString(index++, "%" + subjectName + "%");
            }
            if (quizName != null && !quizName.isEmpty()) {
                ps.setString(index++, "%" + quizName + "%");
            }
            if (quizType != null && !quizType.isEmpty()) {
                ps.setString(index++, quizType);
            }
            ps.setInt(index++, (page - 1) * recordsPerPage);
            ps.setInt(index, recordsPerPage);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    quizzes.add(new QuizDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("subject_name"),
                            rs.getString("level"),
                            rs.getInt("numberQuestion"),
                            rs.getInt("duration"),
                            rs.getString("type")
                    ));
                }
            }
        }

        return quizzes;
    }

    public int getTotalRecords(String subjectName, String quizName) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Quizs q ");
        query.append("JOIN subjects s ON q.subjectId = s.id ");
        query.append("WHERE q.DeleteFlag = 1 ");

        if (subjectName != null && !subjectName.isEmpty()) {
            query.append("AND s.name LIKE ? ");
        }
        if (quizName != null && !quizName.isEmpty()) {
            query.append("AND q.name LIKE ? ");
        }

        ps = connection.prepareStatement(query.toString());
        int index = 1;

        if (subjectName != null && !subjectName.isEmpty()) {
            ps.setString(index++, "%" + subjectName + "%");
        }
        if (quizName != null && !quizName.isEmpty()) {
            ps.setString(index++, "%" + quizName + "%");
        }

        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }

    public int getTotalRecords(String subjectName, String quizName, String quizType) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Quizs q JOIN subjects s ON q.subjectId = s.id WHERE q.DeleteFlag = 1 ");

        if (subjectName != null && !subjectName.isEmpty()) {
            query.append("AND s.name LIKE ? ");
        }
        if (quizName != null && !quizName.isEmpty()) {
            query.append("AND q.name LIKE ? ");
        }
        if (quizType != null && !quizType.isEmpty()) {
            query.append("AND q.type = ? ");
        }

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int index = 1;

            if (subjectName != null && !subjectName.isEmpty()) {
                ps.setString(index++, "%" + subjectName + "%");
            }
            if (quizName != null && !quizName.isEmpty()) {
                ps.setString(index++, "%" + quizName + "%");
            }
            if (quizType != null && !quizType.isEmpty()) {
                ps.setString(index++, quizType);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }

    public boolean deleteQuiz(int quizId) throws SQLException {
        String query = "UPDATE Quizs SET DeleteFlag = 0 WHERE id = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, quizId);
        return ps.executeUpdate() > 0;
    }

    public QuizDTO getQuizById(int quizId) throws SQLException {
        String query = "SELECT q.id, q.name, q.level, q.numberQuestion, q.duration, q.subjectId, s.name AS subject_name, q.type FROM Quizs q JOIN subjects s ON q.subjectId = s.id WHERE q.id = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, quizId);
        rs = ps.executeQuery();

        if (rs.next()) {
            return new QuizDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("subject_name"),
                    rs.getString("level"),
                    rs.getInt("numberQuestion"),
                    rs.getInt("duration"),
                    rs.getInt("subjectId"),
                    rs.getString("type")
            );
        }
        return null;
    }

    public boolean updateQuiz(QuizDTO quiz) throws SQLException {
        String query = "UPDATE Quizs SET name = ?, level = ?, numberQuestion = ?, duration = ?, subjectId = ?, type = ? WHERE id = ?";
        ps = connection.prepareStatement(query);
        ps.setString(1, quiz.getName());
        ps.setString(2, quiz.getLevel());
        ps.setInt(3, quiz.getNumberQuestion());
        ps.setInt(4, quiz.getDuration());
        ps.setInt(5, quiz.getSubjectId());
        ps.setString(6, quiz.getType());  // Changed the order of these two lines
        ps.setInt(7, quiz.getId());
        return ps.executeUpdate() > 0;
    }

    public static void main(String[] args) {
        try {
            QuizDAO q = new QuizDAO();

            System.out.println(q.getQuizById(5));
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addQuiz(QuizDTO quiz) throws SQLException {
        String query = "INSERT INTO Quizs (name, level, numberQuestion, duration, subjectId, DeleteFlag,type) VALUES (?, ?, ?, ?, ?, 1,?)";
        ps = connection.prepareStatement(query);
        ps.setString(1, quiz.getName());
        ps.setString(2, quiz.getLevel());
        ps.setInt(3, quiz.getNumberQuestion());
        ps.setInt(4, quiz.getDuration());
        ps.setInt(5, quiz.getSubjectId());
        ps.setString(7, quiz.getType());
        return ps.executeUpdate() > 0;
    }

    public List<QuizSubjectDTO> getSubjectsOfExpert(int creatorId) throws SQLException {
        List<QuizSubjectDTO> subjects = new ArrayList<>();
        String sql = "SELECT id, name FROM subjects WHERE creator_id = ?";

        ps = connection.prepareStatement(sql);
        ps.setInt(1, creatorId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            QuizSubjectDTO subject = new QuizSubjectDTO(id, name);
            subjects.add(subject);
        }

        return subjects;
    }

    public List<QuizLessonDTO> getLessonQuizOfSubject(int subjectId) throws SQLException {
        List<QuizLessonDTO> lessons = new ArrayList<>();
        String sql = "SELECT id, name FROM lessons WHERE id IN (SELECT lesson_id FROM subject_has_lesson WHERE subject_id = ?) AND Type = 'quiz'";

        ps = connection.prepareStatement(sql);
        ps.setInt(1, subjectId);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            QuizLessonDTO lesson = new QuizLessonDTO(id, name);
            lessons.add(lesson);
        }

        return lessons;
    }

    public int addNewQuiz(String name, String level, int numberQuestion, int duration, String type, int createdBy, int subjectId, int lessonId) throws SQLException {
        String sql = "INSERT INTO Quizs "
                + "VALUES (?, ?, ?, ?, ?, GETDATE(),?,null, null, ?, ?,1)";
        ps = connection.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setString(2, level);
        ps.setInt(3, numberQuestion);
        ps.setInt(4, duration);
        ps.setString(5, type);
        ps.setInt(6, createdBy);
        ps.setInt(7, subjectId);
        ps.setInt(8, lessonId);

        ps.executeUpdate();

        rs = ps.getGeneratedKeys();
        int generatedId = 0;
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }

        return generatedId;
    }

    public void updateLessonQuizId(int lessonId, int quizId) throws SQLException {
        String sql = "UPDATE lessons SET QuizId = ? WHERE id = ?";

        ps = connection.prepareStatement(sql);
        ps.setInt(1, quizId);
        ps.setInt(2, lessonId);

        ps.executeUpdate();
    }

    public int addNewQuestion(String detail, String suggestion) throws SQLException {
        String sql = "INSERT INTO questions VALUES (?, ?, 1, null)";

        ps = connection.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
        ps.setString(1, detail);
        ps.setString(2, suggestion);

        ps.executeUpdate();

        rs = ps.getGeneratedKeys();
        int generatedId = 0;
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }

        return generatedId;
    }

    public int addNewAnswer(String detail, int creatorId, int isCorrect) throws SQLException {
        String sql = "INSERT INTO answers VALUES (?, GETDATE(), null, ?, ?)";

        ps = connection.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
        ps.setString(1, detail);
        ps.setInt(2, creatorId);
        ps.setInt(3, isCorrect);

        ps.executeUpdate();

        rs = ps.getGeneratedKeys();
        int generatedId = 0;
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }

        return generatedId;
    }

    public void addAnswerToQuestion(int questionId, int answerId) throws SQLException {
        String sql = "INSERT INTO question_has_answer VALUES (?, ?)";

        ps = connection.prepareStatement(sql);
        ps.setInt(1, questionId);
        ps.setInt(2, answerId);

        ps.executeUpdate();
    }

    public void addQuestionToQuiz(int quizId, int questionId) throws SQLException {
        String sql = "INSERT INTO Quiz_Has_Question VALUES (?, ?)";

        ps = connection.prepareStatement(sql);
        ps.setInt(1, quizId);
        ps.setInt(2, questionId);

        ps.executeUpdate();
    }

    public List<Question> getSearchQuestionByLessonId(int lessonId) throws SQLException {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE id IN (SELECT QuestionId FROM Lesson_Has_Question WHERE LessonId = ?)";

        ps = connection.prepareStatement(sql);
        ps.setInt(1, lessonId);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String detail = rs.getString("detail");
            String suggestion = rs.getString("suggestion");
            String status = rs.getInt("status") + "";
            String media = rs.getString("media");

            Question question = new Question(id, detail, suggestion, status, media);
            questions.add(question);
        }

        return questions;
    }

    public List<Question> getSearchQuestionByQuizId(int quizId) throws SQLException {
        List<Question> questions = new ArrayList<>();
        String sql = "select * from questions where id in (select QuestionId from Quiz_Has_Question where QuizId = ?)";

        ps = connection.prepareStatement(sql);
        ps.setInt(1, quizId);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String detail = rs.getString("detail");
            String suggestion = rs.getString("suggestion");
            String status = rs.getInt("status") + "";
            String media = rs.getString("media");

            Question question = new Question(id, detail, suggestion, status, media);
            questions.add(question);
        }

        return questions;
    }

    public void deleteQuestionFromQuiz(int quizId, int questionId) throws SQLException {
        String sql = "DELETE FROM Quiz_Has_Question WHERE QuizId = ? AND QuestionId = ?";

        ps = connection.prepareStatement(sql);
        ps.setInt(1, quizId);
        ps.setInt(2, questionId);

        ps.executeUpdate();
    }

    public List<AnswerDTO> getAnswersByQuestionId(int questionId) throws SQLException {
        List<AnswerDTO> answers = new ArrayList<>();
        String sql = "SELECT id, answer_detail, is_correct FROM answers WHERE id in "
                + "(select answer_id from question_has_answer where question_id = ?)";
        ps = connection.prepareStatement(sql);
        ps.setInt(1, questionId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String detail = rs.getString("answer_detail");
            int isCorrect = rs.getInt("is_correct");
            AnswerDTO answer = new AnswerDTO(id, detail, isCorrect);
            answers.add(answer);
        }
        return answers;
    }

    public List<QuizDoneDTO> getDoneQuizzes(int userId, String subjectName, String quizName, int page, int recordsPerPage) throws SQLException {
        List<QuizDoneDTO> doneQuizzes = new ArrayList<>();
        int start = (page - 1) * recordsPerPage;
        String sql = "SELECT q.Id, q.Name AS QuizName, q.Level, q.NumberQuestion, q.Duration, "
                + "(stq.NumberCorrect * 100.0 / q.NumberQuestion) AS Score, s.Name AS SubjectName, stq.Id AS stqId "
                + "FROM Quizs q "
                + "JOIN Student_Take_Quiz stq ON q.Id = stq.QuizId "
                + "JOIN subjects s ON q.SubjectId = s.id "
                + "WHERE stq.UserId = ? "
                + "AND q.DeleteFlag != 0 "
                + (subjectName != null && !subjectName.isEmpty() ? "AND s.Name LIKE ? " : "")
                + (quizName != null && !quizName.isEmpty() ? "AND q.Name LIKE ? " : "")
                + "ORDER BY q.Id "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        ps = connection.prepareStatement(sql);
        int paramIndex = 1;
        ps.setInt(paramIndex++, userId);
        if (subjectName != null && !subjectName.isEmpty()) {
            ps.setString(paramIndex++, "%" + subjectName + "%");
        }
        if (quizName != null && !quizName.isEmpty()) {
            ps.setString(paramIndex++, "%" + quizName + "%");
        }
        ps.setInt(paramIndex++, start);
        ps.setInt(paramIndex++, recordsPerPage);

        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("Id");
            String quizNameRes = rs.getString("QuizName");
            String level = rs.getString("Level");
            int numberQuestion = rs.getInt("NumberQuestion");
            int duration = rs.getInt("Duration");
            double score = rs.getDouble("Score");
            String subjectNameRes = rs.getString("SubjectName");
            int stdId = rs.getInt("stqId");

            doneQuizzes.add(new QuizDoneDTO(id, quizNameRes, level, numberQuestion, duration, score, subjectNameRes, stdId));
        }

        return doneQuizzes;
    }

    public int getTotalDoneQuizzes(int userId, String subjectName, String quizName) throws SQLException {
        String sql = "SELECT COUNT(*) AS Total "
                + "FROM Quizs q "
                + "JOIN Student_Take_Quiz stq ON q.Id = stq.QuizId "
                + "JOIN subjects s ON q.SubjectId = s.id "
                + "WHERE stq.UserId = ? "
                + "AND q.DeleteFlag != 0 "
                + (subjectName != null && !subjectName.isEmpty() ? "AND s.Name LIKE ? " : "")
                + (quizName != null && !quizName.isEmpty() ? "AND q.Name LIKE ? " : "");

        ps = connection.prepareStatement(sql);
        int paramIndex = 1;
        ps.setInt(paramIndex++, userId);
        if (subjectName != null && !subjectName.isEmpty()) {
            ps.setString(paramIndex++, "%" + subjectName + "%");
        }
        if (quizName != null && !quizName.isEmpty()) {
            ps.setString(paramIndex++, "%" + quizName + "%");
        }

        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("Total");
        }

        return 0;
    }

    public List<QuizDTO> searchQuizzes(String subjectId, String subjectName, String quizName, String quizType, int page, int recordsPerPage) throws SQLException {
        List<QuizDTO> quizzes = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT q.id, q.name, s.name AS subject_name, q.level, q.numberQuestion, q.duration, q.type ");
        query.append("FROM Quizs q ");
        query.append("JOIN subjects s ON q.subjectId = s.id ");
        query.append("WHERE q.DeleteFlag = 1 ");

        if (subjectId != null && !subjectId.isEmpty()) {
            query.append("AND s.id = ? ");
        }
        if (subjectName != null && !subjectName.isEmpty()) {
            query.append("AND s.name LIKE ? ");
        }
        if (quizName != null && !quizName.isEmpty()) {
            query.append("AND q.name LIKE ? ");
        }
        if (quizType != null && !quizType.isEmpty()) {
            query.append("AND q.type = ? ");
        }
        query.append("ORDER BY q.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int index = 1;

            if (subjectId != null && !subjectId.isEmpty()) {
                ps.setString(index++, subjectId);
            }
            if (subjectName != null && !subjectName.isEmpty()) {
                ps.setString(index++, "%" + subjectName + "%");
            }
            if (quizName != null && !quizName.isEmpty()) {
                ps.setString(index++, "%" + quizName + "%");
            }
            if (quizType != null && !quizType.isEmpty()) {
                ps.setString(index++, quizType);
            }
            ps.setInt(index++, (page - 1) * recordsPerPage);
            ps.setInt(index, recordsPerPage);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    quizzes.add(new QuizDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("subject_name"),
                            rs.getString("level"),
                            rs.getInt("numberQuestion"),
                            rs.getInt("duration"),
                            rs.getString("type")
                    ));
                }
            }
        }

        return quizzes;
    }

    public int getTotalRecords(String subjectId, String subjectName, String quizName, String quizType) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Quizs q JOIN subjects s ON q.subjectId = s.id WHERE q.DeleteFlag = 1 ");

        if (subjectId != null && !subjectId.isEmpty()) {
            query.append("AND s.id = ? ");
        }
        if (subjectName != null && !subjectName.isEmpty()) {
            query.append("AND s.name LIKE ? ");
        }
        if (quizName != null && !quizName.isEmpty()) {
            query.append("AND q.name LIKE ? ");
        }
        if (quizType != null && !quizType.isEmpty()) {
            query.append("AND q.type = ? ");
        }

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int index = 1;

            if (subjectId != null && !subjectId.isEmpty()) {
                ps.setString(index++, subjectId);
            }
            if (subjectName != null && !subjectName.isEmpty()) {
                ps.setString(index++, "%" + subjectName + "%");
            }
            if (quizName != null && !quizName.isEmpty()) {
                ps.setString(index++, "%" + quizName + "%");
            }
            if (quizType != null && !quizType.isEmpty()) {
                ps.setString(index++, quizType);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }

//    public List<QuizDTO> searchQuizzes(String subjectId, String subjectName, String quizName, int page, int recordsPerPage) throws SQLException {
//        List<QuizDTO> quizzes = new ArrayList<>();
//        StringBuilder query = new StringBuilder("SELECT q.id, q.name, s.name AS subject_name, q.level, q.numberQuestion, q.duration, q.type ");
//        query.append("FROM Quizs q ");
//        query.append("JOIN subjects s ON q.subjectId = s.id ");
//        query.append("WHERE q.DeleteFlag = 1 ");
//
//        if (subjectId != null && !subjectId.isEmpty()) {
//            query.append("AND s.id = ? ");
//        }
//        if (subjectName != null && !subjectName.isEmpty()) {
//            query.append("AND s.name LIKE ? ");
//        }
//        if (quizName != null && !quizName.isEmpty()) {
//            query.append("AND q.name LIKE ? ");
//        }
//        query.append("ORDER BY q.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
//
//        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
//            int index = 1;
//
//            if (subjectId != null && !subjectId.isEmpty()) {
//                ps.setString(index++, subjectId);
//            }
//            if (subjectName != null && !subjectName.isEmpty()) {
//                ps.setString(index++, "%" + subjectName + "%");
//            }
//            if (quizName != null && !quizName.isEmpty()) {
//                ps.setString(index++, "%" + quizName + "%");
//            }
//            ps.setInt(index++, (page - 1) * recordsPerPage);
//            ps.setInt(index, recordsPerPage);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    quizzes.add(new QuizDTO(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getString("subject_name"),
//                            rs.getString("level"),
//                            rs.getInt("numberQuestion"),
//                            rs.getInt("duration"),
//                            rs.getString("type")
//                    ));
//                }
//            }
//        }
//
//        return quizzes;
//    }
//
//    public int getTotalRecords(String subjectId, String subjectName, String quizName) throws SQLException {
//        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Quizs q JOIN subjects s ON q.subjectId = s.id WHERE q.DeleteFlag = 1 ");
//
//        if (subjectId != null && !subjectId.isEmpty()) {
//            query.append("AND s.id = ? ");
//        }
//        if (subjectName != null && !subjectName.isEmpty()) {
//            query.append("AND s.name LIKE ? ");
//        }
//        if (quizName != null && !quizName.isEmpty()) {
//            query.append("AND q.name LIKE ? ");
//        }
//
//        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
//            int index = 1;
//
//            if (subjectId != null && !subjectId.isEmpty()) {
//                ps.setString(index++, subjectId);
//            }
//            if (subjectName != null && !subjectName.isEmpty()) {
//                ps.setString(index++, "%" + subjectName + "%");
//            }
//            if (quizName != null && !quizName.isEmpty()) {
//                ps.setString(index++, "%" + quizName + "%");
//            }
//
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getInt(1);
//                }
//            }
//        }
//
//        return 0;
//    }
    public StudentTakeQuiz getStudentTakeQuizById(int stqId) throws SQLException {
        String query = "SELECT * FROM Student_Take_Quiz WHERE Id = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, stqId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new StudentTakeQuiz(
                    rs.getInt("Id"),
                    rs.getInt("QuizId"),
                    rs.getInt("UserId"),
                    rs.getInt("NumberCorrect")
            );
        }
        return null;
    }

    public int getIdAddCurrent() {
        String sql = "SELECT IDENT_CURRENT('Student_Take_Quiz') AS LastInsertedID";

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log exception as needed
        }

        return 0; // Default return value if no count retrieved
    }

    public int createStudentTakeQuiz(StudentTakeQuiz quiz) throws SQLException {
        String query = "INSERT INTO Student_Take_Quiz (UserId, QuizId, Status, NumberCorrect,CreatedAt) VALUES (?, ?, ?, ?, GETDATE())";
        ps = connection.prepareStatement(query);
        ps.setInt(1, quiz.getUserId());
        ps.setInt(2, quiz.getQuizId());
        ps.setString(3, "pending");
        ps.setObject(4, 0);
        ps.executeUpdate();

        return getIdAddCurrent();
    }

    public List<Integer> getStudentQuizQuestionsByQuizId(int quizId) {
        String query = "SELECT QuestionId\n"
                + "FROM Quiz_Has_Question\n"
                + "WHERE QuizId = ?";
        List<Integer> questions = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, quizId);
            rs = ps.executeQuery();

            while (rs.next()) {
                questions.add(rs.getInt(1));
            }
        } catch (Exception e) {
        }
        return questions;
    }

    public void createStudentQuizQuestion(StudentQuizQuestion question) throws SQLException {
        String query = "INSERT INTO Student_Quiz_Question (StudentQuizId, QuestionId, YourAnswer, IsMarked) VALUES (?, ?, ?, ?)";
        ps = connection.prepareStatement(query);
        ps.setInt(1, question.getStudentQuizId());
        ps.setInt(2, question.getQuestionId());
        ps.setObject(3, question.getYourAnswer());
        ps.setObject(4, question.getIsMarked());
        ps.executeUpdate();
    }

    public String getQuizQuestionJson(int quizId) {
        String result = new String("");
        String query = "SELECT \n"
                + "    q.detail AS question,\n"
                + "    q.Suggestion AS correctAnswer,\n"
                + "    (\n"
                + "        SELECT a.answer_detail\n"
                + "        FROM question_has_answer qa\n"
                + "        INNER JOIN answers a ON qa.answer_id = a.id\n"
                + "        WHERE qa.question_id = q.id\n"
                + "        FOR JSON PATH\n"
                + "    ) AS options\n"
                + "FROM \n"
                + "    [SWP391_G6].[dbo].[Student_Quiz_Question] AS sqq\n"
                + "INNER JOIN \n"
                + "    questions AS q ON q.id = sqq.QuestionId\n"
                + "INNER JOIN\n"
                + "	Quiz_Has_Question AS qhq ON qhq.QuestionId = sqq.QuestionId\n"
                + "WHERE qhq.QuizId = ?\n"
                + "GROUP BY \n"
                + "    q.detail, q.Suggestion, q.id\n"
                + "FOR JSON AUTO;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, quizId);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
