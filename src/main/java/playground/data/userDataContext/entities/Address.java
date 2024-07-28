package playground.data.userDataContext.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address extends UserContextBaseEntity {
    private String country;
    private String city;
    private String street;
    private String postCode;
    private String detail;
    private boolean isPermanent;
}
