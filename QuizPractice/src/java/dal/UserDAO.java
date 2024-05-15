package dal;

import context.DBContext;
import model.User;

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

    public User insert(User user) {
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

        int rowAffected = 0; // Variable to store the number of rows affecteds

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

    public boolean checkUserExistedByUsernameAndEmail(String username,
            String email) {
        String query = "SELECT email, username\n"
                + "FROM users\n"
                + "WHERE email = ? AND username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }

    public int UpdateStatusByUsername(String username) {
        String query = "UPDATE users\n"
                + "SET status_id = 2\n"
                + "WHERE username = ?";
        int rowAffected = 0;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rowAffected = ps.executeUpdate();
        } catch (Exception e) {
        }
        return rowAffected;
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
            if(rs.next()) {
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
    
    public static void main(String[] args) {
        UserDAO d = getInstance();
        System.out.println(d.findUserByEmailAndPassword("vannghibg03@gmail.com", 
                "6361614211bff9a6d7d432cc7c29a15b63f18e519aa846f6262e6771bb3fcf9a35bcece7ad325dc0d84bf6ab2781e37cbfc2bc1c2fcd27e4c8e62beecbf133b0"));
    }
}
