package sg.make.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sg.make.demo.dtos.CreateProductRequestDTO;
import sg.make.demo.model.Product;
import sg.make.demo.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Given
        Product product1 = Product.builder().id(1L).name("Product 1").build();
        Product product2 = Product.builder().id(2L).name("Product 2").build();
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        // When
        List<Product> result = productService.getAllProducts();

        // Then
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductByIdFound() {
        // Given
        Product product = Product.builder().id(1L).name("Product 1").build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // When
        Optional<Product> result = productService.getProductById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Product 1", result.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductByIdNotFound() {
        // Given
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Optional<Product> result = productService.getProductById(1L);

        // Then
        assertFalse(result.isPresent());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateProduct() {
        // Given
        CreateProductRequestDTO dto = new CreateProductRequestDTO();
        dto.setName("New Product");
        dto.setSize(10);
        dto.setSku("SKU123");
        dto.setDescription("Description");
        dto.setThumbImageUrl("http://example.com/thumb.jpg");
        dto.setFullImageUrl("http://example.com/full.jpg");
        dto.setMrp(100.0);
        dto.setPrice(90.0);
        dto.setStockQty(10);

        Product savedProduct = Product.builder()
                .id(1L)
                .name(dto.getName())
                .size(dto.getSize())
                .sku(dto.getSku())
                .description(dto.getDescription())
                .thumbImageUrl(dto.getThumbImageUrl())
                .fullImageUrl(dto.getFullImageUrl())
                .mrp(dto.getMrp())
                .price(dto.getPrice())
                .stockQty(dto.getStockQty())
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // When
        Product result = productService.createProduct(dto);

        // Then
        assertNotNull(result);
        assertEquals("New Product", result.getName());
        assertEquals(1L, result.getId());
        verify(productRepository, times(1)).save(any(Product.class));
    }
}