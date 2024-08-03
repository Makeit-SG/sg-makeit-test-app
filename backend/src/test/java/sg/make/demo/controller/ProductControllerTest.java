package sg.make.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sg.make.demo.dtos.CreateProductRequestDTO;
import sg.make.demo.dtos.ProductDTO;
import sg.make.demo.dtos.ProductDetailDTO;
import sg.make.demo.model.Product;
import sg.make.demo.service.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    @WithMockUser(roles = {"VIEW", "EDIT"})
    void testGetAllProducts() throws Exception {
        // Given
        Product product1 = Product.builder().id(1L).name("Product 1").build();
        Product product2 = Product.builder().id(2L).name("Product 2").build();
        List<Product> products = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);
        when(modelMapper.map(any(Product.class), eq(ProductDTO.class))).thenReturn(new ProductDTO());

        // When & Then
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    @WithMockUser(roles = "EDIT")
    void testAddProduct() throws Exception {
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

        Product product = Product.builder().id(1L).name(dto.getName()).build();

        when(productService.createProduct(any(CreateProductRequestDTO.class))).thenReturn(product);

        ObjectMapper objectMapper = new ObjectMapper();

        // When & Then
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Product"));

        verify(productService, times(1)).createProduct(any(CreateProductRequestDTO.class));
    }

    @Test
    @WithMockUser(roles = {"VIEW", "EDIT"})
    void testGetProductFound() throws Exception {
        // Given
        Product product = Product.builder().id(1L).name("Product 1").build();
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setName(product.getName());

        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        when(modelMapper.map(any(Product.class), eq(ProductDetailDTO.class))).thenReturn(productDetailDTO);

        // When & Then
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product 1"));

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    @WithMockUser(roles = {"VIEW", "EDIT"})
    void testGetProductNotFound() throws Exception {
        // Given
        when(productService.getProductById(1L)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).getProductById(1L);
    }
}
