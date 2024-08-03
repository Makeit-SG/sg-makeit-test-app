package sg.make.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequestDTO {
    private String name;
    private int size;
    private String sku;
    private String description;
    private String thumbImageUrl;
    private String fullImageUrl;
    private double mrp;
    private double price;
    private int stockQty;
}
