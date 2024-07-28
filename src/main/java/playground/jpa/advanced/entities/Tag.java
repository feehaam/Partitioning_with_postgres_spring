package playground.jpa.advanced.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Tag extends ProductContextBaseEntity {
    private String name;
}
