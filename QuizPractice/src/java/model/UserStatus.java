package model;

/**
 * The UserStatus class represents the status of a user with an ID and a name.
 * It is used to define different statuses that a user can have within the application.
 */
public class UserStatus {
    // Fields
    private int id; // Unique identifier for the user status
    private String name; // Name of the user status (e.g., "Active", "Inactive")

    /**
     * Default constructor for creating an empty UserStatus object.
     */
    public UserStatus() {
    }

    /**
     * Parameterized constructor for creating a UserStatus object with a specified id and name.
     *
     * @param id   the unique identifier for the user status
     * @param name the name of the user status
     */
    public UserStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the unique identifier for the user status.
     *
     * @return the id of the user status
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user status.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the user status.
     *
     * @return the name of the user status
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user status.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}

