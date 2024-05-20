/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import util.security.Security;

/**
 *
 * @author nghin
 */
public class UserDAO extends DBContext {

    private static UserDAO instance;
    private static Object lockPad = new Object();

    private UserDAO() {
    }

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

    public boolean checkExistEmail(String email) {
        boolean duplicate = false;
        String sql = "Select * From users where email =?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duplicate;
    }

    public boolean updatePassword(String password, String email) {
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
                + "           ,[status_id]\n"
                + "           ,[token])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            ps.setString(14, user.getToken());

            // Execute the query and get the number of rows affected
            rowAffected = ps.executeUpdate();
        } catch (Exception e) {
            // Log the exception (if a logging framework is available)
            e.printStackTrace(); // Replace with logger in real application
        }
        return rowAffected;
    }

    // Checks if a user already exists by username and email
    public boolean checkUserExistedByUsernameAndEmail(String username, String email) {
        String query = "SELECT email, username\n"
                + "FROM users\n"
                + "WHERE email = ? OR username = ?";
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

    // Finds a user by email and password
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
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPhoneNumber(rs.getString(5));
                user.setGender(rs.getBoolean(6));
                user.setDob(rs.getDate(7));
                user.setProfileImg(rs.getString(8));
                user.setUsername(rs.getString(9));
                user.setPassword(rs.getString(10));
                user.setCreatedAt(rs.getDate(11));
                user.setUpdatedAt(rs.getDate(12));
                user.setRoleId(rs.getInt(13));
                user.setStatusID(rs.getInt(14));
                user.setToken(rs.getString(15));
            }
        } catch (Exception e) {
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
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPhoneNumber(rs.getString(5));
                user.setGender(rs.getBoolean(6));
                user.setDob(rs.getDate(7));
                user.setProfileImg(rs.getString(8));
                user.setUsername(rs.getString(9));
                user.setPassword(rs.getString(10));
                user.setCreatedAt(rs.getDate(11));
                user.setUpdatedAt(rs.getDate(12));
                user.setRoleId(rs.getInt(13));
                user.setStatusID(rs.getInt(14));
                user.setToken(rs.getString(15));
            }
        } catch (Exception e) {
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
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPhoneNumber(rs.getString(5));
                user.setGender(rs.getBoolean(6));
                user.setDob(rs.getDate(7));
                user.setProfileImg(rs.getString(8));
                user.setUsername(rs.getString(9));
                user.setPassword(rs.getString(10));
                user.setCreatedAt(rs.getDate(11));
                user.setUpdatedAt(rs.getDate(12));
                user.setRoleId(rs.getInt(13));
                user.setStatusID(rs.getInt(14));
                user.setToken(rs.getString(15));
            }
        } catch (Exception e) {
        }
        return user;
    }

    // Updates the token of a user by email
    public int UpdateTokenByEmail(String token, String email) {
        String query = "UPDATE users\n"
                + "SET token = ?\n"
                + "WHERE email = ?";
        int rowAffected = 0;
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

}
