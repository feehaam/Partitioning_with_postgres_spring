package playground.data.userDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.data.userDataContext.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
