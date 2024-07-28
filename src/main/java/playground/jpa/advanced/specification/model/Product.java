package playground.jpa.advanced.specification.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;

    private String title;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Variant> variants;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Photo> photos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tag> tags;
}
