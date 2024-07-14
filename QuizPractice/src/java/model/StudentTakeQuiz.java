package model;

public class StudentTakeQuiz {
    private int id;
    private int quizId;
    private int userId;
    private String status;
    private int numberCorrect;

    public StudentTakeQuiz(int id, int quizId, int userId, String status, int numberCorrect) {
        this.id = id;
        this.quizId = quizId;
        this.userId = userId;
        this.status = status;
        this.numberCorrect = numberCorrect;
    }

    public StudentTakeQuiz() {
    }

    public StudentTakeQuiz(int id, int quizId, int userId, int numberCorrect) {
        this.id = id;
        this.quizId = quizId;
        this.userId = userId;
        this.numberCorrect = numberCorrect;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }
}
