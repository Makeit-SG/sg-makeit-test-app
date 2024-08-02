package sg.make.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sg.make.demo.dtos.CreateProductRequestDTO;
import sg.make.demo.model.Product;
import sg.make.demo.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(CreateProductRequestDTO createProductRequestDTO) {
        Product product = Product.builder()
                .name(createProductRequestDTO.getName())
                .size(createProductRequestDTO.getSize())
                .sku(createProductRequestDTO.getSku())
                .description(createProductRequestDTO.getDescription())
                .thumbImageUrl(createProductRequestDTO.getThumbImageUrl())
                .fullImageUrl(createProductRequestDTO.getFullImageUrl())
                .mrp(createProductRequestDTO.getMrp())
                .price(createProductRequestDTO.getPrice())
                .stockQty(createProductRequestDTO.getStockQty())
                .build();

        return productRepository.save(product);
    }

}
