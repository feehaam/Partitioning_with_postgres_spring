package playground.data;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import playground.data.db_first_approach.OldProductRepo;
import playground.data.db_first_approach.ProductRepo;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PartitionTestController {
    private final OldProductRepo oldProductRepo;
    private final ProductRepo productRepo;

    @GetMapping
    public Object runAll() {
        return Map.of(
                "With partition", queryOnPartitionedTable(),
                "Without partition", queryOnOldTable()
        );
    }

    private List<?> queryOnPartitionedTable(){
        System.out.println("Querying on partitioned table.");
        return List.of(
                executeAndAnalyze("Find by product id, indexed*", () -> productRepo.findByProductId(4000123L)),
                executeAndAnalyze("Find by product name, indexed*", () -> productRepo.findByName("Product name 4222333")),
                executeAndAnalyze("Find by sku id, not indexed", () -> productRepo.findBySkuId("12bc5d")),
                executeAndAnalyze("Find by category and availability not indexed", () -> productRepo.findByCategoryAndIsAvailable("Accessories", false)),
                executeAndAnalyze("Find by category and price range composite index*", () -> productRepo.findByCategoryAndPriceBetween("Accessories", 700.8, 899.0))
        );
    }

    private List<?> queryOnOldTable(){
        System.out.println("Querying on old regular table.");
        return List.of(
                executeAndAnalyze("Find by product id, indexed*", () -> oldProductRepo.findByProductId(4000123L)),
                executeAndAnalyze("Find by product name, indexed*", () -> oldProductRepo.findByName("Product name 4111222")),
                executeAndAnalyze("Find by sku id, not indexed", () -> oldProductRepo.findBySkuId("12bc5d")),
                executeAndAnalyze("Find by category and availability not indexed", () -> oldProductRepo.findByCategoryAndIsAvailable("Accessories", false)),
                executeAndAnalyze("Find by category and price range composite index*", () -> oldProductRepo.findByCategoryAndPriceBetween("Accessories", 700.8, 899.0))
        );
    }

    private <IN, OUT> Map<String, Object> executeAndAnalyze(String query, Supplier<List<?>> function) {
        long time = System.currentTimeMillis();
        var result = function.get();
        return Map.of(
                "Query", query,
                "Results count", result.size(),
                "Total time", (System.currentTimeMillis() - time) + "ms"
        );
    }
}
