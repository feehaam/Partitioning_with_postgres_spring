package playground.jpa.advanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.advanced.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {}
