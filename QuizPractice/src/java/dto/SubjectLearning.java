package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class SubjectLearning {
    private int subjectId;
    private String subjectName;
    private String thumbnail;
    private double progress;
    private int numberOfLessons;
}
