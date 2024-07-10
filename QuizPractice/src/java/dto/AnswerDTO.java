package dto;

public class AnswerDTO {
    private int id;
    private String detail;
    private int isCorrect;

    public AnswerDTO(int id, String detail, int isCorrect) {
        this.id = id;
        this.detail = detail;
        this.isCorrect = isCorrect;
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

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }
}