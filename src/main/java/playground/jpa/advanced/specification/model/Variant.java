package playground.jpa.advanced.specification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity @AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String detail;
    private Double price;
    private Integer availableQuantity;
    private boolean isDefault;
}
