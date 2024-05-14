package dto;

import java.util.Date;

/**
 * The RegisterUserDto class represents the data transfer object (DTO) for
 * user registration. It contains the necessary fields for capturing user
 * registration details.
 */
public class RegisterUserDto {
    // Fields
    private String firstName; // User's first name
    private String lastName; // User's last name
    private String email; // User's email address
    private String phoneNumber; // User's phone number
    private boolean gender; // User's gender (true for male, false for female)
    private Date dob; // User's date of birth
    private String username; // User's chosen username
    private String password; // User's password

    /**
     * Default constructor for creating an empty RegisterUserDto object.
     */
    public RegisterUserDto() {
    }

    /**
     * Parameterized constructor for creating a RegisterUserDto object with specified attributes.
     *
     * @param firstName   the user's first name
     * @param lastName    the user's last name
     * @param email       the user's email address
     * @param phoneNumber the user's phone number
     * @param gender      the user's gender
     * @param dob         the user's date of birth
     * @param username    the user's chosen username
     * @param password    the user's password
     */
    public RegisterUserDto(String firstName, String lastName, String email, String phoneNumber, boolean gender, Date dob, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters

    /**
     * Gets the user's first name.
     *
     * @return the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstName the user's first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     *
     * @return the user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastName the user's last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's email address.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the user's email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's phone number.
     *
     * @return the user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the user's phone number.
     *
     * @param phoneNumber the user's phone number to set
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
     * @return the user's date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Sets the user's date of birth.
     *
     * @param dob the user's date of birth to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Gets the user's chosen username.
     *
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's chosen username.
     *
     * @param username the user's username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the user's password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
