package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricePackage {
    private int id;
    private String name;
    private int duration;
    private double salePrice;
    private double price;
}
