package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class Question {
    private int id;
    private String detail;
    private String suggestion;
    private String status;
    private String media;
}