/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;

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
}
