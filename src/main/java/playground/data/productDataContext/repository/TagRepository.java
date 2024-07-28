package playground.data.productDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.data.productDataContext.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {}
