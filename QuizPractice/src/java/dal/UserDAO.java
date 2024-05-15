/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.SQLException;
import model.User;

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
            ps.setString(1, password);
            ps.setString(2, email);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
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

}
