package playground.data.productDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.data.productDataContext.entities.Variant;

public interface VariantRepository extends JpaRepository<Variant, Long> {}
