package playground.data.userDataContext.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class User extends UserContextBaseEntity {
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses;
}
