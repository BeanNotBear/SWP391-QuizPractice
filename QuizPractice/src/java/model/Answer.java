package model;

import java.sql.Timestamp;

public class Answer {
    private int id;
    private String answerDetail;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int creatorId;
    private boolean isCorrect;

    public Answer(int id, String answerDetail, Timestamp createdAt, Timestamp updatedAt, int creatorId, boolean isCorrect) {
        this.id = id;
        this.answerDetail = answerDetail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.creatorId = creatorId;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}