package dto;

public class QuestionDTO {
    private int id;
    private String detail;
    private String suggestion;
    private String status;
    private String media;
    private int questionNumber;

    public QuestionDTO(int id, String detail, String suggestion, String status, String media, int questionNumber) {
        this.id = id;
        this.detail = detail;
        this.suggestion = suggestion;
        this.status = status;
        this.media = media;
        this.questionNumber = questionNumber;
    }

    // Getters và Setters
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

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}