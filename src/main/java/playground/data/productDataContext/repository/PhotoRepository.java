package playground.data.productDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.data.productDataContext.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {}
