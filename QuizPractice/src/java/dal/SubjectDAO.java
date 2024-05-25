package dal;

import context.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Subject;
import model.User;

// Data Access Object for User operations, extending DBContext to utilize database connections
public class SubjectDAO extends DBContext {

    // Singleton instance of UserDAO
    private static SubjectDAO instance;
    // Lock object for thread-safe singleton instantiation
    private static Object lockPad = new Object();

    // Private constructor to prevent instantiation
    private SubjectDAO() {
    }

    // Returns the singleton instance of UserDAO
    public static SubjectDAO getInstance() {
        if (instance == null) {
            synchronized (lockPad) {
                if (instance == null) {
                    instance = new SubjectDAO();
                }
            }
        }
        return instance;
    }

    public List<Subject> allSubjectsWithConditions(String searchParam) {
        List<Subject> subjects = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        UserDAO userDAO = UserDAO.getInstance();
        try {
            StringBuilder query = new StringBuilder();
            query.append("select *"
                    + "from subjects  \n"
                    + "where status = 1 ");
            // Create SQL query with sorting, LIMIT, and OFFSET clauses
            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append(" AND name LIKE ? ");
                list.add("%" + searchParam + "%");
            }

//            query.append(" order by id");
            // Create prepared statement
            PreparedStatement statement = connection.prepareStatement(query.toString());
            mapParams(statement, list);
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve subject details from each row
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setName(resultSet.getString("name"));
                User creator = userDAO.findUserById(resultSet.getInt("creator_id"));
                subject.setCreator(creator);
                subject.setCreate_at(resultSet.getDate("creater_at"));
                subject.setUpdate_at(resultSet.getDate("update_at"));
                subject.setStatus(resultSet.getInt("status"));
                subject.setImg(resultSet.getString("img"));
                // count lesson to add to list subject return by search
                subject.setNumberOfLesson(countLessonsBySubjectId(resultSet.getInt("id")));
                // Add the subject to the list
                subjects.add(subject);
            }
            // Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return subjects;
    }

    public int countLessonsBySubjectId(int subjectId) {
        // count lesson with subject id in database
        int lessonCount = 0;
        try {
            String query = "select count(*) as lesson_count from subject_has_lesson where subject_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, subjectId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                lessonCount = resultSet.getInt("lesson_count");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lessonCount;
    }

    public void mapParams(PreparedStatement ps, List<Object> args) throws SQLException {
        // Khởi tạo biến chỉ số bắt đầu từ 1 (do chỉ số của PreparedStatement bắt đầu từ 1)
        int i = 1;

        // Lặp qua từng phần tử trong danh sách tham số
        for (Object arg : args) {
            // Kiểm tra kiểu của tham số và ánh xạ tương ứng vào PreparedStatement
            if (arg instanceof Date) {
                // Nếu tham số là kiểu Date, ánh xạ thành Timestamp
                ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                // Nếu tham số là kiểu Integer, ánh xạ thành Int
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                // Nếu tham số là kiểu Long, ánh xạ thành Long
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                // Nếu tham số là kiểu Double, ánh xạ thành Double
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Float) {
                // Nếu tham số là kiểu Float, ánh xạ thành Float
                ps.setFloat(i++, (Float) arg);
            } else {
                // Nếu tham số là kiểu String hoặc bất kỳ kiểu nào khác, ánh xạ thành String
                ps.setString(i++, (String) arg);
            }
        }
    }

    // phân trang để hiển thị page
    public List<Subject> Paging(List<Subject> subject, int page, int pageSize) {
        // Tính toán chỉ số bắt đầu của danh sách con cho trang hiện tại
        int fromIndex = (page - 1) * pageSize;

        // Tính toán chỉ số kết thúc của danh sách con cho trang hiện tại
        // Chỉ số kết thúc không được vượt quá kích thước của danh sách
        int toIndex = Math.min(fromIndex + pageSize, subject.size());

        // Đảm bảo rằng fromIndex không lớn hơn toIndex
        // Điều này xử lý trường hợp khi fromIndex được tính toán vượt quá kích thước của danh sách
        if (fromIndex > toIndex) {
            // Điều chỉnh fromIndex bằng với toIndex để tránh ngoại lệ IndexOutOfBoundsException
            fromIndex = toIndex;
        }

        // Trả về danh sách con cho trang cụ thể
        // Điều này sẽ trả về danh sách trống nếu fromIndex bằng với toIndex
        return subject.subList(fromIndex, toIndex);
    }

    public Subject findSubjectById(int id) {
        String query = "SELECT [id]\n"
                + ",[name]\n"
                + ",[creator_id]\n"
                + ",[creater_at]\n"
                + ",[update_at]\n"
                + ",[status]\n"
                + ",[img]\n"
                + "FROM [SWP391_G6].[dbo].[subjects]\n"
                + "WHERE [id] = ?";
        Subject subject = null;
        UserDAO userDAO = UserDAO.getInstance();
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {                
                subject = new Subject();
                subject.setId(rs.getInt("id"));
                subject.setName(rs.getString("name"));
                User creator = userDAO.findUserById(rs.getInt("creator_id"));
                subject.setCreator(creator);
                subject.setCreate_at(rs.getDate("creater_at"));
                subject.setUpdate_at(rs.getDate("update_at"));
                subject.setStatus(rs.getInt("status"));
                subject.setImg(rs.getString("img"));
                // count lesson to add to list subject return by search
                subject.setNumberOfLesson(countLessonsBySubjectId(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return subject;
    }

    public static void main(String[] args) {
        // view all subjects in database with status = 1
        SubjectDAO c = new SubjectDAO();
        List<Subject> l = c.allSubjectsWithConditions("");
        System.out.println(l.size());
        for (Subject cs : l) {
            System.out.println(cs);
        }
    }
}
