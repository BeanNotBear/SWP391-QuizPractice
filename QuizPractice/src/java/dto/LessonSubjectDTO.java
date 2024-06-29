package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuppressWarnings("all")
public class LessonSubjectDTO {
    private int id;
    private String name;
    private String content;
    private int lessonIndex;
    private String type;
}
