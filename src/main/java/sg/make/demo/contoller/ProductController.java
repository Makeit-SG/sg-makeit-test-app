package sg.make.demo.contoller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    @GetMapping("/products")
    public String listProducts() {
        return "List of products";
    }

    @PreAuthorize("hasAuthority('ROLE_EDIT')")
    @PostMapping("/products")
    public String addProduct() {
        return "Product added";
    }
}
