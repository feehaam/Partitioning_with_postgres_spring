package playground.jpa.advanced.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.advanced.specification.model.Photo;
import playground.jpa.advanced.specification.model.Variant;

public interface VariantRepository extends JpaRepository<Variant, Integer> {}
