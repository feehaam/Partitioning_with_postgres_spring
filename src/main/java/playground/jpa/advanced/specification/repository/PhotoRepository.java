package playground.jpa.advanced.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.advanced.specification.model.Photo;
import playground.jpa.advanced.specification.model.Tag;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {}
