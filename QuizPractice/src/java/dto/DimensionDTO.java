package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DimensionDTO {
    private int id;
    private String name;
    
    @Override
    public String toString() {
        return "DimensionDTO{" + "id=" + id + ", name=" + name + '}';
    }
}
