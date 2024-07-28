package playground.jpa.advanced.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Product extends ProductContextBaseEntity {
    private String title;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Variant> variants;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Photo> photos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tag> tags;
}
