package model;

import java.sql.Date;

/**
 * The User class represents a user entity with attributes such as user ID,
 * first name, last name, email, phone number, gender, date of birth, profile
 * image, username, password, creation timestamp, update timestamp, role ID, and
 * status ID.
 */

public class Subject {

    // Fields
    private int id;
    private String name;
    private User creator;
    private Date create_at;
    private Date update_at;
    private int status;
    private String img;
    private int numberOfLesson;

    public Subject() {
    }

    public Subject(int id, String name, User creator, Date create_at, Date update_at, int status, String img, int numberOfLesson) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.create_at = create_at;
        this.update_at = update_at;
        this.status = status;
        this.img = img;
        this.numberOfLesson = numberOfLesson;
    }  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getNumberOfLesson() {
        return numberOfLesson;
    }

    public void setNumberOfLesson(int numberOfLesson) {
        this.numberOfLesson = numberOfLesson;
    }
    
}
