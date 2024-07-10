package model;

public class QuestionImport {
    private int id;
    private String detail;
    private String suggestion;
    private int status;
    private String media;

    public QuestionImport(int id, String detail, String suggestion, int status, String media) {
        this.id = id;
        this.detail = detail;
        this.suggestion = suggestion;
        this.status = status;
        this.media = media;
    }

    public QuestionImport(String detail, String suggestion, int status, String media) {
        this.detail = detail;
        this.suggestion = suggestion;
        this.status = status;
        this.media = media;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public int getStatus() {
        return status;
    }

    public String getMedia() {
        return media;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}