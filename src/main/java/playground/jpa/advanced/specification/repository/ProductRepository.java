package playground.jpa.advanced.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.advanced.specification.model.Product;
import playground.jpa.advanced.specification.model.Variant;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

