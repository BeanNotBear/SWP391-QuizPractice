package model;

public class StudentQuizQuestion {
    private int id;
    private int studentQuizId;
    private int questionId;
    private Integer yourAnswer; // Dùng Integer để có thể null
    private Boolean isMarked; // Dùng Boolean để có thể null

    public StudentQuizQuestion() {
    }

    public StudentQuizQuestion(int id, int studentQuizId, int questionId, Integer yourAnswer, Boolean isMarked) {
        this.id = id;
        this.studentQuizId = studentQuizId;
        this.questionId = questionId;
        this.yourAnswer = yourAnswer;
        this.isMarked = isMarked;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentQuizId() {
        return studentQuizId;
    }

    public void setStudentQuizId(int studentQuizId) {
        this.studentQuizId = studentQuizId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Integer getYourAnswer() {
        return yourAnswer;
    }

    public void setYourAnswer(Integer yourAnswer) {
        this.yourAnswer = yourAnswer;
    }

    public Boolean getIsMarked() {
        return isMarked;
    }

    public void setIsMarked(Boolean isMarked) {
        this.isMarked = isMarked;
    }
}
