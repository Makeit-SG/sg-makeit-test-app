package sg.make.demo.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    private Long id;
    private String name;
    private int size;
    private String sku;
    private String description;
    private String thumbImageUrl;
    private String fullImageUrl;
    private double mrp;
    private double price;
    private int stockQty;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
