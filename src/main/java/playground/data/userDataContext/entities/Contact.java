package playground.data.userDataContext.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity @AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Contact extends UserContextBaseEntity {
    private String type;
    private String contactId;
    private boolean isPrimary;
}
