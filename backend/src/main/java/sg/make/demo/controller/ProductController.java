package sg.make.demo.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sg.make.demo.dtos.CreateProductRequestDTO;
import sg.make.demo.dtos.ProductDTO;
import sg.make.demo.dtos.ProductDetailDTO;
import sg.make.demo.model.Product;
import sg.make.demo.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasAnyAuthority('ROLE_VIEW','ROLE_EDIT')")
    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return this.productService.getAllProducts()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ROLE_EDIT')")
    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        try {
            return ResponseEntity.ok(this.productService.createProduct(createProductRequestDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_VIEW','ROLE_EDIT')")
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDetailDTO> getProduct(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(modelMapper.map(product, ProductDetailDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

}
