package playground.jpa.advanced.entity.manager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class CarRepository {

    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;
    // Create
    public Car create(Car car) {
        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();
        return car;
    }

    // Read
    public Car getCarById(Long id) {
        return entityManager.find(Car.class, id);
    }

    // Update
    public Car update(Car car) {
        entityManager.merge(car);
        return car;
    }

    // Delete
    public void delete(Long id) {
        Car carToDelete = entityManager.find(Car.class, id);
        if (carToDelete != null) {
            entityManager.remove(carToDelete);
        }
    }
}
