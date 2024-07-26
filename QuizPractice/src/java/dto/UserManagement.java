package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class UserManagement {
    private int id;
    private String fullName;
    private String profileImg;
    private String email;
    private String phoneNumber;
    private String gender;
    private String role;
    private String status;
}
