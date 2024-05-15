package model;

import java.sql.Date;

/**
 * The User class represents a user entity with attributes such as user ID,
 * first name, last name, email, phone number, gender, date of birth,
 * profile image, username, password, creation timestamp, update timestamp,
 * role ID, and status ID.
 */
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

    /**
     * Default constructor for creating an empty User object.
     */
    public User() {
    }
    
    /**
     * Parameterized constructor for creating a User object with specified attributes.
     *
     * @param userId the unique identifier for the user
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param email the user's email address
     * @param phoneNumber the user's phone number
     * @param gender the user's gender
     * @param dob the user's date of birth
     * @param profileImg the URL or path to the user's profile image
     * @param username the user's chosen username
     * @param password the user's password
     * @param createdAt the timestamp of when the user was created
     * @param updatedAt the timestamp of the last update
     * @param roleId the role ID of the user
     * @param statusID the status ID of the user
     */
    public User(int userId, String firstName, String lastName, String email, String phoneNumber, boolean gender, Date dob, String profileImg, String username, String password, Date createdAt, Date updatedAt, int roleId, int statusID) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dob = dob;
        this.profileImg = profileImg;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roleId = roleId;
        this.statusID = statusID;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier for the user.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the user's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the user's phone number.
     *
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the user's gender.
     *
     * @return true if male, false if female
     */
    public boolean isGender() {
        return gender;
    }

    /**
     * Sets the user's gender.
     *
     * @param gender true for male, false for female
     */
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    /**
     * Gets the user's date of birth.
     *
     * @return the date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Sets the user's date of birth.
     *
     * @param dob the date of birth to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Gets the URL or path to the user's profile image.
     *
     * @return the profile image URL or path
     */
    public String getProfileImg() {
        return profileImg;
    }

    /**
     * Sets the URL or path to the user's profile image.
     *
     * @param profileImg the profile image URL or path to set
     */
    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    /**
     * Gets the user's chosen username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's chosen username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the timestamp of when the user was created.
     *
     * @return the creation timestamp
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp of when the user was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp of the last update.
     *
     * @return the last update timestamp
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp of the last update.
     *
     * @param updatedAt the last update timestamp to set
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the role ID of the user.
     *
     * @return the role ID
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets the role ID of the user.
     *
     * @param roleId the role ID to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the status ID of the user.
     *
     * @return the status ID
     */
    public int getStatusID() {
        return statusID;
    }

    /**
     * Sets the status ID of the user.
     *
     * @param statusID the status ID to set
     */
    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }
}
