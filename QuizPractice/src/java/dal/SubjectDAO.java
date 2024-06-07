package dal;

import context.DBContext;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.PricePackage;
import model.Subject;
import model.Tag;
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

    public List<Subject> allSubjectsWithConditions(String searchParam, String sort) {
        List<Subject> subjects = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        UserDAO userDAO = UserDAO.getInstance();
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT s.[id]\n"
                    + "      ,[name]\n"
                    + "      ,[creator_id]\n"
                    + "      ,[creater_at]\n"
                    + "      ,[update_at]\n"
                    + "      ,[status]\n"
                    + "      ,[img]\n"
                    + "      ,[description]\n"
                    + "	  , u.full_name\n"
                    + "  FROM [SWP391_G6].[dbo].[subjects] as s\n"
                    + "  INNER JOIN users as u on s.creator_id = u.id\n"
                    + "  WHERE s.status = 1");
            // Create SQL query with sorting, LIMIT, and OFFSET clauses
            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append(" AND name LIKE ?");
                query.append(" OR description LIKE ?");
                query.append(" OR u.full_name LIKE ?");
                list.add("%" + searchParam + "%");
                list.add("%" + searchParam + "%");
                list.add("%" + searchParam + "%");
            }

            if (sort != null && !sort.trim().isEmpty()) {
                query.append(" ORDER BY [update_at] " + sort);
            }

//            query.append(" order by id");
            // Create prepared statement
            ps = connection.prepareStatement(query.toString());
            mapParams(ps, list);
            // Execute the query
            rs = ps.executeQuery();
            // Iterate over the result set
            while (rs.next()) {
                // Retrieve subject details from each row
                Subject subject = new Subject();
                int id = rs.getInt("id");
                subject.setId(id);
                subject.setName(rs.getString("name"));
                User creator = userDAO.findUserById(rs.getInt("creator_id"));
                subject.setCreator(creator);
                subject.setCreate_at(rs.getDate("creater_at"));
                subject.setUpdate_at(rs.getDate("update_at"));
                subject.setStatus(rs.getInt("status"));
                subject.setImg(rs.getString("img"));
                subject.setDescription(rs.getString("description"));
                // count lesson to add to list subject return by search
                subject.setNumberOfLesson(countLessonsBySubjectId(id));
                subject.setPricePackages(findPricePackageBySubjectId(id));
                subject.setTags(findTagsBySubjectId(id));
                System.out.println("ok");
                // Add the subject to the list
                subjects.add(subject);
            }
            // Close the resources
            rs.close();
            ps.close();
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
                + ",[description]"
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
                subject.setDescription(rs.getString("description"));
                // count lesson to add to list subject return by search
                subject.setNumberOfLesson(countLessonsBySubjectId(rs.getInt("id")));
                subject.setPricePackages(findPricePackageBySubjectId(id));
                subject.setTags(findTagsBySubjectId(id));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return subject;
    }

    public List<PricePackage> findPricePackageBySubjectId(int id) {
        List<PricePackage> pricePackages = new ArrayList<>();
        String query = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[duration]\n"
                + "      ,[sale_price]\n"
                + "      ,[price]\n"
                + "FROM [SWP391_G6].[dbo].[package_price]\n"
                + "INNER JOIN [dbo].[subject_has_price_package] AS SP ON SP.price_package_id = id\n"
                + "WHERE SP.subject_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PricePackage pricePackage = new PricePackage();
                pricePackage.setId(resultSet.getInt(1));
                pricePackage.setName(resultSet.getString(2));
                pricePackage.setDuration(resultSet.getInt(3));
                pricePackage.setSalePrice(resultSet.getDouble(4));
                pricePackage.setPrice(resultSet.getDouble(5));
                pricePackages.add(pricePackage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pricePackages;
    }

    public List<Tag> findTagsBySubjectId(int subjectId) {
        List<Tag> tags = new ArrayList<>();
        String query = "SELECT [id]\n"
                + "      ,[tag]\n"
                + "  FROM [SWP391_G6].[dbo].[subject_has_tag]\n"
                + "  INNER JOIN tags as t on t.id = tag_id\n"
                + "  WHERE subject_id = ?";
        PreparedStatement pst;
        ResultSet rst;
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, subjectId);
            rst = pst.executeQuery();
            while (rst.next()) {
                Tag tag = new Tag();
                tag.setId(rst.getInt(1));
                tag.setTag(rst.getString(2));
                tags.add(tag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }

    public List<Subject> listTop8Subject() {
        List<Subject> listSubject = new ArrayList<>();
        try {
            String query = "SELECT TOP 8 * FROM subjects order by creater_at desc";

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int creator_id = rs.getInt(3);
                Date creater_at = rs.getDate(4);
                Date update_at = rs.getDate(5);
                int status = rs.getInt(6);
                String img = rs.getString(7);
                String description = rs.getString(8);

                Subject subject = new Subject();
                subject.setId(id);
                subject.setName(name);
                subject.setCreator_id(creator_id);
                subject.setCreate_at(creater_at);
                subject.setUpdate_at(update_at);
                subject.setStatus(status);
                subject.setImg(img);
                subject.setDescription(description);
                subject.setPricePackages(findPricePackageBySubjectId(id));
                subject.setTags(findTagsBySubjectId(id));
                listSubject.add(subject);
            }
        } catch (SQLException ex) {

        }
        return listSubject;
    }

    public List<Subject> find3FeatureSubject() {
        List<Subject> listSubject = new ArrayList<>();
        try {
            String query = "SELECT TOP 3 * FROM subjects order by creater_at desc";

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int creator_id = rs.getInt(3);
                Date creater_at = rs.getDate(4);
                Date update_at = rs.getDate(5);
                int status = rs.getInt(6);
                String img = rs.getString(7);
                String description = rs.getString(8);

                Subject subject = new Subject();
                subject.setId(id);
                subject.setName(name);
                subject.setCreator_id(creator_id);
                subject.setCreate_at(creater_at);
                subject.setUpdate_at(update_at);
                subject.setStatus(status);
                subject.setImg(img);
                subject.setDescription(description);
                subject.setPricePackages(findPricePackageBySubjectId(id));
                subject.setTags(findTagsBySubjectId(id));
                listSubject.add(subject);
            }
        } catch (SQLException ex) {
        }
        return listSubject;
    }

    public static void main(String[] args) {
        // view all subjects in database with status = 1
        SubjectDAO c = new SubjectDAO();

        for (Subject s : c.allSubjectsWithConditions("", "asc")) {
            System.out.println(s.toString());
        }

    }
}
