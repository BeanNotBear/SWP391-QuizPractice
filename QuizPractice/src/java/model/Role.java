package model;

/**
 * The Role class represents a role with an ID and a name.
 * It is used to define different types of roles within the application.
 */
public class Role {
    // Fields
    private int id; // Unique identifier for the role
    private String name; // Name of the role (e.g., "Admin", "User")

    /**
     * Default constructor for creating an empty Role object.
     */
    public Role() {
    }

    /**
     * Parameterized constructor for creating a Role object with a specified id and name.
     *
     * @param id   the unique identifier for the role
     * @param name the name of the role
     */
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the unique identifier for the role.
     *
     * @return the id of the role
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the role.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     *
     * @return the name of the role
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
