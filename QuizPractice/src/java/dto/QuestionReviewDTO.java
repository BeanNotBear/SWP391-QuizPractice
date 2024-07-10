package dto;

import model.Answer;
import model.Question;

import java.util.List;

public class QuestionReviewDTO {
    private int id;
    private String detail;
    private String suggestion;
    private String status;
    private String media;
    private int yourAnswer;
    private List<Answer> answers;

    public QuestionReviewDTO(int id, String detail, String suggestion, String status, String media, int yourAnswer, List<Answer> answers) {
        this.id = id;
        this.detail = detail;
        this.suggestion = suggestion;
        this.status = status;
        this.media = media;
        this.yourAnswer = yourAnswer;
        this.answers = answers;
    }

    // Getters and Setters
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

    public int getYourAnswer() {
        return yourAnswer;
    }

    public void setYourAnswer(int yourAnswer) {
        this.yourAnswer = yourAnswer;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}