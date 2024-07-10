package model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuppressWarnings("all")
public class Subject {
    private int id;
    private String name;
    private int creatorId;
    private Date createdAt;
    private Date updateAt;
    private int status;
    private String img;
    private String description;
    private int dimensionId;
}
