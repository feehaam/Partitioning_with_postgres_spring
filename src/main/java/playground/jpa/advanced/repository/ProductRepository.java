package playground.jpa.advanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.advanced.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

