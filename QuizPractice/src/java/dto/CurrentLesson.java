package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class CurrentLesson {
    private int id;
    private String name;
    private String content;
    private String media;
    private int lessonIndex;
    private String type;
    private int quizId;
}
