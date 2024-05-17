package model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The User class represents a user entity with attributes such as user ID,
 * first name, last name, email, phone number, gender, date of birth,
 * profile image, username, password, creation timestamp, update timestamp,
 * role ID, and status ID.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // Fields
    private int userId; // Unique identifier for the user
    private String firstName; // User's first name
    private String lastName; // User's last name
    private String email; // User's email address
    private String phoneNumber; // User's phone number
    private boolean gender; // User's gender (true for male, false for female)
    private Date dob; // User's date of birth
    private String profileImg; // URL or path to the user's profile image
    private String username; // User's chosen username
    private String password; // User's password
    private Date createdAt = new Date(System.currentTimeMillis()); // Timestamp of when the user was created
    private Date updatedAt; // Timestamp of the last update
    private int roleId = 1; // Role ID of the user (default is 1)
    private int statusID = 1; // Status ID of the user (default is 1)
}