package model;

public class Question {
    private int id;
    private String detail;
    private String suggestion;
    private String status;
    private String media;

    public Question(int id, String detail, String suggestion, String status, String media) {
        this.id = id;
        this.detail = detail;
        this.suggestion = suggestion;
        this.status = status;
        this.media = media;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", detail=" + detail + ", suggestion=" + suggestion + ", status=" + status + ", media=" + media + '}';
    }
}