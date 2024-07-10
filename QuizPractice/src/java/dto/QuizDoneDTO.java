package dto;

public class QuizDoneDTO {
    private int id;
    private String quizName;
    private String level;
    private int numberQuestion;
    private int duration;
    private double score;
    private String subjectName;

    public QuizDoneDTO(int id, String quizName, String level, int numberQuestion, int duration, double score, String subjectName) {
        this.id = id;
        this.quizName = quizName;
        this.level = level;
        this.numberQuestion = numberQuestion;
        this.duration = duration;
        this.score = score;
        this.subjectName = subjectName;
    }

    // Getters and setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "QuizDoneDTO{" + "id=" + id + ", quizName=" + quizName + ", level=" + level + ", numberQuestion=" + numberQuestion + ", duration=" + duration + ", score=" + score + ", subjectName=" + subjectName + '}';
    }
    
}