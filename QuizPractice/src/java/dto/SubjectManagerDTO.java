package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectManagerDTO {
    private int id;
    private String name;
    private String dimensionName;
    private int numberOfLesson;
    private int status;
    
    @Override
    public String toString() {
        return "SubjectManagerDTO{" + "id=" + id + ", name=" + name + ", dimensionName=" + dimensionName + ", numberOfLesson=" + numberOfLesson + ", status=" + status + '}';
    }
}
