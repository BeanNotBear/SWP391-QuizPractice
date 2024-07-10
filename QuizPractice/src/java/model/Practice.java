package model;

import java.sql.Timestamp;

public class Practice {
    private int id;
    private int userId;
    private int subjectId;
    private String lessonName;
    private int numberQuestion;
    private Timestamp createdAt;
    private int numberCorrect;
    private int duration;

    public Practice(){}
    public Practice(int id, int userId, int subjectId, String lessonName, int numberQuestion, Timestamp createdAt, int numberCorrect, int duration) {
        this.id = id;
        this.userId = userId;
        this.subjectId = subjectId;
        this.lessonName = lessonName;
        this.numberQuestion = numberQuestion;
        this.createdAt = createdAt;
        this.numberCorrect = numberCorrect;
        this.duration = duration;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}