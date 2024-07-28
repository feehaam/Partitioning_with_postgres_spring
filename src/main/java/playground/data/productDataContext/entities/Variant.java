package playground.data.productDataContext.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Variant extends ProductContextBaseEntity {
    private String name;
    private String detail;
    private Double price;
    private Integer availableQuantity;
    private boolean isDefault;
}
