package dal;

import context.DBContext;
import dto.RegisterUserDto;
import jakarta.servlet.jsp.jstl.sql.Result;
import mapper.UserMapper;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * UserDAO is a Data Access Object (DAO) class that provides methods to interact
 * with the users table in the database. It extends the DBContext to utilize the
 * database connection.
 */
public class UserDAO extends DBContext {

    // Singleton instance of UserDAO
    private static UserDAO instance;
    // Lock object for thread-safe singleton instantiation
    private static Object lockPad = new Object();

    // Private constructor to prevent instantiation
    private UserDAO() {
    }

    /**
     * Provides a global point of access to the UserDAO instance. Implements
     * double-checked locking to ensure thread safety.
     *
     * @return the singleton instance of UserDAO
     */
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

    public int insert(User user) {
        // SQL query with placeholders for parameterized input
        String query = "INSERT INTO [dbo].[users]\n"
                + "           ([first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[email]\n"
                + "           ,[phone_number]\n"
                + "           ,[gender]\n"
                + "           ,[dob]\n"
                + "           ,[profile_img]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[created_at]\n"
                + "           ,[updated_at]\n"
                + "           ,[role_id]\n"
                + "           ,[status_id])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int rowAffected = 0; // Variable to store the number of rows affected

        try {

            // Prepare the SQL query for execution
            ps = connection.prepareStatement(query);

            // Set parameters for the PreparedStatement
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber());
            ps.setBoolean(5, user.isGender());
            ps.setDate(6, user.getDob());

            // Set the profile image path based on gender
            String imgPath = user.isGender() ? "images/pic-1.jpg" : "images/pic-5.jpg";
            ps.setString(7, imgPath);

            ps.setString(8, user.getUsername());
            ps.setString(9, user.getPassword()); // Ensure password is hashed
            ps.setDate(10, user.getCreatedAt());
            ps.setDate(11, user.getUpdatedAt());
            ps.setInt(12, user.getRoleId());
            ps.setInt(13, user.getStatusID());

            // Execute the query and get the number of rows affected
            rowAffected = ps.executeUpdate();
        } catch (Exception e) {
            // Log the exception (if a logging framework is available)
            e.printStackTrace(); // Replace with logger in real application
        }

        return rowAffected;
    }

    /**
     * Checks if a user exists in the database by username.
     *
     * @param username the username to check for existence
     * @return true if the username does not exist in the database, false
     * otherwise
     */
    public boolean checkUserExsitedByUsername(String username) {
        // SQL query to check for the existence of the username in the database
        String query = "SELECT username\n"
                + "FROM [dbo].[users]\n"
                + "WHERE username = ?";

        // Declaration of PreparedStatement and ResultSet
        try {
            // Prepare the SQL query for execution
            // The prepareStatement method creates a PreparedStatement object to send
            // parameterized SQL statements to the database, preventing SQL injection
            ps = connection.prepareStatement(query);

            // Set the username parameter in the query
            ps.setString(1, username);

            // Execute the query and get the result set
            rs = ps.executeQuery();

            // If there is a result, the username exists, return false
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            // Log the exception (if a logging framework is available)
            e.printStackTrace(); // Replace with logger in real application
        }

        // If no result is found, return true indicating the username does not exist
        return true;
    }

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
                user.setFirstName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setGender(rs.getBoolean(5));
                user.setDob(rs.getDate(6));
                user.setProfileImg(rs.getString(7));
                user.setUsername(rs.getString(8));
                user.setPassword(rs.getString(9));
                user.setCreatedAt(rs.getDate(10));
                user.setUpdatedAt(rs.getDate(11));
                user.setRoleId(rs.getInt(12));
                user.setStatusID(rs.getInt(13));
            }
        } catch (Exception e) {
        }
        return user;
    }

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
                user.setFirstName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setGender(rs.getBoolean(5));
                user.setDob(rs.getDate(6));
                user.setProfileImg(rs.getString(7));
                user.setUsername(rs.getString(8));
                user.setPassword(rs.getString(9));
                user.setCreatedAt(rs.getDate(10));
                user.setUpdatedAt(rs.getDate(11));
                user.setRoleId(rs.getInt(12));
                user.setStatusID(rs.getInt(13));
            }
        } catch (Exception e) {
        }
        return user;
    }
    
    public int UpdateStatusByEmail(String email) {
        String query = "UPDATE users\n"
                + "SET status_id = 2\n"
                + "WHERE email = ?";
        int rowAffected = 0;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rowAffected = ps.executeUpdate();
        } catch (Exception e) {
        }
        return rowAffected;
    }

}
