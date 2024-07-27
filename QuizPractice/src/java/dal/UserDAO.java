package dal;

import context.DBContext;
import dto.ExpertDTO;
import dto.LearnerDTO;
import dto.OwnerDTO;
import dto.UserManagement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import util.security.Security;
import java.sql.PreparedStatement;

// Data Access Object for User operations, extending DBContext to utilize database connections
public class UserDAO extends DBContext {

    // Singleton instance of UserDAO
    private static UserDAO instance;
    // Lock object for thread-safe singleton instantiation
    private static Object lockPad = new Object();
    private Logger logger = Logger.getLogger(UserDAO.class.getName());

    // Private constructor to prevent instantiation
    private UserDAO() {
    }

    // Returns the singleton instance of UserDAO
    public static UserDAO getInstance() {
        if (instance == null) {
            synchronized (lockPad) {
                if (instance == null) {
                    instance = new UserDAO();
                }
            }
        }
        return instance;
    }

    public boolean checkExistByEmail(String email) {
        boolean isDuplicate = true;
        String sql = "Select 1 From users where email =?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                isDuplicate = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDuplicate;
    }

    public User findUserById(int id) {
        String query = "SELECT * "
                + "FROM users "
                + "WHERE id = ?";
        User user = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setFullName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setGender(rs.getBoolean(5));
                user.setProfileImg(rs.getString(6));
                user.setPassword(rs.getString(7));
                user.setCreatedAt(rs.getDate(8));
                user.setUpdatedAt(rs.getDate(9));
                user.setRoleId(rs.getInt(10));
                user.setStatusID(rs.getInt(11));
                user.setToken(rs.getString(12));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public boolean resetPassword(String password, String email) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, Security.encryptToSHA512(password));
            ps.setString(2, email);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public int updatePassword(String password, String email) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        int rowAffected = 0; // Variable to store the number of rows affected
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, Security.encryptToSHA512(password));
            ps.setString(2, email);
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowAffected;
    }

    public int insert(User user) {
        // SQL query with placeholders for parameterized input
        String query = "INSERT INTO [dbo].[users]\n"
                + "           ([full_name]\n"
                + "           ,[email]\n"
                + "           ,[phone_number]\n"
                + "           ,[gender]\n"
                + "           ,[profile_img]\n"
                + "           ,[password]\n"
                + "           ,[created_at]\n"
                + "           ,[updated_at]\n"
                + "           ,[role_id]role\n"
                + "           ,[status_id]\n"
                + "           ,[token])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int rowAffected = 0; // Variable to store the number of rows affected

        try {
            // Prepare the SQL query for execution
            ps = connection.prepareStatement(query);

            // Set parameters for the PreparedStatement
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhoneNumber());
            ps.setBoolean(4, user.isGender());

            // Set the profile image path based on gender
            String imgPath = user.isGender() ? "images/pic-1.jpg" : "images/pic-5.jpg";
            ps.setString(5, imgPath);

            ps.setString(6, user.getPassword()); // Ensure password is hashed
            ps.setDate(7, user.getCreatedAt());
            ps.setDate(8, user.getUpdatedAt());
            ps.setInt(9, user.getRoleId());
            ps.setInt(10, user.getStatusID());
            ps.setString(11, user.getToken());

            // Execute the query and get the number of rows affected
            rowAffected = ps.executeUpdate();
        } catch (SQLException e) {
            // Log the exception (if a logging framework is available)
            e.printStackTrace(); // Replace with logger in real application
        }
        return rowAffected;
    }

    // Checks if a user already exists by username
    @SuppressWarnings("all")
    public boolean checkUserExistedByUsername(String username) {
        String query = "SELECT 1\n"
                + "FROM users\n"
                + "WHERE username = ?";
        boolean isDuplicate = true;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                isDuplicate = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDuplicate;
    }

    // Finds a user by email and password
    @SuppressWarnings("all")
    public User findUserByEmailAndPassword(String email, String password) {
        String query = "SELECT *\n"
                + "FROM users\n"
                + "WHERE email = ? AND [password] = ?";
        User user = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setFullName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setGender(rs.getBoolean(5));
                user.setProfileImg(rs.getString(6));
                user.setPassword(rs.getString(7));
                user.setCreatedAt(rs.getDate(8));
                user.setUpdatedAt(rs.getDate(9));
                user.setRoleId(rs.getInt(10));
                user.setStatusID(rs.getInt(11));
                user.setToken(rs.getString(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Finds a user by email
    public User findUserByEmail(String email) {
        String query = "SELECT *\n"
                + "FROM users\n"
                + "WHERE email = ?";
        User user = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setFullName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setGender(rs.getBoolean(5));
                user.setProfileImg(rs.getString(6));
                user.setPassword(rs.getString(7));
                user.setCreatedAt(rs.getDate(8));
                user.setUpdatedAt(rs.getDate(9));
                user.setRoleId(rs.getInt(10));
                user.setStatusID(rs.getInt(11));
                user.setToken(rs.getString(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Updates the status of a user by token
    public int UpdateStatusByToken(String token) {
        String query = "UPDATE users\n"
                + "SET status_id = 2\n"
                + "WHERE token = ?";
        int rowAffected = 0;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rowAffected = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowAffected;
    }

    // Finds a user by token
    public User findUserByToken(String token) {
        String query = "SELECT * \n"
                + "FROM users\n"
                + "WHERE token = ?";

        User user = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setFullName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setGender(rs.getBoolean(5));
                user.setProfileImg(rs.getString(6));
                user.setPassword(rs.getString(7));
                user.setCreatedAt(rs.getDate(8));
                user.setUpdatedAt(rs.getDate(9));
                user.setRoleId(rs.getInt(10));
                user.setStatusID(rs.getInt(11));
                user.setToken(rs.getString(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Updates the token of a user by email
    public int UpdateTokenByEmail(String token, String email) {
        String query = "UPDATE users\n"
                + "SET token = ?\n"
                + "WHERE email = ?;";
        int rowAffected = 0;
        System.out.println(token);
        System.out.println(email);
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            ps.setString(2, email);
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowAffected;
    }

    public int UpdateUserProfile(String fullName, String phoneNumber, String gender, String email) {
        String query = "UPDATE [dbo].[users]\n"
                + "   SET [full_name] = ?\n"
                + "      ,[phone_number] = ?\n"
                + "      ,[gender] = ?\n"
                + " WHERE [email] = ?";
        int gen = 0;
        if (gender.equals("true")) {
            gen = 1;
        }
        int rowAffected = 0;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, fullName);
            ps.setString(2, phoneNumber);
            ps.setInt(3, gen);
            ps.setString(4, email);
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowAffected;
    }

    public List<ExpertDTO> findAllExpert() {
        List<ExpertDTO> experts = new ArrayList<>();
        String query = "SELECT * \n"
                + "FROM [dbo].[users]\n"
                + "WHERE role_id = 3";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                experts.add(new ExpertDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
            }
        } catch (Exception e) {
        }

        return experts;
    }

    public int getLastUserId() {
        String query = "SELECT @@IDENTITY AS LastInsertedId";
        try {
            ps = connection.prepareStatement(query);
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

    public int getNumberOfStudentsBySubjectId(int subjectId) {
        String query = "SELECT COUNT(UserId)\n"
                + "FROM [SWP391_G6].[dbo].[Subject_Register]\n"
                + "WHERE SubjectId = ?";
        int numberOfStudents = 0;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectId);
            rs = ps.executeQuery();
            if (rs.next()) {
                numberOfStudents = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfStudents;
    }

    public OwnerDTO getOnwerBySubjectId(int subjectId) {
        String query = "SELECT u.[id]\n"
                + "      ,u.[full_name]\n"
                + "      ,u.[email]\n"
                + "      ,u.[phone_number]\n"
                + "      ,u.[gender]\n"
                + "      ,u.[profile_img]\n"
                + "      ,u.[created_at]\n"
                + "FROM [SWP391_G6].[dbo].[users] AS u\n"
                + "INNER JOIN subjects AS s ON s.creator_id = u.id\n"
                + "WHERE s.id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new OwnerDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(6),
                        rs.getInt(5),
                        rs.getDate(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OwnerDTO> getExpertPagination(int page, int recordsPerPage, int currentOwner, String search) {
        String query = "WITH PageResult AS (\n"
                + "	SELECT [id]\n"
                + "	  ,[full_name]\n"
                + "      ,[email]\n"
                + "      ,[phone_number]\n"
                + "      ,[gender]\n"
                + "      ,[profile_img]\n"
                + "      ,[created_at]\n"
                + "	  ,ROW_NUMBER() OVER (ORDER BY [id]) AS row_num\n"
                + "  FROM [SWP391_G6].[dbo].[users]\n"
                + "  WHERE [id] <> ? AND ([full_name] LIKE ? OR [email] LIKE ? OR [phone_number] LIKE ?) AND [role_id] = 3"
                + ")\n"
                + "SELECT *\n"
                + "FROM PageResult\n"
                + "WHERE row_num BETWEEN ? AND ?;";
        List<OwnerDTO> experts = new ArrayList<>();
        int start = (page - 1) * recordsPerPage + 1;
        int end = start + recordsPerPage - 1;
        String searchCondition = "%" + search + "%";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, currentOwner);
            ps.setString(2, searchCondition);
            ps.setString(3, searchCondition);
            ps.setString(4, searchCondition);
            ps.setInt(5, start);
            ps.setInt(6, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                experts.add(new OwnerDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(6),
                        rs.getInt(5),
                        rs.getDate(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return experts;
    }

    public List<LearnerDTO> getLearnersBySubjectId(int subjectId, int page, int recordsPerPage, String search) {
        String query = "With PageResult AS (\n"
                + "	SELECT u.id, \n"
                + "	u.full_name, \n"
                + "	u.email, \n"
                + "	u.gender, \n"
                + "	u.phone_number, \n"
                + "	u.profile_img,\n"
                + "	ROW_NUMBER() OVER (ORDER BY u.id) AS row_num\n"
                + "	FROM users AS u\n"
                + "	INNER JOIN Subject_Register AS sj ON u.id = sj.UserId\n"
                + "	WHERE sj.SubjectId = ? AND (u.id LIKE ? OR u.full_name LIKE ? OR u.email LIKE ? OR u.phone_number LIKE ?)"
                + ")\n"
                + "SELECT *\n"
                + "FROM PageResult\n"
                + "WHERE row_num BETWEEN ? AND ?";
        List<LearnerDTO> learners = new ArrayList<>();
        int start = (page - 1) * recordsPerPage + 1;
        int end = start + recordsPerPage - 1;
        String searchCondition = "%" + search + "%";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectId);
            ps.setString(2, searchCondition);
            ps.setString(3, searchCondition);
            ps.setString(4, searchCondition);
            ps.setString(5, searchCondition);
            ps.setInt(6, start);
            ps.setInt(7, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                learners.add(new LearnerDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return learners;
    }

    public void ChangeStatusOfRegistartion(int userId, int subjectId, int duration) {
        String query = "UPDATE [dbo].[Subject_Register]\n"
                + "SET [CreatedAt] = GETDATE(),\n"
                + "    [UpdatedAt] = DATEADD(month, ?, GETDATE()),\n"
                + "    [Status] = 'done'\n"
                + "WHERE [SubjectId] = ? AND [UserId] = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, duration);    // Set duration in months
            ps.setInt(2, subjectId);   // Set subjectId
            ps.setInt(3, userId);      // Set userId

            int result = ps.executeUpdate();
            System.out.println("Rows affected: " + result);  // Optional: Print number of rows affected
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddStudentHasLesson(int userId, int subjectId) {
        List<Integer> listOfLessonId = getListOfLessonIdBySubjectId(subjectId);
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO [dbo].[student_has_lesson]\n")
                .append("           ([user_id]\n")
                .append("           ,[lesson_id])\n")
                .append("     VALUES\n")
                .append("           (?, ?)");

        String query = queryBuilder.toString();

        try {
            ps = connection.prepareStatement(query);
            for (Integer lessonId : listOfLessonId) {
                ps.setInt(1, userId);
                ps.setInt(2, lessonId);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getListOfLessonIdBySubjectId(int subjectId) {
        List<Integer> listOfLessonId = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT [lesson_id]\n")
                .append("FROM [SWP391_G6].[dbo].[subject_has_lesson]\n")
                .append("WHERE [subject_id] = ?;");

        String query = queryBuilder.toString();

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, subjectId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listOfLessonId.add(rs.getInt(1));
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfLessonId;
    }

    public List<UserManagement> getUsers(int page, int recordsPerPage, String search) {
        List<UserManagement> users = new ArrayList<>();
        String query = "WITH PageResult AS (\n"
                + "	SELECT u.[id],\n"
                + "	       u.[full_name],\n"
                + "	       u.[email],\n"
                + "	       u.[phone_number],\n"
                + "	       u.[gender],\n"
                + "	       u.[profile_img],\n"
                + "	       s.[name] AS status_name,\n"
                + "	       r.[name] AS role_name\n"
                + "	  FROM [SWP391_G6].[dbo].[users] AS u\n"
                + "	  INNER JOIN [status] AS s ON s.[id] = u.[status_id]\n"
                + "	  INNER JOIN [roles] AS r ON r.[id] = u.[role_id]\n"
                + "	  WHERE (\n"
                + "		u.id LIKE '%' + ? + '%' OR\n"
                + "		u.[full_name] LIKE '%' + ? + '%' OR\n"
                + "		u.[email] LIKE '%' + ? + '%' OR\n"
                + "		u.[phone_number] LIKE '%' + ? + '%' OR\n"
                + "		u.[gender] LIKE '%' + ? + '%' OR\n"
                + "		u.[profile_img] LIKE '%' + ? + '%' OR\n"
                + "		s.[name] LIKE '%' + ? + '%' OR\n"
                + "		r.[name] LIKE '%' + ? + '%'\n"
                + "	 )\n"
                + ")\n"
                + "SELECT *\n"
                + "FROM PageResult\n"
                + "ORDER BY status_name\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, search);
            ps.setString(2, search);
            ps.setString(3, search);
            ps.setString(4, search);
            ps.setString(5, search);
            ps.setString(6, search);
            ps.setString(7, search);
            ps.setString(8, search);
            ps.setInt(9, page);
            ps.setInt(10, recordsPerPage);
            rs = ps.executeQuery();
            String gender = "Male";
            while (rs.next()) {
                if(rs.getInt(5) == 1) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }
                users.add(new UserManagement(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(6),
                        rs.getString(3),
                        rs.getString(4),
                        gender,
                        rs.getString(8),
                        rs.getString(7)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public int getTotalUser(String search) {
        int total = 0;
        String query = "WITH PageResult AS (\n"
                + "	SELECT u.[id],\n"
                + "	       u.[full_name],\n"
                + "	       u.[email],\n"
                + "	       u.[phone_number],\n"
                + "	       u.[gender],\n"
                + "	       u.[profile_img],\n"
                + "	       s.[name] AS status_name,\n"
                + "	       r.[name] AS role_name\n"
                + "	  FROM [SWP391_G6].[dbo].[users] AS u\n"
                + "	  INNER JOIN [status] AS s ON s.[id] = u.[status_id]\n"
                + "	  INNER JOIN [roles] AS r ON r.[id] = u.[role_id]\n"
                + "	  WHERE (\n"
                + "		u.id LIKE '%' + ? + '%' OR\n"
                + "		u.[full_name] LIKE '%' + ? + '%' OR\n"
                + "		u.[email] LIKE '%' + ? + '%' OR\n"
                + "		u.[phone_number] LIKE '%' + ? + '%' OR\n"
                + "		u.[gender] LIKE '%' + ? + '%' OR\n"
                + "		u.[profile_img] LIKE '%' + ? + '%' OR\n"
                + "		s.[name] LIKE '%' + ? + '%' OR\n"
                + "		r.[name] LIKE '%' + ? + '%'\n"
                + "	 )\n"
                + ")\n"
                + "SELECT COUNT(*)\n"
                + "FROM PageResult";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, search);
            ps.setString(2, search);
            ps.setString(3, search);
            ps.setString(4, search);
            ps.setString(5, search);
            ps.setString(6, search);
            ps.setString(7, search);
            ps.setString(8, search);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}
