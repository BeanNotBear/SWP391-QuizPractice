/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
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
    public boolean checkExistEmail(String email){
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
}
