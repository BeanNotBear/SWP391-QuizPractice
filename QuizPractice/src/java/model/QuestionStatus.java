package model;

public class QuestionStatus {
    private int questionId;
    private String detail;
    private int yourAnswer;

    public QuestionStatus(int questionId, String detail, int yourAnswer) {
        this.questionId = questionId;
        this.detail = detail;
        this.yourAnswer = yourAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getYourAnswer() {
        return yourAnswer;
    }

    public void setYourAnswer(int yourAnswer) {
        this.yourAnswer = yourAnswer;
    }
}