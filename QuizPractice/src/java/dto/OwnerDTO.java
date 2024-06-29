package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuppressWarnings("all")
public class OwnerDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String img;
    private int gender;
    private Date date;
}
