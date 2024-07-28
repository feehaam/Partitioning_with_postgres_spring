package playground.jpa.advanced.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.advanced.specification.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {}
