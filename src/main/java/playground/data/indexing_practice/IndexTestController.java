package playground.data.indexing_practice;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

@RestController
@RequestMapping("/")
public class IndexTestController {
    private final JPAStreamer jpaStreamer;
    private final ProductRepo productRepo;

    public IndexTestController(EntityManagerFactory emf, ProductRepo productRepo) {
        jpaStreamer = JPAStreamer.of(emf);
        this.productRepo = productRepo;
    }

    private Stream<Product> _productContext(){
        return jpaStreamer.stream(Product.class);
    }

    @GetMapping
    public Object runAll() {
        return Map.of(
                "JPA", queryUsingJPA(),
                "Streamer", queryUsingStreamer()
        );
    }

    private List<?> queryUsingJPA(){
        System.out.println("Querying using JPA");
        return List.of(
                executeAndAnalyze("Find by product id, indexed*", () -> productRepo.findByProductId(4000123L)),
                executeAndAnalyze("Find by product name, indexed*", () -> productRepo.findByName("Product name 999991")),
                executeAndAnalyze("Find by sku id, not indexed", () -> productRepo.findBySkuId("12bc5d")),
                executeAndAnalyze("Find by category and availability not indexed", () -> productRepo.findByCategoryAndIsAvailable("Accessories", false)),
                executeAndAnalyze("Find by category and price range composite index*", () -> productRepo.findByCategoryAndPriceBetween("Accessories", 700.8, 899.0))
        );
    }

    private List<?> queryUsingStreamer(){
        System.out.println("Querying using Streamer");
        return List.of(
                executeAndAnalyze("Find by product id, indexed*", () -> _productContext()
                        .filter(product -> product.getProductId() == 4000002L).findFirst().stream().toList()),
                executeAndAnalyze("Find by product name, indexed*", () -> _productContext()
                        .filter(product -> product.getName().equals("Product name 999989")).toList()),
                executeAndAnalyze("Find by sku id, not indexed", () -> _productContext()
                        .filter(product -> product.getSkuId().equals("12bc5d")).toList()),
                executeAndAnalyze("Find by category and availability not indexed", () -> _productContext()
                        .filter(product -> product.getCategory().equals("Accessories") && !product.getIsAvailable()).toList()),
                executeAndAnalyze("Find by category and price range composite index*", () -> _productContext()
                        .filter(product -> product.getCategory().equals("Accessories") && product.getPrice() >= 600.8 && product.getPrice() <= 799.0).toList())
        );
    }

    private <IN, OUT> Map<String, Object> executeAndAnalyze(String query, Supplier<List<Product>> function) {
        long time = System.currentTimeMillis();
        var result = function.get();
        return Map.of(
                "Query", query,
                "Results count", result.size(),
                "Total time", (System.currentTimeMillis() - time) + "ms"
        );
    }
}
