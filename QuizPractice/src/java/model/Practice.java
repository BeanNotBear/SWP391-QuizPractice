package model;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class Practice {
    private int id;
    private int userId;
    private int subjectId;
    private String lessonName;
    private int numberQuestion;
    private Timestamp createdAt;
    private int numberCorrect;
    private int duration;
}