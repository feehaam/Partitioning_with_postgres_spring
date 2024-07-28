package playground.data.userDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.data.userDataContext.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
