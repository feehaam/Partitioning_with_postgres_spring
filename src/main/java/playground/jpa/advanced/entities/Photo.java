package playground.jpa.advanced.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Photo extends ProductContextBaseEntity {
    private String url;
    private boolean isThumbnail;
    private String alternativeText;
}
