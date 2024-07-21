package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuppressWarnings("all")
public class RegistrationDTO {
    private int userId;
    private String fullName;
    private String gender;
    private String email;
    private String phoneNumber;
    private int subjectId;
    private String subjectName;
    private int packageId;
    private String packageName;
    private double salePrice;
    private String duration;
    private String status;
    private String img;
}
