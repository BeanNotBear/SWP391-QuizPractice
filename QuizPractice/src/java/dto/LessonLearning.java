package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class LessonLearning {
    private int lessonId;
    private int lessonIndex;
    private String name;
    private int quizId;
    private boolean status;
}
