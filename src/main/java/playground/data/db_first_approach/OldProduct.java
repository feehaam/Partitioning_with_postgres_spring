package playground.data.db_first_approach;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity @Table(name = "old_product") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OldProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    private String name;
    private String category;
    private String skuId;
    private Double price;
    @Column(name = "available_quantity")
    private Integer availableQuantity;
    @Column(name = "is_available")
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