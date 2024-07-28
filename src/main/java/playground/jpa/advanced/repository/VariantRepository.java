package playground.jpa.advanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.advanced.entities.Variant;

public interface VariantRepository extends JpaRepository<Variant, Integer> {}
