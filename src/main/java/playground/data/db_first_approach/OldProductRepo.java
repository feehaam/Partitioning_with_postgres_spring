package playground.data.db_first_approach;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OldProductRepo extends JpaRepository<OldProduct, Long> {
    List<OldProduct> findByProductId(long productId);
    List<OldProduct> findBySkuId(String skuId);
    List<OldProduct> findByName(String name);
    List<OldProduct> findByCategoryAndIsAvailable(String category, boolean isAvailable);
    List<OldProduct> findByCategoryAndPriceBetween(String category, Double minPrice, Double maxPrice);
}
