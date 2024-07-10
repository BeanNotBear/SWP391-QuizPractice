package dto;

public class QuizDTO {
    private int id;
    private String name;
    private String subjectName;
    private String level;
    private int numberQuestion;
    private int duration;
    private int subjectId;
    private String type;

    public QuizDTO(int id, String name, String subjectName, String level, int numberQuestion, int duration, String type) {
        this.id = id;
        this.name = name;
        this.subjectName = subjectName;
        this.level = level;
        this.numberQuestion = numberQuestion;
        this.duration = duration;
        this.type = type;
    }

    public QuizDTO(int id, String name, String subjectName, String level, int numberQuestion, int duration, int subjectId, String type) {
        this.id = id;
        this.name = name;
        this.subjectName = subjectName;
        this.level = level;
        this.numberQuestion = numberQuestion;
        this.duration = duration;
        this.subjectId = subjectId;
        this.type = type;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getLevel() {
        return level;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public int getDuration() {
        return duration;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}