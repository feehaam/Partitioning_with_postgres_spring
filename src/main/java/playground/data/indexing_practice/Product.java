package playground.data.indexing_practice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity @Data @Builder @NoArgsConstructor @AllArgsConstructor
@Table(
        indexes = {
                @Index(name = "idx_product_id", columnList = "productId"),
                @Index(name = "idx_price", columnList = "price"),
                @Index(name = "idx_name", columnList = "name"),
                @Index(name = "idx_category_price", columnList = "category, price"),
        }
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long productId;
    private String name;
    private String category;
    private String skuId;
    private Double price;
    private Integer availableQuantity;
    private Boolean isAvailable;

    public void setAvailableQuantity(Integer availableQuantity) {
        if (Objects.isNull(availableQuantity)) return;
        this.availableQuantity = availableQuantity;
        if (this.availableQuantity > 0) isAvailable = true;
        else isAvailable = false;
    }

    // Set default values using PrePersist lifecycle event
    @PrePersist
    protected void prePersist() {
        if (this.isAvailable == null) {
            this.isAvailable = true;
        }
        if (this.availableQuantity == null) {
            this.availableQuantity = 0;
        }
    }
}
