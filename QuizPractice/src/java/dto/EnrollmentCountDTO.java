package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class EnrollmentCountDTO {
    private int id;
    private String name;
    private int numberOfEnrollments;
}
