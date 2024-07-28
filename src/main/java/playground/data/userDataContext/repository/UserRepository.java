package playground.data.userDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.data.userDataContext.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
