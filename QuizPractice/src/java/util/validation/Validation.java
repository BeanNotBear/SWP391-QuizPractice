/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.validation;

import exception.InvalidEmailFormatException;
import exception.InvalidPhoneNumberFormatException;

/**
 *
 * @author nghin
 */
public class Validation {

    private static Validation instance = null;
    private static Object lockPad = new Object();

    private Validation() {
    }
    
    public static Validation getInstance() {
        if(instance == null) {
            synchronized (lockPad) {
                if(instance == null) {
                    instance = new Validation();
                }
            }
        }
        return instance;
    }
    
    public boolean CheckFormatEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if(!email.matches(emailRegex)) {
            throw new InvalidEmailFormatException();
        }
        return true;
    }

    public boolean CheckFormatPhone(String phone) {
        String phoneRegex = "^\\+?\\d{0,2}\\s?\\(?(\\d{3})\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";
        if(!phone.matches(phoneRegex)) {
            throw new InvalidPhoneNumberFormatException();
        }
        return true;
    }
    
    public boolean CheckFormatPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        return password.matches(passwordRegex);
    }
    
    public static void main(String[] args) {
        Validation v = getInstance();
        System.out.println(v.CheckFormatPassword("Callmebean03@@@"));
    }
}
