package sg.make.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.make.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
