package sg.make.demo.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private int size;
    private String sku;
    private String thumbImageUrl;
    private double mrp;
    private double price;
    private int stockQty;
}
