package playground.data;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ProductPopulateUtil {
    private final ProductRepo productRepo;

    public ProductPopulateUtil(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @PostConstruct
    protected void loadProducts(){
        clear();
        load();
    }

    private void load() {
        List<Product> products = new ArrayList<>();
        for (long i=1; i<=10; i++) {
            products.add(generateProduct(i));
        }
        productRepo.saveAll(products);
    }

    private void clear() {
        productRepo.deleteAll();
    }

    private final Random random = new Random();
    private static final List<String> CAT = List.of("Electronics", "Grocery", "Clothing", "Accessories", "Babies");
    private Product generateProduct(long i) {
        int availableQty = random.nextInt(1000);
        return Product.builder()
                .productId(i)
                .name("Product name " + i)
                .category(CAT.get(random.nextInt(CAT.size())))
                .skuId(UUID.randomUUID().toString().substring(0, 6))
                .price(random.nextDouble(100000))
                .availableQuantity(availableQty)
                .isAvailable(availableQty > 0)
                .build();
    }
}
