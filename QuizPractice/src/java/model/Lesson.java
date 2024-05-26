package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class Lesson {
    private int id;
    private String name;
    private String content;
    private String media;
    private Date updatedAt;
    private Date createdAt;
    private int status;
}
