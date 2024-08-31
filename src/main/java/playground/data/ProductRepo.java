package playground.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByProductId(long productId);
    List<Product> findBySkuId(String skuId);
    List<Product> findByName(String name);
    List<Product> findByCategoryAndIsAvailable(String category, boolean isAvailable);
    List<Product> findByCategoryAndPriceBetween(String category, Double minPrice, Double maxPrice);
}
