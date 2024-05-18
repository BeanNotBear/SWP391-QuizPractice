package model;

import java.sql.Date;


/**
 * The User class represents a user entity with attributes such as user ID,
 * first name, last name, email, phone number, gender, date of birth, profile
 * image, username, password, creation timestamp, update timestamp, role ID, and
 * status ID.
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
    private String token;
    private Role role;
    private UserStatus status;

    public User() {
    }
    

    public User(int userId, String firstName, String lastName, String email, String phoneNumber, boolean gender, Date dob, String profileImg, String username, String password, Date updatedAt, String token, Role role, UserStatus status) {
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
        this.updatedAt = updatedAt;
        this.token = token;
        this.role = role;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

}
