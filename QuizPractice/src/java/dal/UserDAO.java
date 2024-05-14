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

    /**
     * Inserts a new user into the database based on the provided
     * RegisterUserDto.
     *
     * @param registerUserDto the data transfer object containing user
     * registration details
     * @return the number of rows affected by the insert operation
     */
    public User insert(RegisterUserDto registerUserDto) {
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

        // Convert RegisterUserDto to User entity
        User user = UserMapper.ConvertRegisterUserToUser(registerUserDto);
        int rowAffected = 0; // Variable to store the number of rows affected

        // Create a PreparedStatement object
        PreparedStatement ps;

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

        return user;
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
        PreparedStatement ps;
        ResultSet rs;

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
}
